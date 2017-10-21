/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters.ExternalEntityType;

/**
 *
 * @author kieckegard
 */
public interface ExternalEntityVerifier {
    
    boolean exists(String entityId, ExternalEntityType type);
}
