/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.exceptions.EntityConflictException;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.exceptions.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Hotel;
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
public class HotelsJpaImpl implements Hotels {
    
    @PersistenceContext(unitName = "hotels-pu")
    private EntityManager manager;

    @Override
    public void add(Hotel hotel) {
        try {
            this.manager.persist(hotel);
        } catch (EntityExistsException ex) {
            throw new EntityConflictException("Already has a hotel with the id "
                    + hotel.getId(), ex);
        }
    }

    @Override
    public void remove(Long hotelId) {
        Hotel hotelFound = findById(hotelId);
        this.manager.remove(hotelFound);
    }

    @Override
    public void update(Hotel updatedHotel) {
        try {
            this.manager.merge(updatedHotel);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("There's no hotel with the id "
                    + updatedHotel.getId(), ex);
        }
    }

    @Override
    public Hotel findById(Long hotelId) {
        Optional<Hotel> findResult = Optional
                .ofNullable(this.manager.find(Hotel.class, hotelId));
        
        if(!findResult.isPresent()) {
            throw new EntityNotFoundException("There's no hotel with the id "
                    + hotelId);
        }
        
        Hotel hotel = findResult.get();
        hotel.getRooms().size();
        
        return hotel;
    }

    @Override
    public List<Hotel> listAll() {
        return this.manager.createQuery("FROM Hotel h", Hotel.class)
                .getResultList();
    }
    
}
