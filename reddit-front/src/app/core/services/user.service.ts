import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { ConfigService } from './config.service';

const JSON_CONTENT = new HttpHeaders().set(
    'Content-Type',
    'application/json;charset=UTF-8'
);

@Injectable({providedIn: 'root'})
export class UserService {
    constructor(
        private readonly http: HttpClient,
        private readonly configService: ConfigService
    ) { }

    findByUsername(username: string): Observable<User> {
        return this.http.get<User>(this.url(username), {
            headers: JSON_CONTENT
        });
    }

    private url(suffix: string): string {
        return this.configService.apiUrl + 'users/' + suffix;
    }
}
