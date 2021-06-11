import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ResponseDepartments } from '../department/department.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};

@Injectable({
  providedIn: 'root'
})

export class DepartmentService {

  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/departments"
  constructor(private http: HttpClient) { }

  getDepartments(): Observable<ResponseDepartments[]>{
    return this.http.get<ResponseDepartments[]>(this.url, httpOptions);
  }

  insertDepartment(department:ResponseDepartments): Observable<any>{
    return this.http.post<any>(this.url, JSON.stringify(department), httpOptions);
  }

  deleteDepartment(id: number): Observable<any> {
    return this.http.delete<any>(this.url+`/${id}`, httpOptions);
  }

  getWithId(id: number): Observable<any> {
    return this.http.get<any>(`${this.url}/${id}`, httpOptions);
  }

  editDepartment(id:number, department: any): Observable<any>{
    return this.http.patch<any>(this.url+`/${id}`, department, httpOptions);
  }
}
