import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';

const routes: Routes = [
    {
        path: '',
        component: DashboardComponent,
        children: [
            { path: 'explore', loadChildren: () => import('./pages/explore/explore.module').then(m => m.ExploreModule) },
            { path: 'topics', loadChildren: () => import('./pages/topics/topics.module').then(m => m.TopicsModule) },
            { path: 'comments', loadChildren: () => import('./pages/comments/comments.module').then(m => m.CommentsModule) },
            { path: '**', redirectTo: 'explore', pathMatch: 'full' }
        ],
    },
    { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
    imports: [CommonModule, RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DashboardRoutingModule {}
