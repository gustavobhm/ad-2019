import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private baseUrl = 'http://localhost:8081/api/people';

  constructor(private http: HttpClient) { }

  getPeople(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getPerson(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getPersonByName(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/name/${name}`);
  }

  createPerson(pessoa: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}`, pessoa);
  }

  updatePerson(id: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePerson(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete-all`, { responseType: 'text' });
  }
}
