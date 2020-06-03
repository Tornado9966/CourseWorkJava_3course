import {Component, OnInit, Input, ViewChild, AfterViewChecked, AfterViewInit} from '@angular/core';
import {UserService} from '../services/user.service';
import { User } from '../entities/user';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';
import {UserCardComponent} from '../user-card/user-card.component';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tab = '';
  user: User;
  users$: Observable<User[]>;
  isVisible = true;


  private searchUserTerms = new Subject<any>();
  @ViewChild(UserCardComponent, {static: false}) userCardChild: UserCardComponent;

  constructor(private userService: UserService, private location: Location,
              private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.tab = this.route.snapshot.paramMap.get('tab');
    this.user = this.tab === 'Profile' ? this.userService.user : {role: {}} as User;
    this.users$ = this.searchUserTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((obj) => this.userService.searchUsers(obj.role, obj.term)),
    );

  }

  search(term: string): void {
    this.isVisible = false;
    this.searchUserTerms.next({role: this.userService.user.role.id, term: term});
  }

  profileSet( editOnly?: boolean, user?: User) {
    this.user = (user ? user : {role: {}} as User);
    this.changeTab(editOnly ? 'AddProfile' : 'Profile');
  }

  changeTab(tab: string) {
    this.tab = tab;
    this.router.navigateByUrl(`dashboard/${tab}`);
    this.isVisible = true;
  }

  getUser() {
    return this.userService.user;
  }
  getUserName() {
    return this.userService.user.firstName;
  }
  getUserLastName() {
    return this.userService.user.lastName;
  }
  getUserRole() {
    return this.userService.user.role.name;
  }


}
