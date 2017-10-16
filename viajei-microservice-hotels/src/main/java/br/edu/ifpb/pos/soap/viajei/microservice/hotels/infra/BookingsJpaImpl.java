/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.exceptions.EntityConflictException;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.exceptions.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Booking;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author pafer
 */

@RequestScoped
@Transactional
public class BookingsJpaImpl implements Bookings {
    
    @PersistenceContext(unitName = "hotels-pu")
    private EntityManager manager;

    @Override
    public void persist(Booking booking) {
        try {
            this.manager.persist(booking);
        } catch (EntityExistsException ex) {
            throw new EntityConflictException("There's already a"
                    + " booking with the id " + booking.getId(), ex); 
        }
    }

    @Override
    public void remove(Long bookingId) {
        Booking booking = findById(bookingId);
        this.manager.remove(booking);
    }

    @Override
    public void update(Booking updatedBooking) {
        try {
            this.manager.merge(updatedBooking);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("There's no booking with the id "
                    + updatedBooking.getId());
        }
    }

    @Override
    public List<Booking> listAll() {
        return this.manager
                .createQuery("FROM Booking b", Booking.class)
                .getResultList();
    }

    @Override
    public Booking findById(Long bookingId) {
        
        Optional<Booking> searchResult = Optional
                .ofNullable(this.manager.find(Booking.class, bookingId));
        
        if(!searchResult.isPresent())
            throw new EntityNotFoundException("There's no booking with the id "
                    + bookingId);
        
        return searchResult.get();
    }
    
}
