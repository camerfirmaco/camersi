import React, { useEffect, useState } from 'react';
import { Alert, Button, Checkbox, Col, DatePicker, Divider, Form, Input, InputNumber, Radio, Row, Segmented, Select, Space, Switch, TimePicker, Typography, message } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import { CONEXION, EstadoSoporte, OBSERVACIONES, categorias, certificadoExtras, certificados, onSoporte, soporte, subSoporte, userDatos } from '../../Service/Support/Utils/ArrayListObjext';
import { CertiUser, DataUserTipyfiend, Observaciones, etiqueta } from '../../Service/Support/Utils/Interfaces';
import { SegmentedLabeledOption } from 'antd/es/segmented';
import dayjs from 'dayjs';
import Search from 'antd/es/input/Search';
import { getTipyfiendUser, postTipyfiend } from '../../Service/Support/getTipyfiend';
import { useMutation, useQuery } from 'react-query';
import { tapEtiqueta } from '../../Service/Support/Utils/Etiquetas';
import { ClockCircleOutlined, FormOutlined, SearchOutlined, RetweetOutlined, CodeOutlined } from '@ant-design/icons';
import TextArea from 'antd/es/input/TextArea';
import axios from 'axios';
import { generarObservacion } from '../../Service/Support/Utils/Obsercacion';

const { Link } = Typography;

