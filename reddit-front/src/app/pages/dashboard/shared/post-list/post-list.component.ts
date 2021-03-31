import { Component, EventEmitter, Input, Output } from '@angular/core';
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

    @Output()
    postsChange = new EventEmitter<Post[]>();

    onChange() {
        this.posts = this.posts.sort((a, b) => {
            return b.hotness - a.hotness;
        })
    }

    onDelete(item: Post) {
        this.posts = this.posts.filter(e => e.id !== item.id).sort((a, b) => {
            return b.hotness - a.hotness;
        });
        this.postsChange.emit(this.posts);
    }


    trackById(_: number, item: Post) {
        return item.id;
    }

}
