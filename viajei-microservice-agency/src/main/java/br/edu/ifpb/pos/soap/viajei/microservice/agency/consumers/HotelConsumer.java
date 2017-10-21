/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.ExternalEntity;

/**
 *
 * @author kieckegard
 */
public interface HotelConsumer {
    
    ExternalEntity book(String hotelId, String roomId, String clientCpf, 
            String startDate, String endDate);
}
