import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {

    constructor(
        private readonly router: Router,
        private readonly authService: AuthService,
    ) { }

    async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
        if (await this.authService.ready()) {
            return true;
        }

        this.router.navigate(['/sign-in'], {
            queryParams: { next: state.url },
            replaceUrl: true
        });

        return false;
    }
}
