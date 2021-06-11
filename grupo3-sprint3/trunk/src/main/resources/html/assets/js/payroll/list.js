function listarFolhas() {

    console.log('executing')

    let form = document.getElementById('formPayroll');
    let cpf = form['cpf'].value;


    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    var dataset = [];

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/payrolls/collaborator/"+cpf, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {

                if (!json["error"]) {
                    window.sessionStorage.setItem("payrolls",JSON.stringify(json))
                    window.location.href = "payrolls/list.html";
                }

            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}