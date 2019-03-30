import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {routing} from './app.routing'

import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { KweetsComponent } from './kweets/kweets.component';
import { TimeAgoPipe } from 'time-ago-pipe';
import { LoginComponent } from './login/login.component';
import { Globals } from './Globals/globals';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    routing
  ],
  declarations: [
    AppComponent,
    TimeAgoPipe,
    LoginComponent,
    HomeComponent,
    RegisterComponent
  ],
  
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
