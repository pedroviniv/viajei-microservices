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
    private String client_cpf;
    private Integer seat_number;

    public TicketResource(Long transport_id, Long route_id, String client_cpf, Integer seat_number) {
        this.transport_id = transport_id;
        this.route_id = route_id;
        this.client_cpf = client_cpf;
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

    public String getClient_cpf() {
        return client_cpf;
    }

    public void setClient_cpf(String client_cpf) {
        this.client_cpf = client_cpf;
    }

    public Integer getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(Integer seat_number) {
        this.seat_number = seat_number;
    }

    @Override
    public String toString() {
        return "TransportTicketResource{" + "transport_id=" + transport_id + ", route_id=" + route_id + ", client_cpf=" + client_cpf + ", seat_number=" + seat_number + '}';
    }
}
