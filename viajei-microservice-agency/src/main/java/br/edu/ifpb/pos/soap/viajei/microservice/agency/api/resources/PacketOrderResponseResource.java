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
public class PacketOrderResponseResource implements Serializable {
    
    private Long id;
    private ResourceRef packet;
    private ResourceRef hotel_booking;
    private ResourceRef transport_ticket;
    private ResourceRef client;

    public PacketOrderResponseResource(Long id, ResourceRef packet, ResourceRef hotel_booking, ResourceRef transport_ticket, ResourceRef client) {
        this.id = id;
        this.packet = packet;
        this.hotel_booking = hotel_booking;
        this.transport_ticket = transport_ticket;
        this.client = client;
    }

    public PacketOrderResponseResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceRef getPacket() {
        return packet;
    }

    public void setPacket(ResourceRef packet) {
        this.packet = packet;
    }

    public ResourceRef getHotel_booking() {
        return hotel_booking;
    }

    public void setHotel_booking(ResourceRef hotel_booking) {
        this.hotel_booking = hotel_booking;
    }

    public ResourceRef getTransport_ticket() {
        return transport_ticket;
    }

    public void setTransport_ticket(ResourceRef transport_ticket) {
        this.transport_ticket = transport_ticket;
    }

    public ResourceRef getClient() {
        return client;
    }

    public void setClient(ResourceRef client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "PacketOrderResponseResource{" + "id=" + id + ", packet=" + packet + ", hotel_booking=" + hotel_booking + ", transport_ticket=" + transport_ticket + ", client=" + client + '}';
    }
}
