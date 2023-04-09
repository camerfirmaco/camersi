package colombia.authservice.Mapping.Public;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoJwt {
    private String token;
    private String user;
    private Date vencimiento;
    public DtoJwt(String token, String user, Date vencimiento) {
        this.token = token;
        this.user = user;
        this.vencimiento = vencimiento;
    }

    
}
