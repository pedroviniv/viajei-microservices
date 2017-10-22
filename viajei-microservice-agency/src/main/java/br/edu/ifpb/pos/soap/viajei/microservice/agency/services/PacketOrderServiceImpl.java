/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.services;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers.ExternalEntities;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers.ExternalEntityVerifier;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers.HotelConsumer;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers.TicketConsumer;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.PacketOrdersJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.PacketsJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.ExternalEntity;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.PacketOrder;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class PacketOrderServiceImpl implements PacketOrderService {
    
    @PacketsJPA
    @Inject
    private Repository<Packet, Long> packets;
    
    @PacketOrdersJPA
    @Inject
    private Repository<PacketOrder, Long> packetOrders;
    
    @Inject
    private ExternalEntityVerifier externalEntityVerifier;
    
    @Inject TicketConsumer ticketConsumer;
    @Inject HotelConsumer hotelConsumer;

    @Override
    public Long order(Long packetId, String clientId, Integer seatNumber, 
            String startDate, String endDate) {
        
        Packet packetFound = this.packets.findById(packetId);
        
        //Verifying if client id exists
        if(!externalEntityVerifier.exists(clientId, ExternalEntities.CLIENT))
            throw new EntityNotFoundException("There's no client with the id "
                    + clientId);
        
        String transportId, routeId;
        
        transportId = packetFound.getTransport().getId();
        routeId = packetFound.getRoute().getId();
                
        //Creating ticket
        ExternalEntity createdTicket = this.ticketConsumer
                .createTicket(transportId, routeId, seatNumber, clientId);
        
        String hotelId, roomId;
        
        hotelId = packetFound.getHotel().getId();
        roomId = packetFound.getRoom().getId();
        
        //Booking the hotel
        ExternalEntity createdBooking = this.hotelConsumer
                .book(hotelId, roomId, clientId, startDate, endDate);
        
        PacketOrder order = new PacketOrder(new ExternalEntity(clientId), 
                packetFound, 
                createdBooking, createdTicket);
        
        this.packetOrders.persist(order);
        
        return order.getOrderId();
    }

    @Override
    public void remove(Long packerOrderId) {
        PacketOrder packetOrder = this.packetOrders
                .findById(packerOrderId);
        
        if(ticketConsumer.deleteTicket(packetOrder
                .getTransportTicket().getId()) &&
        hotelConsumer.deleteBooking(packetOrder.getHotelBooking().getId())) {
            
            packetOrders.remove(packerOrderId);
        }
    }
    
}
