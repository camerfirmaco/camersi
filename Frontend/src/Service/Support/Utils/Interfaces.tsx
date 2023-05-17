interface DataTipyfiend {
    id: React.Key,
    emision?: Date,
    documento?: Number,
    nombre?: string,
    telefono?: string,
    company?: string,
    email?: string,
    cetificado: etiqueta,
    soporte: etiqueta,
    fecha: Date,
    hora: string,
    observacion: string,
    estado: Boolean,
    tiempo: number,
    categoria: etiqueta,
    agente: string,
    etiquetas: etiqueta[],
    evidencia: Boolean
  }

  interface DataUserTipyfiend {
    documento?: Number,
    nombre?: string,
    telefono?: string,
    company?: string,
    email?: string,
    certificado: CertiUser[],
  }
  interface CertiUser {
    certificado: etiqueta,
    emision: Date,
  }
  
  enum etiqueta {
    ACTIVACION = "ACTIVACION",
    DESBLOQUEO = "DESBLOQUEO",
    ACOMPANAMIENTO = "ACOMPANAMIENTO",
    OPERACIONES = "OPERACIONES",
    EQUIVOCADO = "EQUIVOCADO",
    NO_CONTESTA = "NO_CONTESTA",
    CONSULTA = "CONSULTA",
    INSTALACION = "INSTALACION",
    PROBLEMA = "PROBLEMA",
    NA = "NA",
    ERROR_EMISION = "ERROR_EMISION",
    CRED_OLVIDO = "CRED_OLVIDO",
    CRED_RECIBIDA = "CRED_RECIBIDA",
    REENVIAR = "REENVIAR",
    DEPURACION = "DEPURACION",
    FNA = "FNA",
    PROCESO = "PROCESO",
    BIOMETRIA = "BIOMETRIA",
    FIRMADO = "FIRMADO",
    TOKEN = "TOKEN",
    CKC = "CKC",
    TOP = "TOP",
    VUCE = "VUCE",
    SSL = "SSL",
    RMAIL = "RMAIL",
    P12 = "P12",
    CAMERCLOUD = "CAMERCLOUD",
    CALLBACK = "CALLBACK",
    CALLINPUT = "CALLINPUT",
    OTROS = "OTROS",
    REUNION = "REUNION",
  }
  
  enum soporte {
    ACTIVACION = "ACTIVACION",
    DESBLOQUEO = "DESBLOQUEO",
    FIRMADO = "FIRMADO",
    ACOMPANAMIENTO = "ACOMPANAMIENTO",
    OPERACIONES = "OPERAQCIONES",
    EQUIVOCADO = "EQUIVOCADO",
    NO_CONTESTA = "NO_CONTESTA",
    CONSULTA = "CONSULTA",
    INSTALACION = "INSTALACION",
    PROBLEMA = "PROBLEMA",
    NA = "NA",
  }
  
  enum subSoporte {
    ERROR_EMISION = "ERROR_EMISION",
    CRED_OLVIDO = "CRED_OLVIDO",
    CRED_RECIBIDA = "CRED_RECIBIDA",
    REENVIAR = "REENVIAR",
    DEPURACION = "DEPURACION",
    FNA = "FNA",
    PROCESO = "PROCESO",
    BIOMETRIA = "BIOMETRIA",
    FIRMADO = "FIRMADO",
  }
  
  enum certificado {
    TOKEN = "TOKEN",
    CKC = "CKC",
    TOP = "TOP",
    VUCE = "VUCE",
    SSL = "SSL",
    RMAIL = "RMAIL",
    P12 = "P12",
    CAMERCLOUD = "CAMERCLOUD",
  }
  
  enum categoria {
    CALLBACK = "CALLBACK",
    CALLINPUT = "CALLINPUT",
    OTROS = "OTROS",
    REUNION = "REUNION",
  }

  enum typeResultMethod{
    ERROR = "ERROR",
    FINE = "FINE"
  }

  enum Observaciones{
    GROCESO,
    AGRECIVO,
    MOLESTO,
    PC_LENTO,
    PROCESO_LENTO,
    REUNION,
    ANYDESK,
    TEAMVIEWER,
    COLGO,
    CAPACITACION,
    REINSTALAR,
    DESINSTALACION_OTRA_ENTIDAD,
    CREDENCIALES,
    SIIF,
    MAC,
    LLAMADA,
    SUPER_SALUD
  }

export { etiqueta, categoria, typeResultMethod, Observaciones }
export type { DataTipyfiend, DataUserTipyfiend, CertiUser}
