import { BookEditComponent } from './bookedit/bookedit.component';
import { BookDetailComponent } from './bookdetail/bookdetail.component';
import { BookListComponent } from './booklist/booklist.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavBarComponent } from './navbar/navbar.component';
import { AboutComponent } from './about/about.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { BookCreateComponent } from './bookcreate/bookcreate.component';

import { AppRoutingModule } from './app.router';

@NgModule({
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  declarations: [AppComponent, NavBarComponent, AboutComponent, RegisterComponent,HomeComponent,BookCreateComponent,BookListComponent,BookDetailComponent,BookEditComponent],
  bootstrap: [AppComponent]

})


export class AppModule { }
