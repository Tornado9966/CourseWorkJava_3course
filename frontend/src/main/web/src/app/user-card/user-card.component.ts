
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserService} from "../services/user.service";
import {User} from '../entities/user';
import {UserInvite} from "../entities/user-invite";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {
  @Input()
  user: User;
  @Output()
  onChanged = new EventEmitter<User>();
  inviteForm = new FormGroup({
    'inviteText' : new FormControl(null, [
      Validators.required,
      Validators.minLength(4)
    ]),
  });
  name: string = this.userService.user.firstName;

  nameSendButton = 'Send invitation';
  clicked: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  goToProfile() {
    this.onChanged.emit(this.user);
  }
  userRole() {
    return this.userService.user.role.name;
  }

  sendInvite() {
    this.clicked = true;
    this.nameSendButton = 'Invitation was sent';
    this.userService.sendUserInvite({
      inviteText: this.inviteForm.get('inviteText').value,
      userIdFrom: +this.userService.user.id,
      userIdTo: +this.user.id,
    } as UserInvite).subscribe(data  => {

    });
  }



}
