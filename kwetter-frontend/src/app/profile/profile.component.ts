import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Kweet, Profile, User, Link, LinkMap } from '../_models';
import { Globals } from '../Globals/globals';
import { Subscription } from 'rxjs';
import { AuthService } from '../_services/authentication.service';
import { linkSync } from 'fs';
import { initDomAdapter } from '@angular/platform-browser/src/browser';

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
  canFollow: boolean = true;
  links : LinkMap;

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
      this.init();

    });
 

  }

  async init(){
    await this.getCurrentProfile();
    this.getProfileKweets();
    this.getFollowers();
    this.getFollowing();
  }

  getProfileKweets() {
    this.httpClient.get<Kweet[]>(this.links.getKweets).subscribe(kweets => {
      this.kweets = kweets;
    });
  }
  async getCurrentProfile() {
    let profile = await this.httpClient.get<Profile>(this.globals.baseurl + "profile/" + this.id).toPromise();
    this.profile = profile;
    this.username = profile.username;
    this.links = Link.asMap(this.profile.links);
    if(this.profile.id == this.currentUser.profileId){
      this.canFollow = false;
    }
    else{
      this.canFollow = true;
    }
  }
  getFollowers() {
    this.httpClient.get<Profile[]>(this.links.getFollowers).subscribe(profiles => {
      this.followers = profiles;
      this.followers.forEach(follower => {
        if(this.currentUser.profileId == follower.id){
          this.isFollowing = true;
        }
      });
    });
  }
  getFollowing() {
    this.httpClient.get<Profile[]>(this.links.getFollowing).subscribe(profiles => {
      this.following = profiles;
    });
  }
  followProfile(){
    this.httpClient.put(this.links.follow, null).subscribe(profile =>{
      this.isFollowing = true;
    });
  }
  unfollowProfile(){
    this.httpClient.put(this.links.unfollow, null).subscribe(profile =>{
      this.isFollowing = false;
    });
  }


}
