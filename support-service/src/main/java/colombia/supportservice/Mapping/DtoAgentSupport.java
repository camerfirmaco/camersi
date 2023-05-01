package colombia.supportservice.Mapping;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoAgentSupport {
    private String id;
    private String nombre;
    private List<DtoAgenteTypifiend> typifiends;
    public DtoAgentSupport(String id, String nombre, List<DtoAgenteTypifiend> typifiends) {
        this.id = id;
        this.nombre = nombre;
        this.typifiends = typifiends;
    }

    
}
