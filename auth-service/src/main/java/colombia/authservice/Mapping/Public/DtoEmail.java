package colombia.authservice.Mapping.Public;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoEmail {
    @NotEmpty
    @Email
    private String email;

    public DtoEmail(@NotEmpty @Email String email) {
        this.email = email;
    }



}
