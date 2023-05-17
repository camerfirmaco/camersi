import { Observaciones, etiqueta } from "./Interfaces";

const getNombre = (usuario:string | undefined) =>{
    if(usuario===undefined || usuario===""){
        return "el usuari@, "
    }else{
        let u:string[] = usuario.split(' ');
        let user:string = u[0].toLowerCase();
        return user[0].toUpperCase() + user.substring(1)+", ";

    }
}

const generarObservacion = (categoria: etiqueta, soporte: etiqueta, certificado: etiqueta, estado: boolean, subSoporte?: etiqueta, usuario?: string, obs1?: Observaciones, obs2?: Observaciones, obs3?: Observaciones, pruebas?: boolean) => {
    let observacion: string = "";
    if (categoria === etiqueta.CALLINPUT) {
        observacion = `Se comunica ${getNombre(usuario)} `;
    } else if (categoria === etiqueta.CALLBACK) {
        observacion = `Se establece comunicación con ${getNombre(usuario)} `;
    } else {
        observacion = "Se brinda soporte."
    }
    switch (soporte) {
        case etiqueta.ACTIVACION:
            observacion = observacion + `indicando que requiere realizar la activación del certificado, ${getConexion(obs1)}${getActivacion(certificado)}${getPruebas(pruebas)}${getObservaciones(obs2)}${getObservacionesExtras(obs3)}${getEstado(estado)}`;
            break;
        case etiqueta.ACOMPANAMIENTO:
            observacion = observacion + `indicando que requiere acompañamiento, ${getAcompanamiento(subSoporte)}${getConexion(obs1)}se brinda el acompañamiento, ${getPruebas(pruebas)}${getObservaciones(obs2)}${getObservacionesExtras(obs3)}${getEstado(estado)}`;
            break;
        case etiqueta.DESBLOQUEO:
            observacion = observacion + `indicando que requiere realizar el proceso de desbloque del certificado, ${getConexion(obs1)}${getDesbloqueo(certificado)}${getPruebas(pruebas)}${getObservaciones(obs2)}${getObservacionesExtras(obs3)}${getEstado(estado)}`;
            break;
        case etiqueta.INSTALACION:
            observacion = observacion + `indicando que requiere realizar el proceso de instalación del certificado, ${getConexion(obs1)}${getInstalacion(certificado)}${getPruebas(pruebas)}${getObservaciones(obs2)}${getObservacionesExtras(obs3)}${getEstado(estado)}`;
            break;
        case etiqueta.CONSULTA:
            observacion = `Se comunica ${getNombre(usuario)}indicando que presenta dudas y preguntas con respecto al certificado, por lo cual se le resuelven en el trascurso de la llamada.`;
            break;
        case etiqueta.OPERACIONES:
            observacion = observacion + `indicando que presenta inconvenientes con el certificado, ${getConexion(obs1)}${getProblema(certificado, subSoporte)}${getPruebas(pruebas)}${getObservaciones(obs2)}${getObservacionesExtras(obs3)}${getEstado(estado)}`;
            break;
        case etiqueta.PROBLEMA:
            observacion = observacion + `indicando que presenta inconvenientes con el certificado, ${getConexion(obs1)}${getProblema(certificado, subSoporte)}${getPruebas(pruebas)}${getObservaciones(obs2)}${getObservacionesExtras(obs3)}${getEstado(estado)}`;
            break;
        case etiqueta.NA:
            break;
        case etiqueta.NO_CONTESTA:
            observacion = `Se intenta establecer comunicación con${getNombre(usuario)}pero no hubo respuesta, se deja mensaje de voz.`
            break;
        case etiqueta.EQUIVOCADO:
            observacion = `Se intenta establecer comunicación con${getNombre(usuario)}pero el numero telefonio es erroneo.`
            break;
        default:
            break;
    }
    console.log(observacion);
    return observacion;
}

const getConexion = (conexion: Observaciones | undefined) => {
    switch (conexion) {
        case Observaciones.REUNION:
            return "se establece una reunión por medio de Microsoft Teams, "
        case Observaciones.ANYDESK:
            return "se establece una conexión remota por AnyDesk al equipo, "
        case Observaciones.TEAMVIEWER:
            return "se establece una conexión remota por medio de TeamViewer al equipo, "
        case Observaciones.LLAMADA:
            return "se le indican los pasos a seguir por medio de la llamada, "
        default:
            return ""
    }
}

