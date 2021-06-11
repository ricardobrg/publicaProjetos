import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collaborator } from 'src/app/models/collaborator/collaborator';

@Injectable({
  providedIn: 'root'
})
export class CollaboratorService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') })
  }

  loginUrl = "http://localhost:8080/group_3-0.0.1-SNAPSHOT/collaborators"

  post(collaborator) {
    this.http.post<Collaborator>(this.loginUrl, JSON.stringify(collaborator), this.httpOptions)
      .subscribe(function (response) {
        console.log(response)
        window.location.href = "http://localhost:4200/home"
      })
  }

  get(): Observable<Collaborator[]> {
    return this.http.get<Collaborator[]>(this.loginUrl, this.httpOptions)
  }

  getOne(id): Observable<Collaborator> {
    return this.http.get<Collaborator>(this.loginUrl + "/" + id, this.httpOptions)
  }

  put(collaborator, id){
    this.http.put<Collaborator>(this.loginUrl+"/"+id, collaborator, this.httpOptions)
    .subscribe(function (response) {
      console.log(response)
      window.location.href = "http://localhost:4200/collaborator/list"
    })
  }

  delete(id){
    this.http.delete<Collaborator>(this.loginUrl+"/"+id, this.httpOptions)
    .subscribe(function (response) {
      window.location.href = "http://localhost:4200/collaborator/list"
    })
  }
}
