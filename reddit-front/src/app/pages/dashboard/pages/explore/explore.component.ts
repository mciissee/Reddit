import { Component, OnInit } from '@angular/core';
import { AuthService, User } from '@reddit/core';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.scss']
})
export class ExploreComponent implements OnInit {

    user?: User;

    constructor(
        private readonly authService: AuthService
    ) { }

    async ngOnInit(): Promise<void> {
        this.user = await this.authService.ready();
    }

}
