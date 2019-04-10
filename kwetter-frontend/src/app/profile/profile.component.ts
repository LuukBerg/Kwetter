import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Kweet, Profile, User } from '../_models';
import { Globals } from '../Globals/globals';
import { Subscription } from 'rxjs';
import { AuthService } from '../_services/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  id: number;
  private sub: any;
  currentUser: User;
  currentUserSubscription: Subscription;
  kweets: Kweet[];
  private httpClient: HttpClient;
  followers: Profile[];
  following: Profile[];
  profile: Profile;
  username: string;
  isFollowing: boolean;

  constructor(httpClient: HttpClient, private route: ActivatedRoute, private globals: Globals, private authService: AuthService) {
    this.httpClient = httpClient;
    this.currentUserSubscription = this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
    });

  }

  ngOnInit() {
    this.isFollowing = false;
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.getCurrentProfile();
      this.getProfileKweets();
      this.getFollowers();
      this.getFollowing();
    });
 

  }
  getProfileKweets() {
    this.httpClient.get<Kweet[]>(this.globals.baseurl + "profile/" + this.id + "/kweet").subscribe(kweets => {
      this.kweets = kweets;
    });
  }
  getCurrentProfile() {
    this.httpClient.get<Profile>(this.globals.baseurl + "profile/" + this.id).subscribe(profile => {
      this.profile = profile;
      this.username = profile.username;
    });
  }
  getFollowers() {
    this.httpClient.get<Profile[]>(this.globals.baseurl + "profile/" + this.id + "/followers").subscribe(profiles => {
      this.followers = profiles;
      this.followers.forEach(follower => {
        if(this.currentUser.profileId == follower.id){
          this.isFollowing = true;
        }
      });
    });
  }
  getFollowing() {
    this.httpClient.get<Profile[]>(this.globals.baseurl + "profile/" + this.id + "/following").subscribe(profiles => {
      this.following = profiles;
    });
  }
  followProfile(){
    this.httpClient.put(this.globals.baseurl + "profile/follow/" + this.profile.id, null).subscribe(profile =>{
      this.isFollowing = true;
    });
  }
  unfollowProfile(){

  }


}
