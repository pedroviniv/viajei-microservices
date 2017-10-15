/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.clients.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.clients.model.Client;
import java.util.List;

/**
 *
 * @author pafer
 */
public interface Clients {
    
    void persist(Client client);
    void remove(Long id);
    void update(Client updatedClient);
    Client findById(Long id);
    List<Client> listAll();
}
