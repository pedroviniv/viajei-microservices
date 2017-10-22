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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pafer
 */

@Entity
public class Route implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "from_address")
    private String fromAddress;
    @Column(name = "destination_address")
    private String destinationAddress;
    @Column(name = "km_distance")
    private Integer kmDistance;
    @Embedded
    private Horary horary;

    public Route(String from, String to, Integer kmDistance, Horary horary) {
        this.fromAddress = from;
        this.destinationAddress = to;
        this.kmDistance = kmDistance;
        this.horary = horary;
    }

    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Integer getKmDistance() {
        return kmDistance;
    }

    public void setKmDistance(Integer kmDistance) {
        this.kmDistance = kmDistance;
    }

    public Horary getHorary() {
        return horary;
    }

    public void setHorary(Horary horary) {
        this.horary = horary;
    }
    
    public BigDecimal getKmPrice() {
        return new BigDecimal(this.kmDistance * 0.10);
    }

    @Override
    public String toString() {
        return "Route{" + "id=" + id + ", from=" + fromAddress + ", to=" + destinationAddress + ", kmDistance=" + kmDistance + ", horary=" + horary + '}';
    }
}
