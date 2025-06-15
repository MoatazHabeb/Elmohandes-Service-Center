// @ts-ignore
import {HttpEvent, HttpRequest} from '@angular/common/module.d-CnjH8Dlt';
import {HttpHandler, HttpInterceptor} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from '../auth/auth.service';
import {Injectable} from '@angular/core';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.authService.isUserLogIn()) {
      request = request.clone({
        setHeaders: {
          Authorization: sessionStorage.getItem('token')
        }
      });
    }
    return next.handle(request);
  }

}
