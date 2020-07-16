import {Component, OnInit} from '@angular/core';
import {LoginService} from '../login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loggedIn: boolean;

  constructor(private loginService: LoginService, private router: Router) {
    this.loggedIn = localStorage.getItem('PortalAdminHasLoggedIn') !== null;
  }

  logout() {
    this.loginService.logout();
    localStorage.clear();
    location.replace('/login');
    location.reload();
  }

  getDisplay() {
    if (!this.loggedIn) {
      return 'none';
    } else {
      return ''; // default value
    }
  }

  ngOnInit() {
  }

}
