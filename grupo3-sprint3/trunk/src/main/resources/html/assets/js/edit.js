var id_contact;
var id_endereco;
var id;

window.onload = initPage;

function initPage(id) {

    const queryString = window.location.search;

    id = queryString.replace("?id=","")

    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/collaborators/"+id, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {
                document.getElementById("nomeCompleto").value = json["name"]
                document.getElementById("cpf").value = json["cpf"]
                document.getElementById("telefone").value = json["contact"]["telephone"]
                id_contact = json["contact"]["id"]
                document.getElementById("cep").value = json["endereco"]["cep"]
                id_endereco = json["endereco"]["id"]
                document.getElementById("email").value = json["contact"]["email"]
                document.getElementById("pis").value = json["pis"]
                document.getElementById("salario").value = json["salary"]
                document.getElementById("cargaHoraria").value = json["workHours"]
            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles", myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {
                json.forEach(function (value, key) {
                    document.getElementById("cargo").innerHTML += "<option value=" + value["id"] + ">" + value["name"] + "</option>"
                });
            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}

function editCollaborator() {

    const queryString = window.location.search;

    id = queryString.replace("?id=","")

    let formElement = document.getElementById("formEdit");

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

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/collaborators/"+id, myInit).then(function (response) {
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