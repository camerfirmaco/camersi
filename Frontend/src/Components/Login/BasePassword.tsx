import React from 'react';
import {Row, Col} from 'antd';
import { Outlet } from 'react-router-dom';
import './../../assets/css/ForgotPasswordForm.css';

const BasePassword: React.FC = () => {

    return (
        <Row className='row_login_password'>
            <Col className='col_login_password' span={8} >
                <div className='image_login_password'></div>
                <Outlet />
            </Col>
            <Col className='col_login_password img_login_password' span={8} >

            </Col>
        </Row>

    );
};

export default BasePassword;

