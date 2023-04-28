interface DataTipyfiend {
    id: React.Key,
    emision: String,
    documento: Number,
    nombre: String,
    telefono: String,
    company: String,
    email: String,
    cetificado: etiqueta,
    soporte: etiqueta,
    fecha: String,
    hora: String,
    observacion: string,
    estado: Boolean,
    tiempo: number,
    categoria: etiqueta,
    agente: String,
    etiquetas: etiqueta[],
    emilinado: Boolean
  }
  
  enum etiqueta {
    ACTIVACION = "ACTIVACION",
    DESBLOQUEO = "DESBLOQUEO",
    ACOMPAÑAMIENTO = "ACOMPAÑAMIENTO",
    OPERACIONES = "OPERAQCIONES",
    EQUIVOCADO = "EQUIVOCADO",
    NO_CONTESTA = "NO_CONTESTA",
    CONSULTA = "CONSULTA",
    INSTALACION = "INSTALACION",
    PROBLEMA = "PROBLEMA",
    NA = "NA",
    ERROR_EMISION = "ERROR_EMISION",
    CRED_OLVIDO = "CRED_OLVIDO",
    CRED_RECIBIDA = "CRED_RECIBIDA",
    PRIMERA_VEZ = "PRIMERA_VEZ",
    VUCE_TOP = "VUCE_TOP",
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
    ACOMPAÑAMIENTO = "ACOMPAÑAMIENTO",
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
    PRIMERA_VEZ = "PRIMERA_VEZ",
    VUCE_TOP = "VUCE_TOP",
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

export { etiqueta, categoria, typeResultMethod }
export type { DataTipyfiend}
