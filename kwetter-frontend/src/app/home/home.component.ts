import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthService } from '../_services/authentication.service';
import { UserService } from '../_services/user-service.service';
import { User, Kweet, Profile, Link } from '../_models';
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
import { addListener } from 'cluster';

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
  currentProfile : Profile;
  constructor(private router : Router,private formBuilder: FormBuilder, globals: Globals, private authService: AuthService, private userService: UserService, httpClient: HttpClient) {
    console.log("started")
    this.currentUserSubscription = this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
    });
    this.httpClient = httpClient;
    this.globals = globals;
    this.offset = 0;
    this.getCurrentProfile();
    this.getTimeline();
 
    
  }

  ngOnInit() {
    this.postForm = this.formBuilder.group({
      content: ['', Validators.required],
    });
  
    
  }
  ngOnDestroy(): void {
    //prevent memory leaks
    this.currentUserSubscription.unsubscribe();
    //this.webSocket.close();
  }
  getCurrentProfile() {
    this.httpClient.get<Profile>(this.globals.baseurl + "profile/" + this.currentUser.profileId).subscribe(profile => {
      this.currentProfile = profile;
      let links = Link.asMap(this.currentProfile.links);
      console.log(links)
      console.log(links.self);
    });
  }
  get f() { return this.postForm.controls; }
/*
  async addListener(){
    this.webSocket.obsArray.subscribe(kweet =>{
        if(kweet.content != null){
          console.log(kweet);
          console.log("subkweet");
          this.kweets.unshift(kweet);  
        }
        
      //this.offset += 1;
    });
  }
  */
  async getTimeline() {
    console.log("gettime");
    await this.httpClient.get<Kweet[]>(this.globals.baseurl + 'kweet/' + this.currentUser.profileId + '/' + this.offset).subscribe(kweets => {
      this.kweets = kweets;
    });
    //this.webSocket.open;
    //this.addListener();
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
      await this.httpClient.post<any>(this.globals.baseurl + "kweet/", this.kweet).subscribe(kweet => {
        this.kweets.unshift(kweet);
        console.log(kweet);
      });

      this.postForm.reset();
      this.submitted=false;
      
    }


  }
  profile(kweet){
    this.router.navigate(['/profile', kweet.ownerId]);
  }
}
