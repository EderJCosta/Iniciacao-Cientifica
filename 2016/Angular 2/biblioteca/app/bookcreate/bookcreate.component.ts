import { BOOKS } from '../mock/book.mock';
import { BookService } from '../services/book.service';
import { Component } from '@angular/core';

import { Book } from '../models/book';

@Component({
  selector: 'my-app',
  templateUrl: `app/bookcreate/bookcreate.component.html`,
  providers: [BookService]
})
export class BookCreateComponent {
  constructor(private bookService: BookService) { }
  book = new Book("", "", "")
  books: Book[] = []

  submit(): void {
    this.bookService.create(this.book)
  }
  getBooks(): void {
    this.books = BOOKS
  }
  ngOnInit() {
    this.getBooks();
  }
  get diagnostic() { return JSON.stringify(this.books); }
}
