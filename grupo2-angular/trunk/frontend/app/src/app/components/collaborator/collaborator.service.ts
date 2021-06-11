import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Collaborator } from './models';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};

@Injectable({
  providedIn: 'root'
})
export class CollaboratorService {

  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator"
  private url2 = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/payroll"
  constructor(private http: HttpClient) { }

  getCollabs(): Observable<Collaborator[]>{
    return this.http.get<Collaborator[]>(this.url, httpOptions);
  }

  insertCollab(data:any): Observable<any>{
    return this.http.post<any>(this.url, JSON.stringify(data), httpOptions);
  }

  generatePayroll(id : number): Observable<any>{
    return this.http.post<any>(this.url2+`/${id}`, null, httpOptions);
  }
}