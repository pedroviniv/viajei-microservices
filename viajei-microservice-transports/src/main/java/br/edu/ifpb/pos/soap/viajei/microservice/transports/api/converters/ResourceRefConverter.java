/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.ResourceRef;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */
public interface ResourceRefConverter<T> {
    
    ResourceRef convert(T resource, UriInfo uriInfo);
}
