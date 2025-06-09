import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ChatComponent } from './pages/chat/chat.component';
import { AuthGuard } from './auth/auth.guard';
import { KeycloakAuthGuard } from 'keycloak-angular';

export const routes: Routes = [
    
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'chat',
    component: ChatComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '**',
    redirectTo: "home"
  },
];
