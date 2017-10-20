/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author pafer
 */

@RoutesJPA
@RequestScoped
public class RoutesJpaImpl extends JPARepository<Route, Long> {
    
    public RoutesJpaImpl() {
        super(Route.class);
    }
    
}
