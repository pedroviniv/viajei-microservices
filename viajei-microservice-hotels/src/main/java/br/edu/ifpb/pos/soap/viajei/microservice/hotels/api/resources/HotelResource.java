/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.resources;

import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.HotelEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.api.RoomEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Hotel;
import br.edu.ifpb.pos.soap.viajei.microservice.hotels.model.Room;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author pafer
 */
public class HotelResource implements Serializable {
    
    private Long id;
    private String title;
    private String description;
    private int stars;
    private List<ResourceRef> rooms;

    private HotelResource(Long id, String title, String description, int stars) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.stars = stars;
        this.rooms = new ArrayList<>();
    }
    
    public static HotelResource of(Hotel hotel, UriInfo uriInfo) {
        
        HotelResource hotelResult = new HotelResource(hotel.getId(), hotel.getName(), 
                hotel.getDescription(), hotel.getStars());
        
        List<ResourceRef> roomsLinks = hotel.getRooms()
                .stream()
                .map(r -> HotelResource.toResourceRef(r, uriInfo))
                .collect(Collectors.toList());
        
        hotelResult.setRooms(roomsLinks);
        
        return hotelResult;
    }
    
    public static ResourceRef toResourceRef(Room room, UriInfo uriInfo) {
        
        URI uri = uriInfo.getBaseUriBuilder()
                .path(RoomEndPoint.class)
                .path(String.valueOf(room.getId()))
                .build();
        
        return new ResourceRef(room.getTitle(), uri.toString());
    }

    public HotelResource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<ResourceRef> getRooms() {
        return rooms;
    }

    public void setRooms(List<ResourceRef> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "HotelResult{" + "id=" + id + ", title=" + title + ", description=" + description + ", stars=" + stars + ", rooms=" + rooms + '}';
    }
}
