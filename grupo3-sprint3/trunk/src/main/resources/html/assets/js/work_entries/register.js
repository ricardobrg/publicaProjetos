function confirmEntry() {

    let data = {"cpf" : sessionStorage.getItem("cpf")}

    var myInit = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') },
        body: JSON.stringify(data)
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/work-hours/clock", myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {

                if (!json['error']) {
                    document.getElementById('entry').innerHTML = json['success'].replace('T', ' ')
                }

            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });

}