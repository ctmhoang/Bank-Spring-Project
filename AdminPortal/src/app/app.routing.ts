import {ModuleWithProviders} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {UserAccountComponent} from './user-account/user-account.component';
import {PrimaryTransactionComponent} from './primary-transaction/primary-transaction.component';
import {SavingsTransactionComponent} from './savings-transaction/savings-transaction.component';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent // add to app.component
  },
  {
    path: 'userAccount',
    component: UserAccountComponent
  },
  {
    path: 'primaryTransaction/:username',
    component: PrimaryTransactionComponent
  },
  {
    path: 'savingsTransaction/:username',
    component: SavingsTransactionComponent
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
