/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.model;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author kieckegard
 */

@Entity
public class Packet implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "hotel_id"))
    private ExternalEntity hotel;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "room_id"))
    private ExternalEntity room;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "transport_id"))
    private ExternalEntity transport;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "route_id"))
    private ExternalEntity route;

    public Packet() {
    }

    public Packet(ExternalEntity hotel, ExternalEntity room, ExternalEntity transport, ExternalEntity route) {
        this.hotel = hotel;
        this.room = room;
        this.transport = transport;
        this.route = route;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExternalEntity getHotel() {
        return hotel;
    }

    public void setHotel(ExternalEntity hotel) {
        this.hotel = hotel;
    }

    public ExternalEntity getRoom() {
        return room;
    }

    public void setRoom(ExternalEntity room) {
        this.room = room;
    }

    public ExternalEntity getTransport() {
        return transport;
    }

    public void setTransport(ExternalEntity transport) {
        this.transport = transport;
    }

    public ExternalEntity getRoute() {
        return route;
    }

    public void setRoute(ExternalEntity route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Packet{" + "hotel=" + hotel + ", room=" + room + ", transport=" + transport + ", route=" + route + '}';
    }
}
