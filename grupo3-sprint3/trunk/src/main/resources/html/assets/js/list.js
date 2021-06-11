$(document).ready(function () {
    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    var dataSet = [];

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/collaborators", myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {

                json.forEach(function (value, key) {
                    dataSet.push([value["id"], value["name"], value["cpf"],
                    value["contact"]["email"], value["role"]["name"],
                    value["lastVacationDate"]])
                });
                table(dataSet)
            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });


    function table(dataSet) {
        $('#registries_table').DataTable({
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json'
            },
            data: dataSet,
            columns: [
                { title: "ID" },
                { title: "Nome" },
                { title: "CPF" },
                { title: "Email" },
                { title: "Cargo" },
                { title: "Últimas Ferias" },
                {
                    render: function(){
                        return ''
                        + '<button onclick="editData(this)">Editar</button>'
                        + '<button onclick="deleteModal(this)" data-bs-toggle="modal" data-bs-target="#delete_modal">Deletar</buton>'
                        + '<button onclick="showData(this)" data-bs-toggle="modal" data-bs-target="#data_modal">Ver Mais</button>'
                    }
                }
            ]
        });
    }
});
