package colombia.authservice.Model.Cargo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import colombia.authservice.Model.Usuario.EntityUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cargo")
@Setter
@Getter
@NoArgsConstructor

public class EntityCargo {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // NOMBRE DEL CARGO
    @Column(nullable = false, unique = true)
    private String cargo;

    // DESCRIPCIÓN DE CARGO
    @Column(nullable = true)
    private String descripcion;

    // ESTADO DE ELIMINACION
    @Column(nullable = false)
    private Boolean estado;

    // USUARIO CON EL CARGO
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cargo")
    @JsonBackReference(value = "usuario_cargo")
    private List<EntityUsuario> usuarios;

    public EntityCargo(Integer id, String cargo, String descripcion, Boolean estado, List<EntityUsuario> usuarios) {
        this.id = id;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarios = usuarios;
    }

    public EntityCargo(String cargo, String descripcion, Boolean estado) {
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public EntityCargo(Integer id, String cargo, String descripcion, Boolean estado) {
        this.id = id;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

}
