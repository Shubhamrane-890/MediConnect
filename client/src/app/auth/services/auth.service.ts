import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../../mediconnect/models/User';

export type UserLogin = { username: string; password: string };

export type UserRegistrationDTO = {
  username: string;
  password: string;
  role: string;
  fullName: string;
  contactNumber: string;
  email: string;
  specialty?: string;
  yearsOfExperience?: number;
  dateOfBirth?: string | Date;
  address?: string;
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  /**
   * POST: /user/login
   * Expected response commonly contains token/role/user_id etc.
   * We store token & role if present.
   */
  login(user: Partial<UserLogin>): Observable<{ [key: string]: string }> {
    return this.http
      .post<{ [key: string]: string }>(`${this.loginUrl}/user/login`, user)
      .pipe(
        tap((res) => {
          // Store values if backend returns them
          if (res?.['token']) localStorage.setItem('token', res['token']);
          if (res?.['role']) localStorage.setItem('role', res['role']);
          if (res?.['user_id']) localStorage.setItem('user_id', res['user_id']);
          if (res?.['doctor_id']) localStorage.setItem('doctor_id', res['doctor_id']);
          if (res?.['patient_id']) localStorage.setItem('patient_id', res['patient_id']);
        })
      );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  /**
   * GET users (kept because your diff had getUsers()).
   * If your backend uses a different endpoint, update this URL only.
   */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.loginUrl}/user`);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('user_id');
    localStorage.removeItem('doctor_id');
    localStorage.removeItem('patient_id');
  }

  /**
   * POST: /user/register
   */
  createUser(user: UserRegistrationDTO): Observable<any> {
    return this.http.post<any>(`${this.loginUrl}/user/register`, user);
  }
}
