
const rutaApi = "http://localhost:8080/";
const Header = ( signal?: any, body?: any, type?: boolean,) => {

    let contentType = type ? "multipart/form-data" : 'application/json';

    var header = {
        headers: {
            'Content-Type': contentType,
            'Authorization': ''
        },
        signal: signal,
        body: body,
    };

    return header;
}

const Url: any = (url: string) => {
    return rutaApi + url;
}

export { Header, Url };


