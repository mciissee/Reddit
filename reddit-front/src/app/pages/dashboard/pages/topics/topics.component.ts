import { Component, OnInit } from '@angular/core';
import { AuthService, Post, PostService, User } from '@reddit/core';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

    user?: User;
    topics: Post[] = [];
    requesting = true;

    constructor(
        private readonly authService: AuthService,
        private readonly postService: PostService,
    ) {}


    async ngOnInit(): Promise<void> {
        this.user = await this.authService.ready();
        if (!this.user) {
            this.requesting = false;
            return;
        }

        this.postService.listTopicsOfAuthor(this.user.username).subscribe(topics => {
            this.topics = topics;
            this.requesting = false;
        });
    }
}
