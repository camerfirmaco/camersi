package colombia.authservice.Service.Usuario;

import java.util.List;
import java.util.Optional;

import colombia.authservice.Mapping.Cargo.DtoCreateCargo;
import colombia.authservice.Mapping.Usuario.DtoCreateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUpdateAdmin;
import colombia.authservice.Mapping.Usuario.DtoUpdateUsuario;
import colombia.authservice.Mapping.Usuario.DtoUsuario;
import colombia.authservice.Utils.EnumAcciones;
import colombia.authservice.Utils.EnumRole;

public interface ServiceUsuario {
    Optional<DtoUsuario> consultarId(String id); // CONSULTAR POR ID

    Optional<DtoUsuario> consultarEmail(String email); // CONSULTAR POR EMAIL

    Optional<DtoUsuario> consultarIdentificacion(String identificacion); // CONSULTAR POR IDENTIFICACION

    List<DtoUsuario> listar(); // LISTAR TODOS LOS USUARIOS

    DtoUsuario guardar(DtoCreateUsuario usuario); // GUARDAR USUARIO

    Boolean accion(String id, EnumAcciones accion); // ACTIVAR, BLOQUEAR, HABILITAR, VERIFICAR CORREO, VERIFICAR
                                                    // TELEFONO DEL
    // USUARIO

    Boolean consultarAccion(String id, EnumAcciones accion); // CONSULTAR ACTIVO, BLOQUEADO, HABILITADO, VERIFICADO
                                                             // CORREO,
    // VERIFICADO TELEFONO DEL USUARIO

    Boolean existencia(DtoCreateUsuario usuario); // VERIFICAR EXISTENCIA DE USUARIO

    String guardarCargo(DtoCreateCargo cargo); // GUARDAR CARGO

    Boolean existenciaCargo(DtoCreateCargo cargo); //EXISTENCIA DEL CARGO

    Boolean estadoCargo(String cargo); // CAMBIO DE ESTADO

    List<EnumRole> asignacion(String id, List<EnumRole> roles); //ASIGNACION DE ROLES

    DtoUsuario editar(String id, DtoUpdateUsuario usuario); // EDITAR PERFIL

    DtoUsuario actualizar(String id, DtoUpdateAdmin usuario); // ACTUALIZAR USUARIO

}
