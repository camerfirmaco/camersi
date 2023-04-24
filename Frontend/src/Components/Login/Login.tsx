import React, { useState, useEffect } from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Checkbox, Form, Input, Row, Col, Typography, message, Alert } from 'antd';
import './../../assets/css/Login.css';
import { Link, Navigate } from 'react-router-dom';
import { rutaApi } from './../../Service/API/Header'
const { Title } = Typography;
import axios from 'axios';
import { SaveSignIn, SingIn } from '../../Service/Login/SignInData';


const Login: React.FC = () => {
    const [form] = Form.useForm();
    const [mensaje, setMensaje] = useState(String);
    const [alert, setAlert] = useState(true);
    const [login, setLogin] = useState(false);
    const singIn = SingIn();

    const onFinish = (values: any) => {
        signInData(values);
    };

    useEffect(() => {
        if (singIn)
            setLogin(true);
    }, [login]);

    const signInData = (values: Object) => {
        axios.post(rutaApi + 'auth/login', values)
            .then((response) => responder('response', response))
            .catch((error) => responder('error', error));

        const responder = (type: 'response' | 'error', response: any) => {
            if (type === 'error') {
                if (response.response.data.mensaje) {
                    console.log(response.response.data)
                    switch (response.response.data.detalles) {
                        case "User account is locked":
                            setMensaje('El usuario esta bloqueado, comunicarse con el soporte');
                            setAlert(false);
                            break;

                            case "User is disabled":
                            setMensaje('El usuario esta desactivo, comunicarse con el soporte');
                            setAlert(false);
                            break;

                            case "User account has expired":
                            setMensaje('El usuario esta eliminado');
                            setAlert(false);
                            break;

                            case "Bad credentials":
                            setMensaje('Las credenciales son incorrectas');
                            setAlert(false);
                            break;

                        default:
                            setMensaje('ERROR EN EL SISTEMA '+response.response.data.detalles);
                            setAlert(false);
                            break;
                    }


                }
                else if (response.code) {
                    console.log(response.code)
                    setMensaje('Servico caido ' + response.code);
                    setAlert(false);
                }
            }
            else {
                setAlert(true);
                SaveSignIn(response);
                setLogin(true);
            }
        };
        console.log('Enviado');

    }
    const alerta = () => {
        return <Alert message={mensaje} type='error' showIcon />
    }
    return (

        <Row className='row_login'>
            <Col className='col_login img_login' span={8} >
            </Col>
            <Col className='col_login' span={8} >
                <div className='image_login'></div>
                <Form
                    onFinish={onFinish}
                    form={form}
                    layout='vertical'
                    name='normal_login'
                    className='login-form'
                    initialValues={{ remember: true }}
                    style={{ width: '100%', minWidth: '250px', maxWidth: '350px' }}
                >
                    <Form.Item className='title_login' >
                        <Title id='title_login' level={3}>Inicio de sesión</Title>
                    </Form.Item>

                    <Form.Item
                        name='username'
                        rules={[{ required: true, message: '¡Por favor digitar el usuario!' }]}
                    >
                        <Input prefix={<UserOutlined className='site-form-item-icon' />} placeholder='Usuario' />
                    </Form.Item>
                    <Form.Item
                        name='password'
                        rules={[{ required: true, message: '¡Por favor digitar la contraseña!' }]}
                    >
                        <Input.Password prefix={<LockOutlined className='site-form-item-icon' />}
                            type='password'
                            placeholder='Contraseña' />
                    </Form.Item>
                    <Form.Item>
                        <Form.Item name='remember' valuePropName='checked' noStyle>
                            <Checkbox className='check_login'>Recordarme</Checkbox>
                        </Form.Item>

                        <Link className='login-form-forgot login-link' to={'/password'}>
                            ¿Olvide la contraseña?
                        </Link>
                    </Form.Item>

                    <Form.Item>
                        <Button type='primary' htmlType='submit' className='login-form-button'>
                            Iniciar sesión
                        </Button>
                        O <Link className='login-link' to={'/register'}>Regístrate!</Link>
                    </Form.Item>
                    {alert ? <></> : alerta()}
                    {login ? <Navigate to="/support" replace /> : <></>}
                </Form>
            </Col>
        </Row>

    );
};

export default Login;

