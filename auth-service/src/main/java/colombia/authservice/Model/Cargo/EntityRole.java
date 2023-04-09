package colombia.authservice.Model.Cargo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import colombia.authservice.Model.Usuario.EntityUsuario;
import colombia.authservice.Utils.EnumRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Setter
@Getter
@NoArgsConstructor
public class EntityRole {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // NOMBRE DEL ROL
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EnumRole role;

    // USUARIOS CON EL ROL
    @ManyToMany(mappedBy = "roles")
    @JsonBackReference(value = "usuario_role")
    private List<EntityUsuario> usuarios;

    public EntityRole(Integer id, EnumRole role, List<EntityUsuario> usuarios) {
        this.id = id;
        this.role = role;
        this.usuarios = usuarios;
    }

    public EntityRole(Integer id, EnumRole role) {
        this.id = id;
        this.role = role;
    }

}
