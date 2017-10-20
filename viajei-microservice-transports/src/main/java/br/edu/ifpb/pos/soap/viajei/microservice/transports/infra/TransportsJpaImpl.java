/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Transport;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author pafer
 */

@TransportsJPA
@RequestScoped
public class TransportsJpaImpl extends JPARepository<Transport, Long> {
    
    public TransportsJpaImpl() {
        super(Transport.class);
    }
}
