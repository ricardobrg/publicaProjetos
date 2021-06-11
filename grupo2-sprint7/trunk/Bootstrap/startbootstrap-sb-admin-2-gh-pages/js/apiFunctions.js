function apiGET(url) {
    return fetch(url, {
        method: 'GET',
        withCredentials: true,
        headers: {
            'Authorization': `Bearer ${localStorage.token}`,
            'Content-Type': 'application/json'
        },
    }).then((response) => {
        return response.json().then((data) => {
            return data;
        })
    }).catch((error) => {
        console.log(error);
        return error;
    });
}

function apiPOST(url, requestBody) {
    return fetch(url, {
        method: 'POST',
        withCredentials: true,
        body: JSON.stringify(requestBody),
        headers: {
            'Authorization': `Bearer ${localStorage.token}`,
            'Content-Type': 'application/json'
        },
    }).then((response) => {
        return response.json().then((data) => {
            return data;
        })
    }).catch((error) => {
        console.log(error);
        return error;
    });
}


function apiDEL(url) {
    return fetch(url, {
        method: 'DELETE',
        withCredentials: true,
        headers: {
            'Authorization': `Bearer ${localStorage.token}`,
            'Content-Type': 'application/json'
        },
    }).then((response) => {
        return response.json().then((data) => {
            return data;
        })
    }).catch((error) => {
        console.log(error);
        return error;
    });
}


function apiEDIT(url, requestBody){
    return fetch(url, {
        method: 'PUT',
        withCredentials: true,
        body : JSON.stringify(requestBody),
        headers: {
            'Authorization': `Bearer ${localStorage.token}`,
            'Content-Type': 'application/json'
        },
    }).then((response) => {
        return response.json().then((data) => {
            return data;
        })
    }).catch((error) => {
        console.log(error);
        return error;
    });
    
}