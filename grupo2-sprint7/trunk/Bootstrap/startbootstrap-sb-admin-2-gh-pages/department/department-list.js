if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiUrl = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments';

function confirmDelete(id) {
    let deleteConfirm = confirm("Tem certeza que deseja excluir o registro?");
    if (deleteConfirm) {
        apiDEL(apiUrl + "/" + id).then(
            function (data) {
                console.log(data)
            }
        )

    }
}

$(window).on('load', () => {
    let apiRequest = apiGET('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments');
    apiRequest.then((data) => {
        data.forEach((item) => {
            if (item.manager)
            $('#tbody').append(`
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.manager.name}</td>
                    <td>
                        <a href="${'../department/department-form.html?id='+ item.id}&edit=true"><i class="fas fa-edit"></i></a>
                        <a href="#" onclick="confirmDelete(${item.id})"><i class="fas fa-trash"></i></a>
                    </td>
                </tr>
            `);
        });
    }).then(() => {
        $('#dataTable').DataTable();
    });
});



