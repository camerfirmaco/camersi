package colombia.authservice.Mapping.Usuario;

import colombia.authservice.Utils.EnumGenero;
import colombia.authservice.Utils.EnumTipoDocumento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoUpdateAdmin {
    // TIPO DE DOCUMENTO
    @NotNull
    private EnumTipoDocumento tipoDocumento;

    // IDENTIFICACIÓN
    @NotEmpty
    @Size(max = 15, min = 2)
    private String identificacion;

    // NOMBRE DEL USUARIO
    @NotEmpty
    @Size(max = 60, min = 2)
    private String nombre;

    // EMAIL
    @NotEmpty
    @Email
    @Size(min = 10)
    private String email;

    // PRIMER APELLIDO
    @NotEmpty
    @Size(max = 30, min = 2)
    private String primerApellido;

    // SEGUNDO APELLIDO
    @NotEmpty
    @Size(max = 30, min = 2)
    private String segundoApellido;

    // TELEFONO
    private Long telefono;

    // GENERO
    @NotNull
    private EnumGenero genero;

    // USUARIO HABILITADO
    @NotNull
    private Boolean habilitado;

    // CARGO
    @NotNull
    private String cargo;

    public DtoUpdateAdmin(@NotNull EnumTipoDocumento tipoDocumento,
            @NotEmpty @Size(max = 15, min = 2) String identificacion, @NotEmpty @Size(max = 60, min = 2) String nombre,
            @NotEmpty @Email @Size(min = 10) String email, @NotEmpty @Size(max = 30, min = 2) String primerApellido,
            @NotEmpty @Size(max = 30, min = 2) String segundoApellido, Long telefono, @NotNull EnumGenero genero,
            @NotNull Boolean habilitado, @NotNull String cargo) {
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.email = email;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.genero = genero;
        this.habilitado = habilitado;
        this.cargo = cargo;
    }

    
    
}
