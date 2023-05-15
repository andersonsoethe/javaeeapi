package com.gutosoethe.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class Resource {

    @GET
    public String ola(){
        return "Olá Mundo!";
    }
}
