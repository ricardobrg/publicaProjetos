if (!localStorage.token) {
    window.location.href = "../login/login.html";
}


apiGET("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator").then((data) => {
data.forEach((item) => {
    $('#collaboratorSelect').append(`
    <option value="${item.id}">${item.name}</option>
    `);
});
});



$(window).on('load', () => {
    
    $("#buttonSubmit").on('click', () => {
        let idCollab = document.getElementById('collaboratorSelect').value;
        const urlParams = new URLSearchParams(window.location.search);
        const editParam = urlParams.get('edit');
        
        if (editParam)
            apiEDIT(`http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator/${idCollab}/point/`, dataFromForm).then((data) => {
                console.log(data);
            });
        else
            apiPOST(`http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator/${idCollab}/point/`, null).then((data) => {
            console.log(data);
        });
    });
});