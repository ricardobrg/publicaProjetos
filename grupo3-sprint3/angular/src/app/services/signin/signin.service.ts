import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user';
import { LoginData } from 'src/app/models/user/login.data';

@Injectable({
  providedIn: 'root'
})

export class SigninService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  loginUrl = "http://localhost:8080/group_3-0.0.1-SNAPSHOT/authenticate"

  signin(user: LoginData) {
    this.http.post<User>(this.loginUrl, JSON.stringify(user), this.httpOptions)
      .subscribe(function (response) {
        window.sessionStorage.setItem("token", response["token"])
        window.sessionStorage.setItem("accessLevel", response["accessLevel"])
        window.sessionStorage.setItem("name", response["name"])
        window.sessionStorage.setItem("cpf", response["cpf"])
        window.location.href = "http://localhost:4200/home"
      })
  }

}
