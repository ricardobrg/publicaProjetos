import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EntriesData } from 'src/app/models/work-entry/entries-data';

@Injectable({
  providedIn: 'root'
})
export class WorkEntryService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + sessionStorage.getItem('token') })
  }

  url = "http://localhost:8080/group_3-0.0.1-SNAPSHOT/work-hours"

  post(cpf): Observable<any> {
    return this.http.post<any>(`${this.url}/clock`, cpf, this.httpOptions);
  }

  search(form): Observable<EntriesData> {
    form['initDate'] = form['initDate'].replaceAll('-', '.')
    form['finalDate'] = form['finalDate'].replaceAll('-', '.')
    return this.http.get<EntriesData>(`${this.url}/${form['cpf']}/${form['initDate']}-${form['finalDate']}`, this.httpOptions);
  }

}
