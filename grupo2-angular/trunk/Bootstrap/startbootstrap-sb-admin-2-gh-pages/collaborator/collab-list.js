if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiUrl = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator';

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

function confirmPayroll(id) {
    let payrollConfirm = confirm("Gerar folha de pagamento?");
    if (payrollConfirm) {
        apiPOST('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/payroll/' + id).then((data) => {
            console.log(data);
        });
    }
}

$(window).on('load', () => {
    let apiRequest = apiGET('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator');
    apiRequest.then((response) => {
        let data = response.data;
        data.forEach((item) => {
            $('#tbody').append(`
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.cpf}</td>
                    <td>${item.admissionDate}</td>
                    <td>${item.role?item.role.name:'Nenhum(a)'}</td>
                    <td>${item.inVacation}</td>
                    <td>
                        <a href="${'./role-form.html?id='+item.id}&edit=true"><i class="fal fa-edit"></i></a>
                        <a href="#" onclick="confirmDelete(${item.id})"><i class="fas fa-trash"></i></a>
                        <a href="#" onclick="confirmPayroll(${item.id})"><i class="fas fa-file-alt"></i></a>
                    </td>
                </tr>
            `);
        });
    }).then(() => {
        $('#dataTable').DataTable();
    });
});