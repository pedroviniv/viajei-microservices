/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters.ExternalEntityType;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author kieckegard
 */

@RequestScoped
public class ExternalEntityVerifierJerseyImpl implements ExternalEntityVerifier {

    private Client client = ClientBuilder.newClient();
   
    @Override
    public boolean exists(String entityId, ExternalEntityType type) {
        
        WebTarget target = this.client.target(type.getResourceAddress());
        
        Response getResponse = target
                .path(entityId)
                .request()
                .get();
        
        return getResponse.getStatus() != 404;
    }
    
}
