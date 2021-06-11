function registerRole() {

    var data = {};

    let formElement = document.getElementById("formRole");
    let formData = new FormData(formElement)
    formData.forEach(function (value, key) {
        data[key] = value;
    });

    let myInit = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') },
        body: JSON.stringify(data)
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles", myInit).then(function (response) {
        let contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {
                if (json["success"]) {
                    alert(json["success"])
                    window.location.href = "http://127.0.0.1:5500/home.html"
                } else {
                    window.alert(json["error"]);
                }
            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}

