import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8081/send-email';

  constructor(private http: HttpClient) { }

  sendEmail(user: Object): Observable<any> {
    return this.http.post(`${this.baseUrl}`, user);
  }

}
