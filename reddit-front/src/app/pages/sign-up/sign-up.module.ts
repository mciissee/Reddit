import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignUpComponent } from './sign-up.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
        { path: '', component: SignUpComponent }
    ])
  ],
  declarations: [SignUpComponent]
})
export class SignUpModule { }
