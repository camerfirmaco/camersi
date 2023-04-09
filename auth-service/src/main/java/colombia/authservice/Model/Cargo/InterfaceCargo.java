package colombia.authservice.Model.Cargo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterfaceCargo extends JpaRepository<EntityCargo, Long> {
    Optional<EntityCargo> findByCargo(String cargo);
}
