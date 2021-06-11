import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from 'src/app/models/user/user';
import { LoginData } from 'src/app/models/user/login.data';
import { SigninService } from '../../services/signin/signin.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})

export class SigninComponent {

  loginForm: FormGroup    
  user: User
  loginData: LoginData

  constructor(private formBuilder: FormBuilder, private signinService: SigninService) {
    this.loginForm = this.formBuilder.group({
      username: '',
      password: ''
    });
  }

  onSubmit() {
    this.loginData = {username: this.loginForm.get('username').value, password: this.loginForm.get('password').value}
    this.signinService.signin(this.loginData)
  }
}
