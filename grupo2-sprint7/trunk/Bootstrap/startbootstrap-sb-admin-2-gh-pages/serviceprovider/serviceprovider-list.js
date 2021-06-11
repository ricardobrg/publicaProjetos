if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiUrl = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/providers';

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
    apiRequest.then((data) => {
        
        data.forEach((item) => {
            $('#tbody').append(`
            <tr>
                <td>${item.name}</td>
                <td>${item.contact.phone}</td>
                <td>${item.address}</td>
                <td>${item.cnpj}</td>
                <td>${item.socialReason}</td>
                <td>${item.price}</td>
                <td>${item.description}</td>
                <td>
                    <a href="${'./role-form.html?id='+item.id}&edit=true"><i class="fas fa-edit"></i></a>
                    <a href="#" onclick="confirmDelete(${item.id})"><i class="fas fa-trash"></i></a>
                </td>
                </tr>
            `);
        });

    }).then(() => {
         $('#dataTable').DataTable();
    });
});