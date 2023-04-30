import React, { Suspense, useEffect, useState } from 'react';
import { Table, Dropdown, MenuProps, Tooltip, message } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { TableRowSelection } from 'antd/es/table/interface';
import { CheckCircleOutlined, SettingOutlined, UserOutlined, CloseCircleOutlined} from '@ant-design/icons';
import { DataTipyfiend, etiqueta } from '../../Service/Support/Utils/Interfaces';
import { tapEtiqueta } from '../../Service/Support/Utils/Etiquetas';
import { descripcion } from '../../Service/Support/Utils/Descriptions';
import { getTipyfiend } from '../../Service/Support/getTipyfiend';
import { useQuery } from 'react-query';

const handleMenuClick: MenuProps['onClick'] = (e) => {
  message.info('Click on menu item.' + e.key);
  console.log('click', e);
};

const items: MenuProps['items'] = [
  {
    label: 'Historial',
    key: '1Historial',
    icon: <UserOutlined />,
  },
  {
    label: 'Edición',
    key: '2Edicion',
    icon: <UserOutlined />,
  },
  {
    label: 'Eliminación',
    key: '3Eliminacion',
    icon: <UserOutlined />,
    danger: true,
  },
];

const menuProps = {
  items,
  onClick: handleMenuClick,
};

const columns: ColumnsType<DataTipyfiend> = [
  {
    title: 'Identificación',
    width: 120,
    dataIndex: 'documento',
    key: 'documento',
    fixed: 'left',
  },
  {
    title: 'Fecha',
    width: 90,
    dataIndex: 'fecha',
    key: 'fecha',
  },
  {
    title: 'Hora',
    width: 50,
    dataIndex: 'hora',
    key: 'age',
  },
  {
    title: '+',
    width: 30,
    dataIndex: 'estado',
    key: 'estado',
    render: (estado: boolean) => <>{!estado ? <CheckCircleOutlined style={{ color: "#87d068" }} /> : <CloseCircleOutlined style={{ color: "#f50" }} />}</>
  },
  {
    title: 'Etiquetas',
    dataIndex: 'etiquetas',
    key: 'etiquetas',
    ellipsis: {
      showTitle: false,
    },
    render: (etiquetas: etiqueta[]) => <>{etiquetas?.map((etiqueta) => (tapEtiqueta(etiqueta)))}</>
  },
  { title: 'Time', width: 50, dataIndex: 'tiempo', key: 'tiempo' },
  {
    title: 'Action',
    key: 'operation',
    dataIndex: 'id',
    fixed: 'right',
    width: 100,
    render: (key) =>
      <Dropdown.Button
        onOpenChange={() => { console.log(key) }}
        menu={menuProps}
        buttonsRender={([leftButton, rightButton]) => [
          <Tooltip onOpenChange={() => { console.log(key) }} title="Ajustes" key="leftButton">
            {leftButton}
          </Tooltip>,
          React.cloneElement(rightButton as React.ReactElement<any, string>),
        ]}
      >
        <SettingOutlined onClick={() => { console.log(key) }} />
      </Dropdown.Button>,
  }
];
const TableGetSupport: React.FC = () => {
  const { data, status } = useQuery('tipyfiend', getTipyfiend);
  const [selectedRowKeys, setSelectedRowKeys] = useState<React.Key[]>([]);
  const [loading, setLoading] = useState(false);
  const onSelectChange = (newSelectedRowKeys: React.Key[]) => {
    console.log('selectedRowKeys changed: ', newSelectedRowKeys);
    setSelectedRowKeys(newSelectedRowKeys);
  };
  const rowSelection: TableRowSelection<DataTipyfiend> = {
    selectedRowKeys,
    onChange: onSelectChange
  };
  return (<>
    <Table 
    rowSelection={rowSelection}
    rowKey={(record) =>record.id}
      size='small'
      columns={columns}
      expandable={{
        expandedRowRender: (record) => <>{descripcion(record)}</>,
        rowExpandable: () => true,
      }}
      loading={loading}
      footer={() => 'Footer'}
      dataSource={data}
      summary={() => (
        <Table.Summary fixed={'top'}>
          <Table.Summary.Row>
            <Table.Summary.Cell index={0} colSpan={9}>
              Nombre del Agente de soporte
            </Table.Summary.Cell>
          </Table.Summary.Row>
        </Table.Summary>
      )}
      sticky
    />
    </>
  );
};

export default TableGetSupport;