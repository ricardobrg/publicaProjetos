function showData(element) {
    let id = element.parentElement.parentElement.firstChild.innerHTML;
    let table = document.getElementById('data_modal_table');

    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/service-providers/"+id, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {
                
                table.innerHTML = ''
                + '<tr>'
                + '<th>Nome Fantasia</th>'
                + '<td>' + json["name"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Razão Social</th>'
                + '<td>' + json["socialReason"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>CNPJ</th>'
                + '<td>' + json["cnpj"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Descrição</th>'
                + '<td>' + json["description"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Email</th>'
                + '<td>' + json["contact"]["email"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Telefone</th>'
                + '<td>' + json["contact"]["telephone"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>CEP</th>'
                + '<td>' + json["endereco"]["cep"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Cidade</th>'
                + '<td>' + json["endereco"]["localidade"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Bairro</th>'
                + '<td>' + json["endereco"]["bairro"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Logradouro</th>'
                + '<td>' + json["endereco"]["logradouro"] + '</td>'
                + '</tr>'
                + '<tr>'
                + '<th>Complemento</th>'
                + '<td>' + json["endereco"]["complemento"] + '</td>'
                + '</tr>'

            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });

}

var elementToDelete;

function deleteModal(element) {
    elementToDelete = element.parentElement.parentElement;
    let id = element.parentElement.parentElement.firstChild.innerHTML;
    document.getElementById('element_id').innerHTML = id;
}

function deleteElement() {
    let id = document.getElementById('element_id').innerHTML;

    var myInit = {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/service-providers/"+id, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {
                
                if (json["success"]) {
                    elementToDelete.style.display = "none";
                }

            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}

function editData(element){
    let id = element.parentElement.parentElement.firstChild.innerHTML;
    window.location.href="http://127.0.0.1:5500/providers/edit.html?id="+id
}