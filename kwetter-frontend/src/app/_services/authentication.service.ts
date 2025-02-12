import { Injectable, SystemJsNgModuleLoader } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { logging } from 'protractor';
import { userInfo } from 'os';
import { Globals } from './../Globals/globals'
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../_models/user';
import { Register } from '../_models';
import { LoginComponent } from '../login';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  //what is this?
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;


  private headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });


  constructor(private http: HttpClient, private globals: Globals) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    const body = new HttpParams()
      .set('username', username)
      .set('password', password);
    console.log(body);
    console.log("tets")
    return this.http.post<any>(this.globals.baseurl + 'auth/login', body.toString(), { headers: this.headers })
      .pipe(map(user => {
        console.log(user);
        if (user && user.token) {
          console.log(user.token);
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUserSubject.next(user);
        }
        return user;
      }));;
  }
  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
  //String username, String email, String password, String name, String location, String web, String bio
  register(register: Register){
    return this.http.post<any>(this.globals.baseurl + 'auth/register', register).pipe(map(user => {
      if(user == null){
        alert('something went wrong');
      }
      else{
        alert('register succes!');
        return user;
      }
    }));
  }
}

