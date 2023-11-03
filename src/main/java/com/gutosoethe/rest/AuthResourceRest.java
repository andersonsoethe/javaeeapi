package com.gutosoethe.rest;

import com.gutosoethe.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.security.PermitAll;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/auth")
public class AuthResourceRest {

    private static final String SECRET_KEY =  "teste_jwt";

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response login(@FormParam("username") String username, @FormParam("password") String password){
        //Bucas usuário no banco com o serivço.
        if ("usuario".equals(username) && "senha123".equals(password)){
            String token = createJWT(username);
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private String createJWT(String username) {
        long expirationTime = 3600000;
        Date now = new Date();
        Date expirationDate = new Date(now.getTime()+expirationTime);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return token;
    }
}
