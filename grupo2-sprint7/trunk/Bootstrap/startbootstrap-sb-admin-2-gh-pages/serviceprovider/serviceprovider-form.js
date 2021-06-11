if (!localStorage.token) {
    window.location.href = "../login/login.html";
}

const apiURL = 'http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/providers';

$(window).on('load', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const editParam = urlParams.get('edit');
    const idProvider = urlParams.get('id');

    if (editParam && idProvider) {
        apiGET(apiURL + '/' + idProvider).then((data) => {
            fillForms(data);
        });
    }

    $("#saveButton").on('click', () => {
        let dataFromForm = getFormValues();
        let dataFromContactForm = getFormContactValues();
        let dataFromAddressForm = getFormAddressValues();

        if (editParam){
            formatFormValues(dataFromForm);
            apiEDIT(apiURL+'/'+idProvider, dataFromForm).then((data) => {
                window.location.href="./serviceprovider-list.html";
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

            apiPOST('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/providers', dataFromForm).then((data) => {
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
 * 
 * @param {Object} data 
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