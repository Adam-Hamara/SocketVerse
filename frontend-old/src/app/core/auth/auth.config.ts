// src/app/auth.config.ts
import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  issuer: 'http://localhost:8080/realms/socket_verse-realm', // Your Keycloak realm
  redirectUri: window.location.origin,
  clientId: 'message_app',
  responseType: 'code',
  scope: 'openid profile email',
  showDebugInformation: true,
  strictDiscoveryDocumentValidation: false,
  timeoutFactor: 0.75,
};
