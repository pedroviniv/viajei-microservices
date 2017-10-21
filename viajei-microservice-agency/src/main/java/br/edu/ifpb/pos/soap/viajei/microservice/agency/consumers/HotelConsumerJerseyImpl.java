/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters.ExternalEntityType;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.ExternalEntity;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class HotelConsumerJerseyImpl implements HotelConsumer {
    
    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target(ExternalEntityType.BOOKING
            .getResourceAddress());
    
    @Inject
    private Mapper mapper;

    @Override
    public ExternalEntity book(String hotelId, String roomId, String clientCpf, 
            String startDate, String endDate) {
        
        BookingResource booking = new BookingResource();
        
        booking.setHotel_id(Long.valueOf(hotelId));
        booking.setRoom_id(Long.valueOf(roomId));
        booking.setClient_cpf(clientCpf);
        booking.setStart_date_time(startDate);
        booking.setEnd_date_time(endDate);
        
        String bookingJson = mapper.toString(booking);
        
        Response postResponse = target
                .request()
                .post(Entity.json(bookingJson));
        
        if(postResponse.getStatus() == 404) {
            
            String jsonErrorResponse = postResponse
                    .readEntity(String.class);
            
            ErrorResponse errorResponse = mapper
                    .toObject(jsonErrorResponse, ErrorResponse.class);
            
            throw new EntityNotFoundException(errorResponse.getMessage());
        }
        
        String createdUri = postResponse.getHeaderString("created");
        
        
        return new ExternalEntity(getIdFromUri(createdUri));
    }
    
    public String getIdFromUri(String uri) {
        int lastIndexOf = uri.lastIndexOf("/");
        return uri.substring(lastIndexOf+1, uri.length());
    }
    
}
