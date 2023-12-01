package com.gutosoethe.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;


@WebFilter("/*")
public class JwtTokenFilter implements Filter {

    private static final String SECRET_KEY = "Chave48BitsSecreta";
//    private static final String SECRET_KEY = Arrays.toString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String token = extractTokenFromHeader(servletRequest);
        if (token != null) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();
                String[] roles = claims.get("roles", String.class).split(",");
//                servletRequest.getServletContext().setUserPrincipal(new ApiPrincipal(claims.getSubject(), roles));
                
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Inicialização do filtro, se necessário
    }

    @Override
    public void destroy() {
        // Liberação de recursos, se necessário
    }

    private String extractTokenFromHeader(ServletRequest servletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String authorizationHeader = httpRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // 7 é o comprimento da string "Bearer "
        }

        return null;
    }

    private String[] getRolesFromClaims(Claims claims) {
        if (claims.containsKey("roles")) {
            String rolesClaim = claims.get("roles", String.class);

            return rolesClaim.split(",");
        }
        return new String[]{"ROLE_USER"};
    }

}
