/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.infra;

import java.util.List;

/**
 *
 * @author pafer
 */
public interface Repository<T,E> {
    
    void persist(T obj);
    void update(T obj);
    void remove(E id);
    T findById(E id);
    List<T> listAll();
    
}
