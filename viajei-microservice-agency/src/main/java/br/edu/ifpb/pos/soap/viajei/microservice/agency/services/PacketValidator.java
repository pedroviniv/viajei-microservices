/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.services;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;

/**
 *
 * @author kieckegard
 */
public interface PacketValidator {
    
    void validate(Packet packet);
}
