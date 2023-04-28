import { CheckCircleOutlined, SettingOutlined, UserOutlined, CloseCircleOutlined, LoadingOutlined, CloudOutlined, FormOutlined, DesktopOutlined, AuditOutlined, MinusSquareOutlined, MailOutlined, ExclamationCircleOutlined, StarOutlined, ExceptionOutlined, ApiOutlined, WarningOutlined, MinusCircleOutlined, UsbOutlined, SafetyOutlined, DownloadOutlined, UnlockOutlined, TeamOutlined, PhoneOutlined, QuestionCircleOutlined } from '@ant-design/icons';
import logoVuce from '../../../assets/img/vuce-logo.png';
import { etiqueta } from './Interfaces';
import { Tag } from 'antd';

const tapEtiqueta = (etiq: etiqueta | undefined) => {
    switch (etiq) {
      case etiqueta.ACTIVACION:
        return <Tag icon={<SafetyOutlined />} color="success">Activación</Tag>;
      case etiqueta.DESBLOQUEO:
        return <Tag icon={<UnlockOutlined />} color="warning">Desbloqueo</Tag>;
      case etiqueta.ACOMPAÑAMIENTO:
        return <Tag icon={<TeamOutlined />} color="success">Acompañamiento</Tag>;
      case etiqueta.EQUIVOCADO:
        return <Tag icon={<CloseCircleOutlined />} color="error">Equivocado</Tag>;
      case etiqueta.NO_CONTESTA:
        return <Tag icon={<PhoneOutlined />} color="error">No contesta</Tag>;
      case etiqueta.CONSULTA:
        return <Tag icon={<QuestionCircleOutlined />} color="default">Consulta</Tag>;
      case etiqueta.INSTALACION:
        return <Tag icon={<DownloadOutlined />} color="processing">Instalación</Tag>;
      case etiqueta.PROBLEMA:
        return <Tag icon={<WarningOutlined />} color="error">Problema</Tag>;
      case etiqueta.NA:
        return <Tag icon={<MinusCircleOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.ERROR_EMISION:
        return <Tag icon={<ApiOutlined />} color="volcano">Error en la emisión</Tag>;
      case etiqueta.CRED_OLVIDO:
        return <Tag icon={<ExclamationCircleOutlined />} color="orange">Credenciales olvidadas</Tag>;
      case etiqueta.CRED_RECIBIDA:
        return <Tag icon={<ExceptionOutlined />} color="orange">Credenciales No recibidas</Tag>;
      case etiqueta.PRIMERA_VEZ:
        return <Tag icon={<StarOutlined />} color="lime">Primera vez</Tag>;
      case etiqueta.VUCE_TOP:
        return <Tag icon={<img width={30} alt='logoVuce' src={logoVuce} />} color="processing">Activación de Vuce</Tag>;
      case etiqueta.REENVIAR:
        return <Tag icon={<MailOutlined />} color="processing">Reenvio de correo</Tag>;
      case etiqueta.DEPURACION:
        return <Tag icon={<ExclamationCircleOutlined />} color="red">Depuración</Tag>;
      case etiqueta.FNA:
        return <Tag icon={<MinusSquareOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.PROCESO:
        return <Tag icon={<LoadingOutlined />} color="processing">En proceso</Tag>;
      case etiqueta.BIOMETRIA:
        return <Tag icon={<img width={30} alt='logoVuce' src={logoVuce} />} color="success">Biometria</Tag>;
      case etiqueta.FIRMADO:
        return <Tag icon={<FormOutlined />} color="default">Proceso de firmado</Tag>;
      case etiqueta.TOKEN:
        return <Tag icon={<UsbOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.CKC:
        return <Tag icon={<CloudOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.TOP:
        return <Tag icon={<CloudOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.VUCE:
        return <Tag icon={<img width={30} alt='logoVuce' src={logoVuce} />} color="default">TOP {etiq}</Tag>;
      case etiqueta.SSL:
        return <Tag icon={<AuditOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.RMAIL:
        return <Tag icon={<MailOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.P12:
        return <Tag icon={<AuditOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.CAMERCLOUD:
        return <Tag icon={<CloudOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.CALLBACK:
        return <Tag icon={<PhoneOutlined />} color="success">{etiq}</Tag>;
      case etiqueta.CALLINPUT:
        return <Tag icon={<PhoneOutlined />} color="success">{etiq}</Tag>;
      case etiqueta.OTROS:
        return <Tag icon={<QuestionCircleOutlined />} color="default">{etiq}</Tag>;
      case etiqueta.REUNION:
        return <Tag icon={<DesktopOutlined />} color="default">Reunión</Tag>;
      default:
        return <Tag icon={<MinusCircleOutlined />} color="default">SIN ETIQUETA</Tag>;
    }
  }

  export {tapEtiqueta}