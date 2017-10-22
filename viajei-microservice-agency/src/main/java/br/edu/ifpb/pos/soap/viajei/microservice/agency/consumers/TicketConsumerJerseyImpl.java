/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.ExternalEntity;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class TicketConsumerJerseyImpl implements TicketConsumer {
    
    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target(ExternalEntities.TICKET
            .getResourceAddress());
    private static final Logger LOG = Logger.getLogger(TicketConsumerJerseyImpl.class.getName());
    
    @Inject Mapper mapper;
    
    
    @Override
    public boolean deleteTicket(String ticketId) {
        
        Response deleteResponse = target
                .path(ticketId)
                .request()
                .delete();
        
        if(deleteResponse.getStatus() == 404)
            throw new EntityNotFoundException("There's no ticket with the id "
                    + ticketId);
        
        return deleteResponse.getStatus() == 200;
    }

    @Override
    public ExternalEntity createTicket(String transportId, String routeId, 
            Integer seatNumber, String clientId) {
        
        TicketResource ticketResource = new TicketResource();
        
        ticketResource.setTransport_id(Long.valueOf(transportId));
        ticketResource.setRoute_id(Long.valueOf(routeId));
        ticketResource.setSeat_number(seatNumber);
        ticketResource.setClient_id(clientId);
        
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
            
            LOG.log(Level.WARNING, "Error while creating ticket: {0}", 
                    errorResponse.getMessage());
            
            throw new EntityNotFoundException(errorResponse.getMessage());
        }
        
        MultivaluedMap<String, Object> headers = postResponse.getHeaders();
        Set<String> headersKeys = headers.keySet();
        
        LOG.log(Level.INFO, "ALL HEADERS: ");
        for(String key : headersKeys) {
            String strValue = "";
           for(Object value : headers.get(key)) {
               strValue += ("," + value.toString());
           }
           LOG.log(Level.INFO, "key: {0}, value: {1}", 
                   new String[]{key, strValue});
        }
        
        String createdUri = postResponse.getHeaderString("location");
        
        LOG.log(Level.INFO, "TICKET CREATED: {0}", createdUri);
        
        return new ExternalEntity(getIdFromUri(createdUri));
    }
    
    public String getIdFromUri(String uri) {
        int lastIndexOf = uri.lastIndexOf("/");
        return uri.substring(lastIndexOf+1, uri.length());
    }
    
}
