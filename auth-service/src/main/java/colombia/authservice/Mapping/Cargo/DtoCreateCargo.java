package colombia.authservice.Mapping.Cargo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DtoCreateCargo {

    @NotEmpty
    @Size(min = 3)
    private String cargo;

    private String descripcion;

    public DtoCreateCargo(@NotEmpty @Size(min = 3) String cargo, String descripcion) {
        this.cargo = cargo;
        this.descripcion = descripcion;
    }

}
