/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.services;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.consumers.ClientConsumer;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.RoutesJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.TicketJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.TransportsJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Client;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Ticket;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class TicketServiceImpl implements TicketService {

    @TicketJPA
    @Inject
    private Repository<Ticket, Long> tickets;
    @TransportsJPA
    @Inject
    private Repository<Transport, Long> transports;
    @RoutesJPA
    @Inject
    private Repository<Route, Long> routes;
    
    @Inject
    private ClientConsumer clientConsumer;
    
    /**
     * Creates and persists a new Ticket based on transportId, routeId, clientId
     * and the client transport seat number.
     * Before creating the ticket, it verifies if the client id actually exists
     * by asking to the client restful service. After that, it verifies if the 
     * transportId and the routeId provided exists in the database. If so, a Ticket
     * Object is created and persisted into the database.
     * @param transportId the transport idenfifier
     * @param routeId the route identifier
     * @param clientId the client id
     * @param seatNumber the transport seat number
     * @return the ticket id
     */
    @Override
    public Long add(Long transportId, Long routeId, 
            String clientId, Integer seatNumber) {
        
        if(!clientConsumer.exists(clientId))
            throw new EntityNotFoundException("There's no client with id "
                    + clientId);
        
        Transport transportFound = this.transports.findById(transportId);
        Route routeFound = this.routes.findById(routeId);
        
        Ticket ticket = new Ticket(transportFound, new Client(clientId), 
                seatNumber, routeFound);
        
        this.tickets.persist(ticket);
        
        return ticket.getId();
    }
    
}
