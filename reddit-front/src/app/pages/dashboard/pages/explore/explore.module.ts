import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExploreComponent } from './explore.component';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { NzButtonModule } from 'ng-zorro-antd/button';

import { DashboardSharedModule } from '../../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
        { path: '', component: ExploreComponent }
    ]),

    MatCardModule,
    NzButtonModule,

    DashboardSharedModule,
  ],
  declarations: [ExploreComponent]
})
export class ExploreModule { }
