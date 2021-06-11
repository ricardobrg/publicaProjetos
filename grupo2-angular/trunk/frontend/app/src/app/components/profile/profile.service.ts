import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collaborator } from './profile.model';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};


@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator/"+JSON.parse(localStorage.loggedObject).id;
  
  constructor(private http: HttpClient) { }

  getProfile(): Observable<Collaborator>{
    return this.http.get<Collaborator>(this.url, httpOptions);
  }

}
