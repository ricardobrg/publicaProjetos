document.getElementById("loginBtn").addEventListener('click', function(e){
    e.preventDefault()
    
    var username = document.getElementById("inputCpf").value;
    var password = document.getElementById("inputPassword").value;
    
    fetch('http://localhost:8080/grupo1/login', { 
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({username, password})
}).then((response) => {
    response.json().then((data) => {
        localStorage.setItem('token', data);
        location.assign("../Generic selection/genericSelection.html");
    });
});
})