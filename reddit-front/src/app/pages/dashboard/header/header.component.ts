import { Component, OnInit } from '@angular/core';
import { AuthService, User } from '@reddit/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    readonly links: Link[] = [
        { title: 'Explore', icon: 'global', href: '/dashboard/explore', visible: () => true, },
        { title: 'Discussions', icon: 'message', href: '/dashboard/topics', visible: () => !!this.user, },
        { title: 'Commentaires', icon: 'comment', href: '/dashboard/comments', visible: () => !!this.user, },
    ];

    user?: User;

    constructor(
        private readonly authService: AuthService
    ) { }

    async ngOnInit(): Promise<void> {
        this.user = await this.authService.ready();
    }

    signOut(): void {
        this.authService.signOut();
    }
}


interface Link {
    icon: string;
    href: string;
    title: string;
    visible: () => boolean;
}
