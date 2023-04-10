package colombia.authservice.Security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import colombia.authservice.Service.Usuario.ImpServiceUsuario;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    // Logger para mostrar los errores
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    // Clave para verificar el token
    @Value("${jwt.secret}")
    private String secret;

    // Tiempo base de expiración
    @Value("${jwt.expiration-milliseconds}")
    private Integer expiration;

    // INYECCIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO USUARIO
    @Autowired
    private ImpServiceUsuario impUsuario;

    // METODO CREADOR DE JWT
    public String generateToken(Authentication authentication) {
        // LLAMAR AL USUARIO QUE INICIO SESIÓN CORRECTAMENTE
        UserDetails mainUser = (UserDetails) authentication.getPrincipal();
        // MENSAJE EN CONSOLA CON EL USERNAME DEL USUARIO
        logger.error(mainUser.getUsername());

        // AGREGAR ROLES AL TOKEN
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims();
        claims.put("roles", mainUser.getAuthorities());

        //ENCRIPTADO
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        Keys.secretKeyFor(SignatureAlgorithm.HS256);
        
        
        // CREACIÓN DE JWT RETORNO

        return Jwts.builder().setSubject(mainUser.getUsername()) // ASIGNACIÓN DE JWT - USERNAME
                .setClaims(claims) // ASIGNACIÓN DE JWT ASIGNACIÓN DE ROLES
                .setIssuedAt(new Date()) // ASIGNACIÓN DE JWT - FECHA DE EMISIÓN
                .setExpiration(new Date(new Date().getTime() + expiration * 1000)) // ASIGNACIÓN DE JWT - EXPIRACIÓN
                .signWith(key, SignatureAlgorithm.HS256) // ASIGNACIÓN DE JWT - FIRMA
                .compact();
    }

    // OBTENER EL NOMBRE DEL USUARIO CON EL TOKEN
    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder().build().parseClaimsJws(token).getBody().getSubject();
    }

    // OBTENER EL ID DEL USUARIO CON EL TOKEN
    public String getUserIdFromToken(String token) {
        String id = impUsuario
                .consultarEmail(Jwts.parserBuilder().build().parseClaimsJws(token).getBody().getSubject()).get()
                .getId();
        return id;
    }

    // OBTENER FECHA DE EXPIRACIÓN DEL TOKEN
    public Date getExpirationFromToken(String token) {
        return Jwts.parserBuilder().build().parseClaimsJws(token).getBody().getExpiration();
    }

    // VALIDAR TOKEN CON LA FIRMA SECRETA
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("Token vacío");
        }
        return false;
    }
}
