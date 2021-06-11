import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Vacation } from './models';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator/vacationVars"
  constructor(private http: HttpClient) { }

  getvacations(): Observable<Vacation[]>{
    return this.http.get<Vacation[]>(this.url, httpOptions);
  }

  insertCollab(data:any): Observable<any>{
    return this.http.post<any>(this.url, JSON.stringify(data), httpOptions);
  }
}