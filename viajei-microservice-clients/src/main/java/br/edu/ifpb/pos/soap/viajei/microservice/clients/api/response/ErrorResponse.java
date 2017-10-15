/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pos.soap.viajei.microservice.clients.api.response;

import java.io.Serializable;

/**
 *
 * @author pafer
 */
public class ErrorResponse implements Serializable {
    
    private int statusCode;
    private String msg;

    private ErrorResponse(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
    
    public static ErrorResponse of(int statusCode, String msg) {
        return new ErrorResponse(statusCode, msg);
    }

    public ErrorResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" + "statusCode=" + statusCode + ", msg=" + msg + '}';
    }
}
