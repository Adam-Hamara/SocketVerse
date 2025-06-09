import { APP_INITIALIZER, ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes.mjs';
import { OAuthModule, OAuthService } from 'angular-oauth2-oidc';
import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import { initializeAuth } from './core/auth/auth-init.js';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    
    // ✅ Required providers
    OAuthService,
    HttpClient,
    importProvidersFrom(
      HttpClientModule,
      OAuthModule.forRoot()
    ),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeAuth,
      deps: [OAuthService],
      multi: true
    },
    OAuthService
  ]
};
