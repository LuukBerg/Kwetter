import { Injectable, SystemJsNgModuleLoader } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { logging } from 'protractor';
import { userInfo } from 'os';
import {Globals} from './../Globals/globals'
@Injectable({
  providedIn: 'root'
})
export class UserService {

  public loggedIn = false;
  private headers = new Headers({'Content-Type': 'application/x-www-form-urlencoded'});
  public token;

  constructor(private http: HttpClient, private globals : Globals) {
  }
    login(username, password){
        let body = new URLSearchParams();
        body.set('username', username);
        body.set('password', password);
        this.http.post(this.globals.baseurl, body).subscribe(token =>{
          this.token = token;
        });
   }


}
