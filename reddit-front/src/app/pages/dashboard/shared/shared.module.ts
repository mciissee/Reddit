import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ListItemCommentComponent } from './list-item-comment/list-item-comment.component';
import { ListItemTopicComponent } from './list-item-topic/list-item-topic.component';

@NgModule({
    imports: [CommonModule],
    exports: [ListItemCommentComponent, ListItemTopicComponent],
    declarations: [ListItemCommentComponent, ListItemTopicComponent],
})
export class DashboardSharedModule { }
