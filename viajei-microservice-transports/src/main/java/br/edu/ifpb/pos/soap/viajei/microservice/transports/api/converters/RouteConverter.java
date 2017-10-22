/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.converters;

import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.RoutesEndPoint;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.ResourceRef;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.api.resources.RouteRequestResource;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Horary;
import br.edu.ifpb.pos.soap.viajei.microservice.transports.model.Route;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kieckegard
 */

@ApplicationScoped
public class RouteConverter {
    
    public Route convert(RouteRequestResource req) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        LocalDateTime departure = LocalDateTime.parse(req.getDeparture_date_time(), dtf);
        LocalDateTime arrival = LocalDateTime.parse(req.getArrival_date_time(), dtf);
        
        return new Route(req.getFrom(), req.getTo(), 
                req.getKm_distance(), new Horary(departure, arrival));
    }
}
