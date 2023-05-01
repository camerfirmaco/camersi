package colombia.supportservice.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import colombia.supportservice.Connection.FeignUser;
import colombia.supportservice.Mapping.DtoAgentSupport;
import colombia.supportservice.Mapping.DtoAgenteSupport;
import colombia.supportservice.Mapping.DtoAgenteTypifiend;
import colombia.supportservice.Mapping.DtoAllTypifiend;
import colombia.supportservice.Mapping.DtoCreateUpdateTypifiend;
import colombia.supportservice.Mapping.DtoPending;
import colombia.supportservice.Model.EntityTypifiend;
import colombia.supportservice.Model.InterfaceTypifiend;

@Service
public class ImpServiceTypifiend implements ServiceTypifiend {

    @Autowired
    private InterfaceTypifiend interfaceTypifiend;

    @Autowired
    private FeignUser feignUser;

    // GUARDADO MASIVO
    private List<EntityTypifiend> saveAll(List<EntityTypifiend> entity) {
        Iterable<EntityTypifiend> entitys = entity;
        return interfaceTypifiend.saveAll(entitys);
    }

    // ENTITY A TYPIFIEND ALL
    private DtoAllTypifiend tipifiendAll(EntityTypifiend entity) {
        DtoAllTypifiend dto = new DtoAllTypifiend(entity.getId(), entity.getEmision(), entity.getDocumento(),
                entity.getNombre(), entity.getTelefono(), entity.getCompany(), entity.getEmail(),
                entity.getCetificado(), entity.getSoporte(), entity.getSubSoporte(), entity.getFecha(),
                entity.getHora(), entity.getObservacion(), entity.getEstado(), entity.getTiempo(),
                entity.getCategoria(), entity.getAgente());
        return dto;
    }

    // ENTITY A TYPIFIEND AGENTE
    private DtoAgenteTypifiend typifiendAgente(EntityTypifiend entity) {
        DtoAgenteTypifiend dto = new DtoAgenteTypifiend(entity.getId(), entity.getEmision(), entity.getDocumento(),
                entity.getNombre(), entity.getTelefono(), entity.getCompany(), entity.getEmail(),
                entity.getCetificado(), entity.getSoporte(), entity.getSubSoporte(), entity.getFecha(),
                entity.getHora(), entity.getObservacion(), entity.getEstado(), entity.getTiempo(),
                entity.getCategoria());
        return dto;
    }

    // ENTITY A PENDING
    private DtoPending pending(EntityTypifiend entity) {
        DtoPending dto = new DtoPending(entity.getId(), entity.getEmision(), entity.getDocumento(), entity.getNombre(),
                entity.getTelefono(), entity.getCompany(), entity.getEmail(), entity.getCetificado(),
                entity.getSoporte(), entity.getSubSoporte(), entity.getFecha(), entity.getHora(),
                entity.getObservacion(), entity.getEstado(), entity.getTiempo(), entity.getCategoria(),
                entity.getAgente(), entity.getPendiente(), entity.getGuia());
        return dto;
    }

    // CREAR O ACTUALIZAR
    private EntityTypifiend createTypifiend(DtoCreateUpdateTypifiend dto) {
        if (dto.getId() == null) {
            EntityTypifiend entity = new EntityTypifiend(dto.getEmision(), dto.getDocumento(), dto.getNombre(),
                    dto.getTelefono(), dto.getCompany(), dto.getEmail(), dto.getCetificado(), dto.getSoporte(),
                    dto.getSubSoporte(), dto.getFecha(), dto.getHora(), dto.getObservacion(), dto.getEstado(),
                    dto.getTiempo(), dto.getCategoria(), dto.getAgente(), false, false, null);
            return entity;
        }
        EntityTypifiend entityBD = interfaceTypifiend.findById(dto.getId()).get();
        EntityTypifiend entity = new EntityTypifiend(entityBD.getId(), dto.getEmision(), dto.getDocumento(), dto.getNombre(),
                dto.getTelefono(), dto.getCompany(), dto.getEmail(), dto.getCetificado(), dto.getSoporte(),
                dto.getSubSoporte(), dto.getFecha(), dto.getHora(), dto.getObservacion(), dto.getEstado(),
                dto.getTiempo(), dto.getCategoria(), entityBD.getAgente(), entityBD.getEliminado(), entityBD.getPendiente(), entityBD.getGuia());
        return entity;
    }

