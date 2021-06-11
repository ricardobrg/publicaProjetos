function verifyVacation() {
    let form = document.getElementById('verifyVacationForm');
    let cpf = form['cpf'].value;
    let date = form['date'].value.replaceAll('-', '.');

    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/vacations/"+cpf+"-"+date, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {

                if (!json["error"]) {

                    let table = document.getElementById('data_modal_table');

                    let elegivel = json['canVacation'] ? 'Sim' : 'Não';
                    
                    table.innerHTML = ''
                    + '<tr>'
                    + '<th>Nome</th>'
                    + '<td>' + json["name"] + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<th>CPF</th>'
                    + '<td>' + json["cpf"] + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<th>Dias de Férias</th>'
                    + '<td>' + json["vacationDays"] + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<th>Pagamento das Férias</th>'
                    + '<td>' + json["vacationPayment"] + '</td>'
                    + '</tr>'
                    + '<tr>'
                    + '<th>Elegível para Férias?</th>'
                    + '<td>' + elegivel + '</td>'
                    + '</tr>';

                }

            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}

function confirmVacation() {
    let form = document.getElementById('confirmVacationForm');
    let cpf = form['cpf'].value;
    let date = form['date'].value.replaceAll('-', '.');

    var myInit = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/vacations/allowed/"+cpf+"-"+date, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {

                let msg = document.getElementById('vacationMsg');

                if (!json["error"]) {
                    
                    msg.innerHTML = ''
                    + 'Férias Confirmadas. Data máxima de retorno: '
                    + '<strong> ' + json['returnDate'] +' </strong>'

                }else {
                    msg.innerHTML = json['error'];
                }

            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}