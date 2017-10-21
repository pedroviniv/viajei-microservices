/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.LinkResource;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.PacketResource;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class PacketConverter {
    
    @Inject ExternalEntityConverter externalEntityConverter;
    
    public PacketResource convert(Packet packet) {
        
        PacketResource packetResource = new PacketResource();
        
        LinkResource hotelRes = externalEntityConverter.
                convert(packet.getRoom(), 
                        ExternalEntityType.HOTEL);
        
        LinkResource roomRes = externalEntityConverter
                .convert(packet.getRoom(), ExternalEntityType.ROOM);
        
        LinkResource transportRes = externalEntityConverter
                .convert(packet.getTransport(), ExternalEntityType.TRANSPORT);
        
        LinkResource routeRes = externalEntityConverter
                .convert(packet.getRoute(), ExternalEntityType.ROUTE);
        
        return new PacketResource(
                packet.getId(),
                hotelRes,
                roomRes,
                transportRes,
                routeRes
        );
    }
    
}
