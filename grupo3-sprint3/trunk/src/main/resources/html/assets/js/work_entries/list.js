function searchPoint(event) {
    event.preventDefault();

    let form = document.getElementById('formWorkEntry');
    let cpf = form['cpf'].value;
    let d_initial = form['initial-date'].value.replaceAll('-', '.');
    let d_final = form['final-date'].value.replaceAll('-', '.');

    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/work-hours/"+cpf+"/"+d_initial+"-"+d_final, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {

                if (!json["error"]) {
                    window.sessionStorage.setItem("entries",JSON.stringify(json["entries"]))
                    window.location.href = "work_entries/search.html";
                }

            });
            
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}