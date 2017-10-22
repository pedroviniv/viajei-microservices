/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.consumers;

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
public class ClientConsumer {
    
    public static final String CLIENT_RES = "http://clients-api:8080/viajei-microservice-clients/api/clients/";
    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target(CLIENT_RES);
    
    public boolean exists(String clientId) {
        
        Response getResponse = this.target
                .path(clientId)
                .request()
                .get();
        
        return getResponse.getStatus() != 404;
    }
    
}
