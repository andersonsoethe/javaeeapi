package com.gutosoethe.rest;

import com.gutosoethe.bo.UsuarioBo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Date;

@Path("/auth")
public class AuthResourceRest {

    @Inject
    private UsuarioBo usuarioBo;

    private static final String SECRET_KEY =  "Chave48BitsSecreta";
//    private static final String SECRET_KEY = Arrays.toString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());


    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response login(@FormParam("username") String username, @FormParam("password") String password){
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Parâmetros inválidos").build();
        }
        //Bucas usuário no banco com o serivço.
//        UsuarioBo usuarioBo = new UsuarioRest();
        if (usuarioBo.userIsValid(username, password)){
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

    @GET
    @Path("/secure")
    @RolesAllowed("ADMIN") // Acesso permitido apenas para usuários com a função 'admin'.
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecureData() {
        return Response.ok("Dados seguros!").build();
    }
}
