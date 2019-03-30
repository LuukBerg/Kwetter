import { Component, OnInit, OnDestroy } from '@angular/core';

import {AuthService} from '../_services/authentication.service';
import { UserService } from '../_services/user-service.service';
import { User, Kweet } from '../_models';
import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit, OnDestroy {
  kweets: Kweet; 

  currentUser : User;
  currentUserSubscription: Subscription;

  constructor(private authService : AuthService, private userService: UserService,private httpClient : HttpClient) { 
      this.currentUserSubscription = this.authService.currentUser.subscribe(user =>{
          this.currentUser = user;
      });

  }

  ngOnInit() {
  }
  ngOnDestroy(): void {
    //prevent memory leaks
    this.currentUserSubscription.unsubscribe();
  }

}
