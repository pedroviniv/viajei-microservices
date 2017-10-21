/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.LinkResource;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.ExternalEntity;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class ExternalEntityConverter {
    
    public LinkResource convert(ExternalEntity externalEntity, ExternalEntityType type) {

        String href = type.getResourceAddress() + "/";
        return new LinkResource(externalEntity.getId(), href + externalEntity.getId());
    }
}
