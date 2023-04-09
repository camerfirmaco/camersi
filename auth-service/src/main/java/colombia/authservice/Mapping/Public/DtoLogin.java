package colombia.authservice.Mapping.Public;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoLogin {
    @NotEmpty
    @Email
    private String username;
    @NotEmpty
    private String password;
    public DtoLogin(@NotEmpty @Email String username, @NotEmpty String password) {
        this.username = username;
        this.password = password;
    }

}
