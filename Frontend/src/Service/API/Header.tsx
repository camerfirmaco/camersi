
const rutaApi = "http://localhost:8083/";
const Header = ( type?: boolean,) => {

    let contentType = type ? "multipart/form-data" : 'application/json';

    var header = {
        headers: {
            'Content-Type': contentType,
            'Authorization' : Authorization,
        }
    };

    return header;
}

const Authorization = () =>{
    const token:string | null = localStorage.getItem('token');
    return token;
}

const Url: any = (url: string) => {
    return rutaApi + url;
}

export { Header, Url , rutaApi};


