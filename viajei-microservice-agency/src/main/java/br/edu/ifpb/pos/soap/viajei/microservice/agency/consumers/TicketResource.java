/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.consumers;

/**
 *
 * @author kieckegard
 */
public class TicketResource {
    
    private Long transport_id;
    private Long route_id;
    private String client_id;
    private Integer seat_number;

    public TicketResource(Long transport_id, Long route_id, String client_id, Integer seat_number) {
        this.transport_id = transport_id;
        this.route_id = route_id;
        this.client_id = client_id;
        this.seat_number = seat_number;
    }

    public TicketResource() {
    }

    public Long getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(Long transport_id) {
        this.transport_id = transport_id;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Integer getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(Integer seat_number) {
        this.seat_number = seat_number;
    }

    @Override
    public String toString() {
        return "TransportTicketResource{" + "transport_id=" + transport_id + ", route_id=" + route_id + ", client_id=" + client_id + ", seat_number=" + seat_number + '}';
    }
}
