var json;
var payrollDataSet;
var discountsDataSet;

$(document).ready(function () {

    json = JSON.parse(sessionStorage.getItem("payrolls"))
    payrollDataSet = []

    json.forEach(function (value, key) {
        payrollDataSet.push([value["id"], value["initDate"], value["finalDate"], 
        value["netSalary"]])
    });


    $('#registries_table').DataTable({
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json'
        },
        data: payrollDataSet,
        columns: [
            { title: "id" },
            { title: "Data inicial" },
            { title: "Data Final" },
            { title: "Valor" },
            {
                render: function(){
                    return ''
                    + '<button onclick="discountsModal(this)" data-bs-toggle="modal" data-bs-target="#discounts_modal">Listar Descontos</buton>'
                }
            }
        ]
    });

    $('#discounts_table').DataTable({
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json'
        },
        data: discountsDataSet,
        columns: [
            { title: "Nome" },
            { title: "Valor" },
            { title: "Tipo" }
        ]
    });

})

function discountsModal(element) {
    let id = element.parentElement.parentElement.firstChild.innerHTML;
    let payroll = json.filter(obj => {
        return obj['id'] == id;
    })
    let discounts = payroll[0]['discounts'];
    let discountsDataSet = [];

    discounts.forEach(function(value) {
        let type = value['percentage'] ? 'Percentual' : 'Fixo';
        discountsDataSet.push([value['name'], value['value'], type]);
    })

    let datatable = $('#discounts_table').DataTable();
    datatable.clear();
    datatable.rows.add(discountsDataSet);
    datatable.draw();

}

