import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { catchError} from 'rxjs/operators';

import { Observable,throwError } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/login";
  constructor(private http: HttpClient) { }

  checkLogin(loginData: any): Observable<any>{
      return this.http.post<any>(this.url, loginData, httpOptions)
  }
}
