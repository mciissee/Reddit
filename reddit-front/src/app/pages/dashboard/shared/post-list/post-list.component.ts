import { Component, Input } from '@angular/core';
import { Post, User } from '@reddit/core';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent {

    @Input()
    user?: User;

    @Input()
    posts: Post[] = [];

    @Input()
    parent?: Post;

    trackById(_: number, item: Post) {
        return item.id;
    }

    sort() {
        console.log('sorting');
        this.posts = this.posts.sort((a, b) => {
            return b.hotness - a.hotness;
        })
    }
}
