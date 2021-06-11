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

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles/"+id, myInit).then(function (response) {
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
    window.location.href="http://127.0.0.1:5500/roles/edit.html?id="+id
}