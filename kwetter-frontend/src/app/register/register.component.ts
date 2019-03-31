import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../_services/authentication.service';
import { Register } from './../_models';
import { registerContentQuery } from '@angular/core/src/render3';
import { first } from 'rxjs/operators';
@Component({
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  register: Register;
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      passwordconfirm: ['', Validators.required],
      email: ['', Validators.required],
      name: [],
      location: [],
      web: [],
      bio: ['', Validators.maxLength(160)]
    });
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  }
  get f() { return this.registerForm.controls; }


  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.register = new Register();
    this.register.username = this.f.username.value;
    this.register.email = this.f.email.value;
    this.register.password = this.f.password.value;
    this.register.name = this.f.name.value;
    this.register.location = this.f.location.value;
    this.register.web = this.f.web.value;
    this.register.bio = this.f.bio.value;

    this.authService.register(this.register)
    .pipe(first())
    .subscribe(
        data => {
            console.log(this.returnUrl);
            this.router.navigate([this.returnUrl]);
        },
        error => {
            this.loading = false;
        });
  }


}
