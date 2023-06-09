package colombia.authservice.Mapping.Usuario;
import colombia.authservice.Utils.EnumGenero;
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
public class DtoUpdateUsuario {

    // NOMBRE DEL USUARIO
    @Size(max = 60, min = 2)
    private String nombre;

    // PRIMER APELLIDO
    @Size(max = 30, min = 2)
    private String primerApellido;

    // SEGUNDO APELLIDO
    @Size(max = 30, min = 2)
    private String segundoApellido;

    // EMAIL
    @NotEmpty
    @Email
    @Size(min = 10)
    private String email;

    // TELEFONO
    private Long telefono;

    //GENERO
    @NotNull
    private EnumGenero genero;

    //IMAGEN
    private String imagen;

    public DtoUpdateUsuario(@Size(max = 60, min = 2) String nombre, @Size(max = 30, min = 2) String primerApellido,
            @Size(max = 30, min = 2) String segundoApellido, @NotEmpty @Email @Size(min = 10) String email,
            Long telefono, @NotNull EnumGenero genero, String imagen) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.email = email;
        this.telefono = telefono;
        this.genero = genero;
        this.imagen = imagen;
    }

    
    
}
