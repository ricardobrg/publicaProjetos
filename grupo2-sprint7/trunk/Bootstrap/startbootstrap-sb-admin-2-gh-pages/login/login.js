$(window).on('load', () => {
    let cpf = null;
    let password = null;
    $('#cpf').on('change keyup', (event) => {
        cpf = event.target.value;
    });
    $('#password').on('change keyup', (event) => {
        password = event.target.value;
    });

    $("#loginForm").on('submit', (event)=>{
        event.preventDefault();
        let bodyRequest = {
            "cpf": cpf,
            "password" : password
        }

        fetch('http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/login', {
            method: 'POST',
            withCredentials: true,
            body: JSON.stringify(bodyRequest),
            headers: {
                'Content-Type': 'application/json'
            },
        }).then((response) => {
            response.json().then((data) => {
                if(data.token){
                    localStorage.setItem('token',data.token.token);
                    localStorage.setItem('loggedUser', JSON.stringify(data.loggedObject));
                    window.location.href = "../index.html";
                } else {
                    window.location.href = "./login.html";
                }
                
            })
        }).catch((error) => {
            console.error(error);
        });

        
    });
});
