/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.BookingEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.HotelEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.RoomEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.consumers.ClientConsumer;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.helper.LocalDateTimeConverter;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Booking;
import java.io.Serializable;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */
public class BookingResource implements Serializable {
    
    private Long id;
    private ResourceRef client;
    private ResourceRef hotel;
    private ResourceRef room;
    private String start_date_time;
    private String end_date_time;

    public BookingResource(Long id, ResourceRef client, ResourceRef hotel, ResourceRef room, String start_date_time, String end_date_time) {
        this.id = id;
        this.client = client;
        this.hotel = hotel;
        this.room = room;
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;
    }
    
    public static BookingResource of(Booking booking, UriInfo uriInfo) {
        
        BookingResource res = new BookingResource();
        
        Long hotelId = booking.getHotel().getId();
        String hotelName = booking.getHotel().getName();
        
        res.setHotel(new ResourceRef(hotelName, 
                HotelEndPoint.getUri(uriInfo, hotelId).toString()));
        
        Long roomId = booking.getRoom().getId();
        String roomTitle = booking.getRoom().getTitle();
        
        res.setRoom(new ResourceRef(roomTitle, 
                RoomEndPoint.getUri(uriInfo, roomId).toString()));
        
        String clientCpf = booking.getClient().getCpf();
        
        res.setClient(new ResourceRef(clientCpf,
                ClientConsumer.CLIENT_RES + "/" + clientCpf));
        
        res.setId(booking.getId());
        
        LocalDateTimeConverter converter = new LocalDateTimeConverter();
        
        res.setStart_date_time(converter.convert(booking.getPeriod().getStart(), 
                "dd/MM/yyyy HH:mm"));
        res.setEnd_date_time(converter.convert(booking.getPeriod().getEnd(), 
                "dd/MM/yyyy HH:mm"));
        
        return res;
    }

    public BookingResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceRef getClient() {
        return client;
    }

    public void setClient(ResourceRef client) {
        this.client = client;
    }

    public ResourceRef getHotel() {
        return hotel;
    }

    public void setHotel(ResourceRef hotel) {
        this.hotel = hotel;
    }

    public ResourceRef getRoom() {
        return room;
    }

    public void setRoom(ResourceRef room) {
        this.room = room;
    }

    public String getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(String start_date_time) {
        this.start_date_time = start_date_time;
    }

    public String getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(String end_date_time) {
        this.end_date_time = end_date_time;
    }

    @Override
    public String toString() {
        return "BookingResource{" + "id=" + id + ", client=" + client + ", hotel=" + hotel + ", room=" + room + ", start_date_time=" + start_date_time + ", end_date_time=" + end_date_time + '}';
    }
}
