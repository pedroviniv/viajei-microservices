/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class TransportResponseResource implements Serializable {
    
    private Long id;
    private String type;
    private String company_name;
    private String name;
    private String description;
    private Integer seats_qty;
    private BigDecimal base_price;
    private List<ResourceRef> routes;

    public TransportResponseResource(Long id, String type, String company_name, 
            String name, String description, Integer seats_qty, BigDecimal base_price) {
        this.id = id;
        this.type = type;
        this.company_name = company_name;
        this.name = name;
        this.description = description;
        this.seats_qty = seats_qty;
        this.base_price = base_price;
        this.routes = new ArrayList<>();
    }

    public TransportResponseResource() {
        this.routes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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

    public Integer getSeats_qty() {
        return seats_qty;
    }

    public void setSeats_qty(Integer seats_qty) {
        this.seats_qty = seats_qty;
    }

    public BigDecimal getBase_price() {
        return base_price;
    }

    public void setBase_price(BigDecimal base_price) {
        this.base_price = base_price;
    }

    public List<ResourceRef> getRoutes() {
        return routes;
    }

    public void setRoutes(List<ResourceRef> routes) {
        this.routes = routes;
    }
    
    public void addRoute(ResourceRef route) {
        this.routes.add(route);
    }

    @Override
    public String toString() {
        return "TransportResponseResource{" + "id=" + id + ", type=" + type + ", company_name=" + company_name + ", name=" + name + ", description=" + description + ", seats_qty=" + seats_qty + ", routes=" + routes + '}';
    }
}