const getObservaciones = (obs?: Observaciones) => {

    switch (obs) {
        case Observaciones.GROCESO:
            return " el usuario es groceso en el trascurso del soporte, ";
        case Observaciones.AGRECIVO:
            return " el usuario es agrecivo en el trascurso del soporte, evidenciando la molestia con respecto al proceso el cual esta realizando, ";
        case Observaciones.MOLESTO:
            return " el usuario se evidencia molesto en el trascurso del soporte, ";
        case Observaciones.PC_LENTO:
            return " el equipo en donde se esta realizando el proceso es bastante lento, dificultando más el proceso, ";
        case Observaciones.PROCESO_LENTO:
            return " el proceso es lento ya que el usuario desconoce del proceso a realiazar, ";
        case Observaciones.COLGO:
            return " el usuario cuelga la llamada, ";
        case Observaciones.CAPACITACION:
            return " capacitando asi al usuario con respecto al proceso realizado, ";
        case Observaciones.REINSTALAR:
            return " se realiza la reinstalación de los aplicativos del certificado, ";
        case Observaciones.MAC:
            return " se realiza el proceso en un computador con sistema operativo MacOs, ";
        case Observaciones.DESINSTALACION_OTRA_ENTIDAD:
            return " se realiza la desinstalación de aplicativos no correspondientes al certificado, ";
        case Observaciones.CREDENCIALES:
            return " por motivos de falta de credenciales no es posible continuar con el proceso, ";
        case Observaciones.SIIF:
            return " se realiza el ingreso a SIIF Nación exitosamente, ";
        case Observaciones.SUPER_SALUD:
            return " se realizar el cargue de los archivos a la plataforma de SuperSalud, ";

        default:
            return "";
    }
}

const getObservacionesExtras = (obs?: Observaciones) => {

    switch (obs) {
        case Observaciones.GROCESO:
            return "además, el usuario es groceso en el trascurso del soporte, ";
        case Observaciones.AGRECIVO:
            return "además, el usuario es agrecivo en el trascurso del soporte, evidenciando la molestia con respecto al proceso el cual esta realizando, ";
        case Observaciones.MOLESTO:
            return "además, el usuario se evidencia molesto en el trascurso del soporte, ";
        case Observaciones.PC_LENTO:
            return "además, el equipo en donde se esta realizando el proceso es bastante lento, dificultando más el proceso, ";
        case Observaciones.PROCESO_LENTO:
            return "además, el proceso es lento ya que el usuario desconoce del proceso a realiazar, ";
        case Observaciones.COLGO:
            return "además, el usuario cuelga la llamada, ";
        case Observaciones.CAPACITACION:
            return "además, se capacita al usuario con respecto al proceso realizado, ";
        case Observaciones.REINSTALAR:
            return "además, se realiza la reinstalación de los aplicativos del certificado, ";
        case Observaciones.MAC:
            return "además, se realiza el proceso en un computador con sistema operativo MacOs, ";
        case Observaciones.DESINSTALACION_OTRA_ENTIDAD:
            return "además, se realiza la desinstalación de aplicativos no correspondientes al certificado, ";
        case Observaciones.CREDENCIALES:
            return "además, por motivos de falta de credenciales no es posible continuar con el proceso, ";
        case Observaciones.SIIF:
            return "además, se realiza el ingreso a SIIF Nación exitosamente, ";
        case Observaciones.SUPER_SALUD:
            return "además, se realizar el cargue de los archivos a la plataforma de SuperSalud, ";

        default:
            return "";
    }
}

const getPruebas = (estado: boolean | undefined) => {
    if (estado===true) {
        return "se realizan pruebas de firmado con resultados exitosos, ";
    }
    if (estado===false) {
        return "se realizan pruebas de firmado con resultados fallidos, ";
    }
    return "";
}

const getEstado = (estado: boolean) => {
    if (estado) {
        return "soporte realizado."
    }
    return "soporte sin culminar."
}

