import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { ResponsePoints } from './point.model';

@Injectable({
  providedIn: 'root'
})

export class PointService {

  getHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.token
      })
    };

  }

  private begin_url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/collaborator/";
  private end_url = "/point";
  constructor(private http: HttpClient) { }

  getPoints(): Observable<ResponsePoints[]> {
    let httpOptions = this.getHttpOptions();
    return this.http.get<ResponsePoints[]>(this.begin_url + "id" + this.end_url, httpOptions);
  }

  getCollaboratorPoints(id: number): Observable<ResponsePoints[]> {
    let httpOptions = this.getHttpOptions();
    return this.http.get<ResponsePoints[]>(this.begin_url + id + this.end_url, httpOptions);
  }
  addPoint(id: number): Observable<String> {
    const _url = this.begin_url + id + this.end_url + "/";
    let httpOptions = this.getHttpOptions();
    return this.http.post<any>(_url, null, httpOptions);
    
  }

  deletePoint(idCollaborator: number, idPoint: number): Observable<any> {
    const _url = this.begin_url + idCollaborator + this.end_url + "/" + idPoint;
    let httpOptions = this.getHttpOptions();
    return this.http.delete<any>(_url, httpOptions);
  }

}
