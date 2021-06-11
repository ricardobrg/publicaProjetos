function logout() {
    localStorage.clear();
    window.location.href = "../login/login.html";
}

function login(data) {
    localStorage.setItem('token',data.token.token);
    localStorage.setItem('loggedUser', JSON.stringify(data.loggedObject));
}