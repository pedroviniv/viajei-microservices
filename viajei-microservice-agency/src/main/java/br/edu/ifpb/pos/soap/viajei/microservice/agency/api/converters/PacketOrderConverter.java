/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers.ExternalEntities;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.PacketOrderResponseResource;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.ResourceRef;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.PacketOrder;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class PacketOrderConverter {
    
    @Inject private ResourceRefConverter<Packet> packetConverter;
    
    public PacketOrderResponseResource convert(PacketOrder packetOrder, UriInfo uriInfo) {
        
        PacketOrderResponseResource res = new PacketOrderResponseResource();
        
        String clientId = packetOrder.getClient().getId();
        res.setClient(new ResourceRef(clientId, 
                ExternalEntities.CLIENT.getResourceAddress() + "/" + clientId));
        
        String hotelBookingId = packetOrder.getHotelBooking().getId();
        res.setHotel_booking(new ResourceRef(hotelBookingId, 
                ExternalEntities.BOOKING.getResourceAddress() + "/" + hotelBookingId));
    
        String ticketId = packetOrder.getTransportTicket().getId();
        res.setTransport_ticket(new ResourceRef(ticketId, 
                ExternalEntities.TICKET.getResourceAddress() + "/" + ticketId));
        
        String bookingId = packetOrder.getTransportTicket().getId();
        res.setHotel_booking(new ResourceRef(bookingId, 
                ExternalEntities.BOOKING.getResourceAddress() + "/" + bookingId));
        
        res.setId(packetOrder.getOrderId());
        
        res.setPacket(packetConverter.convert(packetOrder.getPacket(), 
                uriInfo));
        
        return res;
    }
}
