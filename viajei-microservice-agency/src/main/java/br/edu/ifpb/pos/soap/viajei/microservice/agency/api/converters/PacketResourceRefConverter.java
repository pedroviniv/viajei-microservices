/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.PacketsEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.ResourceRef;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;
import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class PacketResourceRefConverter implements ResourceRefConverter<Packet> {

    @Override
    public ResourceRef convert(Packet resource, UriInfo uriInfo) {
        
        ResourceRef res = new ResourceRef();
        
        URI uri = PacketsEndPoint.getURI(uriInfo, resource.getId());
        
        res.setRel(String.valueOf(resource.getId()));
        res.setHref(uri.toString());
        
        return res;
    }
    
}
