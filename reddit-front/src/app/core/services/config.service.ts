import { DOCUMENT } from '@angular/common';
import { Inject, Injectable } from '@angular/core';

@Injectable({providedIn: 'root'})
export class ConfigService {

    get apiUrl(): string {
        return '/api/';
    }

    constructor(
        @Inject(DOCUMENT)
        private readonly document: any
    ) { }

}
