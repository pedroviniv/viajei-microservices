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
   private LinkResource hotel;
   private LinkResource room;
   private LinkResource transport;
   private LinkResource route;

    public PacketResource(Long id, LinkResource hotel, LinkResource room, LinkResource transport, LinkResource route) {
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

    public LinkResource getHotel() {
        return hotel;
    }

    public void setHotel(LinkResource hotel) {
        this.hotel = hotel;
    }

    public LinkResource getRoom() {
        return room;
    }

    public void setRoom(LinkResource room) {
        this.room = room;
    }

    public LinkResource getTransport() {
        return transport;
    }

    public void setTransport(LinkResource transport) {
        this.transport = transport;
    }

    public LinkResource getRoute() {
        return route;
    }

    public void setRoute(LinkResource route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "PacketResource{" + "id=" + id + ", hotel=" + hotel + ", room=" + room + ", transport=" + transport + ", route=" + route + '}';
    }
}
