package colombia.authservice.Service.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import colombia.authservice.Mapping.Cargo.DtoCreateCargo;
import colombia.authservice.Mapping.Usuario.DtoCreateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUpdateAdmin;
import colombia.authservice.Mapping.Usuario.DtoUpdateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUsuario;
import colombia.authservice.Model.Cargo.EntityCargo;
import colombia.authservice.Model.Cargo.EntityRole;
import colombia.authservice.Model.Cargo.InterfaceCargo;
import colombia.authservice.Model.Cargo.InterfaceRole;
import colombia.authservice.Model.Usuario.EntityUsuario;
import colombia.authservice.Model.Usuario.InterfaceUsuario;
import colombia.authservice.Utils.EnumAcciones;
import colombia.authservice.Utils.EnumRole;

@Service
public class ImpServiceUsuario implements ServiceUsuario {

    @Autowired
    private InterfaceUsuario interfaceUsuario;

    @Autowired
    private InterfaceCargo interfaceCargo;

    @Autowired
    private InterfaceRole interfaceRole;

    private PasswordEncoder password = new BCryptPasswordEncoder();

    // METODOS PRIVADOS
    // DE ENTIDAD A DTO
    private DtoUsuario entityUsuario(EntityUsuario entity) {
        String cargo;
        if (entity.getCargo() == null) {
            cargo = "Sin cargo";
        } else {
            cargo = entity.getCargo().getCargo();
        }
        DtoUsuario DTO = new DtoUsuario(
                entity.getId().toString(), entity.getTipoDocumento().toString(), entity.getIdentificacion(),
                entity.getEmail(),
                entity.getNombre(), entity.getPrimerApellido(), entity.getSegundoApellido(), entity.getTelefono(),
                entity.getGenero().toString(), entity.getImagen(), cargo);
        return DTO;

    }

    // DE CREATE A ENTITY
    private EntityUsuario createUsuario(DtoCreateUsuario DTO) {
        EntityUsuario entity = new EntityUsuario(DTO.getTipoDocumento(), DTO.getIdentificacion(), DTO.getEmail(),
                DTO.getNombre(),
                DTO.getPrimerApellido(), DTO.getSegundoApellido(), password.encode(DTO.getPassword()), DTO.getTelefono(),
                DTO.getGenero(), false, false, false, false, false);

        return entity;

    }

    // METODOS PUBLICOS
    // CONSULTAR USUARIO POR ID
    @Override
    public Optional<DtoUsuario> consultarId(String id) {
        Optional<EntityUsuario> opcional = interfaceUsuario.findById(id);
        Optional<DtoUsuario> DTO = Optional.empty();
        if (opcional.isPresent()) {
            DTO = Optional.of(entityUsuario(opcional.get()));
            return DTO;
        }
        return DTO;
    }

    // CONSULTAR USUARIO POR CORREO ELECTRONICO
    @Override
    public Optional<DtoUsuario> consultarEmail(String email) {
        Optional<EntityUsuario> opcional = interfaceUsuario.findByEmail(email);
        Optional<DtoUsuario> DTO = Optional.empty();
        if (opcional.isPresent()) {
            DTO = Optional.of(entityUsuario(opcional.get()));
            return DTO;
        }
        return DTO;
    }

    // CONSULTAR USUARIO POR IDENTIFICACION
    @Override
    public Optional<DtoUsuario> consultarIdentificacion(String identificacion) {
        Optional<EntityUsuario> opcional = interfaceUsuario.findByIdentificacion(identificacion);
        Optional<DtoUsuario> DTO = Optional.empty();
        if (opcional.isPresent()) {
            DTO = Optional.of(entityUsuario(opcional.get()));
            return DTO;
        }
        return DTO;
    }

    //CONSULTAR TODOS LOS USUARIOS
    @Override
    public List<DtoUsuario> listar() {
        List<EntityUsuario> lista = interfaceUsuario.findAll();
        Iterator<EntityUsuario> i = lista.iterator();
        List<DtoUsuario> Dtos = new ArrayList<DtoUsuario>();
        while (i.hasNext()) {
            EntityUsuario usuario = i.next();
            Dtos.add(entityUsuario(usuario));
        }
        return Dtos;
    }

    // GUARDAR USUARIO
    @Transactional
    @Override
    public DtoUsuario guardar(DtoCreateUsuario usuario) {
        EntityUsuario entity = interfaceUsuario.save(createUsuario(usuario));
        DtoUsuario DTO = entityUsuario(entity);
        return DTO;
    }

    // MODIFICAR ACCIONES DEL USUARIO
    @Override
    public Boolean accion(String id, EnumAcciones accion) {
        if (consultarId(id).isPresent()) {
            EntityUsuario usuario = interfaceUsuario.getReferenceById(id);
            EntityUsuario guardado = new EntityUsuario();
            switch (accion) {
                case ACTIVADO:
                    usuario.setActivo(!usuario.getActivo());
                    guardado = interfaceUsuario.save(usuario);
                    return guardado.getActivo();
                case HABILITADO:
                    usuario.setHabilitado(!usuario.getHabilitado());
                    guardado = interfaceUsuario.save(usuario);
                    return guardado.getHabilitado();
                case BLOQUEADO:
                    usuario.setBloqueado(!usuario.getBloqueado());
                    guardado = interfaceUsuario.save(usuario);
                    return guardado.getBloqueado();
                case EMAIL:
                    usuario.setVerificarEmail(!usuario.getVerificarEmail());
                    guardado = interfaceUsuario.save(usuario);
                    return guardado.getVerificarEmail();
                case TELEFONO:
                    usuario.setVerificarTelefono(!usuario.getVerificarTelefono());
                    guardado = interfaceUsuario.save(usuario);
                    return guardado.getVerificarTelefono();
                default:
                    return null;
            }

        }
        return null;
    }

