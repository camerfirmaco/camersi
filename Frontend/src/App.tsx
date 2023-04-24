import React, { useState } from 'react';
import { Button, Form, Steps, Tooltip, Typography } from 'antd';
import { UserOutlined, SmileOutlined, MailOutlined, ArrowLeftOutlined, QuestionCircleOutlined, CheckCircleFilled, ArrowRightOutlined } from '@ant-design/icons';
import PinField from 'react-pin-field';
import { Link } from 'react-router-dom';
const { Title } = Typography;
const App: React.FC = () => {


  const [code, setCode] = useState(String);
  const [completed, setCompleted] = useState(false);

  return (
    <>
      <Steps
        current={0}
        status="process"
        items={[
          {
            title: 'Información personal',
            icon: <UserOutlined />
          },
          {
            title: 'Verificación de correo',
            icon: <MailOutlined />
          },
          {
            title: 'Registro exitoso',
            icon: <SmileOutlined />
          },
        ]}
      />
      {true ?
        <div style={{ display: "flex", justifyContent: "center" }}>
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
            <Form.Item style={{ display: "flex", justifyContent: "space-around" }}>
                <Button type='default'  >
                  <ArrowLeftOutlined />Regresar
                </Button>
              <Tooltip title={"Se enviara el correo a la siguiente dirección de correo: "}>
                <Button className='login_password-form-button' type='primary'  >
                  Reenviar
                </Button>
              </Tooltip>
              <Button disabled={!completed} type='primary' className='login_password-form-button'>
                Verificar <ArrowRightOutlined />
              </Button>
            </Form.Item>
          </Form> </div> : <></>}
    </>

  )
};

export default App;