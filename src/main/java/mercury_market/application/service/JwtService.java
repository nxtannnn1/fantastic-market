package mercury_market.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    private final long expiration;

    public JwtService(
            @Value("${api.security.secret}") String secret,
            @Value("${api.security.expiration}") Long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    public String gerarToken(String email) {
        var agora = new Date();
        var expira = new Date(agora.getTime() + expiration);
        return Jwts.builder() //Processo de criação de token
                .setIssuer("Mercury Market") //Quem emitiu o Token
                .setSubject(email) //Dono do token, identificado pelo email
                .setIssuedAt(agora) //Quando foi criado
                .setExpiration(expira) //Quando expira
                .signWith(secretKey, SignatureAlgorithm.HS256) //Assinado conforme algoritmo HS256
                .compact(); //Estiliza
    }

    public String obterEmailUsuarioAutenticado(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public String getSubject(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token JWT inválido!");
        }
    }

}
