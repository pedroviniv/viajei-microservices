/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.infra;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author pafer
 * @param <T>
 * @param <E>
 */

@Dependent
@Transactional
public class JPARepository<T,E> implements Repository<T,E> {
    
    @PersistenceContext
    private EntityManager manager;
    private final Class<T> entityClass;
    private static final Logger LOG = Logger.getLogger(JPARepository.class.getName());
    
    public JPARepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void persist(T obj) {
        try {
            this.manager.persist(obj);
        } catch (EntityExistsException ex) {
            throw new EntityConflictException("There's already a "
                    + entityClass.getSimpleName() + " instance with the specified id ");
        }
    }

    @Override
    public void update(T obj) {
        try {
            this.manager.merge(obj);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException("There's no "
                    + entityClass.getSimpleName()
                    + " with the specified id!");
        }
    }

    @Override
    public void remove(E id) {
        T entity = findById(id);
        this.manager.remove(entity);
    }

    @Override
    public T findById(E id) {
        Optional<T> result = Optional
                .ofNullable(this.manager.find(entityClass, id));
        
        if(!result.isPresent()) {
            String errorMsg = String.format("There's no %s with the id %s", 
                    entityClass.getName(), id.toString());
            
            throw new EntityNotFoundException(errorMsg);
        }
        
        return result.get();
    }

    @Override
    public List<T> listAll() {
        String jpql = String.format("SELECT e FROM %s e", 
                        entityClass.getSimpleName());
        
        LOG.log(Level.INFO, "QUERY: {0}", jpql);
        
        return this.manager.createQuery(String.format(jpql), entityClass)
                .getResultList();
    }
    
}
