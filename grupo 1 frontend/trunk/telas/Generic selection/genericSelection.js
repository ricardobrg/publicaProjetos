console.log(localStorage.getItem('token'));

document.getElementById("myInfoBtn").addEventListener('click', function(e){
    location.assign("../Profile/ProfileHome.html");
})

document.getElementById("adminBtn").addEventListener('click', function(e){
    location.assign("../Admin/ProfileHomeAdmin.html");
})

document.getElementById("leave").addEventListener('click', function(e){
    location.assign("../Login/login.html");
})