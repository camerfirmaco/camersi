package colombia.supportservice.Messages;

import java.util.Date;

import org.springframework.http.HttpStatus;

import colombia.supportservice.Utils.EnumOperacion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class MessageDetails {
    private HttpStatus estado;
    private EnumOperacion operacion;
    private Date fecha;
    private String mensaje;
    private Object detalles;
    
    public MessageDetails(HttpStatus estado, EnumOperacion operacion, Date fecha) {
        this.estado = estado;
        this.operacion = operacion;
        this.fecha = fecha;
    }

    public MessageDetails(HttpStatus estado, EnumOperacion operacion, Date fecha, String mensaje, Object detalles) {
        this.estado = estado;
        this.operacion = operacion;
        this.fecha = fecha;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public MessageDetails(HttpStatus estado, EnumOperacion operacion, Date fecha, String mensaje) {
        this.estado = estado;
        this.operacion = operacion;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    
}
