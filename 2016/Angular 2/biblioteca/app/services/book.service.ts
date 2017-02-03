import { Router } from '@angular/router';
import { Jsonp } from '@angular/http';
import { create } from 'domain';
import { Injectable } from '@angular/core';
import { Book } from '../models/book';
import { BOOKS } from '../mock/book.mock';

@Injectable()
export class BookService {

    get(): Promise<Book[]> {
        return Promise.resolve(BOOKS)
    }
    getById(id: number): Promise<Book> {
        return this.get().then(BOOKS => BOOKS[id])
    }
    create(book: Book): void {
        BOOKS.push(book)
    }
    update(id: number, book: Book): void {
        BOOKS[id] = book
    }
    delete(id: number): void {
        BOOKS.splice(id, 1)
    }

}