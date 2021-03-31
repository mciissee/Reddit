import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { NzAvatarModule } from "ng-zorro-antd/avatar";
import { NzButtonModule } from "ng-zorro-antd/button";
import { NzCommentModule } from "ng-zorro-antd/comment";
import { NzEmptyModule } from "ng-zorro-antd/empty";
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzMessageModule } from "ng-zorro-antd/message";
import { NzModalModule } from "ng-zorro-antd/modal";
import { NzToolTipModule } from "ng-zorro-antd/tooltip";
import { PostItemComponent } from "./post-item/post-item.component";
import { PostListComponent } from "./post-list/post-list.component";

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        MatFormFieldModule,
        NzButtonModule,
        MatInputModule,
        NzIconModule,
        NzAvatarModule,
        NzEmptyModule,
        NzModalModule,
        NzCommentModule,
        NzToolTipModule,
        NzMessageModule,
    ],
    exports: [PostItemComponent, PostListComponent],
    declarations: [PostItemComponent, PostListComponent],
})
export class DashboardSharedModule {}
