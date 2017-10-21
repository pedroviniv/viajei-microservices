/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.transports.api.response;

import java.io.Serializable;

/**
 *
 * @author pafer
 */
public class ErrorResponse implements Serializable {
    
    private int statusCode;
    private String message;

    private ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    
    public static ErrorResponse of(int statusCode, String message) {
        return new ErrorResponse(statusCode, message);
    }

    public ErrorResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseError{" + "statusCode=" + statusCode + ", message=" + message + '}';
    }
}
