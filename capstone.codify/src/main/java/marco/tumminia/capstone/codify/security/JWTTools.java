package marco.tumminia.capstone.codify.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts; //libreria per generare token
import io.jsonwebtoken.security.Keys;
import marco.tumminia.capstone.codify.entities.utente.Utente;
import marco.tumminia.capstone.codify.exceptions.UnauthorizedException;

@Component
public class JWTTools {
	
    @Value("${spring.jwt.secret}")
    private String secret;

    
    //METODO PER CREARE IL TOKEN
    public String createToken(Utente u) {
        String token = Jwts.builder()
                .setSubject(u.getIdUtente().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
        return token;
    }

    public void verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UnauthorizedException("Il token non è valido! Effettua nuovamente il login");
        }
    }

    public String extractSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
                .getBody().getSubject();
    }
}
