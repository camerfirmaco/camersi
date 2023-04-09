package colombia.authservice.Mapping.Usuario;

import java.util.List;

import colombia.authservice.Utils.EnumRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class DtoAsignacion {

    @NotNull
    private List<EnumRole> roles;

    @NotNull
    private Integer cargo;

    public DtoAsignacion(@NotNull List<EnumRole> roles, @NotNull Integer cargo) {
        this.roles = roles;
        this.cargo = cargo;
    }
    
}