const getProblema = (cert: etiqueta | undefined, probl: etiqueta | undefined) => {
    switch (probl) {
        case etiqueta.ERROR_EMISION:
            switch (cert) {
                case etiqueta.TOKEN:
                    return "se presenta un error con el certificado no se encuentra el disposotivo USB, ";
                case etiqueta.CKC:
                    return "se presenta error en la emisión del certificado, los correos de emisión no fueron resibidos, se escala al área de operaciones, ";
                case etiqueta.CAMERCLOUD:
                    return "se presenta error en la emisión del certificado, los correos de emisión no fueron resibidos, se escala al área de operaciones, ";
                case etiqueta.TOP:
                    return "se presenta error en la emisión del certificado, se escala al área de operaciones, ";
                case etiqueta.VUCE:
                    return "se presenta error en la emisión del certificado, se escala al área de operaciones para un proceso de depuración, se le indica al usuario el lapso de tiempo con respecto a este proceso, se le comentan las recomendaciones al rellenado formulario, ";
                default:
                    return "";
            }
        case etiqueta.CRED_OLVIDO:
            switch (cert) {
                case etiqueta.CKC:
                    return "se presenta inconvenientes, ya que el usuario cambio el PIN del certificado y no lo recuerda, se escala al área comercial, los cuales le indicaran al usuario el paso a seguir, ";
                case etiqueta.TOP:
                    return "se presenta inconvenientes, ya que el usuario no lo recuerda o no asigno el PIN del certificado, se escala al área comercial, los cuales le indicaran al usuario el paso a seguir, ";
                case etiqueta.VUCE:
                    return "se presenta inconvenientes, ya que el usuario no lo recuerda o no asigno el PIN del certificado en la activación, se escala al área de operaciones, para realizar el proceso de depuración, se le indica al usuario el lapso de tiempo con respecto a este proceso, se le comentan las recomendaciones al rellenado formulario, ";

                default:
                    return "";
            }
        case etiqueta.CRED_RECIBIDA:
            switch (cert) {
                case etiqueta.CKC:
                    return "se presenta inconvenientes, el correo con la credenciales no fue recibido, se escala al área de operaciones se le indica al usuario que recibira correos depuración y posteriormente los correos con la información pertinente, ";
                default:
                    return "";
            }
        case etiqueta.REENVIAR:
            switch (cert) {
                case etiqueta.VUCE:
                    return "se realiza el reenvio del correo de activación, se escala al área de operaciones, el usuario confirma el correo recibido, ";
                default:
                    return "";
            }
        case etiqueta.DEPURACION:
            switch (cert) {
                case etiqueta.VUCE:
                    return "se escala al área de operaciones para realizar un proceso de depuración, por motivo de (olvido de PIN, enlace de activación ya utilizado, error intententelo más tarde, cambio de información del formulario,) se le indica al usuario el tiempo estimado frente a este proceso, se le indica las recomendaciones con respecto al rellenado del firmulrio y el paso a seguir, ";
                default:
                    return "";
            }
        case etiqueta.PROCESO:
            return "se le indica que el certificado se encuentra en cola de emisión, se le indica el lapso de tiempo estimado para finalizar el proceso de emisión, ";
        default:
            return "";
    }
}

const getInstalacion = (cert: etiqueta | undefined) => {
    switch (cert) {
        case etiqueta.TOKEN:
            return "se realiza la instalación de la ruta de certificación y el aplicativo epass2003, se realiza el cambio de PIN donde el usuario digita uno nuevo, ";
        case etiqueta.CKC:
            return "se realiza la instalación de la ruta de certificación y el aplicativo KeyController, se realiza la configuración del aplicativo, ";
        case etiqueta.CAMERCLOUD:
            return "se realiza la instalación de la ruta de certificación y se descarga e instala el aplicativo CamerCloud-Desktop, se realiza la configuración del aplicativo, ";
        case etiqueta.TOP:
            return "se realiza la instalación de la ruta de certificación y el aplicativo GoSing-Desktop, se realiza la configuración del aplicativo, ";

        default:
            return "";
    }
}

const getDesbloqueo = (cert: etiqueta | undefined) => {
    switch (cert) {
        case etiqueta.TOKEN:
            return "se ejecuta el aplicativo epass2003Admin, seguido a esto se hace uso del PUK, el usuario asigna un nuevo PIN al certificado, ";
        case etiqueta.CAMERCLOUD:
            return "se realiza el desbloqueo del factor de autenticación OTP en el sistema, se vincula la aplicación movil del telefono del usuario con la plataforma, con se configura el aplicativo CamerCloud-Desktop,";
        default:
            return "";
    }
}

const getActivacion = (cert: etiqueta | undefined) => {
    switch (cert) {
        case etiqueta.TOKEN:
            return "se realiza la instalación de la ruta de certificación y el aplicativo epass2003, se realiza el cambio de PIN donde el usuario digita uno nuevo ";
        case etiqueta.CKC:
            return "se realiza la instalación de la ruta de certificación y el aplicativo KeyController, se realiza la configuración del aplicativo, ";
        case etiqueta.CAMERCLOUD:
            return "se realiza la activación del usuario, se asigna la contraseña y se selecciona los factores de autenticación, se vincula la aplicación movil del telefono del usuario con el factor OTP, se instalación de la ruta de certificación y se descarga e instala el aplicativo CamerCloud-Desktop, se realiza la configuración del aplicativo, ";
        case etiqueta.TOP:
            return "se realiza la instalación de la ruta de certificación y el aplicativo GoSing-Desktop, se realiza la configuración del aplicativo, ";
        case etiqueta.VUCE:
            return "se ingresa al enlace de activación, se continua con el proceso, se asigna el PIN del certificado, se resibe el codigo OPT, se culmina el proceso, ";

        default:
            return "";
    }
}

const getAcompanamiento = (sub: etiqueta | undefined) =>{
    switch (sub) {
        case etiqueta.BIOMETRIA:
            return "en el proceso biometrico para la plataforma del VUCE, indicandole los pasos a seguir para la solicitud, en el proceso de rellenado del formulario y el paso a seguir despues de emisión, ";
        case etiqueta.FIRMADO:
            return "en un proceso de firmado, indicando los pasos a seguir en el proceso de firmado de algun archivo, ";
        case etiqueta.PROCESO:
            return "en un proceso acorde al certificado obtenido, ";
        default:
            return "";
    }
}
export { generarObservacion }