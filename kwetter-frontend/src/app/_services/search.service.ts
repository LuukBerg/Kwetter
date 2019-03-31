import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../Globals/globals';
import { User } from '../_models';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private globals: Globals,private httpClient : HttpClient) { }

  search(queryString : string){
    return this.httpClient.get<User[]>(this.globals.baseurl + "user/search/" + queryString);
  }
}
