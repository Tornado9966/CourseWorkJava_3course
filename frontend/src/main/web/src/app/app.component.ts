import {Component, OnInit, OnDestroy, HostListener} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import { catchError, tap, finalize } from 'rxjs/operators';
import {UserService} from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'ui-app';
  notifications: Notification[] = [];

  constructor(private http: HttpClient,
              private userService: UserService,
              private router: Router ) {

  }

  ngOnInit(): void {
  }

  @HostListener('window:beforeunload')

  ngOnDestroy(): void {
    localStorage.removeItem('user');

  }

  logout() {
    this.userService.logout().subscribe(
      _ => this.router.navigateByUrl('/login'));
  }
getRole() {
    return this.userService.user.role.name;
}
  authenticated() {
    return this.userService.authenticated;
  }

}
