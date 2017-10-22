/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.ResourceRef;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.TransportResponseResource;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class TransportConverter {
    
    @Inject private ResourceRefConverter<Route> routeConverter;
    
    public TransportResponseResource convert(Transport transport, 
            UriInfo uriInfo) {
        
        TransportResponseResource res = new TransportResponseResource();
        
        res.setId(transport.getId());
        res.setCompany_name(transport.getCompany());
        res.setName(transport.getName());
        res.setSeats_qty(transport.getSeats());
        res.setBase_price(transport.getBasePrice());
        res.setDescription(transport.getDescription());
        res.setType(transport.getType().name());
        
        List<ResourceRef> rooms = transport.getRoutes()
                .stream()
                .map(r -> routeConverter.convert(r, uriInfo))
                .collect(Collectors.toList());
        
        res.setRoutes(rooms);
        return res;
    }
}
