package colombia.authservice.Controller.Usuario;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import colombia.authservice.Mapping.Cargo.DtoCreateCargo;
import colombia.authservice.Mapping.Usuario.DtoAgenteSupport;
import colombia.authservice.Mapping.Usuario.DtoAsignacion;
import colombia.authservice.Mapping.Usuario.DtoCreateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUpdateAdmin;
import colombia.authservice.Mapping.Usuario.DtoUpdateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUsuario;
import colombia.authservice.Messages.Global.MessageDetails;
import colombia.authservice.Service.Usuario.ImpServiceUsuario;
import colombia.authservice.Utils.EnumOperacion;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Validated
@RequestMapping("/users")
public class ControllerUsuario {

    // INYECCIÓN DE LA IMPLEMENTACIÓN DEL SERVICIO USUARIO
    @Autowired
    private ImpServiceUsuario impUsuario;

    // CONSULTAT TODOS LOS USUARIOS
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(impUsuario.listar());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Usuario no encontrado", e.getMessage()));
        }

    }

    // CONSULTAR USUARIO POR ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> consultar(@PathVariable String id) {
        try {
            Optional<DtoUsuario> usuario = impUsuario.consultarId(id);
            return ResponseEntity.ok(usuario.get());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Usuario no encontrado", e.getMessage()));
        }
    }

    // EDITAR PERFIL
    @PutMapping("/{id}")
    public ResponseEntity<?> editarPerfil(@PathVariable String id, @RequestBody DtoUpdateUsuario DTO) {
        try {
            if (impUsuario.consultarId(id).isPresent()) {
                return ResponseEntity.ok(impUsuario.editar(id, DTO));
            } else {
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageDetails(HttpStatus.UNPROCESSABLE_ENTITY, EnumOperacion.EDITAR,
                                new Date(), "Usuario no existente", null));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.EDITAR, new Date(),
                            "Usuario no editado", e.getMessage()));
        }
    }

    // ACTUALIZAR
    @PutMapping("/all/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody DtoUpdateAdmin DTO) {
        try {
            if (impUsuario.consultarId(id).isPresent()) {
                return ResponseEntity.ok(impUsuario.actualizar(id, DTO));
            } else {
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageDetails(HttpStatus.UNPROCESSABLE_ENTITY, EnumOperacion.EDITAR,
                                new Date(), "Usuario no existente", null));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.EDITAR, new Date(),
                            "Usuario no editado", e.getMessage()));
        }
    }

    // GUARDAR USUARIO
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody DtoCreateUsuario DTO) {
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

    // ASIGNACION DE ROLES
    @PutMapping(value = "role/{id}")
    public ResponseEntity<?> asignacion(@PathVariable String id, @RequestBody DtoAsignacion asignacion) {
        try {
            if (impUsuario.consultarId(id).isPresent()) {
                return ResponseEntity.ok(impUsuario.asignacion(id, asignacion));
            } else {
                return ResponseEntity.unprocessableEntity()
                        .body(new MessageDetails(HttpStatus.UNPROCESSABLE_ENTITY, EnumOperacion.GUARDAR,
                                new Date(), "Usuario no existente", null));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.GUARDAR, new Date(),
                            "Usuario no asignado", e.getMessage()));
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    // <---CARGO---->//
    /////////////////////////////////////////////////////////////////////////////////////////////

    // REGISTRO DE CARGO
    @PostMapping("/cargo")
    public ResponseEntity<?> guardarCargo(@Valid @RequestBody DtoCreateCargo DTO) {
        try {
            if (impUsuario.existenciaCargo(DTO)) {
                return ResponseEntity.accepted().body(new MessageDetails(HttpStatus.ACCEPTED, EnumOperacion.GUARDAR,
                        new Date(), "Cargo existente", null));
            } else {
                HashMap<String, String> map = new HashMap<>();
                String cargo = impUsuario.guardarCargo(DTO);
                map.put("cargo", cargo);
                return ResponseEntity.ok().body(map);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.GUARDAR, new Date(),
                            "Cargo no registrado", e.getMessage()));
        }
    }

    @GetMapping("/support/{id}")
    public ResponseEntity<DtoAgenteSupport> getAgentSupport(@PathVariable("id") String id) {
        try {
            DtoUsuario user = impUsuario.consultarId(id).get();
            if (user != null)
                return ResponseEntity.ok()
                        .body(new DtoAgenteSupport(user.getId(), user.getNombre() + " " + user.getPrimerApellido()));
            return ResponseEntity.internalServerError().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
