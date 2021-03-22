import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CommentsComponent } from './comments.component';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '@reddit/core';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
        { path: '', component: CommentsComponent, canActivate: [AuthGuard] },
        { path: ':id', component: CommentsComponent },
    ])
  ],
  declarations: [CommentsComponent]
})
export class CommentsModule { }
