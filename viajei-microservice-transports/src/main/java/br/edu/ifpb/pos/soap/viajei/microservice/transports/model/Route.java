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
    private String from;
    private String to;
    @Column(name = "km_distance")
    private Integer kmDistance;
    @Embedded
    private Horary horary;

    public Route(String from, String to, Integer kmDistance, Horary horary) {
        this.from = from;
        this.to = to;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        return new BigDecimal(this.kmDistance * 5.80);
    }

    @Override
    public String toString() {
        return "Route{" + "id=" + id + ", from=" + from + ", to=" + to + ", kmDistance=" + kmDistance + ", horary=" + horary + '}';
    }
}
