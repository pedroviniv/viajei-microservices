/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.mappers;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author pafer
 */

@Provider
public class EntityNotFoundExceptionMapper 
        implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        
        ErrorResponse response = ErrorResponse.of(404, exception.getMessage());
        
        return Response.status(Response.Status.NOT_FOUND)
                .entity(response)
                .build();
    }
    
}
