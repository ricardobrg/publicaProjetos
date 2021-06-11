function registerPayroll(event) {

    event.preventDefault();

    let formElement = document.getElementById("payrollForm");

    let data = {};
    let collaborator = {};
    let discount = {};
    let discounts = [];

    let formData = new FormData(formElement)
    
    collaborator['cpf'] = formData.get('cpf')

    for (let i = 0; i < sessionStorage.getItem('discounts'); i++){
        discount = {
            'name' : formData.get('discount-name'+(i+1)),
            'value' : formData.get('discount-value'+(i+1)),
            'type' : formData.get('discount-type'+(i+1))
        }
        discounts.push(discount)
    }

    data['initDate'] = formData.get('initial_date')
    data['finalDate'] = formData.get('final_date')
    data['extraHourPayment'] = formData.get('extra_hour_payment')
    data['collaborator'] = collaborator;
    data['discounts'] = discounts;

    let myInit = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') },
        body: JSON.stringify(data)
    }

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/payrolls", myInit).then(function (response) {
        let contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(function (json) {

                if(json["success"]){
                    alert(json["success"])
                    window.location.href = "http://127.0.0.1:5500/home.html"

                }else{
                    window.alert(json["error"]);
                }

            });
        } else {
            console.log("Oops, we haven't got JSON!");
        }
    });
}