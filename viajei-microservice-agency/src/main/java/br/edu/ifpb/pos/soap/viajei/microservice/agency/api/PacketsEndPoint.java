/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.converters.PacketConverter;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.PacketResource;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.PacketsJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.Packet;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.services.PacketService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@Path("packets")
@RequestScoped
public class PacketsEndPoint {
    
    @PacketsJPA
    @Inject
    private Repository<Packet, Long> packets;
    @Inject
    private PacketConverter packetConverter;
    @Inject
    private PacketService packetService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(
            @DefaultValue("-1") 
            @PathParam("packetId") 
                    Long packetId) {
        
        Packet found = this.packets.findById(packetId);
        PacketResource packetResource = packetConverter
                .convert(found);
        
        return Response.ok(packetResource).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        
        List<Packet> packetList = this.packets.listAll();
        List<PacketResource> packetResourceList = packetList.stream()
                .map(p -> packetConverter.convert(p))
                .collect(Collectors.toList());
        
        GenericEntity<List<PacketResource>> entity = 
                new GenericEntity<List<PacketResource>>(packetResourceList){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Packet packet, @Context UriInfo uriInfo) {
        
        this.packetService.add(packet);
        
        URI createdURI = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(packet.getId()))
                .build();
        
        return Response.created(createdURI).build();
    }
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Packet updatedPacket) {
        
        this.packetService.update(updatedPacket);
        
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{packetId}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("packetId") 
                    Long packetId) {
        
        this.packets.remove(packetId);
        
        return Response.ok().build();
    }
}
