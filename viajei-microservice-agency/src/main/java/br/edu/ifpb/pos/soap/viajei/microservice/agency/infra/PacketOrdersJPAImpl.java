/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.infra;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.PacketOrder;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kieckegard
 */

@PacketOrdersJPA
@RequestScoped
public class PacketOrdersJPAImpl extends JPARepository<PacketOrder, Long> {
    
    public PacketOrdersJPAImpl() {
        super(PacketOrder.class);
    }
    
}
