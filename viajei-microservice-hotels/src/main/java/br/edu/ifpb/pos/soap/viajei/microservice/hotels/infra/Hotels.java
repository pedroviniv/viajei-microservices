/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Hotel;
import java.util.List;

/**
 *
 * @author pafer
 */
public interface Hotels {
    
    void add(Hotel hotel);
    void remove(Long hotelId);
    void update(Hotel updatedHotel);
    Hotel findById(Long id);
    List<Hotel> listAll();
}
