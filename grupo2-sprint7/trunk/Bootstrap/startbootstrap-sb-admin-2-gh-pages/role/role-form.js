if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiURL = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/roles';

/**
 * This block of code is used for filing the the Department Select Box
 */
apiGET("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments").then((data) => {
data.forEach((item) => {
    $('#departmentSelect').append(`
    <option value="${item.id}">${item.name}</option>
    `);
});
});

$(window).on('load', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const editParam = urlParams.get('edit');
    const idRole = urlParams.get('id');

    if (editParam && idRole) {
        apiGET(apiURL + '/' + idRole).then((data) => {
            fillForms(data);
        });
    }

    $("#saveButton").on('click', () => {
        let dataFromForm = getFormValues();
        
        if (editParam){
            formatFormValues(dataFromForm);
            apiEDIT(apiURL+'/'+idRole, dataFromForm).then((data) => {
                window.location.href="./role-list.html";
            });
        }else{
            apiPOST('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/roles',dataFromForm).then((data) => {
                console.log(data);
                window.location.href="./role-list.html";
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
 * This function is used for populating form with data of
 * a existing role. It will only be used in edition forms
 * 
 * @param {Object} data: Role Data that came from API 
 */
function fillForms(data) {
    let elements = document.getElementsByClassName('form-input');

    Array.prototype.forEach.call(elements, function(item) {
        let fieldName = item.getAttribute('data-name');
        item.value = data[fieldName];
        if (fieldName === 'department') {
            item.value = data[fieldName].id
        }
    });
}

function formatFormValues(data){
    data.department = {id:parseInt(data.department)};
    if (data.accessLevel === "null") {
        data.accessLevel = null;
    }
}