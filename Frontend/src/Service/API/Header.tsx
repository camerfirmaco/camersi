
const rutaApi = "http://localhost:8083/";
const Header = ( signal?: any, body?: any, type?: boolean,) => {

    let contentType = type ? "multipart/form-data" : 'application/json';

    var header = {
        'mode': 'cors',
        headers: {
            'Content-Type': contentType,
            'Access-Control-Allow-Origin': 'http://localhost:5173',
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


