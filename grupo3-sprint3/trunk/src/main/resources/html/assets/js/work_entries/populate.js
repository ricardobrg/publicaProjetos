$(document).ready(function () {

    let dataSet = []

    JSON.parse(sessionStorage.getItem('entries')).forEach(function(element) {
        dataSet.push(element.split('T'))
    }) 

    $('#registries_table').DataTable({
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json'
        },
        data: dataSet,
        columns: [
            { title: "Data" },
            { title: "Horário" }
        ]
    });

})

