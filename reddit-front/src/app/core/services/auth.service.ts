import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { shareReplay, take, tap } from 'rxjs/operators';
import { User } from '../models/user';
import { ConfigService } from './config.service';
import { AuthTokenService } from './auth-token.service';
import { UserService } from './user.service';

const JSON_CONTENT = new HttpHeaders().set(
    'Content-Type',
    'application/json;charset=UTF-8'
);

@Injectable({providedIn: 'root'})
export class AuthService {
    private user?: User;
    private request?: Observable<User | undefined>;

    constructor(
        private readonly http: HttpClient,
        private readonly router: Router,
        private readonly userService: UserService,
        private readonly configService: ConfigService,
        private readonly authTokenService: AuthTokenService,
    ) { }

    ready(): Promise<User | undefined> {
        if (this.request) {
            return this.request.toPromise();
        }

        if (this.user) {
            return Promise.resolve(this.user);
        }

        if (!this.request) {
            this.request = new Observable<User|undefined>(observer => {
                const username = this.authTokenService.username();
                if (!username) {
                    observer.next(undefined);
                    observer.complete();
                    this.request = undefined;
                    return;
                }

                this.userService.findByUsername(username).subscribe({
                    next: response => {
                        observer.next(response);
                        observer.complete();
                        this.request = undefined;
                    },
                    error: error => {
                        observer.error(error);
                        observer.complete();
                        this.request = undefined;
                    }
                });
            }).pipe(
                take(1),
                shareReplay(1)
            );
        }

        return this.request.toPromise();
    }

    signOut(): Promise<boolean> {
        this.user = undefined;
        this.authTokenService.delete();
        return this.router.navigateByUrl('/sign-in', { replaceUrl: true });
    }

    async signIn(args: SignInArgs): Promise<User | undefined> {
        const response = await this.http.post<SignInResponse>(this.url('sign-in'), args, {
            headers: JSON_CONTENT
        }).toPromise();
        this.authTokenService.save(response.token, args.username);
        return this.ready();
    }

    async signUp(args: SignUpArgs): Promise<User | undefined> {
        await this.http.post<void>(this.url('sign-up'), args, {
            headers: JSON_CONTENT
        }).toPromise();

        return this.signIn({
            username: args.username,
            password: args.password
        });
    }

    private url(suffix: string): string {
        return this.configService.apiUrl + 'auth/' + suffix;
    }
}

export interface AuthError {
    code: string;
    message: string;
}

export interface SignInArgs {
    username: string;
    password: string;
}

export interface SignUpArgs {
    email: string;
    username: string;
    password: string;
}

export interface SignInResponse {
    token: string;
}

