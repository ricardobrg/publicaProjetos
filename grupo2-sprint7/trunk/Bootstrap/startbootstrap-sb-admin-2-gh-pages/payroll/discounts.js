if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

$(window).on('load', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    let apiRequest = apiGET('http://127.0.0.1:8080/grupo2-sprint3-0.0.1-SNAPSHOT/payroll/' + id);
    apiRequest.then((data) => {
        $('#collab_card').append(`
            <div class="card" style="width: 25rem;">
                <div class="card-body">
                    <h2 class="card-title">Nome: ${data.collaborator.name}</h2>
                    <br>
                    <h3 class="card-text">Data de Admissão: ${data.collaborator.admissionDate}</h3>
                    <h3 class="card-text">Salário Bruto: ${data.collaborator.grossSalary}</h3>
                    <h3 class="card-text">Salário Final: ${(data.finalSalary).toFixed(2)}</h3>
                </div>
            </div>
        `);
        list = (data.payrollDiscounts).split('/')
        list.forEach(item => {
            discount = item.split(' ')
            if(discount[0] && discount[1] && discount[2])
            $('#tbody').append(`
                    <tr>
                        <td>${discount[0]}</td>
                        <td>${discount[1]}</td>
                        <td>${discount[2]}</td>
                    </tr>
                `);
        })
    })
    .then(() => {
        $('#dataTableCollab').DataTable();
        $('#dataTable').DataTable();
    });
});