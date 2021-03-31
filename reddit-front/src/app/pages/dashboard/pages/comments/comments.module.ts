import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MatCardModule } from "@angular/material/card";
import { RouterModule } from "@angular/router";
import { AuthGuard } from "@reddit/core";
import { NzSkeletonModule } from "ng-zorro-antd/skeleton";
import { DashboardSharedModule } from "../../shared/shared.module";
import { CommentsComponent } from "./comments.component";

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild([
            {
                path: "",
                component: CommentsComponent,
                canActivate: [AuthGuard],
            },
            { path: ":id", component: CommentsComponent },
        ]),

        MatCardModule,
        NzSkeletonModule,

        DashboardSharedModule,
    ],
    declarations: [CommentsComponent],
})
export class CommentsModule {}
