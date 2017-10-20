/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.TransportsJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import java.net.URI;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author pafer
 */

@Path("transports")
@RequestScoped
public class TransportsEndPoint {
    
    
    @Inject
    @TransportsJPA
    private Repository<Transport, Long> transports;
    @Inject
    private TransportRoutesEndPoint transportRoutesEndPoint;
    
    @GET
    @Path("{transportId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @DefaultValue("-1") 
            @PathParam("transportId") 
                    Long transportId) {
        
        Transport found = this.transports.findById(transportId);
        
        return Response.ok(found).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Transport transport, @Context UriInfo uriInfo) {
        
        this.transports.persist(transport);
        
        URI createdUri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(transport.getId()))
                .build();
        
        return Response.created(createdUri).build();
    }
    
    @Path("{transportId}/routes")
    public TransportRoutesEndPoint transportRoutes() {
        return transportRoutesEndPoint;
    }
    
}