    // GET FECHA MENOS LOS MESES
    private Date getFecha(int meses) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, meses);
        Date fecha = c.getTime();
        return fecha;
    }

    // CONSULTAR HISTORIAL DE USUARIO EN ATENCIÓN
    @Override
    public List<DtoAllTypifiend> consultarUsuario(String usuario) {
        Iterator<EntityTypifiend> i = interfaceTypifiend.findByDocumento(usuario).iterator();
        List<DtoAllTypifiend> dtos = new ArrayList<DtoAllTypifiend>();
        while (i.hasNext()) {
            EntityTypifiend entity = i.next();
            if (entity.getFecha().getTime() >= getFecha(-6).getTime()) {
                if (!entity.getEliminado()) {
                    dtos.add(tipifiendAll(entity));
                }
            }
        }
        return dtos;
    }

    // CONSULTAR TIPIFICADO POR AGENTE
    @Override
    public DtoAgentSupport consultarAgente(String agente) {
        Iterator<EntityTypifiend> i = interfaceTypifiend.findByAgente(agente).iterator();
        List<DtoAgenteTypifiend> dtos = new ArrayList<DtoAgenteTypifiend>();
        DtoAgenteSupport agentSupport = feignUser.getAgentSupport(agente);
        while (i.hasNext()) {
            EntityTypifiend entity = i.next();
            if (entity.getFecha().getTime() >= getFecha(-3).getTime()) {
                if (!entity.getEliminado()) {
                    dtos.add(typifiendAgente(entity));
                }
            }
        }
        return new DtoAgentSupport(agentSupport.getId(), agentSupport.getNombre(), dtos);
    }

    // LISTAR TODOS
    @Override
    public List<DtoAllTypifiend> listar() {
        Iterator<EntityTypifiend> i = interfaceTypifiend.findAll().iterator();
        List<DtoAllTypifiend> dtos = new ArrayList<DtoAllTypifiend>();
        while (i.hasNext()) {
            EntityTypifiend entity = i.next();

            if (entity.getFecha().getTime() >= getFecha(-3).getTime()) {
                if (!entity.getEliminado()) {
                    dtos.add(tipifiendAll(entity));
                }
            }
        }
        return dtos;
    }

    // GUARDAR TIPIFICADO
    @Override
    public DtoAllTypifiend guardar(DtoCreateUpdateTypifiend entity) {
        EntityTypifiend agent = createTypifiend(entity);
        return tipifiendAll(interfaceTypifiend.save(agent));
    }

    // CARGA MASIVA DE DATOS
    @Override
    public List<EntityTypifiend> guardarAll(List<EntityTypifiend> entity) {
        return saveAll(entity);
    }


    //EDITAR TYPIFIEND
    @Override
    public DtoAllTypifiend editar(String id, DtoCreateUpdateTypifiend agente) {
        EntityTypifiend agent = createTypifiend(agente);
        if (id != agent.getAgente())
            return null;
        return tipifiendAll(interfaceTypifiend.save(agent));
    }

    //ELIMINAR TYPIFIEND
    @Override
    public void eliminar(Long id) {
        EntityTypifiend typifiend = interfaceTypifiend.findById(id).get();
        if (typifiend != null){
            typifiend.setEliminado(true);
            interfaceTypifiend.save(typifiend);
        }

    }

    // CONSULTAR PENDIENTES
    @Override
    public List<DtoPending> consultarPending() {
        Iterator<EntityTypifiend> i = interfaceTypifiend.findByPendiente(true).iterator();
        List<DtoPending> dtos = new ArrayList<DtoPending>();
        while (i.hasNext()) {
            EntityTypifiend entity = i.next();
            dtos.add(pending(entity));
        }
        return dtos;
    }

    //CONSULTAR TODOS EL ULTIMO AÑO
    @Override
    public List<DtoAllTypifiend> listarAll() {
        Iterator<EntityTypifiend> i = interfaceTypifiend.findAll().iterator();
        List<DtoAllTypifiend> dtos = new ArrayList<DtoAllTypifiend>();
        while (i.hasNext()) {
            EntityTypifiend entity = i.next();

            if (entity.getFecha().getTime() >= getFecha(-12).getTime()) {
                if (!entity.getEliminado()) {
                    dtos.add(tipifiendAll(entity));
                }
            }
        }
        return dtos;
    }

    // LISTAR TODOS LOS ELIMINADOS EL ULTIMO AÑO
    @Override
    public List<DtoAllTypifiend> listarEliminados() {
        Iterator<EntityTypifiend> i = interfaceTypifiend.findAll().iterator();
        List<DtoAllTypifiend> dtos = new ArrayList<DtoAllTypifiend>();
        while (i.hasNext()) {
            EntityTypifiend entity = i.next();

            if (entity.getFecha().getTime() >= getFecha(-12).getTime()) {
                if (entity.getEliminado()) {
                    dtos.add(tipifiendAll(entity));
                }
            }
        }
        return dtos;
    }

}
