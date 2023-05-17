import React, { useState } from 'react';
import { ArrowRightOutlined, MailOutlined, IdcardOutlined } from '@ant-design/icons';
import { Button, InputNumber, Form, Input, Typography, Space, Switch, Alert } from 'antd';
import './../../assets/css/ForgotPasswordForm.css';
import { Link, Navigate } from 'react-router-dom';
import { rutaApi } from '../../Service/API/Header';
import axios from 'axios';
import { ForgotPassword, DataForgotPassword } from '../../Service/Login/SignInData';

const { Title } = Typography;

const ForgotPasswordForm: React.FC = () => {
    const [input, setInput] = useState(false);
    const [mensaje, setMensaje] = useState<string>("");
    const [alert, setAlert] = useState(true);
    const [forgot, setForgot] = useState(false);
    const onFinish = (values: any) => {
        console.log('Received values of form: ', values);
        ForgotPasswordData(values);
    };

    const alerta = () => {
        return <Alert message={mensaje} type='error' showIcon />
    }

    const ForgotPasswordData = (values: Object) => {
        axios.post(rutaApi + 'auth/password', values)
            .then((response) => responder('response', response))
            .catch((error) => responder('error', error));

        const responder = (type: 'response' | 'error', response: any) => {
            if (type === 'error') {
                if (response.response.data.mensaje) {
                    console.log(response.response.data)
                    setMensaje('Usuario no encontrado');
                    setAlert(false);
                }
                else if (response.code) {
                    console.log(response.code)
                    setMensaje('Servico caido ' + response.code);
                    setAlert(false);
                }
            }
            else {
                setAlert(true);
                ForgotPassword(response.data);
                setForgot(true);
            }
        };
        console.log('Enviado');

    }

    return (
        <Form
            layout="vertical"
            name='normal_login_password'
            className='login_password-form'
            initialValues={{ remember: true }}
            onFinish={onFinish}
            style={{ width: "100%", minWidth: "250px", maxWidth: "350px" }}
        >
            <Form.Item className='title_login_password' >
                <Title id='title_login_password' level={3}>Olvido de contraseña</Title>
            </Form.Item>
            <Space direction="vertical" style={{ width: '100%' }}>
                <Space wrap>
                    <Switch
                        checked={input}
                        checkedChildren={<IdcardOutlined className='site-form-item-icon' />}
                        unCheckedChildren={<MailOutlined className='site-form-item-icon' />}
                        onChange={() => {
                            setInput(!input);
                        }}
                    />
                    {input ? "Identificación" : "Correo electrónico"}
                </Space>
                {input ?
                    <Form.Item
                        name='identificacion'
                        rules={[{ required: true, message: '¡Por favor digitar la identificación!' }]}
                    >
                        <InputNumber style={{ width: "100%" }} min={1} maxLength={15} prefix={<IdcardOutlined className='site-form-item-icon' />} placeholder='Identificación' />
                    </Form.Item>
                    :
                    <Form.Item
                        name='email'
                        rules={[{ required: true, message: '¡Por favor digitar el email!' }, { type: "email", message: '¡Por favor digitar un email valido!' }]}
                    >
                        <Input prefix={<MailOutlined className='site-form-item-icon' />} placeholder='Email' />
                    </Form.Item>}
            </Space>
            <Form.Item style={{ display: "flex", justifyContent: "flex-end" }}>
                <Link to={"/"}>
                    <Button type='default'  >
                        Cancelar
                    </Button>
                </Link>
                <Button type='primary' htmlType='submit' className='login_password-form-button'>
                    Enviar <ArrowRightOutlined />
                </Button>
            </Form.Item>
            {alert ? <></> : alerta()}
            {forgot ? <Navigate to="valide" replace />: <></>}
        </Form>

    );
};

export default ForgotPasswordForm;

