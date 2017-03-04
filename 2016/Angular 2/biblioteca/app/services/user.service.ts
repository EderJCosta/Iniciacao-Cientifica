import { Router } from '@angular/router';
import { Jsonp } from '@angular/http';
import { create } from 'domain';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { USERS } from '../mock/users.mock';

@Injectable()
export class UserService {

    get(): User[] {
        return USERS
    }
    getById(id: number): User {
        return USERS[id]
    }
    create(user: User): void {
        USERS.push(user)
    }
    update(id: number, user: User): void {
        USERS[id] = user
    }
    delete(id: number): void {
        USERS.splice(id, 1)
    }

}
