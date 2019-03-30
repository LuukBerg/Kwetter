import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {routing} from './app.routing'

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { KweetsComponent } from './kweets/kweets.component';
import { TimeAgoPipe } from 'time-ago-pipe';
import { LoginComponent } from './login/login.component';
import { Globals } from './Globals/globals';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import {MatGridListModule} from '@angular/material';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    routing,
    MatGridListModule
  ],
  declarations: [
    AppComponent,
    TimeAgoPipe,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    
  ],
  
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    
    Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
