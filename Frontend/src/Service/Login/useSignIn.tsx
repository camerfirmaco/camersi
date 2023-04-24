import axios from "axios";
import { useState, useEffect } from "react";

export function useSignIn(url:string, body:Object) {
  const [data, setData] = useState(Object);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(Object);
  const [controller, setController] = useState(new AbortController());

  useEffect(() => {
    const abortController = new AbortController();
    setController(abortController);

    axios.post(url, body, { signal: abortController.signal })
      .then((response) => setData(response))
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
}