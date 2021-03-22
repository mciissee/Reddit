import { Component, Input } from '@angular/core';
import { Topic, User } from '@reddit/core';

@Component({
  selector: 'app-list-item-topic',
  templateUrl: './list-item-topic.component.html',
  styleUrls: ['./list-item-topic.component.scss']
})
export class ListItemTopicComponent {
    @Input() topic: Topic = {
        id: 0,
        downvotes: {
            count: 100,
            active: true,
        },
        upvotes: {
            count: 100,
            active: false
        },
        date: Date.now(),
        author: 'admin',
        text: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsam maxime dolor, iste nulla, laboriosam consectetur modi unde veniam consequuntur vitae excepturi velit quaerat beatae facere ut distinctio enim aut sed.',
        title: 'Lorem ipsum dolor'
    };

    @Input() user?: User;

    get canVote(): boolean {
        return !!this.user;
    }

    get canReply(): boolean {
        return !!this.user;
    }

    get canDelete(): boolean {
        return !!this.user && (this.user.role === 'ADMIN' || this.user.username === this.topic.author);
    }

    reply(): void {}
    delete(): void {}
    upvote(): void {}
    downvote(): void {}
}
