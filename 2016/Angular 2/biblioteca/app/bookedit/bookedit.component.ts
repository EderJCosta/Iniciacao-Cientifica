import { Jsonp } from '@angular/http';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { BOOKS } from '../mock/book.mock';
import { BookService } from '../services/book.service';
import { Component, Input, OnInit } from '@angular/core';
import 'rxjs/add/operator/switchMap';

import { Book } from '../models/Book';

@Component({
    moduleId: module.id,
    selector: 'my-app',
    // Note que o template URL  é diferente,  aponta para mesma pasta, outros pegam o caminho completo desde app/...
    templateUrl: `./bookedit.component.html`,
    providers: [BookService]
})
export class BookEditComponent implements OnInit {
    constructor(
        private bookService: BookService,
        private route: ActivatedRoute,
        private location: Location
    ) { }
    @Input() theBook: Book = new Book("", "", "")
    book = new Book("", "", "")
    books: Book[] = []

    getBooks(): void {
        this.books = BOOKS
    }
    submit():void{
        this.bookService.update(BOOKS.indexOf(this.theBook),this.theBook)
        this.goBack();
    }
    delete(book: Book): void {
        console.log(BOOKS.indexOf(book))
        this.bookService.delete(BOOKS.indexOf(book));
        this.getBooks()
    }
    ngOnInit() {
        this.route.params.switchMap((params: Params) => this.bookService.getById(+params['id'])).subscribe(theBook => this.theBook = theBook)
        this.getBooks()
    }
    goBack(): void {
        this.location.back();
    }
    get diagnostic() { return JSON.stringify(this.theBook); }


}
