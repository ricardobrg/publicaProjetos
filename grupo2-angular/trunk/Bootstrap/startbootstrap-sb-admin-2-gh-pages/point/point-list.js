apiGET("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments").then((data) => {
data.forEach((item) => {
    $('#collaboratorSearch').append(`
    <option value="${item.id}">${item.name}</option>
    `);
});
});

function timeConvert(data) {
    var minutes = data % 60;
    var hours = parseInt((data) / 60);
    
    if (minutes < 10) {
        return `${hours}:0${minutes}`;
    }
    return `${hours}:${minutes}`;

    }

$(window).on('load', () => {
    let apiRequest = apiGET('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator/29/point');
    apiRequest.then((data) => {
        data.forEach((item) => {
            $('#tbody').append(`
                <tr>
                    <td>${item.id}</td>
                    <td>${item.collaborator.name}</td>
                    <td>${item.date}</td>
                    <td>${timeConvert(item.dayMinute)}</td>
                    <a href="${'./point-register.html?id='+item.id}&edit=true"><i class="fas fa-edit"></i></a>
                    <a href="#" onclick="confirmDelete(${item.id})"><i class="fas fa-trash"></i></a>
                </tr>
            `);
        });
    }).then(() => {
        $('#dataTable').DataTable();
    });
});


