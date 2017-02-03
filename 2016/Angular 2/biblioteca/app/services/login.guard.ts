import { LoginService } from './login.service';
import { Inject, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable()
export class LoginGuard implements CanActivate{

    constructor(private loginService: LoginService,private router: Router){}
    canActivate() {
        if(this.loginService.logged()){
            return true;
        }
        this.router.navigate(['/']);
    }
}