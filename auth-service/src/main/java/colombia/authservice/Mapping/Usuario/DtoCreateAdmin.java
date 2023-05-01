package colombia.authservice.Mapping.Usuario;

import java.util.List;

import colombia.authservice.Utils.EnumGenero;
import colombia.authservice.Utils.EnumRole;
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
public class DtoCreateAdmin {
    // TIPO DE DOCUMENTO
    @NotNull
    private EnumTipoDocumento tipoDocumento;

    // IDENTIFICACIÓN
    @NotEmpty
    @Size(max = 15, min = 2)
    private String identificacion;

    // EMAIL
    @NotEmpty
    @Email
    @Size(min = 10)
    private String email;

    // NOMBRE DEL USUARIO
    @NotEmpty
    @Size(max = 60, min = 2)
    private String nombre;

    // PRIMER APELLIDO
    @NotEmpty
    @Size(max = 30, min = 2)
    private String PrimerApellido;

    // SEGUNDO APELLIDO
    @Size(max = 30, min = 2)
    private String segundoApellido;

    // TELEFONO
    private Long telefono;

    //GENERO
    private EnumGenero genero;

    // CARGO DE USUARIO
    private String cargo;

    // ROLES EN EL SISTEMA
    private List<EnumRole> roles;

    public DtoCreateAdmin(@NotNull EnumTipoDocumento tipoDocumento,
            @NotEmpty @Size(max = 15, min = 2) String identificacion, @NotEmpty @Email @Size(min = 10) String email,
            @NotEmpty @Size(max = 60, min = 2) String nombre, @NotEmpty @Size(max = 30, min = 2) String primerApellido,
            @Size(max = 30, min = 2) String segundoApellido, Long telefono, EnumGenero genero, String cargo,
            List<EnumRole> roles) {
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        PrimerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.genero = genero;
        this.cargo = cargo;
        this.roles = roles;
    }

    
}
