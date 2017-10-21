/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources;

import java.io.Serializable;

/**
 *
 * @author kieckegard
 */
public class PacketOrderRequestResource implements Serializable {
    
    private Long packet_id;
    private String client_cpf;
    private Integer seat_number;
    private String start_date;
    private String end_date;

    public PacketOrderRequestResource(Long packet_id, String client_cpf, Integer seat_number, String start_date, String end_date) {
        this.packet_id = packet_id;
        this.client_cpf = client_cpf;
        this.seat_number = seat_number;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public PacketOrderRequestResource() {
    }

    public Long getPacket_id() {
        return packet_id;
    }

    public void setPacket_id(Long packet_id) {
        this.packet_id = packet_id;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "PacketOrderRequestResource{" + "packet_id=" + packet_id + ", client_cpf=" + client_cpf + ", seat_number=" + seat_number + ", start_date=" + start_date + ", end_date=" + end_date + '}';
    }
}
