import { UserService } from '../services/user.service';
import { Component } from '@angular/core';
import {OnInit} from '@angular/core';

import { User } from '../models/user';

@Component({
  selector: 'my-app',
  templateUrl: `app/register/register.component.html`,
  providers: [UserService]
})
export class RegisterComponent  {
  constructor(private userService: UserService) { }
  private users: User[]=[];
  success: boolean = false
  user = new User("","");

  submit(): void{
    this.success = true;
    this.userService.create(this.user);
  }
  getUsers(): void {
    this.users = this.userService.get();
  }
  ngOnInit() {
    this.getUsers();
  }
   get diagnostic() { return JSON.stringify(this.users); }
}
