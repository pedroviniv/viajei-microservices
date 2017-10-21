/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author kieckegard
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.edu.ifpb.pos.soap.viajei.microservice.agency.api.PacketOrdersEndPoint.class);
        resources.add(br.edu.ifpb.pos.soap.viajei.microservice.agency.api.PacketsEndPoint.class);
        resources.add(br.edu.ifpb.pos.soap.viajei.microservice.agency.api.response.EntityConflictExceptionMapper.class);
        resources.add(br.edu.ifpb.pos.soap.viajei.microservice.agency.api.response.EntityNotFoundExceptionMapper.class);
    }
    
}
