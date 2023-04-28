import axios from "axios";
import { DataTipyfiend, etiqueta, typeResultMethod } from "./Utils/Interfaces";
 const toTipyfiend = (response:any)=>{
    let data:any = response;
    data.etiquetas=[response.cetificado, ];
    return data;
 }

 const toDataTipyfiend = (response:any)=>{
    const data:DataTipyfiend[] = [];
    response?.map((e:any)=>(data.push(toTipyfiend(e))));
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



export {getTipyfiend}