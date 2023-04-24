package colombia.supportservice.Service;

import java.util.List;

import colombia.supportservice.Model.EntityTypifiend;

public interface ServiceTypifiend {

    EntityTypifiend consultarId(String id); // CONSULTAR POR ID

    List<EntityTypifiend> consultarUsuario(String usuario); // CONSULTAR POR USUARIO

    List<EntityTypifiend> consultarAgente(String agente); // CONSULTAR POR AGENTE

    List<EntityTypifiend> listar(); // LISTAR TODOS

    EntityTypifiend guardar(EntityTypifiend entity); //GUARDAR

    List<EntityTypifiend> guardarAll(List<EntityTypifiend> entity); //GUARDARALL

    EntityTypifiend editar(String id, EntityTypifiend agente); // EDITAR

    EntityTypifiend actualizar(String id, EntityTypifiend usuario); // ACTUALIZAR

}
