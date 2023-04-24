package colombia.supportservice.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import colombia.supportservice.Model.EntityTypifiend;
import colombia.supportservice.Model.InterfaceTypifiend;

@Service
public class ImpServiceTypifiend implements ServiceTypifiend {

    @Autowired
    private InterfaceTypifiend interfaceTypifiend;

    private List<EntityTypifiend> saveAll(List<EntityTypifiend> entity){
        Iterable<EntityTypifiend> entitys = entity;
        return interfaceTypifiend.saveAll(entitys);
    }
    private EntityTypifiend save(EntityTypifiend entity){
        return interfaceTypifiend.save(entity);
    }

    @Override
    public EntityTypifiend consultarId(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'consultarId'");
    }

    @Override
    public List<EntityTypifiend> consultarUsuario(String usuario) {
        throw new UnsupportedOperationException("Unimplemented method 'consultarUsuario'");
    }

    @Override
    public List<EntityTypifiend> consultarAgente(String agente) {
        throw new UnsupportedOperationException("Unimplemented method 'consultarAgente'");
    }

    @Override
    public List<EntityTypifiend> listar() {
        return interfaceTypifiend.findAll();
    }

    @Override
    public EntityTypifiend editar(String id, EntityTypifiend agente) {
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public EntityTypifiend actualizar(String id, EntityTypifiend usuario) {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }
    @Override
    public EntityTypifiend guardar(EntityTypifiend entity) {
        return save(entity);
    }
    @Override
    public List<EntityTypifiend> guardarAll(List<EntityTypifiend> entity) {
        return saveAll(entity);
    }
    
}
