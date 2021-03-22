import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConfigService } from './config.service';

@Injectable({providedIn: 'root'})
export class PostService {

    constructor(
        private readonly http: HttpClient,
        private readonly configService: ConfigService
    ) { }

}
