package colombia.authservice.Controller.Public;

import org.springframework.web.bind.annotation.RestController;

import colombia.authservice.Mapping.Public.DtoEmail;
import colombia.authservice.Mapping.Public.DtoJwt;
import colombia.authservice.Mapping.Public.DtoLogin;
import colombia.authservice.Mapping.Public.DtoNewPassword;
import colombia.authservice.Mapping.Public.DtoPassword;
import colombia.authservice.Mapping.Public.RequestDto;
import colombia.authservice.Mapping.Public.TokenDto;
import colombia.authservice.Mapping.Usuario.DtoCreateUsuario;
import colombia.authservice.Messages.Global.MessageDetails;
import colombia.authservice.Service.Auth.ImpAuthService;
import colombia.authservice.Service.Usuario.ImpServiceUsuario;
import colombia.authservice.Utils.EnumOperacion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {
    // INYECCIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO USUARIO
    @Autowired
    private ImpServiceUsuario impUsuario;

    // INYECCIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO DE AUTENTICACIÓN
    @Autowired
    private ImpAuthService impAuth;

    // LOGIN
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody DtoLogin login) {
        try {
            DtoJwt jwtDto = impAuth.login(login);
            return ResponseEntity.ok().body(jwtDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.LOGIN, new Date(),
                            "Inicio de sesión fallido", e.getMessage()));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto dto) {
        TokenDto tokenDto = impAuth.validateToken(token, dto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    // ENVIO DE CORREO PARA RESTABLECER CONTRASEÑA
    @PostMapping(value = "/password")
    public ResponseEntity<?> password(@RequestBody DtoPassword dtoPassword) {
        try {
            String[] respuesta = impAuth.olvidoPasword(dtoPassword);
            if (respuesta == null)
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                                "Usuario no encontrado", "Los datos enviados no se encuentran"));
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Correo no enviado", e.getMessage()));
        }
    }

    // NUEVA CONTRASEÑA
    @PostMapping(value = "/password/new")
    public ResponseEntity<?> passwordNew(@Valid @RequestBody DtoNewPassword dtoPassword) {
        try {
            boolean respuesta = impAuth.newPasword(dtoPassword);
            if (!respuesta)
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageDetails(HttpStatus.UNAUTHORIZED, EnumOperacion.MODIFICAR, new Date(),
                                "Fallo en cambio de contraseña", "Los datos no coinciden"));
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.MODIFICAR, new Date(),
                            "Error en cambio de contraseña", e.getMessage()));
        }
    }

    // GUARDAR USUARIO
    @PostMapping("/register")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody DtoCreateUsuario DTO) {
        try {
            if (impUsuario.existencia(DTO)) {
                return ResponseEntity.accepted().body(new MessageDetails(HttpStatus.ACCEPTED, EnumOperacion.GUARDAR,
                        new Date(), "Usuario existente", null));
            } else {
                return ResponseEntity.ok(impUsuario.guardar(DTO));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.GUARDAR, new Date(),
                            "Usuario no registrado", e.getMessage()));
        }

    }

    // ENVIAR VALIDACIÓN EMAIL
    @PostMapping(value = "/email")
    public ResponseEntity<?> valideEmail(@NotNull @RequestBody DtoEmail email) {
        try {
            return ResponseEntity.ok().body(impAuth.valideMail(email.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Correo no enviado", e.getMessage()));
        }
    }

    // VALIDAR EMAIL
    @PostMapping(value = "/email/valide")
    public ResponseEntity<?> valideEmailKey(@NotNull @RequestBody String[] valide) {
        try {
            return ResponseEntity.ok().body(impAuth.valideMailKey(valide));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Correo no enviado", e.getMessage()));
        }
    }

}