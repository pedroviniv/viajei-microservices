/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Room;

/**
 *
 * @author pafer
 */
public interface Rooms {
    
    void update(Room room);
    Room find(Long roomId);
    void remove(Long roomId);
}
