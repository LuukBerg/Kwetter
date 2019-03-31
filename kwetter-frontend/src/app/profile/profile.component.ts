import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Kweet, Profile } from '../_models';
import { Globals } from '../Globals/globals';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  id: number;
  private sub: any;
  kweets: Kweet[];
  private httpClient: HttpClient;
  followers: Profile[];
  following: Profile[];
  profile: Profile;
  username: string;

  constructor(httpClient: HttpClient, private route: ActivatedRoute, private globals: Globals) {
    this.httpClient = httpClient;


  }

  ngOnInit() {
  
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
    });
  }
  getFollowing() {
    this.httpClient.get<Profile[]>(this.globals.baseurl + "profile/" + this.id + "/following").subscribe(profiles => {
      this.following = profiles;
    });
  }


}
