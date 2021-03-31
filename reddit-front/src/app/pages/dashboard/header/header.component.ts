import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthService, User } from '@reddit/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';

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

    @ViewChild("tplTitle", { read: TemplateRef })
    tplTitle!: TemplateRef<{}>;

    @ViewChild("tplContent", { read: TemplateRef })
    tplContent!: TemplateRef<{}>;

    @ViewChild("tplFooter", { read: TemplateRef })
    tplFooter!: TemplateRef<{}>;


    oldPassword = '';
    newPassword = '';

    user?: User;

    get canSubmitResetPasswordForm() {
        return !!this.oldPassword && this.newPassword?.length >= 4;
    }

    constructor(
        private readonly authService: AuthService,
        private readonly modalService: NzModalService,
        private readonly messageService: NzMessageService,
    ) { }

    async ngOnInit(): Promise<void> {
        this.user = await this.authService.ready();
    }

    signOut(): void {
        this.authService.signOut();
    }


    async resetPassword() {
        this.authService.resetPassword({
            oldPassword: this.oldPassword,
            newPassword: this.newPassword
        }).then(() => {
            this.messageService.success('Votre mot de passe a bien été modifié !');
        }).catch(error => {
            console.error(error);
            this.messageService.error('Une erreur est survenue, merci de réessayer ultérieurement');
        })
    }


    openResetPasswordDialog() {
        this.modalService.create({
            nzTitle: this.tplTitle,
            nzContent: this.tplContent,
            nzFooter: this.tplFooter,
            nzMaskClosable: false,
            nzClosable: false,
        });
    }
}


interface Link {
    icon: string;
    href: string;
    title: string;
    visible: () => boolean;
}
