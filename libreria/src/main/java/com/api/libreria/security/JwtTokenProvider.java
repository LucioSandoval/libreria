package com.api.libreria.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private static  final String ACCESS_TOKEN_SECRET = "88b73517-902d-46a0-8421-9ae89335c35e";
    private static final Long EXPIRATION_TIME = 28800000L;

    /**
     * Método que genera token
     * @param authentication objeto que contiene información del usuario autentificado
     * @return retorna el token generado
     */
    public String generateToken(Authentication authentication) {


        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + EXPIRATION_TIME);

        Map<String, Object> extra = new HashMap<>();

        String token = Jwts.builder()
                .setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();

        return token;
    }

    /**
     * Método que permite extraer el rut del usuario
     * @param token contiene un token de autentificación
     * @return retorna el rut del usuario
     */
    public String obtenerUserNameDelJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Método para validar token
     * @param token contiene un token
     * @return retorna un boleano true si el token es valido y false si no lo es
     */
    public Boolean validarToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Token JWT no válido");
            return false;
        } catch (ExpiredJwtException ex) {
            System.out.println("Token JWT caducado");
            return false;
        } catch (UnsupportedJwtException ex) {
            System.out.println("Token JWT no compatible");
            return false;
        } catch (Exception ex) {
            System.out.println("Error al validar el token JWT");
            return false;
        }
    }
}

