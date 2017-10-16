/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transport.ticket.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Transport implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String company;
    
    @Enumerated(EnumType.STRING)
    private TransportType type;
    
    private String name;
    private String description;
    private Integer seats;
    
    @Column(name = "base_price")
    private BigDecimal basePrice;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id")
    private List<Route> routes;
    
    

    public Transport(String company, TransportType type, String name, 
            String description, Integer seats) {
        this.company = company;
        this.type = type;
        this.name = name;
        this.description = description;
        this.seats = seats;
    }

    public Transport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) {
        this.type = type;
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

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
    
    public void addRoute(Route route) {
        this.routes.add(route);
    }
    
    public void removeRoute(Long routeId) {
        this.routes.removeIf(r -> r.getId().equals(routeId));
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", company=" + company + ", name=" + name + ", description=" + description + ", seats=" + seats + '}';
    }
}
