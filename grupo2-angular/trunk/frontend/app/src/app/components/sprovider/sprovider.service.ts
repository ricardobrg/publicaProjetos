import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { RequestUpdate, ResponseProviders, ResponseUpdate, SProvider } from './sprovider.model';
import { HttpHeaders } from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.token
  })
};

@Injectable({
  providedIn: 'root'
})

export class ProviderService {

  private url = "http://localhost:8080/grupo2-sprint3-0.0.1-SNAPSHOT/providers"
  constructor(private http: HttpClient) { }

  getProviders(): Observable<ResponseProviders[]> {
    return this.http.get<ResponseProviders[]>(this.url, httpOptions);
  }

  getProvider(id: string): Observable<SProvider> {
    const _url = `${this.url}/${id}`;
    return this.http.get<any>(_url, httpOptions);
  }

  saveProvider(data: any): Observable<any> {
    return this.http.post<any>(this.url, data, httpOptions);
  }
  
  updateProvider(id: string, request: RequestUpdate): Observable<ResponseUpdate> {
    const _url = `${this.url}/${id}`;
    return this.http.patch<RequestUpdate>(_url, request, httpOptions);
  }

  deleteProvider(id: number): Observable<any> {
    const _url = `${this.url}/${id}`;
    return this.http.delete<any>(_url, httpOptions);
  }
}
