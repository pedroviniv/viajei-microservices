/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra.exceptions.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Room;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author pafer
 */


@RequestScoped
@Transactional
public class RoomsJpaImpl implements Rooms {
    
    @PersistenceContext(unitName = "hotels-pu")
    private EntityManager manager;

    @Override
    public void update(Room room) {
        try {
            this.manager.merge(room);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("There's no room with the id "
                    + room.getId(), ex);
        }
    }

    @Override
    public Room find(Long roomId) {
        Optional<Room> searchResult = Optional
                .ofNullable(this.manager.find(Room.class, roomId));
        
        if(!searchResult.isPresent()) 
            throw new EntityNotFoundException("There's no room with the id "
                    + roomId);
        
        return searchResult.get();
    }

    @Override
    public void remove(Long roomId) {
        Room roomFound = find(roomId);
        this.manager.remove(roomFound);
    }
    
}
