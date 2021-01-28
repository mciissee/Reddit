import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AuthInterceptorProvider } from './interceptors/auth.interceptor';

@NgModule({
  imports: [HttpClientModule],
  exports: [HttpClientModule],
  providers: [
    AuthInterceptorProvider
  ],
})
export class CoreModule { }
