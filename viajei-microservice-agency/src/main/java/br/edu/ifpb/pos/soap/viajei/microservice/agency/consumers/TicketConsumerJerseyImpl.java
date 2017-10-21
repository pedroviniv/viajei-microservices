/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters.ExternalEntityType;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.ExternalEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class TicketConsumerJerseyImpl implements TicketConsumer {
    
    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target(ExternalEntityType.TICKET
            .getResourceAddress());
    
    @Inject Mapper mapper;

    @Override
    public ExternalEntity createTicket(String transportId, String routeId, 
            Integer seatNumber, String clientCpf) {
        
        TicketResource ticketResource = new TicketResource();
        
        ticketResource.setTransport_id(Long.valueOf(transportId));
        ticketResource.setRoute_id(Long.valueOf(routeId));
        ticketResource.setSeat_number(seatNumber);
        ticketResource.setClient_cpf(clientCpf);
        
        String ticketResJson = mapper.toString(ticketResource);
        
        Entity<String> entity = Entity.json(ticketResJson);
        
        Response postResponse = this.target
                .request()
                .post(entity);
        
        if(postResponse.getStatus() == 404) {
            
            String jsonErrorResponse = postResponse
                    .readEntity(String.class);
            
            ErrorResponse errorResponse = mapper
                    .toObject(jsonErrorResponse, ErrorResponse.class);
            
            throw new EntityNotFoundException(errorResponse.getMessage());
        }
        
        String createdUri = postResponse.getHeaderString("created");
        
        return new ExternalEntity(getIdFromUri(createdUri));
    }
    
    public String getIdFromUri(String uri) {
        int lastIndexOf = uri.lastIndexOf("/");
        return uri.substring(lastIndexOf+1, uri.length());
    }
    
}
