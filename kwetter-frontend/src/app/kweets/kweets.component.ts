import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormControl, FormGroup, Validators} from '@angular/forms';
@Component({selector: 'app-kweets',templateUrl: './kweets.component.html'})

export class KweetsComponent{
  
  postKweetForm: FormGroup;
  kweets;
  //injects HttpClient
  constructor(private http: HttpClient) 
  { 

  
    this.postKweetForm = new FormGroup({ 
      content : new FormControl()
    });
   this.http.get('http://localhost:8080/Kwetter/api/kweet').subscribe(kweets => {
     this.kweets = kweets;
   })
  }

  postKweet(){
    this.http.post('http://localhost:8080/Kwetter/api/kweet', this.postKweetForm.value).subscribe(updateKweet =>{
      this.kweets.push(updateKweet);
    })
  }
  
}

