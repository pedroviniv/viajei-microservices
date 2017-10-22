/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.RoutesEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.ResourceRef;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class RouteResourceRefConverter implements ResourceRefConverter<Route> {

    @Override
    public ResourceRef convert(Route route, UriInfo uriInfo) {
        String rel = route.getFromAddress()
                + " - "
                + route.getDestinationAddress();
        
        URI uri = RoutesEndPoint.getURI(uriInfo, route.getId());
        
        return new ResourceRef(rel, uri.toString());
    }
    
}
