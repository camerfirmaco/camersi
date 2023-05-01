package colombia.authservice.Service.Usuario;

import java.util.List;
import java.util.Optional;

import colombia.authservice.Mapping.Cargo.DtoCreateCargo;
import colombia.authservice.Mapping.Usuario.DtoAcciones;
import colombia.authservice.Mapping.Usuario.DtoAsignacion;
import colombia.authservice.Mapping.Usuario.DtoCreateAdmin;
import colombia.authservice.Mapping.Usuario.DtoCreateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUpdateAdmin;
import colombia.authservice.Mapping.Usuario.DtoUpdateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUsuario;
import colombia.authservice.Model.Cargo.EntityCargo;
import colombia.authservice.Utils.EnumRole;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;

public interface ServiceUsuario {
    Optional<DtoUsuario> consultarId(String id); // CONSULTAR POR ID

    Optional<DtoUsuario> consultarEmail(String email); // CONSULTAR POR EMAIL

    Optional<DtoUsuario> consultarIdentificacion(String identificacion); // CONSULTAR POR IDENTIFICACION

    List<DtoUsuario> listar(); // LISTAR TODOS LOS USUARIOS

    List<DtoUsuario> listarSinAsignacion(); // LISTAR TODOS LOS USUARIOS SIN ASIGNACIÓN

    DtoUsuario guardar(DtoCreateUsuario usuario); // GUARDAR USUARIO

    DtoUsuario guardarAdmin(DtoCreateAdmin usuario)  throws AddressException, MessagingException ; // GUARDAR USUARIO DESDE EL ADMINISTRADOR

    DtoAcciones editarAccion(String id, DtoAcciones accion); // EDITAR ACTIVAR, BLOQUEAR, HABILITAR, VERIFICAR CORREO, VERIFICAR TELEFONO DEL USUARIO

    DtoAcciones consultarAccion(String id); // CONSULTAR ACTIVO, BLOQUEADO, HABILITADO, VERIFICADO CORREO, VERIFICADO TELEFONO DEL USUARIO

    Boolean existencia(DtoCreateUsuario usuario); // VERIFICAR EXISTENCIA DE USUARIO

    String guardarCargo(DtoCreateCargo cargo); // GUARDAR CARGO

    Boolean existenciaCargo(DtoCreateCargo cargo); // EXISTENCIA DEL CARGO

    List<EntityCargo> listarCargo(); // LISTAR CARGOS

    Boolean estadoCargo(String cargo); // CAMBIO DE ESTADO

    List<EnumRole> asignacion(String id, DtoAsignacion asignacion); // ASIGNACION DE ROLES

    DtoUsuario editar(String id, DtoUpdateUsuario usuario); // EDITAR PERFIL

    DtoUsuario actualizar(String id, DtoUpdateAdmin usuario); // ACTUALIZAR USUARIO
}
