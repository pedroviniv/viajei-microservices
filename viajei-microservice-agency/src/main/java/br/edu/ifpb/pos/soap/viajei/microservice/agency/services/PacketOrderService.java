/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.services;


/**
 *
 * @author kieckegard
 */
public interface PacketOrderService {
    
    Long order(Long packetId, String clientId, Integer seatNumber, String hotelStartDate, String hotelEndDate);
    void remove(Long packerOrderId);
}
