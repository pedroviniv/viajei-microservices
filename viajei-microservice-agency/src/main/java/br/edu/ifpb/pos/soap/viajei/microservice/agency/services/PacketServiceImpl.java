/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.services;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.PacketsJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class PacketServiceImpl implements PacketService {
    
    @Inject
    @PacketsJPA
    private Repository<Packet, Long> packets;
    
    @Inject private PacketValidator packetValidator;
    
    @Override
    public void add(Packet packet) {
        
        this.packetValidator.validate(packet);
        this.packets.persist(packet);
    }

    @Override
    public void update(Packet updatedPacket) {
        this.packetValidator.validate(updatedPacket);
        this.packets.update(updatedPacket);
    }
}
