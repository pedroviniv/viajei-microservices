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
public class PacketOrderServiceException extends RuntimeException {

    public PacketOrderServiceException(String message) {
        super(message);
    }

    public PacketOrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PacketOrderServiceException(Throwable cause) {
        super(cause);
    }
}
