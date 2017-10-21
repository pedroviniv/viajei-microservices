/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.TicketRequestResource;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.TicketJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Ticket;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.services.TicketService;
import java.net.URI;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@Path("tickets")
@RequestScoped
public class TicketsEndPoint {
    
    @Inject
    @TicketJPA
    private Repository<Ticket, Long> tickets;
    @Inject
    private TicketService ticketService;
    
    @GET
    @Path("{ticketId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @DefaultValue("-1") 
            @PathParam("{ticketId}") 
                    Long ticketId) {
        
        Ticket ticketFound = this.tickets.findById(ticketId);
        
        return Response.ok(ticketFound).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        
        GenericEntity<List<Ticket>> entity = 
                new GenericEntity<List<Ticket>>(this.tickets.listAll()){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(TicketRequestResource req, @Context UriInfo uriInfo) {
        
        Long ticketId = this.ticketService.add(req.getTransport_id(), 
                req.getRoute_id(), req.getClient_cpf(), 
                req.getSeat_number());
        
        URI createdUri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(ticketId))
                .build();
        
        return Response.created(createdUri).build();
    }
    
    @DELETE
    @Path("{ticketId}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("{ticketId}") 
                    Long ticketId) {
        
        this.tickets.remove(ticketId);
        
        return Response.ok().build();
    }
    
    @PUT
    public Response update(Ticket ticket) {
        
        this.tickets.update(ticket);
        
        return Response.ok().build();
    }
}
