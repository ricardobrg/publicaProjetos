function auth(){

    var formElement = document.getElementById("formLogin");

    var data = {};
    var formData = new FormData(formElement)
    formData.forEach(function(value, key){ 
        data[key] = value;
    });    

    var myInit = { method: 'POST',
    headers:{'Content-Type':'application/json'},
    body: JSON.stringify(data)}

    fetch("http://localhost:8080/group_3-0.0.1-SNAPSHOT/authenticate", myInit).then(function(response) {
        var contentType = response.headers.get("content-type");
        if(contentType && contentType.indexOf("application/json") !== -1) {
          return response.json().then(function(json) {
              if(!json['error']){
                window.sessionStorage.setItem("token",json["token"])
                window.sessionStorage.setItem("accessLevel",json["accessLevel"])
                window.sessionStorage.setItem("name",json["name"])
                window.sessionStorage.setItem("cpf", json["cpf"])
                window.location.href = "http://127.0.0.1:5500/home.html"
              }else{
                alert(json["error"])
              }
          });
        } else {
          console.log("Oops, we haven't got JSON!");
        }
      });
  }

