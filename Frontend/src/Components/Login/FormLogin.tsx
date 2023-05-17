import React, { useState } from 'react';
import { ArrowLeftOutlined, QuestionCircleOutlined, CheckCircleFilled, IdcardOutlined, LockOutlined, MailOutlined, PhoneOutlined, ArrowRightOutlined } from '@ant-design/icons';
import { Button, Space, Form, Input, Row, Col, Typography, Select, InputNumber, Checkbox, Alert, Tooltip, Result } from 'antd';
import { Link } from 'react-router-dom';
import './../../assets/css/FormLogin.css';
import axios from 'axios';
import { rutaApi } from '../../Service/API/Header';
import PinField from 'react-pin-field';
import { KeyValideEmail, ValideEmail } from '../../Service/Login/SignInData';

const { Title } = Typography;
const options = [
    {
        value: 'CC',
        label: 'CC',
    },
    {
        value: 'CE',
        label: 'CE',
    },
    {
        value: 'NIT',
        label: 'NIT',
    },
    {
        value: 'NIP',
        label: 'NIP',
    },
    {
        value: 'TI',
        label: 'TI',
    },
    {
        value: 'PAS',
        label: 'PAS',
    },
];

const FormLogin: React.FC = () => {

    const [code, setCode] = useState<string>("");
    const [completed, setCompleted] = useState(false);
    const [mensaje, setMensaje] = useState<string>("");
    const [alert, setAlert] = useState(true);
    const [verificar, setVerificar] = useState(false);
    const [resultado, setResultado] = useState(false);
    const [valid, setValid] = useState(false);
    const [form, setForm] = useState();

    const onFinish = (values: any) => {
        console.log('Received values of form: ', values);
        setVerificar(true);
        valideEmail(values.email);
        setForm(values);
    };

    const valideEmail = (email: any) => {
        console.log(email);
        axios.post(rutaApi + 'auth/email', { "email": email })
            .then((response) => responder('response', response))
            .catch((error) => responder('error', error));

        const responder = (type: 'response' | 'error', response: any) => {
            if (type === 'error') {
                console.log(response.code)
                console.log(response);
                setMensaje('Servico caido ' + response.code);
                setAlert(false);
            }
            else {
                ValideEmail(response.data);
                console.log(response);
                setAlert(true);
            }
        };
        console.log('Correo Enviado');
    }

    const keyMailValide = () => {
        axios.post(rutaApi + 'auth/email/valide', KeyValideEmail(code))
            .then((response) => responder('response', response))
            .catch((error) => responder('error', error));

        const responder = (type: 'response' | 'error', response: any) => {
            if (type === 'error') {
                console.log(response.code)
                console.log(response);
                setMensaje('Servico caido ' + response.code);
                setAlert(false);
            }
            else {
                if (response.data) {
                    setAlert(true);
                    console.log(response);
                    console.log("formulario enviado");
                    setMensaje("Solicitud de registro exitoso");
                    signInRegister(form);
                    //
                } else {
                    setAlert(false)
                    console.log(response);
                    console.log("El PIN no coincide")
                    setMensaje("El PIN no coincide");
                }

            }
        };
        console.log('Correo Enviado');
    }

    const signInRegister = (values: Object | undefined) => {
        axios.post(rutaApi + 'auth/register', values)
            .then((response) => responder('response', response))
            .catch((error) => responder('error', error));

        const responder = (type: 'response' | 'error', response: any) => {
            if (type === 'error') {
                if (response.response.data.mensaje) {
                    console.log(response.response.data.mensaje)
                    setMensaje('Error en el carge de la información ' + response.response.data.mensaje);
                    setAlert(false);

                }
                else if (response.code) {
                    console.log(response.code)
                    setMensaje('Servico caido ' + response.code);
                    setAlert(false);
                }
            }
            else {
                if (response.status == 202) {
                    setMensaje('Usuario existente, por favor iniciar sesión!');
                    setAlert(false);
                    setResultado(true);
                } else {
                    setAlert(true);
                    setValid(true);
                    setResultado(true);
                    console.log(response);
                }
            }
        };
        console.log('Enviado');

    }

    const alerta = (status: boolean) => {
        if (status) {
            return <Alert message={mensaje} type='success' showIcon />
        }
        return <Alert message={mensaje} type='error' showIcon />
    }

    const result = () => {
        return (

            <Result
                status={valid ? "success" : "error"}
                title={mensaje}
                subTitle = {valid?"Su solicitud se encuentra en proceso de verificación, debe esperar a la notificación por correo electrónico, su cuenta se mantendra bloqueada hasta la confirmación":""}
                />
        )
    }

    return (
        <Row className='row_form_login'>
            <Col className='col_form_login' style={!verificar ? {} : { minWidth: "330px", maxWidth: "600px", maxHeight: "600px", height: "50%", padding: "50px" }} span={16} >
                {!verificar ?
                    <Form
                        layout='vertical'
                        name='normal_login'
                        initialValues={{ remember: true }}
                        onFinish={onFinish}
                    >
                        <Form.Item className='title_form_login' >
                            <Title id='title_form_login' level={3}>Formulario de registro</Title>
                        </Form.Item>
                        <Row className='row_form_signin' gutter={12}>
                            <Col span={8} >
                                <Form.Item
                                    label='Nombre'
                                    name='nombre'
                                    rules={[{ required: true, message: '¡Por favor digitar el nombre!' }]}
                                >
                                    <Input placeholder='Nombre' />
                                </Form.Item>
                            </Col>
                            <Col span={8} >
                                <Form.Item
                                    label='Primer Apellido'
                                    name='primerApellido'
                                    rules={[{ required: true, message: '¡Por favor digitar el Apellido!' }]}
                                >
                                    <Input placeholder='Apellido' />
                                </Form.Item>
                            </Col>
                            <Col span={8} >
                                <Form.Item
                                    label='Segundo Apellido'
                                    name='segundoApellido'
                                >
                                    <Input placeholder='Apellido' />
                                </Form.Item>
                            </Col>
                            <Col span={12} >
                                <Form.Item
                                    label='Email'
                                    name='email'
                                    rules={[{ required: true, message: '¡Por favor digitar el email!' }, { type: "email", message: '¡Por favor digitar un email valido!' }]}
                                >
                                    <Input prefix={<MailOutlined className='site-form-item-icon' />} placeholder='Email' />
                                </Form.Item>
                            </Col>
                            <Col span={12} >
                                <Space.Compact style={{ width: "100%" }}>
                                    <Form.Item
                                        label='Identificación'
                                        name='tipoDocumento'
                                        rules={[{ required: true, message: '¡Por favor seleccionar el tipo de identificación!' }]}
                                    >
                                        <Select options={options} />
                                    </Form.Item>
                                    <Form.Item
                                        label=' '
                                        name='identificacion'
                                        rules={[{ required: true, message: '¡Por favor digitar la identificación!' }]}
                                    >
                                        <InputNumber style={{ width: "100%" }} min={1} maxLength={15} prefix={<IdcardOutlined className='site-form-item-icon' />} placeholder='Identificación' />
                                    </Form.Item>
                                </Space.Compact>
                            </Col>
                            <Col span={12} >
                                <Form.Item
                                    label='Teléfono'
                                    name='telefono'
                                >
                                    <InputNumber style={{ width: "100%" }} min={1} maxLength={10} prefix={<PhoneOutlined className='site-form-item-icon' />} placeholder='Teléfono' />
                                </Form.Item>
                            </Col>
                            <Col span={12}>
                                <Form.Item
                                    label='Género'
                                    name='genero'
                                    rules={[{ required: true, message: '¡Por favor seleccionar el género!' }]}
                                >
                                    <Select placeholder='Género'
                                        options={[{ value: 'MA', label: 'Masculino' }, { value: 'FE', label: 'Femenino' }, { value: 'NA', label: 'Otros/No informa' }]}
                                    />
                                </Form.Item>
                            </Col>
                            <Col span={12} >
                                <Form.Item
                                    name="password"
                                    label="Contraseña"
                                    rules={[
                                        {
                                            required: true,
                                            message: '¡Por favor digitar la contraseña!',
                                        },
                                    ]}
                                    hasFeedback
                                >
                                    <Input.Password prefix={<LockOutlined className='site-form-item-icon' />} />
                                </Form.Item>
                            </Col>
                            <Col span={12}>
                                <Form.Item
                                    name="confirm"
                                    label="Confirmar contraseña"
                                    dependencies={['password']}
                                    hasFeedback
                                    rules={[
                                        {
                                            required: true,
                                            message: '¡Por favor confirmar la contraseña!',
                                        },
                                        ({ getFieldValue }) => ({
                                            validator(_, value) {
                                                if (!value || getFieldValue('password') === value) {
                                                    return Promise.resolve();
                                                }
                                                return Promise.reject(new Error('The two passwords that you entered do not match!'));
                                            },
                                        }),
                                    ]}
                                >
                                    <Input.Password prefix={<LockOutlined className='site-form-item-icon' />} />
                                </Form.Item>
                            </Col>

                        </Row>
                        <Form.Item
                            name="agreement"
                            valuePropName="checked"
                            rules={[
                                {
                                    required: true,
                                    message: '!Por favor aceptar los terminos y condiciones!',
                                },
                            ]}
                        >
                            <Checkbox>
                                Acepto los <a href="">términos y condiciones</a>
                            </Checkbox>
                        </Form.Item>

                        <Form.Item style={{ display: "flex", justifyContent: "end" }}>
                            <Row gutter={12}>
                                <Col>
                                    <Link to={"/"}>
                                        <Button type='default' >
                                            Cancelar
                                        </Button>
                                    </Link>
                                </Col>
                                <Col>
                                    <Button type='primary' htmlType='submit' className='login-form-button'>
                                        Registrarse <ArrowRightOutlined />
                                    </Button>
                                </Col>

                            </Row>

                        </Form.Item>
                    </Form> :
                    <>{!resultado ?
                        <Form
                            layout="vertical"
                            name='normal_login_password'
                            className='login_password-form'
                            style={{ width: "100%", minWidth: "250px", maxWidth: "350px" }}
                        >
                            <Form.Item className='title_login_password' >
                                <Title id='title_login_password' style={{ marginBottom: "0" }} level={3}>Verificación de Correo</Title>
                            </Form.Item>
                            <Form.Item className='pin-field-container' style={{ marginBottom: "30px" }} >
                                <Tooltip title={"Digite el PIN que fue enviado al correo " + " IMPORTANTE: Solo tiene un intento"}>
                                    <QuestionCircleOutlined style={{ margin: "10px" }} />
                                </Tooltip>
                                <PinField
                                    className="pin-field"
                                    onChange={setCode}
                                    length={6}
                                    onComplete={() => setCompleted(true)}
                                    format={k => k.toUpperCase()}
                                    validate="0123456789" inputMode="numeric"
                                />
                                <Tooltip title={completed ? "" : "Por favor digitar el PIN enviado al correo"}>
                                    <CheckCircleFilled style={completed ? { margin: "10px", color: "#52c41a" } : { margin: "10px" }} />
                                </Tooltip>
                            </Form.Item>
                            <Form.Item style={{ display: "flex", justifyContent: "space-around" }}>
                                <Button type='default' onClick={() => { setVerificar(false) }} >
                                    <ArrowLeftOutlined />Regresar
                                </Button>
                                <Tooltip title={"Se enviara el correo a la siguiente dirección de correo: "}>
                                    <Button className='login_password-form-button' type='primary'  >
                                        Reenviar
                                    </Button>
                                </Tooltip>
                                <Button disabled={!completed} type='primary' onClick={() => { keyMailValide() }} className='login_password-form-button'>
                                    Verificar <ArrowRightOutlined />
                                </Button>
                            </Form.Item>
                            {alert ? <></> : alerta(true)}
                        </Form> : result()}
                    </>
                }

            </Col>
        </Row>

    );
};

export default FormLogin;

