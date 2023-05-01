package colombia.authservice.Router;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import colombia.authservice.Mapping.Public.RequestDto;

@Service
public class RouteValidator {
    @Autowired
    private RouterAgenteSupport agenteSupport; // AGENTE DE SOPORTE

    @Autowired
    private RouterSuperAdmin superAdmin; // ADMINISTRADOR DEL SISTEMA

    @Autowired
    private RouterSupervisorSupport supervisorSupport; // SUPERVISOR DE SOPORTE

    @Autowired
    private RouterUserAdmin userAdmin; // ADMINISTRADOR DE USUARIOS

    @Autowired
    private RouterUser user; // USUARIOS

    public boolean isRoute(RequestDto dto, ArrayList<String> roles) {
        for (String string : roles) {
            if(isRole(string, dto)){
                return true;
            }
        }
        return false;
    }

    private boolean isRole(String role, RequestDto dto) {

        switch (role) { // SI EL ROL ES, VERIFICA RUTA
            case "USER": 
                return user.isPath(dto);
            case "ADMIN_USERS":
                return userAdmin.isPath(dto);
            case "AGENTE_SUPPORT":
                return agenteSupport.isPath(dto);
            case "SUPERVISOR_SUPPORT":
                return supervisorSupport.isPath(dto);
            case "SUPER_ADMIN":
                return superAdmin.isPath(dto);
            default:
                return false;
        }
    }

}
