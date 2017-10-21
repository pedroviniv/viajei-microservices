/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 *
 * @author pafer
 */

@Entity
public class Ticket implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Transport transport;
    
    @Embedded
    private Client client;
    
    private Integer seatNumber;
    
    @Column(precision = 11, scale = 2)
    private BigDecimal price;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Route route;

    public Ticket(Transport transport, Client client, Integer seatNumber, Route route) {
        this.client = client;
        this.seatNumber = seatNumber;
        this.transport = transport;
        this.route = route;
        this.price = calculatePrice();
    }
    
    private BigDecimal calculatePrice() {
        return this.transport.getBasePrice()
                .multiply(route.getKmPrice());
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", transport=" + transport + ", client=" + client + ", seatNumber=" + seatNumber + ", price=" + price + ", route=" + route + '}';
    }
}
