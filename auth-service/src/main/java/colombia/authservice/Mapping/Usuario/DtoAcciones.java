package colombia.authservice.Mapping.Usuario;
import java.util.List;

import colombia.authservice.Utils.EnumRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoAcciones {
    private boolean ACTIVADO;
    private boolean HABILITADO;
    private boolean BLOQUEADO;
    private boolean EMAIL;
    private boolean TELEFONO;
    private List<EnumRole> roles;
    public DtoAcciones(boolean aCTIVADO, boolean hABILITADO, boolean bLOQUEADO, boolean eMAIL, boolean tELEFONO,
            List<EnumRole> roles) {
        ACTIVADO = aCTIVADO;
        HABILITADO = hABILITADO;
        BLOQUEADO = bLOQUEADO;
        EMAIL = eMAIL;
        TELEFONO = tELEFONO;
        this.roles = roles;
    }
    

    
}
