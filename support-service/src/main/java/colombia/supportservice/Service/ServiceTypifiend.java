package colombia.supportservice.Service;

import java.util.List;

import colombia.supportservice.Mapping.DtoAgentSupport;
import colombia.supportservice.Mapping.DtoAllTypifiend;
import colombia.supportservice.Mapping.DtoCreateUpdateTypifiend;
import colombia.supportservice.Mapping.DtoPending;
import colombia.supportservice.Model.EntityTypifiend;

public interface ServiceTypifiend {

    List<DtoAllTypifiend> consultarUsuario(String usuario); // CONSULTAR POR USUARIO

    DtoAgentSupport consultarAgente(String agente); // CONSULTAR POR AGENTE

    List<DtoAllTypifiend> listarAll(); // LISTAR TODOS

    List<DtoAllTypifiend> listar(); // LISTAR TODOS ULTIMOS 3 MESES

    List<DtoAllTypifiend> listarEliminados(); // LISTAR TODOS LOS ELIMINADOS

    DtoAllTypifiend guardar(DtoCreateUpdateTypifiend entity); //GUARDAR

    List<EntityTypifiend> guardarAll(List<EntityTypifiend> entity); //GUARDARALL OPCIONAL

    DtoAllTypifiend editar(String id, DtoCreateUpdateTypifiend agente); // EDITAR

    void eliminar(Long id); // ELIMINAR

    List<DtoPending> consultarPending(); //CONSULTAR PENDIENTES
}
