if (!localStorage.token) {
    window.location.href = "./login.html";
}

const apiURL = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/indexpage';

$(window).on('load', () => {
    apiGET(apiURL).then((response) => {
        let data = response.data;
        $('#collabAmount').text(data.collabAmount);
        $('#serviceProvider').text(data.serviceProvidersAmount);
        $('#departmentAmount').text(data.departmentsAmount);
        $('#rolesAmount').text(data.rolesAmount);
    });
});