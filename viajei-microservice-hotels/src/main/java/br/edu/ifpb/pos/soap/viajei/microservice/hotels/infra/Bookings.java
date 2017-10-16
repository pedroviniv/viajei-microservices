/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Booking;
import java.util.List;

/**
 *
 * @author pafer
 */
public interface Bookings {
    
    void persist(Booking booking);
    void remove(Long bookingId);
    void update(Booking updatedBooking);
    List<Booking> listAll();
    Booking findById(Long bookingId);
    
}
