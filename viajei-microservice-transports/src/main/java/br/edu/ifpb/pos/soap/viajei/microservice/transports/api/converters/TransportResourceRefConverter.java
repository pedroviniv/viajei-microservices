/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.TransportsEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.ResourceRef;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class TransportResourceRefConverter implements ResourceRefConverter<Transport> {

    @Override
    public ResourceRef convert(Transport resource, UriInfo uriInfo) {
        
        URI uri = TransportsEndPoint.getURI(uriInfo, resource.getId());
        return new ResourceRef(resource.getName(), uri.toString());
    }
    
}
