import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Provider } from 'src/app/models/provider/provider';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') })
  }

  loginUrl = "http://localhost:8080/group_3-0.0.1-SNAPSHOT/service-providers"

  post(provider) {
    this.http.post<Provider>(this.loginUrl, JSON.stringify(provider), this.httpOptions)
      .subscribe(function (response) {
        console.log(response)
        window.location.href = "http://localhost:4200/home"
      })
  }

  get(): Observable<Provider[]> {
    return this.http.get<Provider[]>(this.loginUrl, this.httpOptions)
  }

  getOne(id): Observable<Provider> {
    return this.http.get<Provider>(this.loginUrl + "/" + id, this.httpOptions)
  }

  put(provider, i){
    this.http.put<Provider>(this.loginUrl+"/"+i, provider, this.httpOptions)
    .subscribe(function (response) {
      window.location.href = "http://localhost:4200/provider/list"
    })
  }

  delete(id){
    this.http.delete<Provider>(this.loginUrl+"/"+id, this.httpOptions)
    .subscribe(function (response) {
      window.location.href = "http://localhost:4200/provider/list"
    })
  }

}