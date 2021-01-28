import { HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
import { Injectable, Provider } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { AuthTokenService } from '../services/auth-token.service';

/**
 * Intercepts http request to add Authorization header with logged user json web token.
 */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authTokenService: AuthTokenService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.authTokenService.token();
    if (token != null) {
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token),
      });
    }
    return next.handle(req);
  }
}

export const AuthInterceptorProvider: Provider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true,
};
