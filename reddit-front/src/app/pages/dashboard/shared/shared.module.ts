import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ListItemCommentComponent } from './list-item-comment/list-item-comment.component';
import { ListItemTopicComponent } from './list-item-topic/list-item-topic.component';

import { NzCommentModule } from 'ng-zorro-antd/comment';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';

@NgModule({
    imports: [CommonModule, NzIconModule, NzAvatarModule, NzCommentModule, NzToolTipModule],
    exports: [ListItemCommentComponent, ListItemTopicComponent],
    declarations: [ListItemCommentComponent, ListItemTopicComponent],
})
export class DashboardSharedModule { }
