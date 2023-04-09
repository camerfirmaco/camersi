package colombia.authservice.Mapping.Usuario;

import colombia.authservice.Utils.EnumGenero;
import colombia.authservice.Utils.EnumTipoDocumento;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoCreateUsuario {
    // TIPO DE DOCUMENTO
    @NotNull
    private EnumTipoDocumento tipoDocumento;
    // IDENTIFICACIÓN
    @NotEmpty
    @Size(max = 15, min = 2)
    private String identificacion;

    // EMAIL
    @Size(min = 10)
    private String email;

    // NOMBRE DEL USUARIO
    @Size(max = 60, min = 2)
    private String nombre;

    // PRIMER APELLIDO
    @Size(max = 30, min = 2)
    private String PrimerApellido;

    // SEGUNDO APELLIDO
    @Size(max = 30, min = 2)
    private String segundoApellido;

    // CONTRASEÑA
    @NotEmpty
    @Size(min = 8)
    private String password;

    // TELEFONO
    private Long telefono;

    //GENERO
    @NotNull
    private EnumGenero genero;

    public DtoCreateUsuario(@NotNull EnumTipoDocumento tipoDocumento,
            @NotEmpty @Size(max = 15, min = 2) String identificacion, @Size(min = 10) String email,
            @Size(max = 60, min = 2) String nombre, @Size(max = 30, min = 2) String primerApellido,
            @Size(max = 30, min = 2) String segundoApellido, @NotEmpty @Size(min = 8) String password, Long telefono,
            @NotNull EnumGenero genero) {
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        PrimerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.genero = genero;
    }



}
