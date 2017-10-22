/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.converter;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources.BookingRequestResource;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources.BookingResource;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.Hotels;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.Rooms;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Booking;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Client;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Hotel;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Period;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Room;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author pafer
 */

@ApplicationScoped
public class BookingConverter {
    
    @Inject private Hotels hotels;
    @Inject private Rooms rooms;
    
    private final DateTimeFormatter dtf = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HH:mm");
    
    public Booking convert(BookingRequestResource resource) {
        
        Hotel hotel = hotels.findById(resource.getHotel_id());
        Room room = rooms.find(resource.getRoom_id());
        
        Period period = new Period(
                LocalDateTime
                        .parse(resource.getStart_date_time(), dtf),
                LocalDateTime
                        .parse(resource.getEnd_date_time(), dtf));
        
        Client client = new Client(resource.getClient_id());
        
        return new Booking(hotel, room, client, period);
    }
}
