import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { SearchService } from '../_services/search.service';
import { User } from '../_models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {


  results: User[];
  form : FormGroup;

  constructor(private router : Router,private searchService: SearchService, private fb :FormBuilder) { }

  ngOnInit() {
    this.form = this.fb.group({
      query: ''
    });
    this.form.controls.query.valueChanges
    .subscribe(result =>
      {
        if(result == ""){
            this.results = [];
        }
        else{
          this.searchService.search(result)
          .subscribe(response => this.results = response)
        }
     
    });
  }
  clear(){
    console.log("blur");
    this.results = [];
  }
  profile(user : User){
    this.router.navigate(['/profile', user.profileId]);
  }


}
