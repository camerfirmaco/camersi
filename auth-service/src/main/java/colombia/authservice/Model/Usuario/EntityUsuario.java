package colombia.authservice.Model.Usuario;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import colombia.authservice.Model.Cargo.EntityCargo;
import colombia.authservice.Model.Cargo.EntityRole;
import colombia.authservice.Utils.EnumGenero;
import colombia.authservice.Utils.EnumTipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"identificacion", "email"}))
public class EntityUsuario {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(36)", nullable = false)
    private String id;

    // TIPO DE DOCUMENTO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumTipoDocumento tipoDocumento;

    // IDENTIFICACIÓN
    @Column(nullable = false, unique = true, length = 15, name = "identificacion")
    private String identificacion;

    // EMAIL
    @Column(nullable = false, unique = true, name = "email")
    @NaturalId
    private String email;

    // NOMBRE DEL USUARIO
    @Column(nullable = false, length = 60)
    private String nombre;

    // PRIMER APELLIDO
    @Column(nullable = false, length = 30)
    private String PrimerApellido;

    // SEGUNDO APELLIDO
    @Column(nullable = true, length = 30)
    private String segundoApellido;

    // CONTRASEÑA
    @Column(nullable = false)
    private String password;

    // TELEFONO
    @Column(nullable = true)
    private Long telefono;

    //GENERO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumGenero genero;

    // FOTO DE PERFIL
    @Column(nullable = true)
    private String imagen;

    // USUARIO HABILITADO
    @Column(nullable = false)
    private Boolean habilitado;

    // USUARIO ACTIVO
    @Column(nullable = false)
    private Boolean activo;

    // USUARIO BLOQUEADO
    @Column(nullable = false)
    private Boolean bloqueado;

    // CORREO VERFICADO
    @Column(nullable = false)
    private Boolean verificarEmail;

    // TELEFONO VERIFICADO
    @Column(nullable = false)
    private Boolean verificarTelefono;

    // CARGO DE USUARIO
    @ManyToOne(optional = true)
    @JsonManagedReference(value = "usuario_cargo")
    private EntityCargo cargo;

    // ROLES EN EL SISTEMA
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference(value = "usuario_role")
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
    private List<EntityRole> roles;

    public EntityUsuario(EnumTipoDocumento tipoDocumento, String identificacion, String email, String nombre,
            String primerApellido, String segundoApellido, String password, Long telefono, EnumGenero genero,
            Boolean habilitado, Boolean activo, Boolean bloqueado, Boolean verificarEmail, Boolean verificarTelefono,
            EntityCargo cargo, List<EntityRole> roles) {
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        PrimerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.genero = genero;
        this.habilitado = habilitado;
        this.activo = activo;
        this.bloqueado = bloqueado;
        this.verificarEmail = verificarEmail;
        this.verificarTelefono = verificarTelefono;
        this.cargo = cargo;
        this.roles = roles;
    }

    public EntityUsuario(String id, EnumTipoDocumento tipoDocumento, String identificacion, String email, String nombre,
            String primerApellido, String segundoApellido, String password, Long telefono, EnumGenero genero,
            String imagen, Boolean habilitado, Boolean activo, Boolean bloqueado, Boolean verificarEmail,
            Boolean verificarTelefono, EntityCargo cargo, List<EntityRole> roles) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        PrimerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.genero = genero;
        this.imagen = imagen;
        this.habilitado = habilitado;
        this.activo = activo;
        this.bloqueado = bloqueado;
        this.verificarEmail = verificarEmail;
        this.verificarTelefono = verificarTelefono;
        this.cargo = cargo;
        this.roles = roles;
    }

    public EntityUsuario(EnumTipoDocumento tipoDocumento, String identificacion, String email, String nombre,
            String primerApellido, String segundoApellido, String password, Long telefono, EnumGenero genero,
            Boolean habilitado, Boolean activo, Boolean bloqueado, Boolean verificarEmail, Boolean verificarTelefono) {
        this.tipoDocumento = tipoDocumento;
        this.identificacion = identificacion;
        this.email = email;
        this.nombre = nombre;
        PrimerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.password = password;
        this.telefono = telefono;
        this.genero = genero;
        this.habilitado = habilitado;
        this.activo = activo;
        this.bloqueado = bloqueado;
        this.verificarEmail = verificarEmail;
        this.verificarTelefono = verificarTelefono;
    }


    
}
