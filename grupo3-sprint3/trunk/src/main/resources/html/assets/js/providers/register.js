function registerProvider() {

    let formElement = document.getElementById("formProvider");

    let data = {};
    let contact = {};
    let endereco = {};
    let formData = new FormData(formElement)
    formData.forEach(function (value, key) {
        if (key == 'email' || key == 'telephone') {
            contact[key] = value
        } else {
            if (key == 'cep') {
                endereco['cep'] = value;
            } else {
                data[key] = value;
            }
        }
    });

    data['contact'] = contact;
    data['endereco'] = endereco;

    let myInit = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') },
        body: JSON.stringify(data)
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/service-providers", myInit).then(function (response) {
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