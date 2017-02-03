import { BookEditComponent } from './bookedit/bookedit.component';
import { BookDetailComponent } from './bookdetail/bookdetail.component';
import { BookListComponent } from './booklist/booklist.component';
import { BookCreateComponent } from './bookcreate/bookcreate.component';
import { LoginService } from './services/login.service';
import { HomeComponent } from './home/home.component';
import { expand } from 'rxjs/operator/expand';
import {NgModule} from '@angular/core';
import { CanActivate, Router, RouterModule, Routes } from '@angular/router';
import { LoginGuard } from './services/login.guard';

import {AppComponent} from './app.component';
import {AboutComponent} from './about/about.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
    {path:'', redirectTo:'/', pathMatch:'full'},
    {path:'about', component: AboutComponent},
    {path:'register', component: RegisterComponent},
    {path:'home', component: HomeComponent, canActivate: [LoginGuard]},
    {path:'book/create', component: BookCreateComponent, canActivate: [LoginGuard]},
    {path:'book/list', component: BookListComponent, canActivate: [LoginGuard]},
    {path:'book/:id', component: BookDetailComponent, canActivate: [LoginGuard]},
    {path:'book/:id/edit', component: BookEditComponent, canActivate: [LoginGuard]}
];
@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports: [RouterModule],
    //Providers Utilizada para rodar o loginGuard
    providers: [ LoginGuard, LoginService ]
})
export class AppRoutingModule{}