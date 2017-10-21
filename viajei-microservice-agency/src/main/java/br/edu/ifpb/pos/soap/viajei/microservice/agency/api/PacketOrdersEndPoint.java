/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.agency.api;

import br.edu.ifpb.pos.soap.viajei.microservice.agency.api.resources.PacketOrderRequestResource;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.PacketOrdersJPA;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.infra.Repository;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.model.PacketOrder;
import br.edu.ifpb.pos.soap.viajei.microservice.agency.services.PacketOrderService;
import java.net.URI;
import java.util.List;
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

@Path("packet_orders")
@RequestScoped
public class PacketOrdersEndPoint {
        
    @Inject private PacketOrderService packetOrderService;
    
    @PacketOrdersJPA
    @Inject
    private Repository<PacketOrder, Long> packetOrders;
    
    @GET
    @Path("{packetOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @DefaultValue("-1") 
            @PathParam("packetOrderId") 
                    Long packetOrderId) {
        
        PacketOrder packetOrderFound = this.packetOrders
                .findById(packetOrderId);
        
        return Response.ok(packetOrderFound).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        
        List<PacketOrder> packetOrderList = this.packetOrders.listAll();
        
        GenericEntity<List<PacketOrder>> entity = 
                new GenericEntity<List<PacketOrder>>(packetOrderList){};
        
        return Response.ok(entity).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response order(PacketOrderRequestResource req, @Context UriInfo uriInfo) {
        
        Long packetOrderId = this.packetOrderService.order(req.getPacket_id(), 
                req.getClient_cpf(), req.getSeat_number(), 
                req.getStart_date(), req.getEnd_date());
        
        URI createdUri = uriInfo.getBaseUriBuilder()
                .path(this.getClass())
                .path(String.valueOf(packetOrderId))
                .build();
        
        return Response.created(createdUri).build();
    }
    
    @DELETE
    @Path("{packetOrderId}")
    public Response remove(
            @DefaultValue("-1") 
            @PathParam("packetOrderId") 
                    Long packetOrderId) {
        
        this.packetOrders.remove(packetOrderId);
        
        return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(PacketOrder updatedPacketOrder) {
        
        this.packetOrders.update(updatedPacketOrder);
        
        return Response.ok().build();
    }
    
    
}
