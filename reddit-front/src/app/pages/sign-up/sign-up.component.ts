import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService, User } from '@reddit/core';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
    form!: FormGroup;
    submitting = false;

    constructor(
        private readonly router: Router,
        private readonly authService: AuthService,
        private readonly formBuilder: FormBuilder,
        private readonly messageService: NzMessageService,
    ) {}

    ngOnInit(): void {
        this.form = this.formBuilder.group({
            email: [null, [Validators.compose([Validators.required, Validators.email])]],
            username: [null, [Validators.compose([Validators.required, Validators.minLength(4)])]],
            password: [null, [Validators.compose([Validators.required, Validators.minLength(4)])]],
        });
    }

    signUp(): void {
        this.submitting = true;
        this.authService.signUp(this.form.value).then(user => {
            if (user) {
                this.onDidSuccessAuthentication(user);
            } else {
                this.onFailedAuthentication(user);
            }
        }).catch(this.onFailedAuthentication.bind(this));
    }

    private onFailedAuthentication(error: any): void {
        this.submitting = false;
        this.messageService.error('Une erreur est survenue, merci de réessayer ultérieurement');
        console.error(error);
    }

    private onDidSuccessAuthentication(user: User): void {
        this.submitting = false;
        this.messageService.success('Bienvenue ' + user.username);
        this.router.navigateByUrl('/dashboard', { replaceUrl: true });
    }

}
