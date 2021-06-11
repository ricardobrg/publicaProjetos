import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payroll } from 'src/app/models/payroll/payroll';

@Injectable({
  providedIn: 'root'
})
export class PayrollService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') })
  }

  url = "http://localhost:8080/group_3-0.0.1-SNAPSHOT/payrolls"

  post(payroll) {
    this.http.post<any>(this.url, JSON.stringify(payroll), this.httpOptions)
      .subscribe(function (response) {
        console.log(response)
        window.location.href = "http://localhost:4200/home"
      })
  }

  search(cpf): Observable<Payroll[]>{
    return this.http.get<Payroll[]>(`${this.url}/collaborator/${cpf}`, this.httpOptions)
  }
}
