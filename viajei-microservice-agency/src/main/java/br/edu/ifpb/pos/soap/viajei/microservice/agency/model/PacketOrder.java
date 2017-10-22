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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author kieckegard
 */

@Entity
public class PacketOrder implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long orderId;
    
    @Embedded
    @AttributeOverride(
            name = "id", 
            column = @Column(name = "client_id")
    )
    private ExternalEntity client;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packet_id")
    private Packet packet;
    
    @Embedded
    @AttributeOverride(
            name = "id", 
            column = @Column(name = "booking_id")
    )
    private ExternalEntity hotelBooking;
    @AttributeOverride(
            name = "id", 
            column = @Column(name = "ticket_id")
    )
    private ExternalEntity transportTicket;

    public PacketOrder(ExternalEntity client, Packet packet, 
            ExternalEntity hotelBooking, ExternalEntity transportTicket) {
        
        this.client = client;
        this.packet = packet;
        this.hotelBooking = hotelBooking;
        this.transportTicket = transportTicket;
    }

    public PacketOrder() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ExternalEntity getClient() {
        return client;
    }

    public void setClient(ExternalEntity client) {
        this.client = client;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public ExternalEntity getHotelBooking() {
        return hotelBooking;
    }

    public void setHotelBooking(ExternalEntity hotelBooking) {
        this.hotelBooking = hotelBooking;
    }

    public ExternalEntity getTransportTicket() {
        return transportTicket;
    }

    public void setTransportTicket(ExternalEntity transportTicket) {
        this.transportTicket = transportTicket;
    }

    @Override
    public String toString() {
        return "PacketOrder{" + "orderId=" + orderId + ", client=" + client + ", packet=" + packet + ", hotelBooking=" + hotelBooking + ", transportTicket=" + transportTicket + '}';
    }
}
