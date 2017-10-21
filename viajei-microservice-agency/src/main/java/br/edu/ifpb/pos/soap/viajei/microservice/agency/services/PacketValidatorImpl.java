/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.services;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters.ExternalEntityType;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers.ExternalEntityVerifier;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.EntityNotFoundException;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class PacketValidatorImpl implements PacketValidator {
    
    
    @Inject private ExternalEntityVerifier externalEntityVerifier;

    @Override
    public void validate(Packet packet) {
        
         String roomId = packet.getRoom().getId();
        
        if(this.externalEntityVerifier.exists(roomId, ExternalEntityType.ROOM))
            throw new EntityNotFoundException("There's no room with the id "
                    + roomId);
        
        String hotelId = packet.getHotel().getId();
        
        if(this.externalEntityVerifier.exists(hotelId, ExternalEntityType.HOTEL))
            throw new EntityNotFoundException("There's no hotel with the id "
                    + hotelId);
        
        String transportId = packet.getTransport().getId();
        
        if(this.externalEntityVerifier.exists(transportId, ExternalEntityType.TRANSPORT))
            throw new EntityNotFoundException("There's no transport with the id"
                    + transportId);
        
        String routeId = packet.getRoute().getId();
        
        if(this.externalEntityVerifier.exists(routeId, ExternalEntityType.ROUTE))
            throw new EntityNotFoundException("There's no route with the id"
                    + routeId);
    }
    
}
