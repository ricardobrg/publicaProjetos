if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiURL = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments';

apiGET("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator").then((data) => {
data.forEach((item) => {
    $('#collaboratorSelect').append(`
    <option value="${item.id}">${item.name}</option>
    `);
});
});

$(window).on('load', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const editParam = urlParams.get('edit');
    const idDepartment = urlParams.get('id');

    if (editParam && idDepartment) {
        apiGET(apiURL + '/' + idDepartment).then((data) => {
            fillForms(data);
        });
    }

    $("#saveButton").on('click', () => {
        let dataFromForm = getFormValues();
        
        if (editParam){
            formatFormValues(dataFromForm);
            apiEDIT(apiURL+'/'+idDepartment, dataFromForm).then((data) => {
                window.location.href="./department-list.html";
            });
        }else{
            apiPOST('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments',dataFromForm).then((data) => {
                console.log(data);
            });
        }           
           
    });
});


/**
 * This function is used to get attibutes of the form and throw it on a variable
 * that will be used to send the request to Java API
 */
function getFormValues() {
    let elements = document.getElementsByClassName('form-input');

    let formValues = {};
    
    Array.prototype.forEach.call(elements, function(el) {
        formValues[el.getAttribute("data-name")] = el.value;
    });
    return formValues;
}

/**
 * 
 * @param {Object} data 
 */
function fillForms(data) {
    let elements = document.getElementsByClassName('form-input');

    Array.prototype.forEach.call(elements, function(item) {
        let fieldName = item.getAttribute('data-name');
        item.value = data[fieldName];
        if (fieldName === 'manager') {
            item.value = data[fieldName].id
        }
    });
}

function formatFormValues(data){
    data.manager = {id:parseInt(data.manager)};
    if (data.manager.name === "null") {
        data.manager.name = null;
    }
}

