// src/app/auth/keycloak-auth.guard.ts

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard extends KeycloakAuthGuard {
  constructor(protected override router: Router, protected override keycloakAngular: KeycloakService) {
    super(router, keycloakAngular);
  }

  async isAccessAllowed(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    // Ensure user is logged in
    if (!this.authenticated) {
      await this.keycloakAngular.login({
        redirectUri: window.location.origin + state.url
      });
      return false;
    }

    // Optionally check roles or permissions here
    // const requiredRoles = route.data['roles'];
    // if (requiredRoles && !requiredRoles.some(role => this.roles.includes(role))) {
    //   this.router.navigate(['/unauthorized']);
    //   return false;
    // }

    return true;
  }
}
