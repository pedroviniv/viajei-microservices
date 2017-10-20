/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author pafer
 */

@Embeddable
public class Client implements Serializable {
    
    private String cpf;

    public Client(String cpf) {
        this.cpf = cpf;
    }
    
    public Client() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Client{" + "cpf=" + cpf + '}';
    }
}
