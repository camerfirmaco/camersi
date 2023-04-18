import React, { useState } from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Checkbox, Form, Input, Row, Col, Typography } from 'antd';
import './../../assets/css/Login.css';
import { Link } from 'react-router-dom';
import SignInData from './../../Service/Login/SignInData';
import useSignIn from '../../Service/Login/useSignIn';

const { Title } = Typography;



const Login: React.FC = () => {
    const [form, setForm] = useState();
    const onFinish = (values: any) => {
        setForm(values);
        console.log('Received values of form: ', form);
    };
    SignInData("auth/login", {username: "marcos.rincon1903@gmail.com", password : "hola"});




    return (
        <Row className='row_login'>
            <Col className='col_login img_login' span={8} >

            </Col>
            <Col className='col_login' span={8} >
                <div className='image_login'></div>
                <Form
                    layout="vertical"
                    name='normal_login'
                    className='login-form'
                    initialValues={{ remember: true }}
                    onFinish={onFinish}
                    style={{ width: "100%", minWidth: "250px", maxWidth: "350px" }}
                >
                    <Form.Item className='title_login' >
                        <Title id='title_login' level={3}>Inicio de sesión</Title>
                    </Form.Item>
                    <Form.Item
                        name='usuario'
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

                        <Link className='login-form-forgot login-link' to={"/password"}>
                            ¿Olvide la contraseña?
                        </Link>
                    </Form.Item>

                    <Form.Item>
                        <Button type='primary' htmlType='submit' className='login-form-button'>
                            Iniciar sesión
                        </Button>
                        O <Link className='login-link' to={'/register'}>Regístrate!</Link>
                    </Form.Item>

                </Form>
            </Col>
        </Row>

    );
};

export default Login;

