// src/app/auth/auth.guard.ts
import { inject } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = () => {
  const auth = inject(OAuthService);

  if (auth.hasValidAccessToken()) {
    return true;
  }

  auth.initLoginFlow(); // redirect to Keycloak
  return false;
};
