/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources.HotelResource;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.Hotels;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Hotel;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
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
 * @author pafer
 */

@Path("hotels")
@RequestScoped
public class HotelEndPoint {
    
    @Inject private Hotels hotels;
    @Inject private HotelRoomEndPoint hotelRoomEndPoint;
    
    @GET
    @Path("{hotelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
                @DefaultValue("-1")
                @PathParam("hotelId")
                Long hotelId) {
        
        Hotel hotelFound = this.hotels.findById(hotelId);
        
        return Response.ok(hotelFound).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@Context UriInfo uriInfo) {
        
        List<Hotel> hotelsResult = hotels.listAll();
        
        List<HotelResource> hotelsResponse = hotelsResult
                .stream()
                .map(h -> HotelResource.of(h, uriInfo))
                .collect(Collectors.toList());
        
        GenericEntity<List<HotelResource>> entity =
                new GenericEntity<List<HotelResource>>(hotelsResponse){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Hotel hotel, @Context UriInfo uriInfo) {
        
        this.hotels.add(hotel);
        URI createdUri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(hotel.getId()))
                .build();
        
        return Response.created(createdUri).build();
    }
    
    @Path("{hotelId}/rooms")
    public HotelRoomEndPoint hotelRooms() {
       return hotelRoomEndPoint;
    }
    
    @DELETE
    @Path("{hotelId}")
    public Response remove(
                @DefaultValue("-1")
                @PathParam("hotelId")
                Long hotelId) {
        
        this.hotels.remove(hotelId);
        
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Hotel updatedHotel) {
        this.hotels.update(updatedHotel);
        return Response.ok().build();
    }
    
}
