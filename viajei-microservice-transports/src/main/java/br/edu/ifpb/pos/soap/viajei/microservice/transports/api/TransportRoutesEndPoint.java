/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.TransportsJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import java.net.URI;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author pafer
 */

@RequestScoped
public class TransportRoutesEndPoint {
    
    @Inject 
    @TransportsJPA
    private Repository<Transport, Long> transports;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRoute(
            @DefaultValue("-1") 
            @PathParam("transportId") Long transportId,
            Route route,
            @Context UriInfo uriInfo) {
        
        Transport transportFound = this.transports.findById(transportId);
        transportFound.addRoute(route);
        
        this.transports.update(transportFound);
        
        URI routeURI = uriInfo.getBaseUriBuilder()
                .path(RoutesEndPoint.class)
                .path(String.valueOf(route.getId()))
                .build();
        
        return Response.created(routeURI).build();
    }

}
