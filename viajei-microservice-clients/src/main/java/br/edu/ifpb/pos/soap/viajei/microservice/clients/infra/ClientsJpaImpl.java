/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.clients.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.clients.model.Client;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author pafer
 */

@RequestScoped
@Transactional
public class ClientsJpaImpl implements Clients {
    
    @PersistenceContext(unitName = "clients-pu")
    private EntityManager manager;
    
    private static final Logger LOG = Logger
            .getLogger(ClientsJpaImpl.class.getName());

    @Override
    public void persist(Client client) {
        try {
            this.manager.persist(client);
            LOG.log(Level.INFO, "Entity {0} was persisted!", client.getId());
        } catch (EntityExistsException ex) {
            String msg = String.format("Already has an entity with the id %s!", 
                    client.getId());
            LOG.log(Level.INFO, msg);
            throw new EntityConflictException(msg, ex);
        }
    }

    @Override
    public void remove(Long id) {
        Client found = findById(id);
        this.manager.merge(found);
        this.manager.remove(found);
        LOG.log(Level.INFO, "Entity {0} was removed!", id);
    }

    @Override
    public void update(Client updatedClient) {
        this.manager.merge(updatedClient);
        LOG.log(Level.INFO, "Entity {0} was updated!", updatedClient.getId());
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> searchResult = Optional
                .ofNullable(this.manager.find(Client.class, id));
        if(!searchResult.isPresent()) {
            String msg = "There's no entity with the id " + id;
            LOG.log(Level.INFO, msg);
            throw new EntityNotFoundException(msg);
        }
        
        return searchResult.get();
    }

    @Override
    public List<Client> listAll() {
        return this.manager.createQuery("FROM Client c", 
                Client.class)
                .getResultList();
    }
    
}
