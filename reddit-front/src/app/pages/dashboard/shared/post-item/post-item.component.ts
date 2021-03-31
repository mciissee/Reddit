import {
    ChangeDetectionStrategy,
    ChangeDetectorRef,
    Component,
    EventEmitter,
    Input,
    OnInit,
    Output,
    TemplateRef,
    ViewChild,
} from "@angular/core";
import { Comment, Post, PostService, Topic, User, Vote } from "@reddit/core";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalService } from "ng-zorro-antd/modal";

@Component({
    selector: "app-post-item",
    templateUrl: "./post-item.component.html",
    styleUrls: ["./post-item.component.scss"],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PostItemComponent implements OnInit {
    @Input() user?: User;
    @Input() post!: Post;
    @Input() parent?: Post;

    @Output() didChange = new EventEmitter<void>();
    @Output() didDelete = new EventEmitter<void>();

    @ViewChild("tplTitle", { read: TemplateRef })
    tplTitle!: TemplateRef<{}>;

    @ViewChild("tplContent", { read: TemplateRef })
    tplContent!: TemplateRef<{}>;

    @ViewChild("tplFooter", { read: TemplateRef })
    tplFooter!: TemplateRef<{}>;

    vote?: Vote;
    comments: Comment[] = [];
    newComment = "";
    canShowComments = false;

    get title(): string | undefined {
        const o: Topic | Comment = this.post as any;
        if ("title" in o) {
            return o.title;
        }
        return "";
    }

    get canVote(): boolean {
        return !!this.user;
    }

    get canReply(): boolean {
        return !!this.user;
    }

    get canDelete(): boolean {
        return (
            !!this.user &&
            (this.user.role === "ADMIN" ||
                this.user.username === this.post.author)
        );
    }

    get canSubmitCommentForm(): boolean {
        return !!this.newComment;
    }


    constructor(
        private readonly modalService: NzModalService,
        private readonly postService: PostService,
        private readonly nzMessageService: NzMessageService,
        private readonly changeDetectorRef: ChangeDetectorRef
    ) {}

    async ngOnInit(): Promise<void> {
        try {
            if (this.canVote) {
                this.vote = await this.postService
                    .voteStatus(this.post.id)
                    .toPromise();
                this.changeDetectorRef.markForCheck();
            }
        } catch {
            this.vote = undefined;
        }
    }

    async comment(): Promise<void> {
        try {
            const comment = await this.postService
                .createComment({
                    postId: this.post.id,
                    content: this.newComment,
                })
                .toPromise();
            this.newComment = "";
            this.comments.push(comment);
            this.post.comments++;
            this.didChange.emit();
            this.changeDetectorRef.markForCheck();
            this.nzMessageService.success("Commentaire créé !");
        } catch (error) {
            console.error(error);
            this.nzMessageService.error(
                "Une erreur est survenue lors de la création du commentaire, Veuillez réessayer plus tard !"
            );
        }
    }

    async delete(): Promise<void> {
        try {
            const o: Topic | Comment = this.post as any;
            if ("title" in o) {
                await this.postService.deleteTopic(this.post.id).toPromise();
            } else {
                await this.postService.deleteComment(this.post.id).toPromise();
            }

            if (this.parent) {
                this.parent.comments--;
            }

            this.didDelete.emit()
            this.changeDetectorRef.markForCheck();
            this.nzMessageService.success("Post supprimé !");
        } catch (error) {
            console.error(error);
            this.nzMessageService.error(
                "Une erreur est survenue lors de la suppression du post, Veuillez réessayer plus tard !"
            );
        }
    }

    async upvote(): Promise<void> {
        const oldVote = this.vote;
        if (oldVote?.type === 'UP_VOTE') {
            await this.postService.unvote(this.post.id).toPromise();
            this.post.upvotes--;
            this.updateVote(undefined);
            return;
        }

        const newVote = await this.postService.upvote(this.post.id).toPromise();
        if (!oldVote) {
            this.post.upvotes++;
        } else {
            if (oldVote.type === "DOWN_VOTE") {
                this.post.upvotes++;
                this.post.downvotes--;
            }
        }

        this.updateVote(newVote);
    }

    async downvote(): Promise<void> {
        const oldVote = this.vote;
        if (oldVote?.type === 'DOWN_VOTE') {
            await this.postService.unvote(this.post.id).toPromise();
            this.post.downvotes--;
            this.updateVote(undefined);
            return;
        }

        const newVote = await this.postService
            .downvote(this.post.id)
            .toPromise();

        if (!oldVote) {
            this.post.downvotes++;
        } else {
            if (oldVote.type === "UP_VOTE") {
                this.post.downvotes++;
                this.post.upvotes--;
            }
        }

        this.updateVote(newVote);
    }

    async showComments(): Promise<void> {
        this.canShowComments = !this.canShowComments;
        this.comments = await this.postService
            .listComments(this.post.id)
            .toPromise();
        this.changeDetectorRef.markForCheck();
    }

    openCommentDialog() {
        this.modalService.create({
            nzTitle: this.tplTitle,
            nzContent: this.tplContent,
            nzFooter: this.tplFooter,
            nzMaskClosable: false,
            nzClosable: false,
        });
    }


    private updateVote(newVote?: Vote) {
        this.vote = newVote;
        this.post.hotness = this.post.upvotes - this.post.downvotes;
        this.didChange.emit();
        this.changeDetectorRef.markForCheck();
    }

}
