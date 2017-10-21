/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author kieckegard
 */

public class MapperProducer {
    
    @RequestScoped
    @Default
    @Produces
    public Mapper getMapper() {
        return new Mapper();
    }
}
