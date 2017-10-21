/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.Rooms;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Room;
import java.io.Serializable;
import java.net.URI;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author pafer
 */

@Path("rooms")
@RequestScoped
public class RoomEndPoint implements Serializable {
    
    @Inject private Rooms rooms;
    
    public static URI getUri(UriInfo uriInfo, Long roomId) {
        
        return uriInfo.getBaseUriBuilder()
                .path(RoomEndPoint.class)
                .path(String.valueOf(roomId))
                .build();
    }
    
    @GET
    @Path("{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @DefaultValue("-1") 
            @PathParam("roomId") 
                    Long roomId) {
        
        Room roomFound = this.rooms.find(roomId);
        return Response.ok(roomFound).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Room room) {
        
        this.rooms.update(room);
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{roomId}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("roomId") 
                    Long roomId) {
        
        this.rooms.remove(roomId);
        return Response.ok().build();
    }
}
