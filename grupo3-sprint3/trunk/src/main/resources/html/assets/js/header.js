window.onload = initPage;

function initPage(){
    document.getElementById("name").innerHTML = "Olá, " + sessionStorage.getItem("name")

    if(sessionStorage.getItem("accessLevel") <= 1){
        document.getElementById("collaborators").remove()
        document.getElementById("services").remove()
        document.getElementById("roles").remove()
        document.getElementById("payrolls-register").remove()
        document.getElementById("vacations-entry").remove()
    }

    if(sessionStorage.getItem("accessLevel") <= 2){
        
    }

}

