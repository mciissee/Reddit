import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExploreComponent } from './explore.component';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzSkeletonModule } from 'ng-zorro-antd/skeleton';
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzMessageModule } from "ng-zorro-antd/message";
import { NzModalModule } from "ng-zorro-antd/modal";

import { DashboardSharedModule } from '../../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild([
        { path: '', component: ExploreComponent }
    ]),

    MatCardModule,
    NzButtonModule,
    NzSkeletonModule,

    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    NzIconModule,
    NzModalModule,
    NzMessageModule,

    DashboardSharedModule,
  ],
  declarations: [ExploreComponent]
})
export class ExploreModule { }
