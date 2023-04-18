package colombia.authservice.Controller.Public;

import org.springframework.web.bind.annotation.RestController;

import colombia.authservice.Mapping.Public.DtoJwt;
import colombia.authservice.Mapping.Public.DtoLogin;
import colombia.authservice.Messages.Global.MessageDetails;
import colombia.authservice.Security.jwt.JwtProvider;
import colombia.authservice.Service.Email.EnvioEmail;
import colombia.authservice.Utils.EnumOperacion;
import jakarta.validation.Valid;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private EnvioEmail email;

    @GetMapping(value = "/email")
    public ResponseEntity<?> envio() {
        email.sendEmail("marcos.rincon1903@gmail.com", "Envio de correo prueba",
                "Contenido de información, todo reservado por Marcos Company");
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody DtoLogin login) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    login.getUsername(), login.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            Date vence = jwtProvider.getExpirationFromToken(jwt);
            String id = jwtProvider.getUserIdFromToken(jwt);

            DtoJwt jwtDto = new DtoJwt(jwt, id, vence);
            return ResponseEntity.ok().body(jwtDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.LOGIN, new Date(),
                            "Inicio de sesión fallido", e.getMessage()));
        }
    }
    @PostMapping(value="/validate")
    public ResponseEntity<?> postMethodName() {
        
        return ResponseEntity.accepted().body(new MessageDetails(HttpStatus.ACCEPTED, EnumOperacion.TOKEN, new Date(),
        "Token aceptado, procede a ruta", null));
    }
    
    

}
