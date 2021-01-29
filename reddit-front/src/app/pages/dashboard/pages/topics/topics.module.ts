import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TopicsComponent } from './topics.component';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '@reddit/core';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
        { path: '', component: TopicsComponent, canActivate: [AuthGuard] },
        { path: ':id', component: TopicsComponent },
    ])
  ],
  declarations: [TopicsComponent]
})
export class TopicsModule { }
