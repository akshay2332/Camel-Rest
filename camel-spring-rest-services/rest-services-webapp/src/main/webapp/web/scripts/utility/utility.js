function createElementWithProp(elementName, properties) {

    let element = document.createElement(elementName);
    if (properties) {
        Object.entries(properties).forEach(([key, value]) => {
            element.setAttribute(key, value);
        });
    }
    return element;
}


async function executePostRequest(url, data, headers) {


    const response = await fetch(`${baseURL}${url}`, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: headers,
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });

    /*
    const jsonString = response.json();
    console.log(jsonString);
     */
    return response; // parses JSON response into native JavaScript objects
}
