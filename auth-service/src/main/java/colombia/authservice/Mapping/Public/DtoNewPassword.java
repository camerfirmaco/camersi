package colombia.authservice.Mapping.Public;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DtoNewPassword {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String key;
    @NotBlank
    @Size(min = 6)
    private String pin;
    public DtoNewPassword(@NotBlank String id, @NotBlank String password, @NotBlank String key,
            @NotBlank @Size(min = 6) String pin) {
        this.id = id;
        this.password = password;
        this.key = key;
        this.pin = pin;
    }
    
    
    
}
