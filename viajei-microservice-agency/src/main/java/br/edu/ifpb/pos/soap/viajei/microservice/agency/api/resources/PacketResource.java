/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources;

import java.io.Serializable;


/**
 *
 * @author kieckegard
 */
public class PacketResource implements Serializable {
    
   private Long id;
   private ResourceRef hotel;
   private ResourceRef room;
   private ResourceRef transport;
   private ResourceRef route;

    public PacketResource(Long id, ResourceRef hotel, ResourceRef room, ResourceRef transport, ResourceRef route) {
        this.id = id;
        this.hotel = hotel;
        this.room = room;
        this.transport = transport;
        this.route = route;
    }

    public PacketResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceRef getHotel() {
        return hotel;
    }

    public void setHotel(ResourceRef hotel) {
        this.hotel = hotel;
    }

    public ResourceRef getRoom() {
        return room;
    }

    public void setRoom(ResourceRef room) {
        this.room = room;
    }

    public ResourceRef getTransport() {
        return transport;
    }

    public void setTransport(ResourceRef transport) {
        this.transport = transport;
    }

    public ResourceRef getRoute() {
        return route;
    }

    public void setRoute(ResourceRef route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "PacketResource{" + "id=" + id + ", hotel=" + hotel + ", room=" + room + ", transport=" + transport + ", route=" + route + '}';
    }
}
