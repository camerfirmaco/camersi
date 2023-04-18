import React, { useState } from 'react';
import {
  DesktopOutlined,
  FileOutlined,
  PieChartOutlined,
  TeamOutlined,
  UserOutlined,
  LogoutOutlined,
  StarOutlined,
  SettingOutlined,
  PoweroffOutlined
} from '@ant-design/icons';
import type { MenuProps, MenuTheme } from 'antd';
import { Breadcrumb, Layout, Menu, Avatar, Dropdown, Space, Button, Switch } from 'antd';
import './../../../assets/css/Dashboard.css';
import logoLight from './../../../assets/img/Logo-sin-fondo-blanco.png';
import logoDark from './../../../assets/img/Logo-sin-fondo-black.png';
import { Link } from 'react-router-dom';
import TableGetSupport from '../../Support/TableGetSupport';

const { Header, Content, Footer, Sider } = Layout;

type MenuItem = Required<MenuProps>['items'][number];

function getItem(
  label: React.ReactNode,
  key: React.Key,
  icon?: React.ReactNode,
  children?: MenuItem[],
): MenuItem {
  return {
    key,
    icon,
    children,
    label,
  } as MenuItem;
}

const itemsMenu: MenuItem[] = [
  getItem('Option 1', '1', <PieChartOutlined />),
  getItem('Option 2', '2', <DesktopOutlined />),
  getItem('User', 'sub1', <UserOutlined />, [
    getItem('Tom', '3'),
    getItem('Bill', '4'),
    getItem('Alex', '5'),
  ]),
  getItem('Team', 'sub2', <TeamOutlined />, [getItem('Team 1', '6'), getItem('Team 2', '8')]),
  getItem('Files', '9', <FileOutlined />),
]

function getLink(to: string, name: string) {
  return (<Link style={{ width: "100px" }} to={to}>{name}</Link>);
}

const items: MenuProps['items'] = [
  getItem(getLink("/", "Perfil"), '1', <UserOutlined />),
  getItem(getLink("/", "Ajustes"), '2', <SettingOutlined />),
  getItem(getLink("/", "Roles"), '3', <StarOutlined />),
  { type: 'divider', },
  { key: '4', danger: true, label: getLink("/", "Cerrar Sesión"), icon: <LogoutOutlined /> },
];


const Dashboard: React.FC = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [tema, setTema] = useState<MenuTheme>('dark');
  const changeTheme = (value: boolean) => {
    setTema(value ? 'dark' : 'light');
  };

  return (
    <Layout className='layout-dashboard'>
      {/* Sidebar */}
      <Sider theme={tema} collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
        <div className='nav-dashboard' >
          {/* Logo del sistema */}

        </div>
        {/* Menú */}
        <Menu theme={tema} defaultSelectedKeys={['1']} mode="inline" items={itemsMenu} />
      </Sider >
      <Layout className="site-layout">
        {/* Sidebar */}
        <Header className='header-dashboard' style={tema === 'dark' ? { backgroundColor: "#001529" } : { backgroundColor: "rgb(255, 255, 255)" }} >
          {/* Logo de Camerfirma */}
          <img src={tema === 'dark' ? logoLight : logoDark} alt="camerfirma-logo" className='logo-dashboard' />
          {/* Sub menu con el logo de avatar */}
          <div>
            <Switch
              checked={tema === 'dark'}
              onChange={changeTheme}
              checkedChildren="Dark"
              unCheckedChildren="Light"
            />
            <Dropdown menu={{ items }} placement="bottomRight">
              <a onClick={(e) => e.preventDefault()}>
                <Space>
                  <Avatar className='avatar-dashboard' shape="square" icon={<UserOutlined />} />
                </Space>
              </a>
            </Dropdown>
            <Space>
              <Button
                className='avatar-dashboard'
                danger
                type="primary"
                icon={<PoweroffOutlined />}
              />
            </Space>

          </div>
        </Header>
        <Content style={{ padding: '0 16px' }}>
          <Breadcrumb style={{ margin: '16px 0', }} items={[{ title: 'sample' }, { title: 'sample' }]} />
          <div style={{ padding: 24, minHeight: 360, background: "rgb(255, 255, 255)", borderRadius: "20px" }}>
            <TableGetSupport />
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}>Camerfirma Colombia ©2023 Creado por Marcos :p</Footer>
      </Layout>
    </Layout>
  );
};

export default Dashboard;