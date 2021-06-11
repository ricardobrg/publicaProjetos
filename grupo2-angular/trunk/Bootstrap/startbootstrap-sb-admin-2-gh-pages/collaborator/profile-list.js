if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

$(window).on('load', () => {
    let loggedUser = JSON.parse(localStorage.loggedUser);
    document.getElementById("name").value= loggedUser.name;
    document.getElementById("cpf").value = loggedUser.cpf;
    document.getElementById("admissionDate").value = loggedUser.admissionDate;
    document.getElementById("role").value = loggedUser.role_id;
    document.getElementById("inVacation").value = loggedUser.inVacation;  
});