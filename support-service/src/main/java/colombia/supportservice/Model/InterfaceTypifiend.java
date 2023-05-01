package colombia.supportservice.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterfaceTypifiend extends JpaRepository<EntityTypifiend, Long>{
    List<EntityTypifiend> findByAgente(String agente);
    List<EntityTypifiend> findByDocumento(String documento);
    List<EntityTypifiend> findByPendiente(Boolean pendiente);
}
