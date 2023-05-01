package colombia.authservice.Mapping.Usuario;

import java.util.List;

import colombia.authservice.Utils.EnumRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class DtoAsignacion {
    private List<EnumRole> roles;
    private String cargo;
    public DtoAsignacion(List<EnumRole> roles, String cargo) {
        this.roles = roles;
        this.cargo = cargo;
    }
    
    
}
