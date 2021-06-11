var token = localStorage.getItem('token');
var data;

fetch('http://localhost:8080/grupo1/collaborator/profile', { 
method: 'GET',
headers: {
    "Content-Type" : "application/json",
    "Authorization": "Bearer " + token
},

}).then((response) => {
    response.json().then((_data) => {
        data = _data;

        /*Profile info*/
        document.getElementById("name").innerHTML          = _data.name;
        document.getElementById("surname").innerHTML       = _data.surname;
        document.getElementById("admission").innerHTML     = _data.admissionDate;
        document.getElementById("birthDate").innerHTML     = _data.birthDate;
        document.getElementById("email").innerHTML         = _data.contact.email;
        document.getElementById("phone1").innerHTML        = _data.contact.phoneList[0];
        try{
            document.getElementById("phone2").innerHTML    = _data.contact.phoneList[1]
        } catch (error){
            console.log("Não existe um segundo número de telefone!")
        } 
        document.getElementById("cpf").innerHTML           = _data.cpf;
        document.getElementById("bairro").innerHTML        = _data.endereco.bairro;
        document.getElementById("cep").innerHTML           = _data.endereco.cep.cep;
        document.getElementById("complemento").innerHTML   = _data.endereco.complemento;
        document.getElementById("cidade").innerHTML        = _data.endereco.localidade;
        document.getElementById("logradouro").innerHTML    = _data.endereco.logradouro;
        document.getElementById("numero").innerHTML        = _data.endereco.numero;
        document.getElementById("genre").innerHTML         = _data.genre.nameGenre;
        document.getElementById("id").innerHTML            = _data.idCollaborator;
        document.getElementById("admission").innerHTML     = _data.lastVacationDate;
        document.getElementById("maritalStatus").innerHTML = _data.maritalStatus;
        document.getElementById("name").innerHTML          = _data.name;
        document.getElementById("accessLevel").innerHTML   = _data.permission.accessLevel.name;
        document.getElementById("department").innerHTML    = _data.permission.department.name;
        document.getElementById("role").innerHTML          = _data.permission.role.name;
        document.getElementById("rg").innerHTML            = _data.rg;
        document.getElementById("salary").innerHTML        = _data.salary;
        document.getElementById("surname").innerHTML       = _data.surname;

        if(_data.imgUrl != null)
            document.getElementById("profilepic").setAttribute("src", _data.imgUrl);
        
        /*EditTab Values*/ 
        document.getElementById("inputname").value         = _data.name;
        document.getElementById("inputsurname").value      = _data.surname;
        document.getElementById("inputcpf").value          = _data.cpf;
        document.getElementById("inputrg").value           = _data.rg;
        document.getElementById("inputbirthDate").value    = _data.birthDate;
        document.getElementById("inputadmissionDate").value= _data.admissionDate;
        document.getElementById("inputgenre").value        = _data.genre.nameGenre;
        document.getElementById("inputmaritalStatus").value= _data.maritalStatus;
        document.getElementById("inputemail").value        = _data.contact.email;
        document.getElementById("inputphone1").value       = _data.contact.phoneList[0];
        try{
            document.getElementById("phone2").value        = _data.contact.phoneList[1]
        } catch (error){
            console.log("Não existe um segundo número de telefone!")
        }
        document.getElementById("inputcep").value          = _data.endereco.cep.cep;
        document.getElementById("inputcidade").value       = _data.endereco.cidade;
        document.getElementById("inputbairro").value       = _data.endereco.bairro;
        document.getElementById("inputlogradouro").value   = _data.endereco.logradouro;
        document.getElementById("inputnumero").value       = _data.endereco.numero;
        document.getElementById("inputcomplemento").value  = _data.complemento;
    });
})

document.getElementById("submitBtn").addEventListener('click', function(e){

    var col = data;
    col.name                      = document.getElementById("inputname").value;
    col.surname                   = document.getElementById("inputsurname").value;
    col.cpf                       = document.getElementById("inputcpf").value;
    col.rg                        = document.getElementById("inputrg").value;
    col.birthDate                 = document.getElementById("inputbirthDate").value;
    col.admissionDate             = document.getElementById("inputadmissionDate").value;
    col.genre                     = document.getElementById("inputgenre").value;
    col.maritalStatus             = document.getElementById("inputmaritalStatus").value;
    col.contact.email             = document.getElementById("inputemail").value;
    col.contact.phoneList[0]      = document.getElementById("inputphone1").value;
    col.contact.phoneList[1]      = document.getElementById("inputphone2").value;
    col.endereco.cep.cep          = document.getElementById("inputcep").value;
    col.endereco.localidade       = document.getElementById("inputcidade").value;
    col.endereco.bairro           = document.getElementById("inputbairro").value;
    col.endereco.logradouro       = document.getElementById("inputlogradouro").value;
    col.endereco.numero           = document.getElementById("inputnumero").value;
    col.endereco.complemento      = document.getElementById("inputcomplemento").value;

    fetch('http://localhost:8080/grupo1/collaborator/'.concat(data.idCollaborator), { 
        method: 'PUT',
        headers: {
            "Content-Type" : "application/json",
            "Authorization": "Bearer " + token
    },
        body: JSON.stringify(col)
    }).then((response) => {
        response.json().then((_data) => {
            console.log("Retorno do servidor: " + _data);
            data = _data;
        });
    });
})