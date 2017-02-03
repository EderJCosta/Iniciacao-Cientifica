import { isNull } from 'util';
import { LoginService } from '../services/login.service';
import { User } from '../models/User';
import { Component } from '@angular/core';

@Component({
  selector: 'navbar',
  templateUrl: `app/navbar/navbar.component.html`,
  providers: [LoginService]
})
export class NavBarComponent {
  constructor(private loginService: LoginService) { }
  user = new User("","")
  userLogged: string = sessionStorage['user']

  singIn(user: User): void {
    this.loginService.singIn(user)
    this.userLogged = sessionStorage['user']
  }
  logout(): void{
    this.userLogged= undefined
    this.loginService.logout()
    
  }

}
