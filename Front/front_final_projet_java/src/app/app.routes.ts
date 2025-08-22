import { Routes } from '@angular/router';
import { Dashboard } from './components/dashboard/dashboard';
import { Home } from './components/home/home';
import { Tournaments } from './components/tournaments/tournaments';

export const routes: Routes = [
    {path:"", component:Home},
    {path:"dashboard", component:Dashboard},
    {path:"tournament", component:Tournaments}
];
