import React, { useState } from 'react';
import { ArrowRightOutlined, LockOutlined, QuestionCircleOutlined } from '@ant-design/icons';
import { Button, Form, Input, Typography, Tooltip } from 'antd';
import './../../assets/css/ForgotPasswordForm.css';
import { Link } from 'react-router-dom';
import { PinField } from 'react-pin-field';
import './../../assets/css/inputPin.scss';

const { Title} = Typography;

const EmailSent: React.FC = () => {
    const onFinish = (values: any) => {
        console.log('Received values of form: ', values);
    };
    const [code, setCode] = useState("");
    const [completed, setCompleted] = useState(false);

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
                <Title id='title_login_password' style={{marginBottom: "0"}} level={3}>Nueva contraseña</Title>
            </Form.Item>
            <Form.Item className='pin-field-container' >
                <Tooltip title="Digite el PIN que fue enviado al correo ma***************@gmail.com">
                    <QuestionCircleOutlined style={{margin: "10px"}}/>
                </Tooltip>
                <PinField
                    className="pin-field"
                    onChange={setCode}
                    length={6}
                    onComplete={() => setCompleted(true)}
                    format={k => k.toUpperCase()}
                    validate="0123456789" inputMode="numeric"
                />
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

        </Form>

    );
};

export default EmailSent;

