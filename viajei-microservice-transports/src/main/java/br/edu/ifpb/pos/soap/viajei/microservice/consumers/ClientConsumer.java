/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.consumers;

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
    
    private Client client = ClientBuilder.newClient();
    private final static String CLIENTS_URI = "http://clients-api/viajei-microservice-clients/api/clients";
    private WebTarget target = client.target(CLIENTS_URI);
    
    public boolean exists(String clientCpf) {
        
        Response getResponse = this.target.path(clientCpf)
                .request()
                .get();
        
        return getResponse.getStatus() != 404;
    }
}
