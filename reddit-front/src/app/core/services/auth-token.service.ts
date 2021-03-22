import { Injectable } from '@angular/core';

const USER_KEY = 'auth-user';
const TOKEN_KEY = 'auth-token';

@Injectable({
  providedIn: 'root'
})
export class AuthTokenService {

    token(): string | undefined {
        return localStorage.getItem(TOKEN_KEY) || undefined;
    }

    username(): string | undefined {
        return localStorage.getItem(USER_KEY) || undefined;
    }

    save(token: string, username: string): void {
        localStorage.setItem(TOKEN_KEY, token);
        localStorage.setItem(USER_KEY, username);
    }

    delete(): void {
        localStorage.removeItem(USER_KEY);
        localStorage.removeItem(TOKEN_KEY);
    }

}
