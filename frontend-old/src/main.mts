// main.ts
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component.mjs';
import { appConfig } from './app/app.config.mjs';
import { inject } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { authConfig } from './app/core/auth/auth.config.js';

function configureAuth() {
  const authService = inject(OAuthService);
  authService.configure(authConfig);
  return authService.loadDiscoveryDocumentAndLogin(); // will auto-login if already logged in
}

bootstrapApplication(AppComponent, {
  ...appConfig,
  providers: [
    ...appConfig.providers || [],
    {
      provide: 'APP_INITIALIZER',
      useFactory: () => configureAuth,
      multi: true,
      deps: [OAuthService],
    },
  ]
});
