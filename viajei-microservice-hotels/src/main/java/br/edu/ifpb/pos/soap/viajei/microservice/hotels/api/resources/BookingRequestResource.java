/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources;

import java.io.Serializable;

/**
 *
 * @author pafer
 */
public class BookingRequestResource implements Serializable {
    
    private Long id;
    private Long hotel_id;
    private String client_id;
    private Long room_id;
    private String start_date_time;
    private String end_date_time;

    public BookingRequestResource(Long hotel_id, String client_id, Long room_id, 
            String start_date_time, String end_date_time) {
        
        this.hotel_id = hotel_id;
        this.client_id = client_id;
        this.room_id = room_id;
        this.start_date_time = start_date_time;
        this.end_date_time = end_date_time;
    }

    public BookingRequestResource() {
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public String getStart_date_time() {
        return start_date_time;
    }

    public void setStart_date_time(String start_date_time) {
        this.start_date_time = start_date_time;
    }

    public String getEnd_date_time() {
        return end_date_time;
    }

    public void setEnd_date_time(String end_date_time) {
        this.end_date_time = end_date_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookingResource{" + "id=" + id + ", hotel_id=" + hotel_id + ", client_id=" + client_id + ", room_id=" + room_id + ", start_date_time=" + start_date_time + ", end_date_time=" + end_date_time + '}';
    }
}
