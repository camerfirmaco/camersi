import axios from 'axios';
import { useState, useEffect } from 'react';

const useGetUsers: any = (url: string) => {
    const [datas, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(String);
    const [controller, setController] = useState(new AbortController());

    useEffect(() => {
        const abortController = new AbortController();
        setController(abortController);

        axios.get(url, { signal: abortController.signal })
            .then((response) => setData(response.data))
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

    return { datas, loading, error, handleCancelRequest };
};

export default useGetUsers;