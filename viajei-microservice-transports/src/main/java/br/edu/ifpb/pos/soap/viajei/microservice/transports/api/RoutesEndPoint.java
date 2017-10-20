/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.infra.RoutesJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pafer
 */

@Path("routes")
@RequestScoped
public class RoutesEndPoint {
    
    @Inject
    @RoutesJPA
    private Repository<Route, Long> routes;
    
    @GET
    @Path("{routeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @DefaultValue("-1") 
            @PathParam("routeId") 
                    Long id) {
        
        Route foundRoute = this.routes.findById(id);
        
        return Response.ok(foundRoute).build();
    }
}
