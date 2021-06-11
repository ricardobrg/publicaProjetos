var id_contact;
var id_endereco;
var id;

window.onload = initPage;

function initPage() {

    const queryString = window.location.search;

    id = queryString.replace("?id=","")

    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/service-providers/"+id, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {
                document.getElementById("nome").value = json["name"]
                document.getElementById("razaoSocial").value = json["socialReason"]
                document.getElementById("cnpj").value = json["cnpj"]
                document.getElementById("telefone").value = json["contact"]["telephone"]
                id_contact = json["contact"]["id"]
                document.getElementById("cep").value = json["endereco"]["cep"]
                id_endereco = json["endereco"]["id"]
                document.getElementById("email").value = json["contact"]["email"]
                document.getElementById("descricao").value = json["description"]
                console.log(json);
            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}

function editProvider() {

    const queryString = window.location.search;

    id = queryString.replace("?id=","")

    let formElement = document.getElementById("formProvider");

    console.log(formElement)

    let data = {};
    let contact = {};
    let role = {};
    let endereco = {};
    let formData = new FormData(formElement)
    formData.forEach(function (value, key) {
        if (key == 'email' || key == 'telephone') {
            contact[key] = value
        } else {
            if (key == 'id') {
                role['id'] = value
            } else {
                if (key == 'cep') {
                    endereco['cep'] = value;
                } else {
                    data[key] = value;
                }
            }
        }
    });

    contact['id'] = id_contact;
    endereco['id'] = id_endereco

    data['contact'] = contact;
    data['role'] = role;
    data['endereco'] = endereco;

    let myInit = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') },
        body: JSON.stringify(data)
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/service-providers/"+id, myInit).then(function (response) {
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