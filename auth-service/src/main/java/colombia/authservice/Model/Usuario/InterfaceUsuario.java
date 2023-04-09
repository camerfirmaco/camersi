package colombia.authservice.Model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterfaceUsuario extends JpaRepository<EntityUsuario, String> {
    Optional<EntityUsuario> findByIdentificacion(String identificacion);
    Optional<EntityUsuario> findByEmail(String email);
}
