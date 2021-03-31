import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TopicsComponent } from "./topics.component";
import { RouterModule } from "@angular/router";
import { MatCardModule } from "@angular/material/card";
import { NzSkeletonModule } from "ng-zorro-antd/skeleton";
import { DashboardSharedModule } from "../../shared/shared.module";

import { AuthGuard } from "@reddit/core";

@NgModule({
    imports: [
        CommonModule,
        RouterModule.forChild([
            { path: "", component: TopicsComponent, canActivate: [AuthGuard] },
            { path: ":id", component: TopicsComponent },
        ]),

        MatCardModule,
        NzSkeletonModule,

        DashboardSharedModule,
    ],
    declarations: [TopicsComponent],
})
export class TopicsModule {}
