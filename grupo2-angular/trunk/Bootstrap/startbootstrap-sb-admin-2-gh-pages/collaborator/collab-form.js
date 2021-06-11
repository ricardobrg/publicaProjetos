if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiURL = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator';

/**
 * This block of code is used for filing the the Role Select Box
 */
apiGET("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/roles").then((data) => {
data.forEach((item) => {
    $('#roleSelect').append(`
    <option value="${item.id}">${item.name}</option>
    `);
});
});

$(window).on('load', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const editParam = urlParams.get('edit');
    const idCollab = urlParams.get('id');

    if (editParam && idCollab) {
        apiGET(apiURL + '/' + idCollab).then((data) => {
            fillForms(data);
        });
    }

    $("#saveButton").on('click', () => {
        let dataFromForm = getFormValues();
        let dataFromContactForm = getFormContactValues();
        let dataFromAddressForm = getFormAddressValues();

        if (editParam){
            formatFormValues(dataFromForm);
            apiEDIT(apiURL+'/'+idCollab, dataFromForm).then((data) => {
                window.location.href="./collab-list.html";
            });
        }else{
            console.log(dataFromForm);

            // Creates the contact's object
            apiPOST('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/contact', dataFromContactForm).then((data) => {
                console.log(data);
            });
            console.log(dataFromContactForm);
            
            // Creates the address' object
            apiPOST('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/address', dataFromAddressForm).then((data) => {
                console.log(data);
            });
            console.log(dataFromAddressForm);

            /*apiPOST('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator', dataFromForm).then((data) => {
                console.log(data);
            });*/
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
 * This function is used to get contact's attibutes of the form and throw it on a variable
 * that will be used to send the request to Java API to create a new contact's object and returns the ID
 */
function getFormContactValues() {
    let elements = document.getElementsByClassName('form-contact');

    let formValues = {};

    Array.prototype.forEach.call(elements, function(el) {
        formValues[el.getAttribute("data-name")] = el.value;
    });
    return formValues;
}

/**
 * This function is used to get address' attibutes of the form and throw it on a variable
 * that will be used to send the request to Java API to create a new address' object and returns the ID
 */
function getFormAddressValues(){
    let elements = document.getElementsByClassName('form-address');

    let formValues = {};

    Array.prototype.forEach.call(elements, function(el) {
        formValues[el.getAttribute("data-name")] = el.value;
    });
    return formValues;
}

/**
 * This function is used for populating form with data of
 * a existing collaborator. It will only be used in edition forms
 * 
 * @param {Object} data: Collaborator Data that came from API 
 */
function fillForms(data) {
    let elements = document.getElementsByClassName('form-input');

    Array.prototype.forEach.call(elements, function(item) {
        let fieldName = item.getAttribute('data-name');
        item.value = data[fieldName];
        if (fieldName === 'role') {
            item.value = data[fieldName].id
        }
    });
}

function formatFormValues(data){
    data.role = {id:parseInt(data.role.id)};
}