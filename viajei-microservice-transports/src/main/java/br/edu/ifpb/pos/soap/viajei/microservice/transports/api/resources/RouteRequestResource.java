/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources;

import java.io.Serializable;

/**
 *
 * @author kieckegard
 */
public class RouteRequestResource implements Serializable {
    
    private String from;
    private String to;
    private Integer km_distance;
    private String departure_date_time;
    private String arrival_date_time;

    public RouteRequestResource(String from, String to, Integer km_distance, String departure_date_time, String arrival_date_time) {
        this.from = from;
        this.to = to;
        this.km_distance = km_distance;
        this.departure_date_time = departure_date_time;
        this.arrival_date_time = arrival_date_time;
    }

    public RouteRequestResource() {
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

    public Integer getKm_distance() {
        return km_distance;
    }

    public void setKm_distance(Integer km_distance) {
        this.km_distance = km_distance;
    }

    public String getDeparture_date_time() {
        return departure_date_time;
    }

    public void setDeparture_date_time(String departure_date_time) {
        this.departure_date_time = departure_date_time;
    }

    public String getArrival_date_time() {
        return arrival_date_time;
    }

    public void setArrival_date_time(String arrival_date_time) {
        this.arrival_date_time = arrival_date_time;
    }

    @Override
    public String toString() {
        return "RouteRequestResource{" + "from=" + from + ", to=" + to + ", km_distance=" + km_distance + ", departure_date_time=" + departure_date_time + ", arrival_date_time=" + arrival_date_time + '}';
    }
}
