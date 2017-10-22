/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.clients.api;

import br.edu.ifpb.pos.soap.viajei.microservice.clients.infra.Clients;
import br.edu.ifpb.pos.soap.viajei.microservice.clients.model.Client;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author pafer
 */
@Path("clients")
@RequestScoped
public class ClientEndPoint {
    
    @Inject
    private Clients clients;
    
    @GET
    @Path("{clientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @DefaultValue("-1") 
            @PathParam("clientId") Long clientId) {
        
        Client clientFound = this.clients.findById(clientId);
        
        return Response.ok(clientFound).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        
        List<Client> clientsResult = this.clients.listAll();
        
        GenericEntity<List<Client>> entity =
                new GenericEntity<List<Client>>(clientsResult){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Client client, @Context UriInfo uriInfo) {
        
        this.clients.persist(client);
        URI uri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(client.getId()))
                .build();
        
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{clientId}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("clientId") Long clientId) {
        
        this.clients.remove(clientId);
        
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveChanges(Client updatedClient) {
        
        this.clients.update(updatedClient);
        
        return Response.ok().build();
    }
    
    
}
