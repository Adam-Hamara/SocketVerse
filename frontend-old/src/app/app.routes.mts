import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component.mjs';
import { authGuard } from './core/auth/authGuard.mjs';
import { LayoutComponent } from './shared/layout/layout.component.mjs';

export const routes: Routes = [
  {
    path: 'home',
    component: LayoutComponent,
    children: [
      {
        path: '',
        loadComponent: () => import('./features/home/home.component.mjs').then(m => m.HomeComponent),
        canActivate: [authGuard],
      }
    ]
  },
  {
    path: 'chats',
    component: LayoutComponent,
    children: [
      {
        path: '',
        loadComponent: () => import('./features/chats/chats.component.mjs').then(m => m.ChatsComponent),
        canActivate: [authGuard],
      }
    ]
  },
  { path: '**', redirectTo: "home"},
];
