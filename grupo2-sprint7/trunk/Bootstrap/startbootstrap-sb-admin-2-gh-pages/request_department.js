function getAllDepartments(){
    fetch("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments/", {mode:"no-cors"})
    .then(response => {
        // valida se a requisição falhou
        if (!response.ok) {
            return new Error('falhou a requisição') // cairá no catch da promise
        }

        // verificando pelo status
        if (response.status === 404) {
            return new Error('não encontrou qualquer resultado')
        }
        response.json().then((json) => {
            $(document).ready(function() {
                $('#dataTable').DataTable({
                  "data" : json
                });
              });
        })
        
    })
}


 

function addDepartment(deparment){
    fetch("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments/", {
        mode:"no-cors",
        method:"POST",
        body: deparment
    })
        .then(response => {
            // valida se a requisição falhou
            if (!response.ok) {
                return new Error('falhou a requisição') // cairá no catch da promise
            }
            
            // verificando pelo status
            if (response.status === 404) {
                return new Error('não encontrou qualquer resultado')
            }
            return response.json()
        })
}  



    
    // fetch("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments/", {
    //     mode:"no-cors",
    //     method:"DELETE"})
    // .then(response => {
    //     // valida se a requisição falhou
    //     if (!response.ok) {
    //         return new Error('falhou a requisição') // cairá no catch da promise
    //     }

    //     // verificando pelo status
    //     if (response.status === 404) {
    //         return new Error('não encontrou qualquer resultado')
    //     }
    //     return response.json()
    // })



    // fetch("http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments/2", {
    //     mode:"no-cors",
    //     method:"PATCH"})
    // .then(response => {
    //     // valida se a requisição falhou
    //     if (!response.ok) {
    //         return new Error('falhou a requisição') // cairá no catch da promise
    //     }

    //     // verificando pelo status
    //     if (response.status === 404) {
    //         return new Error('não encontrou qualquer resultado')
    //     }
    //     return response.json()
    // })





