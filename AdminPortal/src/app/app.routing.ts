import {ModuleWithProviders} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {UserAccountComponent} from './user-account/user-account.component';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent // add to app.component
  }
  ,
  {
    path: 'userAccount',
    component: UserAccountComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
