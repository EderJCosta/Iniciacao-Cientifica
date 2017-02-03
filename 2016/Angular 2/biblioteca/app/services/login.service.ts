import { userInfo } from 'os';
import { Router } from '@angular/router';
import { USERS } from '../mock/Users.mock';
import { User } from '../models/User';
import { Injectable } from '@angular/core';

@Injectable()
export class LoginService {
    constructor(private router: Router) { }

    singIn(user: User): void {
        var result = false;
        USERS.forEach(element => {
            if (element.name == user.name && element.password == user.password) {
                result = true
            }
        });
        if (result) {
            sessionStorage['token'] = user.name
            sessionStorage['user'] = user.name
            this.router.navigate(['/home'])
        } else {
            this.router.navigate(['/'])
        }
    }
    logged() {
        return sessionStorage['token'] === sessionStorage['user']
    }
    logout() {
        sessionStorage['token'] = "-1"
        delete sessionStorage['user']
        this.router.navigate(['/']);
    }

}