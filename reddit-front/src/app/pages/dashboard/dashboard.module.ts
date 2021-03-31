import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzPageHeaderModule } from 'ng-zorro-antd/page-header';
import { NzMessageModule } from 'ng-zorro-antd/message';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { DashboardRoutingModule } from './dashboard-routing.module';

import { DashboardComponent } from './dashboard.component';
import { HeaderComponent } from './header/header.component';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    DashboardRoutingModule,

    MatInputModule,
    MatFormFieldModule,

    NzIconModule,
    NzModalModule,
    NzAvatarModule,
    NzButtonModule,
    NzMessageModule,
    NzPopoverModule,
    NzPageHeaderModule,
  ],
  declarations: [DashboardComponent, HeaderComponent]
})
export class DashboardModule { }
