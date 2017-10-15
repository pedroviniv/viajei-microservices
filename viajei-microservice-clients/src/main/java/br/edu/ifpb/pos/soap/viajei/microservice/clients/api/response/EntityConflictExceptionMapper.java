/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.clients.api.response;

import br.edu.ifpb.pos.soap.viajei.microservice.clients.infra.EntityConflictException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author pafer
 */
public class EntityConflictExceptionMapper implements ExceptionMapper<EntityConflictException> {

    @Override
    public Response toResponse(EntityConflictException exception) {
        
        Status status = Status.CONFLICT;
        
        ErrorResponse response = ErrorResponse.of(status.getStatusCode(), 
                exception.getMessage());
        
        return Response.status(status)
                .entity(response)
                .build();
    } 
    
}
