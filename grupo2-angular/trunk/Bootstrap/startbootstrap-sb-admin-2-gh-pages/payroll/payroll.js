if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiUrl = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/payroll';

function confirmDelete(id) {
    let deleteConfirm = confirm("Tem certeza que deseja excluir o registro?");
    if (deleteConfirm) {
        apiDEL(apiUrl+"/"+id).then(
            function(data){
                console.log(data);
                location.reload();
            }
        )
    }
}

$(window).on('load', () => {
    let apiRequest = apiGET(apiUrl);
    apiRequest.then((response) => {
        let data = response.data;
        data.forEach((item) => {
            $('#tbody').append(`
                <tr>
                    <td>${item.id}</td>
                    <td>${item.collaborator.name}</td>
                    <td>${item.timeLog}</td>
                    <td>
                        <a href="${'discounts.html?id='+item.id}"><i class="fas fa-edit"></i></a>
                        <a href="#" onclick="confirmDelete(${item.id})"><i class="fas fa-trash"></i></a>
                    </td>
                </tr>
            `);
        });
    }).then(() => {
        $('#dataTable').DataTable();
    });
});