import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {routing} from './app.routing'

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { KweetsComponent } from './kweets/kweets.component';
import { TimeAgoPipe } from 'time-ago-pipe';
import { LoginComponent } from './login/login.component';
import { Globals } from './Globals/globals';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import {MatGridListModule} from '@angular/material';
import { SearchComponent } from './search/search.component';
import { SearchService } from './_services/search.service';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    routing,
    MatGridListModule,
    FormsModule
  ],
  declarations: [
    AppComponent,
    TimeAgoPipe,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    SearchComponent,
    ProfileComponent,
    KweetsComponent
    
  ],
  
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    SearchService,
    Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
