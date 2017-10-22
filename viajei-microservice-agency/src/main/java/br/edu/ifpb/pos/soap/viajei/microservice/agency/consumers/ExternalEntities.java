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
public enum ExternalEntities {
    
    CLIENT("http://clients-api:8080/viajei-microservice-clients/api/clients"),
    HOTEL("http://hotels-api:8080/viajei-microservice-hotels/api/hotels"),
    ROOM("http://hotels-api:8080/viajei-microservice-hotels/api/rooms"),
    BOOKING("http://hotels-api:8080/viajei-microservice-hotels/api/bookings"),
    TRANSPORT("http://transports-api:8080/viajei-microservice-transports/api/transports"),
    ROUTE("http://transports-api:8080/viajei-microservice-transports/api/routes"),
    TICKET("http://transports-api:8080/viajei-microservice-transports/api/tickets");
    
    private final String resourceAddress;
    
    ExternalEntities(String resourceAddress) {
        this.resourceAddress = resourceAddress;
    }
    
    public String getResourceAddress() {
        return this.resourceAddress;
    }
}
