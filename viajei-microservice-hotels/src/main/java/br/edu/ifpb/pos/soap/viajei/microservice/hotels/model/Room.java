/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pafer
 */

@Entity
public class Room implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String roomId;
    
    private String title;
    
    @ElementCollection(
            targetClass = Attribute.class, 
            fetch = FetchType.EAGER)
    private List<Attribute> attributes;

    private boolean occupied;
    
    @Column(precision = 11, scale = 2)
    private BigDecimal daily;

    public Room(String roomId, String title, BigDecimal daily, boolean occupied) {
        this.roomId = roomId;
        this.title = title;
        this.daily = daily;
        this.occupied = occupied;
        this.attributes = new ArrayList<>();
    }
    
    public Room() {
        this.attributes = new ArrayList<>();
        this.occupied = false;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String id) {
        this.roomId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getDaily() {
        return daily;
    }

    public void setDaily(BigDecimal daily) {
        this.daily = daily;
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }
    
    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }
    
    public void removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", roomId=" + roomId + ", title=" + title + ", attributes=" + attributes + ", occupied=" + occupied + '}';
    }
}
