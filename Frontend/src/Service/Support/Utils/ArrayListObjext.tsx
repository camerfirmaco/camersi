import { Avatar, Button, Space, TimelineItemProps, Tooltip, Typography } from 'antd';
import { PhoneOutlined, FileSearchOutlined, UserOutlined, MailOutlined, WarningOutlined, AuditOutlined, CloudOutlined, UsbOutlined, DesktopOutlined, QuestionCircleOutlined } from '@ant-design/icons';
import { SegmentedLabeledOption } from 'antd/es/segmented';
import logoVuce from '../../../assets/img/iconVuce.png';
import logoFondo from '../../../assets/img/iconFondo.png';
import { tapEtiqueta } from '../../../Service/Support/Utils/Etiquetas';
import { DataUserTipyfiend, etiqueta, Observaciones } from '../../../Service/Support/Utils/Interfaces';
import dayjs from 'dayjs';

const { Text, Title } = Typography;

const categorias: SegmentedLabeledOption[] = [
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar style={{ backgroundColor: "#0075A6" }} icon={<PhoneOutlined />} />
                <div><Text>Entrante</Text></div >
            </div>
        ),
        value: 'CALLINPUT',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar style={{ backgroundColor: "#175173" }} icon={<PhoneOutlined />} />
                <div><Text>Saliente</Text></div >
            </div>
        ),
        value: 'CALLBACK',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar style={{ backgroundColor: "#162E4D" }} icon={<DesktopOutlined />} />
                <div><Text>Reunión</Text></div >
            </div>
        ),
        value: 'REUNION',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar icon={<QuestionCircleOutlined />} />
                <div><Text>Otros</Text></div >
            </div>
        ),
        value: 'OTROS',
    },
]
const certificadoExtras: SegmentedLabeledOption[] = [
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' style={{ backgroundColor: "#0075A6" }} icon={<AuditOutlined />} />
                <div><Text>SSL</Text></div >
            </div>
        ),
        value: 'SSL',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' style={{ backgroundColor: "#0075A6" }} icon={<MailOutlined />} />
                <div><Text>RMAIL</Text></div >
            </div>
        ),
        value: 'RMAIL',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' style={{ backgroundColor: "#0075A6" }} icon={<AuditOutlined />} />
                <div><Text>P12</Text></div >
            </div>
        ),
        value: 'P12',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' icon={<WarningOutlined />} />
                <div><Text>NA</Text></div >
            </div>
        ),
        value: 'NA',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' icon={<img alt='logoFondo' src={logoFondo} />} />
                <div><Text>FNA</Text></div >
            </div>
        ),
        value: 'FNA',
    },
]
const certificados: SegmentedLabeledOption[] = [
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' style={{ backgroundColor: "#0075A6" }} icon={<UsbOutlined />} />
                <div><Text>TOKEN</Text></div >
            </div>
        ),
        value: 'TOKEN',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' style={{ backgroundColor: "#175173" }} icon={<CloudOutlined />} />
                <div><Text>CKC</Text></div >
            </div>
        ),
        value: 'CKC',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' style={{ backgroundColor: "#162E4D" }} icon={<CloudOutlined />} />
                <div><Text>TOP</Text></div >
            </div>
        ),
        value: 'TOP',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' style={{ backgroundColor: "#243786" }} icon={<CloudOutlined />} />
                <div><Text>CLOUD</Text></div >
            </div>
        ),
        value: 'CAMERCLOUD',
    },
    {
        label: (
            <div style={{ padding: 4 }}>
                <Avatar size='small' icon={<img width={30} alt='logoVuce' src={logoVuce} />} />
                <div><Text>VUCE</Text></div >
            </div>
        ),
        value: 'VUCE',
    },

]
const soporte: SegmentedLabeledOption[] = [
    {
        label: (<div>{tapEtiqueta(etiqueta.ACTIVACION)}</div >),
        value: etiqueta.ACTIVACION,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.ACOMPANAMIENTO)}</div >),
        value: etiqueta.ACOMPANAMIENTO,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.DESBLOQUEO)}</div >),
        value: etiqueta.DESBLOQUEO,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.INSTALACION)}</div >),
        value: etiqueta.INSTALACION,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.CONSULTA)}</div >),
        value: etiqueta.CONSULTA,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.OPERACIONES)}</div >),
        value: etiqueta.OPERACIONES,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.NO_CONTESTA)}</div >),
        value: etiqueta.NO_CONTESTA,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.PROBLEMA)}</div >),
        value: etiqueta.PROBLEMA,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.EQUIVOCADO)}</div >),
        value: etiqueta.EQUIVOCADO,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.NA)}</div >),
        value: etiqueta.NA,
    },

]
const PROBLEMA: SegmentedLabeledOption[] = [
    {
        label: (<div>{tapEtiqueta(etiqueta.ERROR_EMISION)}</div >),
        value: etiqueta.ERROR_EMISION,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.CRED_OLVIDO)}</div >),
        value: etiqueta.CRED_OLVIDO,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.CRED_RECIBIDA)}</div >),
        value: etiqueta.CRED_RECIBIDA,
    },
]
const OPERACIONES: SegmentedLabeledOption[] = [
    {
        label: (<div>{tapEtiqueta(etiqueta.REENVIAR)}</div >),
        value: etiqueta.REENVIAR,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.DEPURACION)}</div >),
        value: etiqueta.DEPURACION,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.FNA)}</div >),
        value: etiqueta.FNA,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.PROCESO)}</div >),
        value: etiqueta.PROCESO,
    },
]
const ACOMPANAMIENTO: SegmentedLabeledOption[] = [
    {
        label: (<div>{tapEtiqueta(etiqueta.BIOMETRIA)}</div >),
        value: etiqueta.BIOMETRIA,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.FIRMADO)}</div >),
        value: etiqueta.FIRMADO,
    },
    {
        label: (<div>{tapEtiqueta(etiqueta.PROCESO)}</div >),
        value: etiqueta.PROCESO,
    },
]
const EXTRAOBSERVACIONES: SegmentedLabeledOption[] = [
    {
        label: (<>Usuario groceso</>),
        value: Observaciones.GROCESO,
    },
    {
        label: (<>Usuario Agresivo</>),
        value: Observaciones.AGRECIVO,
    },
    {
        label: (<>Usuario molesto</>),
        value: Observaciones.MOLESTO,
    },
    {
        label: (<>PC lento</>),
        value: Observaciones.PC_LENTO,
    },
    {
        label: (<>Proceso lento</>),
        value: Observaciones.PROCESO_LENTO,
    },
    {
        label: (<>Reunión</>),
        value: Observaciones.REUNION,
    },
    {
        label: (<>AnyDesk</>),
        value: Observaciones.ANYDESK,
    },
    {
        label: (<>TeamViewer</>),
        value: Observaciones.TEAMVIEWER,
    },
    {
        label: (<>Colgo llamada</>),
        value: Observaciones.COLGO,
    },
    {
        label: (<>Capacitación</>),
        value: Observaciones.CAPACITACION,
    },
    {
        label: (<>Reinstalación</>),
        value: Observaciones.REINSTALAR,
    },
    {
        label: (<>Desinstalación de otra entidad</>),
        value: Observaciones.DESINSTALACION_OTRA_ENTIDAD,
    },
    {
        label: (<>Falta de credenciales</>),
        value: Observaciones.CREDENCIALES,
    },
    {
        label: (<>Proceso de SIIF</>),
        value: Observaciones.SIIF,
    },
    {
        label: (<>PC MacOs</>),
        value: Observaciones.MAC,
    },
]
const OBSERVACIONES: SegmentedLabeledOption[] = [
    {
        label: (<>Usuario groceso</>),
        value: Observaciones.GROCESO,
    },
    {
        label: (<>Usuario Agresivo</>),
        value: Observaciones.AGRECIVO,
    },
    {
        label: (<>Usuario molesto</>),
        value: Observaciones.MOLESTO,
    },
    {
        label: (<>PC lento</>),
        value: Observaciones.PC_LENTO,
    },
    {
        label: (<>Proceso lento</>),
        value: Observaciones.PROCESO_LENTO,
    },
    {
        label: (<>Colgo llamada</>),
        value: Observaciones.COLGO,
    },
    {
        label: (<>Capacitación</>),
        value: Observaciones.CAPACITACION,
    },
    {
        label: (<>Reinstalación</>),
        value: Observaciones.REINSTALAR,
    },
    {
        label: (<>Desinstalación</>),
        value: Observaciones.DESINSTALACION_OTRA_ENTIDAD,
    },
    {
        label: (<>Falta de credenciales</>),
        value: Observaciones.CREDENCIALES,
    },
    {
        label: (<>Proceso de SIIF</>),
        value: Observaciones.SIIF,
    },
    {
        label: (<>PC MacOs</>),
        value: Observaciones.MAC,
    },
    {
        label: (<>Super salud</>),
        value: Observaciones.SUPER_SALUD,
    },
]
const CONEXION: SegmentedLabeledOption[] = [
    {
        label: (<>Reunión</>),
        value: Observaciones.REUNION,
    },
    {
        label: (<>AnyDesk</>),
        value: Observaciones.ANYDESK,
    },
    {
        label: (<>TeamViewer</>),
        value: Observaciones.TEAMVIEWER,
    },
    {
        label: (<>En llamada</>),
        value: Observaciones.LLAMADA,
    },
]
const EstadoSoporte: SegmentedLabeledOption[] = [
    {
        label: (<div>ABIERTO</div >),
        value: "true",
    },
    {
        label: (<div>CERRADO</div >),
        value: "false",
    },
]
const subSoporte = (etiq: etiqueta, certificado: etiqueta) => {
    const array: any[] = [];
    switch (certificado) {
        case etiqueta.TOKEN:
            switch (etiq) {
                case etiqueta.PROBLEMA:
                    PROBLEMA.forEach(element => {
                        if (element.value === etiqueta.ERROR_EMISION) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACTIVACION:
                    return array;
                case etiqueta.OPERACIONES:
                    OPERACIONES.forEach(element => {
                        if (element.value === etiqueta.PROCESO) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACOMPANAMIENTO:
                    ACOMPANAMIENTO.forEach(element => {
                        if (element.value != etiqueta.BIOMETRIA) {
                            array.push(element);
                        }
                    });
                    return array;
                default:
                    return [];
            }
        case etiqueta.CAMERCLOUD:
            switch (etiq) {
                case etiqueta.PROBLEMA:
                    PROBLEMA.forEach(element => {
                        if (element.value === etiqueta.ERROR_EMISION) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACTIVACION:
                    return array;
                case etiqueta.OPERACIONES:
                    OPERACIONES.forEach(element => {
                        if (element.value === etiqueta.PROCESO || element.value === etiqueta.REENVIAR) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACOMPANAMIENTO:
                    ACOMPANAMIENTO.forEach(element => {
                        if (element.value != etiqueta.BIOMETRIA) {
                            array.push(element);
                        }
                    });
                    return array;
                default:
                    return [];
            }
        case etiqueta.TOP:
            switch (etiq) {
                case etiqueta.PROBLEMA:
                    PROBLEMA.forEach(element => {
                        if (element.value != etiqueta.CRED_RECIBIDA) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACTIVACION:
                    return array;
                case etiqueta.OPERACIONES:
                    OPERACIONES.forEach(element => {
                        if (element.value === etiqueta.PROCESO || element.value === etiqueta.REENVIAR) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACOMPANAMIENTO:
                    ACOMPANAMIENTO.forEach(element => {
                        if (element.value != etiqueta.BIOMETRIA) {
                            array.push(element);
                        }
                    });
                    return array;
                default:
                    return [];
            }
        case etiqueta.VUCE:
            switch (etiq) {
                case etiqueta.PROBLEMA:
                    PROBLEMA.forEach(element => {
                        if (element.value != etiqueta.CRED_RECIBIDA) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACTIVACION:
                    return array;
                case etiqueta.OPERACIONES:
                    OPERACIONES.forEach(element => {
                        if (element.value != etiqueta.FNA) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACOMPANAMIENTO:
                    return ACOMPANAMIENTO;
                default:
                    return [];
            }
        case etiqueta.CKC:
            switch (etiq) {
                case etiqueta.PROBLEMA:
                    return PROBLEMA;
                case etiqueta.ACTIVACION:
                    return array;
                case etiqueta.OPERACIONES:
                    OPERACIONES.forEach(element => {
                        if (element.value === etiqueta.PROCESO) {
                            array.push(element);
                        }
                    });
                    return array;
                case etiqueta.ACOMPANAMIENTO:
                    ACOMPANAMIENTO.forEach(element => {
                        if (element.value != etiqueta.BIOMETRIA) {
                            array.push(element);
                        }
                    });
                    return array;
                default:
                    return [];
            }
        case etiqueta.P12:
            return [];
        case etiqueta.SSL:
            return [];
        case etiqueta.RMAIL:
            return [];
        case etiqueta.NA:
            return [];
        default:
            return [];
    }
}

const onSoporte = (e: any) => {
    const array: any[] = [];
    switch (e) {
        case 'TOKEN':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[2])//DESBLOQUEO
            array.push(soporte[3])//INSTALACION
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'CKC':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[3])//INSTALACION
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'TOP':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[3])//INSTALACION
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'CAMERCLOUD':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[2])//DESBLOQUEO
            array.push(soporte[3])//INSTALACION
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'VUCE':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'SSL':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[3])//INSTALACION
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'RMAIL':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[3])//INSTALACION
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'P12':
            array.push(soporte[0])//ACTIVACION
            array.push(soporte[1])//ACOMPAÑAMIENTO
            array.push(soporte[3])//INSTALACION
            array.push(soporte[4])//CONSULTA
            array.push(soporte[5])//OPERACIONES
            array.push(soporte[6]) //NO CONTESTA
            array.push(soporte[7]) //PROBLEMA
            array.push(soporte[8]) //EQUIVOCADO
            array.push(soporte[9]) //NA
            return array;
        case 'NA':
            return soporte;
        case 'FNA':
            return soporte;
        default:
            return [];
    }
}
let unicos: any = [];

const userDatos = (data: any[]) => {
    if (data === undefined) {
        return undefined;
    }
    unicos = [];
    let user: any = {
        certificado: [],
    };
    data?.map((element: any) => (
        user = userDato(element, user)
    ))
    user.certificado = unicos;
    const usuario: DataUserTipyfiend = user;
    return usuario;
}

const userDato = (element: any, user: any) => {
    if (element.cetificado != null) {
        user.nombre = element.nombre ? element.nombre : user.nombre;
        user.documento = element.documento ? element.documento : user.documento;
        user.company = element.company ? element.company : user.company;
        user.telefono = element.telefono ? element.telefono : user.telefono;
        user.email = element.email ? element.email : user.email;

        if (element.emision != null) {
            for (var i = 0; i < user.certificado.length; i++) {
                const elemento = user.certificado[i];
                if (!unicos.includes(elemento)) {
                    unicos.push(elemento);
                }
            }
            user.certificado.push({
                certificado: element.cetificado,
                emision: element.emision,
            });

        }
    }
    return user;
}

const userLineTime = (data: any[]) => {
    const respuesta: TimelineItemProps[] = [];
    if (data === undefined) {
        return undefined;
    }
    data?.map((element: any) => (
        respuesta.push(userLineDato(element))
    ))
    return respuesta;
}
const userLineDato = (element: any) => {
    let dato: TimelineItemProps = {
        label: (<>{tapEtiqueta(element.cetificado)}{tapEtiqueta(element.soporte)}{tapEtiqueta(element.subSoporte)}{tapEtiqueta(element.categoria)}</>),
        children: (
            <>
                <Space>
                    <Title level={5}>{element.agente}</Title>
                    <Tooltip placement="topLeft" title={element.documento+"\n"+element.nombre+"\n"+element.email+"\n"+element.telefono}>
                        <Button type="default" icon={<UserOutlined />} size="small" />
                    </Tooltip>
                    <Tooltip placement="topLeft" title={element.observacion}>
                        <Button type="primary" icon={<FileSearchOutlined />} size="small" />
                    </Tooltip>
                </Space><br />
                <Space>
                    <Text>{dayjs(element.fecha).format('DD/MM/YYYY')} - </Text>
                    <Text>{dayjs(element.hora).format('HH:mm')} - </Text>
                    <Text>{element.tiempo} min</Text>
                </Space>
            </>),
        color: !element.estado ? 'green' : "red",
    };
    return dato;
}

export { EstadoSoporte, userDatos, userLineTime, onSoporte, subSoporte, soporte, categorias, certificados, certificadoExtras, OBSERVACIONES, CONEXION }