import { Component, OnInit } from '@angular/core';
import {Router, RouterModule} from "@angular/router";
import {AuthService} from "../../../service/auth/auth.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent  {

  isLoggedIn$: Observable<boolean>;

  constructor(private authService: AuthService, private router: Router) {
    this.isLoggedIn$ = this.authService.isLoggedIn$;
  }

  logOut(): void {
    this.authService.logout();
    this.router.navigate(['/login']); // Adjust as needed
  }


  isUserLogin(){
    return this.authService.isUserLogIn();
  }


  // tslint:disable-next-line:typedef
  login() {
    this.router.navigateByUrl('/login');
  }

  // tslint:disable-next-line:typedef
  signup() {
    this.router.navigateByUrl('/signup');
  }
}
