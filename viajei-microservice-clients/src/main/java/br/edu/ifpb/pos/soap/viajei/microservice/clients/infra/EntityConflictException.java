/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.clients.infra;

/**
 *
 * @author pafer
 */
public class EntityConflictException extends IllegalArgumentException {

    public EntityConflictException(String s) {
        super(s);
    }

    public EntityConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityConflictException(Throwable cause) {
        super(cause);
    }
}
