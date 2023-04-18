import React from 'react';
import { IdcardOutlined, LockOutlined, MailOutlined, PhoneOutlined, ArrowRightOutlined } from '@ant-design/icons';
import { Button, Space, Form, Input, Row, Col, Typography, Select, InputNumber, Checkbox } from 'antd';
import { Link } from 'react-router-dom';
import './../../assets/css/FormLogin.css';

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
    const onFinish = (values: any) => {
        console.log('Received values of form: ', values);
    };

    return (
        <Row className='row_form_login'>
            <Col className='col_form_login' span={16} >
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
                                name='apellido1'
                                rules={[{ required: true, message: '¡Por favor digitar el Apellido!' }]}
                            >
                                <Input placeholder='Apellido' />
                            </Form.Item>
                        </Col>
                        <Col span={8} >
                            <Form.Item
                                label='Segundo Apellido'
                                name='apellido2'
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
                                name='tipoidentificacion'
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

                </Form>
            </Col>
        </Row>

    );
};

export default FormLogin;

