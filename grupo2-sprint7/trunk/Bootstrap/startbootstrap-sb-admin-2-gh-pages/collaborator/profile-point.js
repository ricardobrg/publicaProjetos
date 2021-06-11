function timeConvert(data) {
    var minutes = data % 60;
    var hours = parseInt((data) / 60);
    
    if (minutes < 10) {
        return `${hours}:0${minutes}`;
    }
    return `${hours}:${minutes}`;

    }


$(window).on('load', () => {
    let loggedUser = JSON.parse(localStorage.loggedUser);
    let apiRequest = apiGET(`http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator/${loggedUser.id}/point`);
    apiRequest.then((data) => {
        data.forEach((item) => {
            $('#tbody').append(`
                <tr>
                    <td>${item.id}</td>
                    <td>${item.date}</td>
                    <td>${timeConvert(item.dayMinute)}</td>
                </tr>
            `);
        });
    }).then(() => {
        $('#dataTable').DataTable();
    });
});