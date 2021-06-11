import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { ResponsePayrolls } from './payroll.model';
import { HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};

@Injectable({
  providedIn: 'root'
})
export class PayrollService {

  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/payroll"
  constructor(private http: HttpClient) { }

  getPayrolls(): Observable<ResponsePayrolls[]>{
    return this.http.get<ResponsePayrolls[]>(this.url, httpOptions);
  }

  deletePayroll(id: number): Observable<any> {
    return this.http.delete<any>(this.url+`/${id}`, httpOptions);
  }
}
