package colombia.supportservice.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import colombia.supportservice.Messages.MessageDetails;
import colombia.supportservice.Model.EntityTypifiend;
import colombia.supportservice.Service.ImpServiceTypifiend;
import colombia.supportservice.Utils.EnumOperacion;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/support")
public class TypifiendController {

    @Autowired
    private ImpServiceTypifiend impTypifiend;

    // CONSULTAT TODOS LOS USUARIOS
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

    // GUARDAR TIPIFICADO
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody EntityTypifiend Entity) {
        try {
            return ResponseEntity.ok(impTypifiend.guardar(Entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageDetails(HttpStatus.INTERNAL_SERVER_ERROR, EnumOperacion.GUARDAR, new Date(),
                            "Tipificado no registrado", e.getMessage()));
        }
    }

    // GUARDAR ALL TIPIFICADOS
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
}
