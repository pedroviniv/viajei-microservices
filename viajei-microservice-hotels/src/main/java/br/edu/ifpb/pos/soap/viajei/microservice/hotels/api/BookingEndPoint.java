/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.converter.BookingConverter;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources.BookingRequestResource;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources.BookingResource;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.consumers.ClientConsumer;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.Bookings;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.exceptions.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Booking;
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

@Path("bookings")
@RequestScoped
public class BookingEndPoint {
    
    @Inject private Bookings bookings;
    @Inject private BookingConverter bookingConverter;
    @Inject private ClientConsumer clientConsumer;
    
    public static URI getUri(UriInfo uriInfo, Long bookingId) {
        
        return uriInfo.getBaseUriBuilder()
                .path(BookingEndPoint.class)
                .path(String.valueOf(bookingId))
                .build();
    }
    
    @GET
    @Path("{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @DefaultValue("-1") 
            @PathParam("bookingId") 
                    Long bookingId,
            @Context UriInfo uriInfo) {
        
        Booking booking = this.bookings.findById(bookingId);
        
        BookingResource bookingResource = BookingResource.of(booking, uriInfo);
        
        return Response.ok(bookingResource).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@Context UriInfo uriInfo) {
        
        List<Booking> bookingList = this.bookings.listAll();
        
        List<BookingResource> bookingResourceList = bookingList.stream()
                .map(b -> BookingResource.of(b, uriInfo))
                .collect(Collectors.toList());
        
        GenericEntity<List<BookingResource>> entity = new
            GenericEntity<List<BookingResource>>(bookingResourceList){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(BookingRequestResource bookingResource, @Context UriInfo uriInfo) {
        
        String clientCpf = bookingResource.getClient_cpf();
        
        if(!clientConsumer.exists(clientCpf))
            throw new EntityNotFoundException("There's no client with cpf "
                    + clientCpf);
        
        Booking booking = this.bookingConverter
                .convert(bookingResource);
        
        this.bookings.persist(booking);
        
        URI uri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(booking.getId()))
                .build();
        
        return Response.created(uri).build();
    }
    
    @DELETE
    @Path("{bookingId}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("bookingId") 
                    Long bookingId) {
        
        this.bookings.remove(bookingId);
        
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(BookingRequestResource bookingResource) {
        
        Booking booking = this.bookingConverter.convert(bookingResource);
        this.bookings.update(booking);
        
        return Response.ok().build();
    }
}
