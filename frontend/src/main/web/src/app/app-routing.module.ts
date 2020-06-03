import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthorizationComponent} from "./authorization/authorization.component";
import {HomeComponent} from "./home/home.component";
import {AppComponent} from "./app.component";
import {RegistrationComponent} from "./registration/registration.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {AboutusComponent} from "./aboutus/aboutus.component";
import {NewsComponent} from "./news/news.component";


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home'},
  { path: 'dashboard/:tab', component: DashboardComponent},
  { path: 'dashboard', pathMatch: 'full', redirectTo: '/dashboard/Profile'},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: AuthorizationComponent},
  { path: 'signup', component: RegistrationComponent },
  { path: 'profile', component: UserProfileComponent },
  { path: 'aboutus', component: AboutusComponent },
  { path: 'news', component: NewsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
