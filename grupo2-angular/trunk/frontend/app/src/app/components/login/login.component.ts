import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { LoginService } from './login.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  cpf = new FormControl();
  password = new FormControl();

  error: string;

  constructor(private loginService : LoginService,
              private router : Router) {  }

  ngOnInit(): void {
  }

  verifyLogin():void {
    let loginObject = {
      cpf: this.cpf.value,
      password: this.password.value
    };
  
    this.loginService.checkLogin(loginObject).subscribe(
      (data) => {
      localStorage.setItem('loggedObject', JSON.stringify(data.loggedObject));
      localStorage.setItem('token', data.token.token);
      window.location.href="/";
    }, (error) => {
      this.error = "Login Falhou. Verifique suas credenciais";
    });
    
  }
}
