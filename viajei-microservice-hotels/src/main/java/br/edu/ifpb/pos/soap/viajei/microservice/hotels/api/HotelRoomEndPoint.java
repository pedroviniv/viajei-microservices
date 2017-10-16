/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.exceptions.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.Hotels;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.Rooms;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Hotel;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Room;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HotelRoomEndPoint {
    
    @Inject private Hotels hotels;
    @Inject private Rooms rooms;
    
    private static final Logger LOG = Logger
            .getLogger(HotelRoomEndPoint.class.getName());
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRoom(
            @DefaultValue("-1")
            @PathParam("hotelId") Long hotelId, 
            Room room,
            @Context UriInfo uriInfo) {
        
        LOG.log(Level.INFO, "room attributes: {0}", room.getAttributes());
        
        Hotel hotel = this.hotels.findById(hotelId);
        hotel.addRoom(room);
        
        this.hotels.update(hotel);
        
        URI uri = uriInfo.getBaseUriBuilder()
                .path(RoomEndPoint.class)
                .path(room.getRoomId())
                .build();
        
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{roomId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeRoom(
            @DefaultValue("-1")
            @PathParam("hotelId") Long hotelId, 
            @DefaultValue("-1")
            @PathParam("roomId") Long roomId) {
        
        Hotel hotel = this.hotels.findById(hotelId);
        
        if(!hotel.removeRoom(roomId))
            throw new EntityNotFoundException("There's no room with the id "
                    + roomId);
        
        this.hotels.update(hotel);
        
        return Response.ok().build();
    }
    
}
