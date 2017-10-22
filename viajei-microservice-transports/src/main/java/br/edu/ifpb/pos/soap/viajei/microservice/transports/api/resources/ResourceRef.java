/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources;

import java.io.Serializable;

/**
 *
 * @author kieckegard
 */
public class ResourceRef implements Serializable {
    
    private String rel;
    private String href;

    public ResourceRef(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public ResourceRef() {
    }
    
    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "ResourceRef{" + "rel=" + rel + ", href=" + href + '}';
    }
}
