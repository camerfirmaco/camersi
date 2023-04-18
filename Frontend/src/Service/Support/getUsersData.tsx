function getSuspender(promise:any) {
    let status = "pending";
    let response:any;

    const suspender = promise.then(

        (res:any) => {
            status = "success";
            response = res;
        },
        (err:any) => {
            status = "error";
            response = err;
        }
    );
    const read = () => {
        switch (status) {
            case "pending":
                throw suspender;
            case "error":
                throw response;
            default:
                return response;
        }
    };
};

export function getUserData(url: string) {
    const promise = fetch(url)
    .then((response) => response.json())
    .then((json) => json);

    return getSuspender(promise);
}