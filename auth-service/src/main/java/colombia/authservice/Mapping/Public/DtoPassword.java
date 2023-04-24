package colombia.authservice.Mapping.Public;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoPassword {

    private String identificacion;
    private String email;
    public DtoPassword(String identificacion, String email) {
        this.identificacion = identificacion;
        this.email = email;
    }

    
}
