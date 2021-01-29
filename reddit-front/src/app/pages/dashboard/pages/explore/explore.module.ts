import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExploreComponent } from './explore.component';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { NzButtonModule } from 'ng-zorro-antd/button';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
        { path: '', component: ExploreComponent }
    ]),

    MatCardModule,

    NzButtonModule,
  ],
  declarations: [ExploreComponent]
})
export class ExploreModule { }
