import { useState, useEffect } from 'react';

const useSignIn: any = (url: string) => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(String);
    const [controller, setController] = useState(new AbortController());

    useEffect(() => {
        const abortController = new AbortController();
        setController(abortController);

        fetch(url, {method:'POST', mode: "no-cors", body: JSON.stringify({"username": "marcos.rincon1903@gmail.com","password": "hola"}), headers:{'Access-Control-Allow-Origin': 'http://localhost:5173', 'Content-Type': 'application/json'}, signal: abortController.signal })
            .then((response) => response.json())
            .then((json) => setData(json))
            .catch((error) => {
                if (error.name === 'AbortError') {
                    console.log('Cancelled request');
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
            setError('Cancelled Request');
        }
    };

    return { data, loading, error, handleCancelRequest };
};

export default useSignIn;