import { Component, OnInit } from '@angular/core';
import { AuthService, Post, PostService, User } from '@reddit/core';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent implements OnInit {
    user?: User;
    comments: Post[] = [];
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

        this.postService.listCommentsOfAuthor(this.user.username).subscribe(comments => {
            this.comments = comments;
            this.requesting = false;
        });
    }
}
