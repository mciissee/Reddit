import { TemplateRef, ViewChild } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { AuthService, User, PostService, Post } from '@reddit/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.scss']
})
export class ExploreComponent implements OnInit {

    @ViewChild("tplTitle", { read: TemplateRef })
    tplTitle!: TemplateRef<{}>;

    @ViewChild("tplContent", { read: TemplateRef })
    tplContent!: TemplateRef<{}>;

    @ViewChild("tplFooter", { read: TemplateRef })
    tplFooter!: TemplateRef<{}>;


    user?: User;
    topics: Post[] = [];
    requesting = true;
    newTopicTitle = '';
    newTopicContent = '';

    get canSubmitTopicForm(): boolean {
        return !!this.newTopicTitle && !!this.newTopicContent;;
    }

    constructor(
        private readonly authService: AuthService,
        private readonly postService: PostService,
        private readonly modalService: NzModalService,
        private readonly nzMessageService: NzMessageService,
    ) {}


    async ngOnInit(): Promise<void> {
        this.user = await this.authService.ready();
        this.postService.listTopics().subscribe(topics => {
            this.topics = topics;
            this.requesting = false;
        });
    }


    async createTopic(): Promise<void> {
        try {
        const topic = await this.postService
            .createTopic({
                title: this.newTopicTitle,
                content: this.newTopicContent,
            })
            .toPromise();

            this.newTopicTitle = "";
            this.newTopicContent = "";

            this.topics.push(topic);

            this.nzMessageService.success("Topic créé !");
        } catch (error) {
            console.error(error);
            this.nzMessageService.error(
                "Une erreur est survenue lors de la création du topic, Veuillez réessayer plus tard !"
            );
        }
    }

    openTopicDialog() {
        this.modalService.create({
            nzTitle: this.tplTitle,
            nzContent: this.tplContent,
            nzFooter: this.tplFooter,
            nzMaskClosable: false,
            nzClosable: false,
        });
    }
}
