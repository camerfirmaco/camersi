import Cookies from 'universal-cookie';

const cookies = new Cookies();

const SaveSignIn = (response: any) => {
  localStorage.setItem("token", response.data.token);
  localStorage.setItem("user", response.data.user);
  localStorage.setItem("vencimiento", response.data.vencimiento);
  console.log("guardados");
}

const SignUp = () => {
  if (localStorage.getItem('token')) {
    localStorage.clear();
    console.log("cierre de sesión");
    return true
  }
  return false
}

const SingIn = () => {
  if (localStorage.getItem('token')){
    console.log("Sesión guardada");
    return true}
  return false
}

const ForgotPassword = (response:any) =>{
  cookies.set("keyforgot", response[0]);
  cookies.set("userforgot", response[1]);
  cookies.set("dateforgot", response[2]);
  cookies.set("idforgot", response[3]);
}

const DataForgotPassword = ()=>{
  return [cookies.get("keyforgot"),cookies.get("userforgot"),cookies.get("dateforgot"),cookies.get("idforgot") ];
}

const DeleteDataForgotPassword = ()=>{
  cookies.remove("keyforgot");
  cookies.remove("userforgot");
  cookies.remove("dateforgot");
  cookies.remove("idforgot");
}

const ValideEmail = (key:any) =>{
  cookies.set("keyvalide", key);
}

const KeyValideEmail = (pin:any) =>{
  let key = cookies.get("keyvalide");
  let body = [pin, key];
  cookies.remove("keyvalide");
  return body;
}

export {KeyValideEmail, ValideEmail, SaveSignIn, SingIn, SignUp, ForgotPassword, DataForgotPassword, DeleteDataForgotPassword}