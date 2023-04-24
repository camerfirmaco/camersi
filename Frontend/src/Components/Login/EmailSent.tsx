import React, { useState } from 'react';
import { ArrowRightOutlined, LockOutlined, QuestionCircleOutlined, CheckCircleFilled } from '@ant-design/icons';
import { Button, Form, Input, Typography, Tooltip, Alert, Result } from 'antd';
import './../../assets/css/ForgotPasswordForm.css';
import { Link } from 'react-router-dom';
import { PinField } from 'react-pin-field';
import './../../assets/css/inputPin.scss';
import { DataForgotPassword , DeleteDataForgotPassword} from '../../Service/Login/SignInData';
import axios from 'axios';
import { rutaApi } from '../../Service/API/Header';

const { Title } = Typography;

const EmailSent: React.FC = () => {
    const data = DataForgotPassword();
    const [code, setCode] = useState(String);
    const [completed, setCompleted] = useState(false);
    const [mensaje, setMensaje] = useState(String);
    const [alert, setAlert] = useState(true);
    const [status, setStatus] = useState("error");
    const onFinish = (values: any) => {
        values.key = data[0];
        values.pin = code;
        values.id = data[3];
        DeleteDataForgotPassword();
        console.log('Received values of form: ', values);
        signInData(values);
    };

    const signInData = (values: Object) => {
        axios.post(rutaApi + 'auth/password/new', values)
            .then((response) => responder('response', response))
            .catch((error) => responder('error', error));

        const responder = (type: 'response' | 'error', response: any) => {
            if (type === 'error') {
                if (response.response.data.mensaje) {
                    console.log(response.response.data.mensaje)
                    setMensaje('El PIN registrado no coincide, Clic para reintentar');
                    setAlert(false);

                }
                else if (response.code) {
                    console.log(response.code)
                    setMensaje('Servico caido ' + response.code);
                    setAlert(false);
                }
            }
            else if (response.data) {
                console.log("cambio exitoso");
                setStatus("success");
                setMensaje('El cambio de contraseña fue exitoso');
                setAlert(false);

            } else {
                setMensaje('El PIN registrado no coincide, Clic para reintentar');
                setAlert(false);
            }
        };
        console.log('Enviado');

    }


    return (<>{alert ?
        <Form
            disabled={!alert}
            layout="vertical"
            name='normal_login_password'
            className='login_password-form'
            initialValues={{ remember: true }}
            onFinish={onFinish}
            style={{ width: "100%", minWidth: "250px", maxWidth: "350px" }}
        >
            <Form.Item className='title_login_password' >
                <Title id='title_login_password' style={{ marginBottom: "0" }} level={3}>Nueva contraseña</Title>
            </Form.Item>
            <Form.Item className='pin-field-container' >
                <Tooltip title={"Digite el PIN que fue enviado al correo " + data[1] + " IMPORTANTE: Solo tiene un intento"}>
                    <QuestionCircleOutlined style={{ margin: "10px" }} />
                </Tooltip>
                <PinField
                    className="pin-field"
                    disabled={completed}
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
        </Form> :
        <Result
            status={status === "error" ? "error" : "success"}
            title={mensaje}
            extra={
                status === "error" ?
                    <Link to="/password"> <Button type="primary" className='login_password-form-button'>
                        Reintentar
                    </Button></Link> :
                    <Link to="/"> <Button type="primary" className='login_password-form-button'>
                    Inciar sesión
                </Button></Link>
            } />}
    </>
    );
};

export default EmailSent;

