import { Routes } from '@angular/router';
import { Dashboard } from './components/dashboard/dashboard';
import { Home } from './components/home/home';
import { Tournaments } from './components/tournaments/tournaments';
import { RegisterForm } from './components/register-form/register-form';
import { LoginForm } from './components/login-form/login-form';
import { isLoggedGuard } from './utils/guards/is-logged-guard';
import { UpdateForm } from './components/update-form/update-form';

export const routes: Routes = [
    {path:"", component:Home},
    {path:"dashboard", component:Dashboard , canActivate: [isLoggedGuard]},
    {path:"tournaments", component:Tournaments},
    {path:"registerform", component:RegisterForm},
    {path:"login", component:LoginForm},
    { path: 'profile/edit', component: UpdateForm, canActivate: [isLoggedGuard] },
];
