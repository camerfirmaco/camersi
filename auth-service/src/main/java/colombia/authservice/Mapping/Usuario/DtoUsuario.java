package colombia.authservice.Mapping.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoUsuario {
    // ID
    private String id;

    // TIPO DE DOCUMENTO
    private String tipoDocumento;

    // IDENTIFICACIÓN
    private String identificacion;

    // EMAIL
    private String email;

    // NOMBRE DEL USUARIO
    private String nombre;

    // PRIMER APELLIDO
    private String PrimerApellido;

    // SEGUNDO APELLIDO
    private String segundoApellido;

    // TELEFONO
    private Long telefono;

    //GENERO
    private String genero;

    // FOTO DE PERFIL
    private String imagen;

    // CARGO DE USUARIO
    private String cargo;

    public DtoUsuario(String id, String tipoDocumento, String identificacion, String email, String nombre,
            String primerApellido, String segundoApellido, Long telefono, String genero, String imagen, String cargo) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        PrimerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.genero = genero;
        this.imagen = imagen;
        this.cargo = cargo;
    }

    
}
