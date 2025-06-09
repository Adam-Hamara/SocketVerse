// auth-init.ts
import { OAuthService } from 'angular-oauth2-oidc';
import { authConfig } from './auth.config';

export function initializeAuth(oauthService: OAuthService): () => Promise<void> {
  return async () => {
    oauthService.configure(authConfig);
    oauthService.setupAutomaticSilentRefresh();
    await oauthService.loadDiscoveryDocumentAndTryLogin();
  };
}