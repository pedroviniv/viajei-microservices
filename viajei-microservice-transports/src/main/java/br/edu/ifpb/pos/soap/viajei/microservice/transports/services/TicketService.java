/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.services;

/**
 *
 * @author kieckegard
 */
public interface TicketService {
    
    Long add(Long transportId, Long routeId, 
            String clientCpf, Integer seatNumber);
}
