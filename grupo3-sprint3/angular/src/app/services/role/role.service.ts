import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from 'src/app/models/role/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') })
  }

  loginUrl = "http://localhost:8080/group_3-0.0.1-SNAPSHOT/roles"

  get() : Observable<Role[]>{
    return this.http.get<Role[]>(this.loginUrl, this.httpOptions)
  }

  getOne(i) : Observable<Role>{
    return this.http.get<Role>(this.loginUrl+"/"+i, this.httpOptions)
  }

  post(role) {
    this.http.post<Role>(this.loginUrl, JSON.stringify(role), this.httpOptions)
      .subscribe(function (response) {
        console.log(response)
        window.location.href = "http://localhost:4200/home"
      })
  }

  put(role, i){
    this.http.put<Role>(this.loginUrl+"/"+i, role, this.httpOptions)
    .subscribe(function (response) {
      window.location.href = "http://localhost:4200/role/list"
    })
  }

  delete(id){
    this.http.delete<Role>(this.loginUrl+"/"+id, this.httpOptions)
    .subscribe(function (response) {
      window.location.href = "http://localhost:4200/role/list"
    })
  }
}
