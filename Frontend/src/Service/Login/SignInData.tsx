import { useState, useEffect } from 'react';
import {Header, Url} from './../API/Header'
import axios from 'axios';


const SignInData: any = (url: string, body:any ) => {
    const [data, setData] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(String);
    const [controller, setController] = useState(new AbortController());

    useEffect(() => {
        
        const ruta = Url(url);
        const abortController = new AbortController();
        setController(abortController);
        const headers = Header(abortController.signal, body );

        axios.post(ruta,headers)
            .then((response) => response)
            .then((json) => setData(json))
            .catch((error) => {
                if (error.name === "AbortError") {
                    console.log("Cancelled request");
                } else {
                    setError(error);
                }
            })
            .finally(() => setLoading(false));

        return () => abortController.abort();
    }, []);

    const handleCancelRequest = () => {
        if (controller) {
            controller.abort();
            setError("Cancelled Request");
        }
    };

    return { data, loading, error, handleCancelRequest };
};

export default SignInData;