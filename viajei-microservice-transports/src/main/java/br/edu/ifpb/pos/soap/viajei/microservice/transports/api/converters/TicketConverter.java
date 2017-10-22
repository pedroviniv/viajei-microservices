/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.consumers.ClientConsumer;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.ResourceRef;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.TicketResponseResource;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Ticket;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class TicketConverter {
    
    @Inject private ResourceRefConverter<Route> routeConverter;
    @Inject private ResourceRefConverter<Transport> transportConverter;
    
    public TicketResponseResource convert(Ticket ticket, UriInfo uriInfo) {
        
        TicketResponseResource res = new TicketResponseResource();
        
        res.setId(ticket.getId());
        res.setPrice(ticket.getPrice());
        res.setSeat_number(ticket.getSeatNumber());
        
        String clientId = ticket.getClient().getId();
        
        res.setClient(new ResourceRef(clientId, 
                ClientConsumer.CLIENTS_URI + "/" + clientId));
        
        res.setRoute(routeConverter.convert(ticket.getRoute(), 
                uriInfo));
        
        res.setTransport(transportConverter.convert(ticket.getTransport(),
                uriInfo));
        
        return res;
    }
}
