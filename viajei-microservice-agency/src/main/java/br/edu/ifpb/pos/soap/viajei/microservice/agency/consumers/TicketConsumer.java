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
public interface TicketConsumer {
    
    ExternalEntity createTicket(String transportId, String routeId, 
            Integer seatNumber, String clientCpf);
    
}
