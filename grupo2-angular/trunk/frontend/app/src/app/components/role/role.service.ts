import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ResponseRoles, Role, RequestUpdate, ResponseUpdate } from './role.model';
import { HttpHeaders } from '@angular/common/http';
import { ReturnStatement } from '@angular/compiler';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};

@Injectable({
  providedIn: 'root'
})

export class RoleService {

  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/roles"
  constructor(private http: HttpClient) { }

  getRoles(): Observable<ResponseRoles[]> {
    return this.http.get<ResponseRoles[]>(this.url, httpOptions);
  }

  getRole(id: string): Observable<Role> {
    const _url = `${this.url}/${id}`;
    return this.http.get<any>(_url, httpOptions);
  }

  saveRole(data: any): Observable<any> {
    return this.http.post<any>(this.url, data, httpOptions);
  }
  
  updateRole(id: string, request: RequestUpdate): Observable<ResponseUpdate> {
    const _url = `${this.url}/${id}`;
    return this.http.put<RequestUpdate>(_url, request, httpOptions);
  }

  deleteRole(id: number): Observable<any> {
    const _url = `${this.url}/${id}`;
    return this.http.delete<any>(_url, httpOptions);
  }
}
