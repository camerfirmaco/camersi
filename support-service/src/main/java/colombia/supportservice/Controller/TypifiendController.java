package colombia.supportservice.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import colombia.supportservice.Mapping.DtoCreateUpdateTypifiend;
import colombia.supportservice.Messages.MessageDetails;
import colombia.supportservice.Model.EntityTypifiend;
import colombia.supportservice.Service.ImpServiceTypifiend;
import colombia.supportservice.Utils.EnumOperacion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/support")
public class TypifiendController {

    @Autowired
    private ImpServiceTypifiend impTypifiend;

    // CONSULTAR TODOS LOS TIPIFICADOS DEL LOS ULTIMOS 3 MESES
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            return ResponseEntity.ok(impTypifiend.listar());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Tipificados no encontrados", e.getMessage()));
        }
    }

    // CONSULTAR TODOS LOS TIPIFICADOS DEL ULTIMO AÑO
    @GetMapping(value = "/all")
    public ResponseEntity<?> listarAll() {
        try {
            return ResponseEntity.ok(impTypifiend.listarAll());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Tipificados no encontrados", e.getMessage()));
        }
    }
    // CONSULTAR TODOS LOS TIPIFICADOS ELIMINADOS DEL ULTIMO AÑO
    @GetMapping(value = "/all/delete")
    public ResponseEntity<?> listarDelete() {
        try {
            return ResponseEntity.ok(impTypifiend.listarEliminados());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Tipificados no encontrados", e.getMessage()));
        }
    }

    // CONSULTAR TIPIFICADO POR AGENTE
    @CircuitBreaker(name = "agenteTypifiendCB", fallbackMethod = "fallBackGetAgenteTypifiend")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> listarAgente(@PathVariable String id) {
        try {
            return ResponseEntity.ok(impTypifiend.consultarAgente(id));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Tipificados no encontrados por Agente", e.getMessage()));
        }
    }

    // CONSULTAR TODOS LOS TIPIFICADOS PENDIENTES
    @GetMapping(value = "/pending")
    public ResponseEntity<?> listarPendientes() {
        try {
            return ResponseEntity.ok(impTypifiend.consultarPending());
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Tipificados pendientes no encontrados", e.getMessage()));
        }
    }

    //CONSULTAR HISTORIAL POR USUARIO
    @GetMapping(value = "/history/{id}")
    public ResponseEntity<?> consultarUsuario(@PathVariable String id) {
        try {
            return ResponseEntity.ok(impTypifiend.consultarUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.CONSULTAR, new Date(),
                            "Tipificados no encontrados por Usuario", e.getMessage()));
        }
    }

    // GUARDAR TIPIFICADO
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody DtoCreateUpdateTypifiend Entity) {
        try {
            return ResponseEntity.ok(impTypifiend.guardar(Entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.GUARDAR, new Date(),
                            "Tipificado no registrado", e.getMessage()));
        }
    }

    // GUARDAR ALL TIPIFICADOS CARGA MASIVA
    @PostMapping(value="/all")
    public ResponseEntity<?> guardarAll(@Valid @RequestBody List<EntityTypifiend> Entity) {
        try {
            return ResponseEntity.ok(impTypifiend.guardarAll(Entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.GUARDAR, new Date(),
                            "Tipificados no registrado", e.getMessage()));
        }
    }

    // CORTA CIRCUITO
    public ResponseEntity<?> fallBackGetAgenteTypifiend(@PathVariable String id, RuntimeException e) {
        return ResponseEntity.ok("Service-Auth Caido, la consulta de typifiend no fue posible "+e.getMessage());
    }

}
