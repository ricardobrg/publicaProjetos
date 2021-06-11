import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedName :string;

  location:string = location.pathname;
  constructor(public router : Router) { }

  ngOnInit(): void {
    if (localStorage.loggedObject) {
      this.loggedName = JSON.parse(localStorage.loggedObject).name;
    }
  }


  handleLogout() {
    localStorage.clear();
    window.location.href="/login";
  }
}
