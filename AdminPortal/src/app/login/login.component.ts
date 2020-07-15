import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {LoginService} from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loggedIn: boolean;
  username: string;
  password: string;

  constructor(private loginService: LoginService) {
    // 5MiB
    this.loggedIn = localStorage.getItem('PortalAdminHasLoggedIn') !== null;
  }

  onSubmit() {    
    this.loginService.sendCredential(this.username, this.password).subscribe(
      res => {
        if (res.url.indexOf('error') === -1) {
          this.loggedIn = true;
          localStorage.setItem('PortalAdminHasLoggedIn', 'true');
          location.reload(); // reload the page
        }
      },
      err => console.log(err)
    );
  }

  ngOnInit() {
  }

}
