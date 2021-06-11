import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};

@Injectable({
  providedIn: 'root'
})
export class EventService {
  
  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/events";
  
  constructor(private http:HttpClient) { }

  getEvents(): Observable<any[]>{
    return this.http.get<any[]>(this.url, httpOptions);
  }
}
