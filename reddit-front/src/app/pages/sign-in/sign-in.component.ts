import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AuthService, User } from 'src/app/core';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent implements OnInit {
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
            username: [null, [Validators.required]],
            password: [null, [Validators.required]],
        });
    }

    signIn(): void {
        this.submitting = true;
        this.authService.signIn(this.form.value).then(user => {
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
