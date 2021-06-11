if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

$(window).on('load', () => {
    let loggedUser = JSON.parse(localStorage.loggedUser);
    document.getElementById("name").value= loggedUser.name;
    document.getElementById("cpf").value = loggedUser.cpf;
    document.getElementById("pis").value = loggedUser.pis;

    if ( loggedUser.role)
    document.getElementById("role").value = loggedUser.role.name;
    else
    document.getElementById("role").value = "Não possui cargo ainda";

    if ( loggedUser.department)
        document.getElementById("department").value = loggedUser.department.name;
    else
        document.getElementById("department").value = "Não possui departamento ainda";
    
    document.getElementById("admissionDate").value = loggedUser.admissionDate;
    if ( loggedUser.inVacation)
        document.getElementById("inVacation").value = "Está de férias";
    else
        document.getElementById("inVacation").value = "Não está de férias";
    document.getElementById("lastVacation").value = loggedUser.lastVacation;  
    document.getElementById("email").value = loggedUser.contact.email;  
    document.getElementById("phone").value = loggedUser.contact.phone;  
    document.getElementById("street").value = loggedUser.address.street;  
    document.getElementById("district").value = loggedUser.address.district;
    document.getElementById("locality").value = loggedUser.address.locality;   
    document.getElementById("uf").value = loggedUser.address.uf;
    document.getElementById("cep").value = loggedUser.address.cep;     
    
});