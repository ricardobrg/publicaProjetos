var id;

window.onload = initPage;

function initPage(id) {

    const queryString = window.location.search;

    id = queryString.replace("?id=", "")

    var myInit = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') }
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles/" + id, myInit).then(function (response) {
        var contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {
                document.getElementById("name").value = json["name"]
                document.getElementById("accessLevel").value = json["accessLevel"]
            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}

function editRole() {

    var data = {};

    const queryString = window.location.search;

    id = queryString.replace("?id=", "")

    let formElement = document.getElementById("formEdit");

    let formData = new FormData(formElement)

    formData.forEach(function (value, key) {
        data[key] = value;
    });

    let myInit = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') },
        body: JSON.stringify(data)
    }

    console.log(data)

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles/"+id, myInit).then(function (response) {
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