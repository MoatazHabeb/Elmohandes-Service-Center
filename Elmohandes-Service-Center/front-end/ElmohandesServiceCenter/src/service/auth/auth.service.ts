import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from "rxjs";
import { catchError, map, tap } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = 'http://localhost:1050/manager';

  private currentUserSubject = new BehaviorSubject<any>(null);
  currentUser$ = this.currentUserSubject.asObservable();
  private loggedInSubject = new BehaviorSubject<boolean>(this.hasToken());
  isLoggedIn$ = this.loggedInSubject.asObservable();

  constructor(private http: HttpClient) {

  }

  private hasToken(): boolean {
    return !!sessionStorage.getItem('token');
  }
  isUserLogIn(){
    return sessionStorage.getItem('token') != null &&  sessionStorage.getItem('token') != undefined;
  }

  createAccount(name: string, email: string, phoneNumber: string, password: string, age: string): Observable<any> {
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;

    if (!passwordPattern.test(password)) {
      return throwError({
        messageEn: 'Password must be at least 8 characters long and include letters, numbers, and special characters.',
        messageAr: 'يجب أن تكون كلمة المرور مكونة من 8 أحرف على الأقل وتشمل حروفًا وأرقامًا ورموزًا خاصة.'
      });
    }

    return this.http.post(this.baseUrl + '/create-user', {
      fullname: name,
      email,
      phoneNumber,
      password,
      age
    }).pipe(
      map(response => response),
      catchError((error: HttpErrorResponse) => {
        if (error.status === 400 && error.error) {
          return throwError(error.error);
        }
        return throwError({
          messageEn: 'An unexpected error occurred.',
          messageAr: 'حدث خطأ غير متوقع.'
        });
      })
    );
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(this.baseUrl + '/login', { email, password }).pipe(
      tap((response: any) => {
        if (response.token) {
          sessionStorage.setItem('token', response.token);
          sessionStorage.setItem('roles', response.roles || '');
          this.currentUserSubject.next(response);
          this.loggedInSubject.next(true);
        }
      })
    );
  }

  logout(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('roles');
    this.currentUserSubject.next(null);
    this.loggedInSubject.next(false);
  }

  isUserLoggedIn(): boolean {
    return this.hasToken();
  }

  isUserAdmin(): boolean {
    const roles = sessionStorage.getItem('roles');
    return roles ? roles.includes('ADMIN') : false;
  }

  getToken(): string | null {
    return sessionStorage.getItem('token');
  }
}
