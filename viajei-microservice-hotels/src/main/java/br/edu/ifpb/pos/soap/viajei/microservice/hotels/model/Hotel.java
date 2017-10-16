    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author pafer
 */

@Entity
public class Hotel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    private String description;
    private int stars;
    
    private String address;
    
    @OneToMany(
            fetch = FetchType.EAGER, 
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
            orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    private List<Room> rooms;

    public Hotel(String name, String description, int stars, String address) {
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.rooms = new ArrayList<>();
        this.address = address;
    }

    public Hotel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    
    public void addRoom(Room room) {
        this.rooms.add(room);
    }
    
    public boolean removeRoom(Long roomId) {
        return this.rooms.removeIf(r -> r.getId().equals(roomId));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", name=" + name + ", description=" + description + ", stars=" + stars + ", address=" + address + ", rooms=" + rooms + '}';
    }
}
