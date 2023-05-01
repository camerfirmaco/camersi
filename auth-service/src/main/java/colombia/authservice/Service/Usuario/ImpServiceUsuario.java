package colombia.authservice.Service.Usuario;

import java.security.SecureRandom;
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
import colombia.authservice.Mapping.Usuario.DtoAcciones;
import colombia.authservice.Mapping.Usuario.DtoAsignacion;
import colombia.authservice.Mapping.Usuario.DtoCreateAdmin;
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
import colombia.authservice.Utils.EnumRole;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import colombia.authservice.Service.Email.EnvioEmail;

@Service
public class ImpServiceUsuario implements ServiceUsuario {

    @Autowired
    private InterfaceUsuario interfaceUsuario;

    @Autowired
    private InterfaceCargo interfaceCargo;

    @Autowired
    private InterfaceRole interfaceRole;

    @Autowired
    private EnvioEmail EnvioEmail;

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
                DTO.getPrimerApellido(), DTO.getSegundoApellido(), password.encode(DTO.getPassword()),
                DTO.getTelefono(),
                DTO.getGenero(), false, false, false, true, false);

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

    // CONSULTAR TODOS LOS USUARIOS
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
    public DtoAcciones editarAccion(String id, DtoAcciones accion) {
        if (consultarId(id).isPresent()) {
            EntityUsuario usuario = interfaceUsuario.getReferenceById(id);
            usuario.setActivo(accion.isACTIVADO());
            usuario.setBloqueado(accion.isBLOQUEADO());
            usuario.setHabilitado(accion.isBLOQUEADO());
            usuario.setRoles(entityRoles(accion.getRoles()));
            interfaceUsuario.save(usuario);
            return accion;
        }
        return null;
    }

    // CONSULTAR ACCIONES DEL USUARIO
    @Override
    public DtoAcciones consultarAccion(String id) {
        if (consultarId(id).isPresent()) {
            EntityUsuario usuario = interfaceUsuario.getReferenceById(id);
            return new DtoAcciones(usuario.getActivo(), usuario.getHabilitado(), usuario.getBloqueado(),
                    usuario.getVerificarEmail(), usuario.getVerificarTelefono(), EnumRoles(usuario.getRoles()));
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

    // DE ENTITY A ENUM
    private List<EntityRole> entityRoles(List<EnumRole> roles){
        List<EntityRole> role = new ArrayList<EntityRole>();
        if (roles.isEmpty()) {
            return null;
        } else {
            Iterator<EnumRole> i = roles.iterator();
            while (i.hasNext()) {
                role.add(interfaceRole.findByRole(i.next()));
            }
            return role;
        }
    }
    
    // DE ENTITY A ENUM
    private List<EnumRole> EnumRoles(List<EntityRole> roles){
        List<EnumRole> role = new ArrayList<EnumRole>();
        if (roles.isEmpty()) {
            return null;
        } else {
            Iterator<EntityRole> i = roles.iterator();
            while (i.hasNext()) {
                EntityRole rol = i.next();
                role.add(rol.getRole());
            }
            return role;
        }
    }
    
    // ASIGNACION DE ROLES
    @Override
    public List<EnumRole> asignacion(String id, DtoAsignacion asignacion) {
        List<EntityRole> role = entityRoles(asignacion.getRoles());
        EntityCargo cargo = interfaceCargo.findByCargo(asignacion.getCargo()).get();
            Optional<EntityUsuario> usuario = interfaceUsuario.findById(id);
            usuario.get().setRoles(role);
            usuario.get().setActivo(true);
            usuario.get().setHabilitado(true);
            usuario.get().setBloqueado(true);
            usuario.get().setCargo(cargo);
            interfaceUsuario.save(usuario.get());
        return asignacion.getRoles();
    }

    // EDITAR PERFIL
    @Override
    public DtoUsuario editar(String id, DtoUpdateUsuario usuario) {
        EntityUsuario entity = interfaceUsuario.findById(id).get();
        entity.setNombre(usuario.getNombre());
        entity.setPrimerApellido(usuario.getPrimerApellido());
        entity.setSegundoApellido(usuario.getSegundoApellido());
        entity.setEmail(usuario.getEmail());
        entity.setTelefono(usuario.getTelefono());
        entity.setGenero(usuario.getGenero());
        entity.setImagen(usuario.getImagen());

        DtoUsuario DTO = entityUsuario(interfaceUsuario.save(entity));
        return DTO;
    }

    // ACTUALIZAR USUARIO
    @Override
    public DtoUsuario actualizar(String id, DtoUpdateAdmin usuario) {
        EntityUsuario entity = interfaceUsuario.findById(id).get();
        entity.setTipoDocumento(usuario.getTipoDocumento());
        entity.setIdentificacion(usuario.getIdentificacion());
        entity.setNombre(usuario.getNombre());
        entity.setEmail(usuario.getEmail());
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

    // CONSULTAR USUARIOS SIN ASIGNACION
    @Override
    public List<DtoUsuario> listarSinAsignacion() {
        List<EntityUsuario> lista = interfaceUsuario.findAll();
        Iterator<EntityUsuario> i = lista.iterator();
        List<DtoUsuario> Dtos = new ArrayList<DtoUsuario>();
        while (i.hasNext()) {
            EntityUsuario usuario = i.next();
            if (usuario.getRoles() != null && usuario.getCargo() != null)
                Dtos.add(entityUsuario(usuario));
        }
        return Dtos;
    }

    // REGISTRAR USUARIO COMO ADMINISTRADOR
    @Override
    public DtoUsuario guardarAdmin(DtoCreateAdmin usuario) throws AddressException, MessagingException {
        // CREAR CONTRASEÑA
        String passwordString = generateRandomPassword();
        // ENVIAR CORREO CON CREDENCIALES
        EnvioEmail.sendEmailPassword(usuario.getEmail(), passwordString);
        // LISTA DE EMUM DE ROLES
        List<EnumRole> roles = usuario.getRoles();
        // BUSQUEDA DE CARGO PARA CREACION
        EntityCargo cargo = interfaceCargo.findByCargo(usuario.getCargo()).get();
        // ROLES FINALES PARA REGISTRO
        List<EntityRole> role = new ArrayList<EntityRole>();
        Iterator<EnumRole> i = roles.iterator();
        while (i.hasNext()) {
            role.add(interfaceRole.findByRole(i.next()));
        }

        EntityUsuario user = new EntityUsuario(usuario.getTipoDocumento(), usuario.getIdentificacion(),
                usuario.getEmail(), usuario.getEmail(), usuario.getNombre(), usuario.getSegundoApellido(),
                password.encode(passwordString),
                usuario.getTelefono(), usuario.getGenero(), true, true, true, true, false, cargo, role);
        DtoUsuario DTO = entityUsuario(interfaceUsuario.save(user));
        return DTO;
    }

    // CREAR CONTRASEÑA RANDOM
    private String generateRandomPassword() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
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

    // CAMBIO DE ESTADO DEL CARGO ELIMINADO
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

    @Override
    public List<EntityCargo> listarCargo() {
        List<EntityCargo> lista = interfaceCargo.findAll();
        Iterator<EntityCargo> i = lista.iterator();
        List<EntityCargo> list = new ArrayList<EntityCargo>();
        while (i.hasNext()) {
            EntityCargo cargo = i.next();
            if (!cargo.getEstado())
                list.add(cargo);
        }
        return list;
    }

}