const RegisterTipyfiend: React.FC = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const [form] = Form.useForm();
  const [formStatus, setFormStatus] = useState<boolean>(false);
  const [tiempo, setTiempo] = useState(dayjs());
  const [cerificado, setCerificado] = useState(certificados);
  const [valueSearch, setValueSearch] = useState<string | undefined>(undefined);
  const [moreLoaded, setMoreLoaded] = useState(false);
  const [cert, setCert] = useState<any>("NA");
  const [soporteObs, setSoporteObs] = useState<any>(undefined);
  const [estadoObs, setEstadoObs] = useState<any>(true);
  const [editObs, setEditObs] = useState<boolean>(false);
  const [subSoporteOsb, setSubSoporteOsbs] = useState<any>(undefined);
  const [nameObs, setNameObs] = useState<string | undefined>(undefined);
  const [obs1, setObs1] = useState<any>(undefined);
  const [obs2, setObs2] = useState<any>(undefined);
  const [obs3, setObs3] = useState<any>(undefined);
  const [pruebaFirm, setPruebaFirm] = useState<any>(undefined);
  const [categ, setCateg] = useState<any>("CALLINPUT");
  const { data, status } = useQuery(['tipyfiend', valueSearch, {}], getTipyfiendUser);
  const [validSupSoporte, setValidSupSoporte] = useState<"error" | "warning" | undefined>("error");
  const [subSupport, setSubSupport] = useState<SegmentedLabeledOption[]>([]);
  const [support, setSupport] = useState<SegmentedLabeledOption[]>(onSoporte(cert));
  const handleLoadOptions = () => {
    setCerificado((prev:any) => [...prev, ...certificadoExtras]);
    setMoreLoaded(true);
  };

  const mutation = useMutation({
    mutationFn: (newValues) => {
      return postTipyfiend(newValues);
    },
  })

  const onFinish = (values: any) => {
    console.log(values);
    mutation.mutate(values);
  };

  useEffect(() => {
    if (mutation.isSuccess){
      messageApi.open({
        type: 'success',
        content: 'Registro exitoso',
      });
      setTiempo(dayjs());
      setCerificado(certificados);
      setValueSearch(undefined);
      setSoporteObs(undefined);
      setEstadoObs(true);
      setEditObs(false);
      setCert("NA");
      setSubSoporteOsbs(undefined);
      setNameObs(undefined);
      setObs1(undefined);
      setObs2(undefined);
      setObs3(undefined);
      setPruebaFirm(undefined);
      setCateg("CALLINPUT");
      setValidSupSoporte("error")
      setSubSupport([]);
      setSupport(onSoporte(cert));
      setMoreLoaded(false);
      form.resetFields();
    }
  }, [mutation.isSuccess]);

  useEffect(() => {
    if (mutation.isError){
      messageApi.open({
        type: 'error',
        content: 'Registro fallido',
      });
    }
  }, [mutation.isError]);

  useEffect(() => {
    setFormStatus(mutation.isLoading);
  }, [mutation.isLoading]);

  useEffect(() => {
    setSupport(onSoporte(cert));
  }, [cert]);

  useEffect(() => {
    onFill();
  }, [data]);

  const onFill = () => {
    form.setFieldsValue({ telefono: data?.telefono, nombre: data?.nombre, email: data?.email, company: data?.company });
    setNameObs(data?.nombre);
  };

  const onFillFecha = () => {
    form.setFieldsValue({ fecha: dayjs() });
  };
  const onFillHora = () => {
    form.setFieldsValue({ hora: dayjs() });
    setTiempo(dayjs());
  };
  const onFillTiempo = () => {
    const time: number = Math.trunc(dayjs().diff(tiempo) / 60000);
    form.setFieldsValue({ tiempo: time, });
  };

  const onFillCertificado = (cert: CertiUser | undefined) => {
    form.setFieldsValue({ emision: dayjs(cert?.emision) });
    setCertificado(cert?.certificado);
  };

  const onSearch = (value: string) => {
    setValueSearch(value);
  };

  const setCertificado = (value: any) => {
    setCert(value);
    form.setFieldsValue({ cetificado: value });
  }
  const setCategoriaValue = (value: any) => {
    setCateg(value);
    console.log(categ);
    form.setFieldsValue({ categoria: value });
  }
  const generarObs = () => {
    setEditObs(true)
    form.setFieldsValue({ observacion: generarObservacion(categ, soporteObs, cert, estadoObs, subSoporteOsb, nameObs, obs1, obs2, obs3, pruebaFirm,) });
  }
  return (
    <Form disabled={formStatus} onFinish={onFinish} form={form} layout='vertical' style={{ margin: "30px" }}>
      {contextHolder}
      <Row className='row_form_signin' gutter={12}>
        <Col >
          <Form.Item >
            <Segmented disabled={formStatus} value={categ}
              onChange={(e) => setCategoriaValue(e)}
              options={categorias}
            />
          </Form.Item>
          <Form.Item style={{ display: "none" }} hidden name="categoria" initialValue="CALLINPUT" >
            <Input />
          </Form.Item>
        </Col>
      </Row>
      <Row className='row_form_signin' gutter={12}>
        <Col span={6} >
          <Form.Item
            label='Fecha'
            name='fecha'
            rules={[{ required: true, message: '¡Por favor digitar la fecha!' }]}
          >
            <DatePicker placement='bottomLeft' format={'YYYY/MM/DD'} />
          </Form.Item>
        </Col>
        <Col span={2} >
          <Button onClick={onFillFecha} icon={<RetweetOutlined />} type="default" />
        </Col>
        <Col span={6} >
          <Form.Item
            label='Hora'
            name='hora'
            rules={[{ required: true, message: '¡Por favor digitar la Hora!' }]}
          >
            <TimePicker format={'HH:mm'} onChange={(e: any) => setTiempo(e)} />
          </Form.Item>
        </Col>
        <Col span={2} >
          <Button onClick={onFillHora} icon={<RetweetOutlined />} type="default" />
        </Col>
        <Col span={6} >
          <Form.Item
            label='Tiempo'
            name='tiempo'
            rules={[{ required: true, message: '¡Por favor digitar el tiempo!' }]}
          >
            <InputNumber min={1} style={{ width: "100%" }} />
          </Form.Item>
        </Col>
        <Col span={2} >
          <Button onClick={onFillTiempo} icon={<ClockCircleOutlined />} type="default" />
        </Col>

      </Row>
      {categ === "CALLBACK" || categ === "CALLINPUT" ?
        <>
          <Row className='row_form_signin' gutter={12}>
            <Col span={6} >
              <Form.Item
                label='Identificación'
                name='documento'
              >
                <Search placeholder='Identificación' onClick={() => onFill()} onSearch={onSearch} loading={status === "loading" ? true : false} />
              </Form.Item>
            </Col>
            <Col span={12} >
              <Form.Item
                label='Nombre'
                name='nombre'
              >
                <Input placeholder='Nombre' onChange={(e: any) => setNameObs(e.target.value)} />
              </Form.Item>
            </Col>
            <Col span={6} >
              <Form.Item
                label='Teléfono'
                name='telefono'
              >
                <Input placeholder='Teléfono' />
              </Form.Item>
            </Col>
          </Row>
          <Row className='row_form_signin' gutter={12}>
            <Col span={12} >
              <Form.Item
                label='Email'
                name='email'
                rules={[{ type: "email", message: '¡Por favor digitar un email valido!' }]}
              >
                <Input placeholder='Email' />
              </Form.Item>
            </Col>
            <Col span={12} >
              <Form.Item
                label='Entidad o Empresa'
                name='company'
              >
                <Input placeholder='Entidad o Empresa' />
              </Form.Item>
            </Col>

          </Row>
        </>
        : <></>}
      <Row className='row_form_signin' gutter={12}>

        <Col>
          <Form.Item>
            {data?.certificado.length ? <><Alert message={<><>{"Certificados del usuario: "}</>{data?.certificado?.map((e) => (<Link key={e.certificado} onClick={() => onFillCertificado(e)}>{tapEtiqueta(e.certificado)}</Link>))}</>} type="info" showIcon /><br /></> : <></>}
            <Space direction="horizontal">
              <Segmented disabled={formStatus} value={cert} onChange={(e) => setCertificado(e)} options={cerificado} size='small' />
              <Button loading={formStatus} icon={<PlusOutlined />} shape="circle" type="primary" disabled={moreLoaded} onClick={handleLoadOptions} />
            </Space>
          </Form.Item>
          <Form.Item style={{ display: "none" }} hidden name="cetificado" initialValue="NA">
            <Input />
          </Form.Item>
        </Col>
      </Row>
      <Row className='row_form_signin' gutter={12}>
        {categ === "OTROS" || categ === "REUNION" ?
          <Col span={8}>
            <Form.Item label name="estado" initialValue={true}>
              <Radio.Group
                onChange={(e) => setEstadoObs(e.target.value)}
                options={[
                  { label: 'Abierto', value: true },
                  { label: 'Cerrado', value: false },
                ]}
                optionType="button"
                buttonStyle="solid"
              />
            </Form.Item>
          </Col>
          : <></>}
        {categ === "CALLBACK" || categ === "CALLINPUT" ?
          <Col span={8}>
            <Form.Item
              label='Fecha de emisión'
              name='emision'>
              <DatePicker placement='bottomLeft' format={'YYYY/MM/DD'} />
            </Form.Item>
          </Col>
          : <></>}
        <Col span={8}>
          <Form.Item
            label='Soporte'
            name='soporte'
            rules={[{ required: true, message: '¡Por favor seleccionar el tipo de soporte!' }]} >
            <Select
              allowClear
              onChange={(e) => { setSubSupport(subSoporte(e, cert)); setValidSupSoporte("error"); setSoporteObs(e) }}
              options={support}
            />
          </Form.Item>
        </Col>

        {subSupport.length > 0 ?
          <Col span={8}>
            <Form.Item
              label='Sub Soporte'
              name='subsoporte'>
              <Select
                allowClear
                onChange={(e) => { setValidSupSoporte(undefined); setSubSoporteOsbs(e) }}
                status={validSupSoporte}
                disabled={subSupport.length > 0 ? false : true}
                options={[...subSupport]}
              />
            </Form.Item>
          </Col>
          : <></>}

      </Row>
      <Divider>Observaciones</Divider>
      <Row className='row_form_signin' gutter={12}>
        {categ === "CALLBACK" || categ === "CALLINPUT" ?
          <>
            <Col span={8}>
              <Form.Item >
                <Select onChange={(e) => setObs1(e)}
                  allowClear
                  options={CONEXION}
                />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item >
                <Select onChange={(e) => setObs2(e)}
                  allowClear
                  options={OBSERVACIONES}
                />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item >
                <Select onChange={(e) => setObs3(e)}
                  allowClear
                  options={OBSERVACIONES}
                />
              </Form.Item>
            </Col>
          </>
          : <></>
        }
      </Row>
      <Row className='row_form_signin' gutter={12}>
        {categ === "CALLBACK" || categ === "CALLINPUT" ?
          <Col span={8}>
            <Form.Item name="estado" initialValue={true}>
              <Radio.Group
                onChange={(e) => setEstadoObs(e.target.value)}
                options={[
                  { label: 'Abierto', value: true },
                  { label: 'Cerrado', value: false },
                ]}
                optionType="button"
                buttonStyle="solid"
              />
            </Form.Item>
          </Col>
          : <></>}
        {categ === "CALLBACK" || categ === "CALLINPUT" ?
          <>
            <Col span={8}>
              <Form.Item>
                <Radio.Group
                  onChange={(e) => {setPruebaFirm(e.target.value)}}
                  options={[
                    { label: 'Firmo', value: true },
                    { label: 'No firmo', value: false },
                  ]}
                  optionType="button"
                  buttonStyle="solid"
                />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item >
                <Space.Compact direction="horizontal">
                  <Button type="primary" onClick={() => generarObs()} icon={<CodeOutlined />}>Generar</Button>
                  <Button type="primary" onClick={() => setEditObs(!editObs)} icon={<FormOutlined />}>Editar</Button>
                </Space.Compact>
              </Form.Item>
            </Col>
          </>
          : <></>}
      </Row>
      <Row className='row_form_signin' gutter={12}>
        <Col span={24}>
          <Form.Item name="observacion" rules={[{ required: true, min: 10, message: '¡Son como minimo 10 caracteres!' }]}>
            <TextArea rows={6} showCount maxLength={1000} disabled={editObs} />
          </Form.Item>
        </Col>
      </Row>

      <Form.Item >
        <Button loading={formStatus} type="primary" htmlType="submit">Enviar</Button>
      </Form.Item>
    </Form>
  )
};

export default RegisterTipyfiend;