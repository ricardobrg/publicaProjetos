import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VacationService {
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') })
  }
  
  url = "http://localhost:8080/group_3-0.0.1-SNAPSHOT/vacations"

  check(form): Observable<any> {
    form['date'] = form['date'].replaceAll('-', '.')
    return this.http.get(`${this.url}/${form['cpf']}-${form['date']}`, this.httpOptions);
  }
  
  confirm(form): Observable<any> {
    form['exitDate'] = form['exitDate'].replaceAll('-', '.')
    return this.http.post<any>(`${this.url}/allowed/${form['cpf']}-${form['exitDate']}`, null, this.httpOptions)
      .pipe(
        catchError(error => {
          window.alert(error.error['error'])
          return throwError(error.error)
        })
      );
  }
  
  constructor(private http: HttpClient) { }
}
