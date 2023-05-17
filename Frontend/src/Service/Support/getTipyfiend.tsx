import axios from "axios";
import { DataTipyfiend, DataUserTipyfiend, etiqueta, typeResultMethod } from "./Utils/Interfaces";
import { userDatos, userLineTime } from "./Utils/ArrayListObjext";
import { TimelineItemProps } from "antd";
const toTipyfiend = (response: any) => {
   let data: any = response;
   data.etiquetas = [response.cetificado, response.categoria, response.soporte, response.subSoporte];
   return data;
}

const toDataTipyfiend = (response: any) => {
   const data: DataTipyfiend[] = [];
   response?.map((e: any) => (data.push(toTipyfiend(e))));
   console.log(data);
   return data;
}


async function getTipyfiend() {
   var data: DataTipyfiend[];
   const response = await axios.get("http://localhost:8083/support");
   data = toDataTipyfiend(response.data);
   console.log(response.data);
   return data;
}

async function getTipyfiendUser(QueryKey:any) {
   var data: DataUserTipyfiend | undefined;
   const response = await axios.get(`http://localhost:8083/support/history/${QueryKey?.queryKey[1]}`);
   data = userDatos(response.data);
   return data;
}

async function getTipyfiendUserLineTime(QueryKey:any) {
   var data: TimelineItemProps[] | undefined;
   const response = await axios.get(`http://localhost:8083/support/history/${QueryKey?.queryKey[1]}`);
   data = userLineTime(response.data);
   return data;
}


function postTipyfiend(values:any) {
   var data = axios.post(`http://localhost:8083/support`, values);
   return data;
}
export { getTipyfiend, getTipyfiendUser, postTipyfiend, getTipyfiendUserLineTime }