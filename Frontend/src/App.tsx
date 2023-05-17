import React, { useState } from 'react';
import { SmileOutlined } from '@ant-design/icons';
import { Timeline, message } from 'antd';
import Search from 'antd/es/input/Search';
import { useQuery } from 'react-query';
import { getTipyfiendUser, getTipyfiendUserLineTime } from './Service/Support/getTipyfiend';

const App: React.FC = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const [valueSearch, setValueSearch] = useState<string | undefined>(undefined);
  const { data, status } = useQuery(['tipyfiend', valueSearch], getTipyfiendUserLineTime);

  const onSearch = (value: string) => {
    setValueSearch(value);
  };

  return(
    <>
    <Search placeholder='Identificación' onSearch={onSearch} loading={status === "loading" ? true : false} />
  <Timeline mode="left"
    items={data}
  />
  </>
)};

export default App;