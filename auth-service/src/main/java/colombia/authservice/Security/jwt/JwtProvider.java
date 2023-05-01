package colombia.authservice.Security.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import colombia.authservice.Mapping.Public.RequestDto;
import colombia.authservice.Model.Usuario.InterfaceUsuario;
import colombia.authservice.Router.RouteValidator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

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
    private InterfaceUsuario impUsuario;

    // INYECCIÓN DE RUTAS PARA VALIDACIÓN
    @Autowired
    private RouteValidator routeValidator;

    // METODO CREADOR DE JWT
    public String generateToken(Authentication authentication) {
        // LLAMAR AL USUARIO QUE INICIO SESIÓN CORRECTAMENTE
        UserDetails mainUser = (UserDetails) authentication.getPrincipal();
        // MENSAJE EN CONSOLA CON EL USERNAME DEL USUARIO
        logger.error(mainUser.getUsername());

        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(mainUser.getUsername());
        Object[] roles = mainUser.getAuthorities().toArray();
        claims.put("role", getRoleStrings(roles));

        // CREACIÓN DE JWT RETORNO
        return Jwts.builder().setSubject(mainUser.getUsername()) // ASIGNACIÓN DE JWT - USERNAME
                .setIssuedAt(new Date()) // ASIGNACIÓN DE JWT - FECHA DE EMISIÓN
                .setClaims(claims)// AGREGAR LOS ROLES EN UN ARRAY
                .setExpiration(new Date(new Date().getTime() + expiration * 1000)) // ASIGNACIÓN DE JWT - EXPIRACIÓN
                .signWith(SignatureAlgorithm.HS512, secret) // ASIGNACIÓN DE JWT - FIRMA
                .compact();
    }

    // OBTENER EL NOMBRE DEL USUARIO CON EL TOKEN
    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    // OBTENER EL ID DEL USUARIO CON EL TOKEN
    public String getUserIdFromToken(String token) {
        String id = impUsuario
                .findByEmail(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject()).get()
                .getId();
        return id;
    }

    // OBTENER LOS ROLES DEL USUARIO CON EL TOKEN
    public ArrayList<String> getRolesFromToken(String token) {
        Object objeto = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role");
        if (objeto != null) {
            ArrayList<String> roles = (ArrayList<String>) objeto;
            return roles;
        } else {
            return null;
        }
    }

    // OBTENER FECHA DE EXPIRACIÓN DEL TOKEN
    public Date getExpirationFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }

    // VALIDAR TOKEN CON LA FIRMA SECRETA
    public boolean validateToken(String token, RequestDto ruta) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
            return false;
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
            return false;
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado");
            return false;
        } catch (IllegalArgumentException e) {
            logger.error("Token vacío");
            return false;
        } catch (SignatureException e) {
            logger.error("Fail en la firma");
            return false;
        }
        return routeValidator.isRoute(ruta, getRolesFromToken(token));
    }

    // ROLES PARA ASIGNACIÓN EN CREACIÓN DE TOKEN
    private ArrayList<String> getRoleStrings(Object[] role) {
        ArrayList<String> roles = new ArrayList<String>();
        for (Object object : role) {
            roles.add(object.toString());
        }
        return roles;
    }
}
