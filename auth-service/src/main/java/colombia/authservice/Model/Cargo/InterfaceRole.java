package colombia.authservice.Model.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;

import colombia.authservice.Utils.EnumRole;

public interface InterfaceRole extends JpaRepository<EntityRole, Integer>  {
    EntityRole findByRole(EnumRole role);
}
