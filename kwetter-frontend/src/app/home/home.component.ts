import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthService } from '../_services/authentication.service';
import { UserService } from '../_services/user-service.service';
import { User, Kweet } from '../_models';
import { Subscription } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { KweetsComponent } from '../kweets/kweets.component';
import { Globals } from '../Globals/globals';
import { map } from 'rxjs/operators';
import { userInfo } from 'os';
import { Form, FormControl } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { WebsocketService } from '../_services/websocket.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit, OnDestroy {
  kweets: Kweet[];
  currentUser: User;
  currentUserSubscription: Subscription;
  postForm: FormGroup;
  kweet: Kweet;
  private httpClient: HttpClient;
  private globals: Globals;
  private offset: number;
  submitted = false;

  constructor(private router : Router,private formBuilder: FormBuilder, globals: Globals, private authService: AuthService, private userService: UserService, httpClient: HttpClient, private webSocket : WebsocketService) {
    console.log("started")
    this.currentUserSubscription = this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
    this.httpClient = httpClient;
    this.globals = globals;
    this.offset = 0;
    this.getTimeline();
    this.webSocket.open;
  }

  ngOnInit() {
    this.postForm = this.formBuilder.group({
      content: ['', Validators.required],
    });
  }
  ngOnDestroy(): void {
    //prevent memory leaks
    this.currentUserSubscription.unsubscribe();
  }

  get f() { return this.postForm.controls; }

  getTimeline() {
    console.log("gettime");
    this.httpClient.get<Kweet[]>(this.globals.baseurl + 'kweet/' + this.currentUser.profileId + '/' + this.offset).subscribe(kweets => {
      this.kweets = kweets;
    });
    this.offset += 10;
  }
  async postKweet(event) {
    if (event.keyCode == 13 && !event.shiftKey) {
      event.preventDefault();
      this.submitted = true;
      if (this.postForm.invalid) {
        return;
      }
      this.kweet = new Kweet();
      this.kweet.content = this.f.content.value;
      //this.kweets.unshift(kweet);
     /* await this.httpClient.post<any>(this.globals.baseurl + "kweet/", this.kweet).subscribe(kweet => {
        this.kweets.unshift(kweet);
        console.log(kweet);
      });
      */
      this.webSocket.send(this.kweet.content);
      this.postForm.reset();
      this.submitted=false;
      
    }


  }
  profile(kweet){
    this.router.navigate(['/profile', kweet.ownerId]);
  }
}
