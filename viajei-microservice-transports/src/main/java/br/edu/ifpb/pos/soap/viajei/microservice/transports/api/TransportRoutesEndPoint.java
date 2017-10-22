/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.converters.RouteConverter;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.RouteRequestResource;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.TransportsJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import java.net.URI;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    
    @Inject private RouteConverter routeConverter; 
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRoute(
            @DefaultValue("-1") 
            @PathParam("transportId") Long transportId,
            RouteRequestResource routeReq,
            @Context UriInfo uriInfo) {
        
        Transport transportFound = this.transports.findById(transportId);
        Route route = routeConverter.convert(routeReq);
        transportFound.addRoute(route);
        
        this.transports.update(transportFound);
        
        URI routeURI = uriInfo.getBaseUriBuilder()
                .path(RoutesEndPoint.class)
                .path(String.valueOf(route.getId()))
                .build();
        
        return Response.created(routeURI).build();
    }
    
    @DELETE
    @Path("{routeId}")
    public Response removeRoute(
            @DefaultValue("-1") 
            @PathParam("transportId") Long transportId,
            @DefaultValue("-1")
            @PathParam("routeId") Long routeId) {
        
        Transport transportFound = this.transports
                .findById(transportId);
        
        transportFound.removeRoute(routeId);
        
        this.transports.update(transportFound);
        
        return Response.ok().build();
    }

}
