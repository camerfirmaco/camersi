import { Badge, Descriptions, Image } from "antd";
import { DataTipyfiend } from "./Interfaces";
import { tapEtiqueta } from "./Etiquetas";
import imagen from '../../../assets/img/imagen.png';
import dayjs from "dayjs";

const descripcion = (data: DataTipyfiend) => {

  return (
    <>
      <Descriptions size='small' layout="vertical" bordered column={{ xxl: 6, xl: 5, lg: 4, md: 4, sm: 3, xs: 1 }}>
        {data.documento ? <Descriptions.Item label="Identificación">{new String(data.documento)}</Descriptions.Item> : <></>}
        {data.nombre ? <Descriptions.Item label="Nombre">{data.nombre}</Descriptions.Item> : <></>}
        {data.telefono ? <Descriptions.Item label="Telefono">{data.telefono}</Descriptions.Item> : <></>}
        {data.email ? <Descriptions.Item label="Email">{data.email}</Descriptions.Item> : <></>}
        <Descriptions.Item label="Compañia">{data.company}</Descriptions.Item>
        <Descriptions.Item label="Estado">{!data.estado ? <Badge status="success" text="CERRADO" /> : <Badge status="error" text="ABIERTO" />}</Descriptions.Item>
        <Descriptions.Item label="Certificado">{tapEtiqueta(data.cetificado)}</Descriptions.Item>
        <Descriptions.Item label="Emisión">{dayjs(data.emision).format('DD/MM/YYYY')}</Descriptions.Item>
        <Descriptions.Item label="Fecha y hora">{dayjs(data.fecha).format('DD/MM/YYYY')+"-"+dayjs(data.hora).format('HH:mm')}</Descriptions.Item>
        <Descriptions.Item label="Soporte">{tapEtiqueta(data.soporte)}</Descriptions.Item>
        <Descriptions.Item label="Tiempo">{data.tiempo}</Descriptions.Item>
        <Descriptions.Item label="Categoria">{tapEtiqueta(data.categoria)}</Descriptions.Item>
        {data.evidencia?<Descriptions.Item label="Evidencias"><Image.PreviewGroup preview={{ onChange: (current, prev) => console.log(`current index: ${current}, prev index: ${prev}`), }}>
          <Image width={20} src={imagen} preview={{ src: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', }} />
          <Image width={20} src={imagen} preview={{ src: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png', }} />
        </Image.PreviewGroup></Descriptions.Item>:<></>}
        <Descriptions.Item label="Observaciones"><p>{data.observacion}</p></Descriptions.Item>
      </Descriptions>
    </>
  );
}
export { descripcion }