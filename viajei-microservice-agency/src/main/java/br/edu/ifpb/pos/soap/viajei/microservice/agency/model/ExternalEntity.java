/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author kieckegard
 */

@Embeddable
public class ExternalEntity implements Serializable {
    
    private String id;

    public ExternalEntity() {
    }

    public ExternalEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExternalEntity{" + "id=" + id + '}';
    }
}
