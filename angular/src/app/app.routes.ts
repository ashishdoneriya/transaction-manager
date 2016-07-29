import { ROUTER_DIRECTIVES } from '@angular/router';

import { provideRouter, RouterConfig } from '@angular/router';

const routes: RouterConfig = [
/*  { path: 'crisis-center', component: CrisisCenterComponent },
  { path: 'heroes', component: HeroListComponent },
  { path: 'hero/:id', component: HeroDetailComponent },
  { path: '**', component: PageNotFoundComponent }*/
];

export const appRouterProviders = [
  provideRouter(routes)
];
