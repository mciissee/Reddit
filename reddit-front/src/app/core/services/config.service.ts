import { DOCUMENT } from '@angular/common';
import { Inject, Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class ConfigService {

    get apiUrl(): string {
        if (this.document.location.origin === 'http://localhost:4200') {
            return 'http://localhost:8080/api/';
        }
        return '/api/';
    }

    constructor(
        @Inject(DOCUMENT)
        private readonly document: any
    ) { }

}
