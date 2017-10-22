/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author kieckegard
 */
public class TicketResponseResource implements Serializable {
    
    private Long id;
    private ResourceRef transport;
    private ResourceRef client;
    private Integer seat_number;
    private BigDecimal price;
    private ResourceRef route;

    public TicketResponseResource(Long id, ResourceRef transport, ResourceRef client, Integer seat_number, BigDecimal price, ResourceRef route) {
        this.id = id;
        this.transport = transport;
        this.client = client;
        this.seat_number = seat_number;
        this.price = price;
        this.route = route;
    }

    public TicketResponseResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceRef getTransport() {
        return transport;
    }

    public void setTransport(ResourceRef transport) {
        this.transport = transport;
    }

    public ResourceRef getClient() {
        return client;
    }

    public void setClient(ResourceRef client) {
        this.client = client;
    }

    public Integer getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(Integer seat_number) {
        this.seat_number = seat_number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ResourceRef getRoute() {
        return route;
    }

    public void setRoute(ResourceRef route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "TicketResponseResource{" + "id=" + id + ", transport=" + transport + ", client=" + client + ", seat_number=" + seat_number + ", price=" + price + ", route=" + route + '}';
    }
}
