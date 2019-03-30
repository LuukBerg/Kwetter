import { Component, OnInit, OnDestroy } from '@angular/core';

import {AuthService} from '../_services/authentication.service';
import { UserService } from '../_services/user-service.service';
import { User, Kweet } from '../_models';
import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { KweetsComponent } from '../kweets/kweets.component';
import { Globals } from '../Globals/globals';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit, OnDestroy {
  kweets: Kweet[];
  currentUser : User;
  currentUserSubscription: Subscription;
  private httpClient : HttpClient;
  private globals : Globals;
  private offset : number;
  constructor(globals : Globals,private authService : AuthService, private userService: UserService,httpClient : HttpClient) { 
    console.log("started")  
    this.currentUserSubscription = this.authService.currentUser.subscribe(user =>{
          this.currentUser = user;
      });
      this.httpClient = httpClient;
      this.globals = globals;
      this.offset = 0;
      this.getTimeline();
     
      
  }

  ngOnInit() {
  }
  ngOnDestroy(): void {
    //prevent memory leaks
    this.currentUserSubscription.unsubscribe();
  }


  getTimeline(){
    console.log("gettime")
      this.httpClient.get<any>(this.globals.baseurl +'kweet/'+ this.currentUser.profileId + '/' + this.offset).subscribe(kweets =>{
        this.kweets = kweets;
      });
      this.offset += 10;
  }
}
