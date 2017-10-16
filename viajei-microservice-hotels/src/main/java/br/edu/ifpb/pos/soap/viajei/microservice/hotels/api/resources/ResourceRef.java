/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources;

import java.io.Serializable;


/**
 *
 * @author pafer
 */
public class ResourceRef implements Serializable {
    
    private String description;
    private String uri;

    public ResourceRef(String description, String uri) {
        this.description = description;
        this.uri = uri;
    }

    public ResourceRef() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "RoomRef{" + "description=" + description + ", uri=" + uri + '}';
    }
}
