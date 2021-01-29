import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzPageHeaderModule } from 'ng-zorro-antd/page-header';

import { DashboardRoutingModule } from './dashboard-routing.module';

import { DashboardComponent } from './dashboard.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule,

    NzIconModule,
    NzAvatarModule,
    NzButtonModule,
    NzPopoverModule,
    NzPageHeaderModule,
  ],
  declarations: [DashboardComponent, HeaderComponent]
})
export class DashboardModule { }