    // CONSULTAR ACCIONES DEL USUARIO
    @Override
    public Boolean consultarAccion(String id, EnumAcciones accion) {
        if (consultarId(id).isPresent()) {
            EntityUsuario usuario = interfaceUsuario.getReferenceById(id);
            switch (accion) {
                case ACTIVADO:
                    return usuario.getActivo();
                case HABILITADO:
                    return usuario.getHabilitado();
                case BLOQUEADO:
                    return usuario.getBloqueado();
                case EMAIL:
                    return usuario.getVerificarEmail();
                case TELEFONO:
                    return usuario.getVerificarTelefono();
                default:
                    return null;
            }

        }
        return null;
    }

    // EXISTENCIA DEL USUARIO
    @Override
    public Boolean existencia(DtoCreateUsuario usuario) {
        if (consultarIdentificacion(usuario.getIdentificacion()).isPresent()) {
            return true;
        } else if (consultarEmail(usuario.getEmail()).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    // ASIGNACION DE ROLES
    @Override
    public List<EnumRole> asignacion(String id, List<EnumRole> roles) {

        List<EntityRole> role = new ArrayList<EntityRole>();
        if (roles.isEmpty()) {
            Optional<EntityUsuario> usuario = interfaceUsuario.findById(id);
            usuario.get().setRoles(role);
            interfaceUsuario.save(usuario.get());
            return roles;
        } else {
            Iterator<EnumRole> i = roles.iterator();
            while (i.hasNext()) {
                role.add(interfaceRole.findByRole(i.next()));
            }
            Optional<EntityUsuario> usuario = interfaceUsuario.findById(id);
            usuario.get().setRoles(role);
            interfaceUsuario.save(usuario.get());
        }
        return roles;
    }

    // EDITAR PERFIL
    @Override
    public DtoUsuario editar(String id, DtoUpdateUsuario usuario) {
        EntityUsuario entity = interfaceUsuario.findById(id).get();
        entity.setNombre(usuario.getNombre());
        entity.setPrimerApellido(usuario.getPrimerApellido());
        entity.setSegundoApellido(usuario.getSegundoApellido());
        entity.setTelefono(usuario.getTelefono());
        entity.setGenero(usuario.getGenero());
        entity.setImagen(usuario.getImagen());

        DtoUsuario DTO = entityUsuario(interfaceUsuario.save(entity));
        return DTO;
    }

    //ACTUALIZAR USUARIO
    @Override
    public DtoUsuario actualizar(String id, DtoUpdateAdmin usuario) {
        EntityUsuario entity = interfaceUsuario.findById(id).get();
        entity.setTipoDocumento(usuario.getTipoDocumento());
        entity.setIdentificacion(usuario.getIdentificacion());
        entity.setNombre(usuario.getNombre());
        entity.setPrimerApellido(usuario.getPrimerApellido());
        entity.setSegundoApellido(usuario.getSegundoApellido());
        entity.setTelefono(usuario.getTelefono());
        entity.setGenero(usuario.getGenero());
        entity.setHabilitado(usuario.getHabilitado());
        EntityCargo cargo = interfaceCargo.findByCargo(usuario.getCargo()).get();
        entity.setCargo(cargo);

        DtoUsuario DTO = entityUsuario(interfaceUsuario.save(entity));
        return DTO;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    // <---CARGO---->//
    /////////////////////////////////////////////////////////////////////////////////////////////

    // REGISTRAR CARGO
    @Override
    public String guardarCargo(DtoCreateCargo cargo) {
        Optional<EntityCargo> opcinal = interfaceCargo.findByCargo(cargo.getCargo());
        if (opcinal.isPresent()) {
            if (opcinal.get().getEstado()) {
                return null;
            } else {
                estadoCargo(opcinal.get().getCargo());
                return opcinal.get().getCargo();
            }
        } else {
            EntityCargo entity = interfaceCargo.save(new EntityCargo(cargo.getCargo(), cargo.getDescripcion(), true));
            return entity.getCargo();
        }
    }

    // EXISTENCIA DEL CARGO
    @Override
    public Boolean existenciaCargo(DtoCreateCargo cargo) {
        Optional<EntityCargo> opcinal = interfaceCargo.findByCargo(cargo.getCargo());
        if (opcinal.isPresent()) {
            if (opcinal.get().getEstado()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // CAMBIO DE ESTADO DEL CARGO
    @Override
    public Boolean estadoCargo(String cargo) {
        Optional<EntityCargo> entity = interfaceCargo.findByCargo(cargo);
        if (entity.isPresent()) {
            entity.get().setEstado(!entity.get().getEstado());
            EntityCargo resultado = interfaceCargo.save(null);
            return resultado.getEstado();
        } else {
            return null;
        }
    }
}
