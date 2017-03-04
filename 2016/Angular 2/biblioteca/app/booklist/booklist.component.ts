import { BOOKS } from '../mock/book.mock';
import { BookService } from '../services/book.service';
import { Component } from '@angular/core';

import { Book } from '../models/book';

@Component({
  selector: 'my-app',
  templateUrl: `app/booklist/booklist.component.html`,
  providers: [BookService]
})
export class BookListComponent {
  constructor(private bookService: BookService) { }
  book = new Book("", "", "")
  books: Book[] = []

  getBooks(): void {
    this.books = BOOKS
  }
  delete(book: Book):void{
    console.log(BOOKS.indexOf(book))
    this.bookService.delete(BOOKS.indexOf(book));
    this.getBooks()
  }
  ngOnInit() {
    this.getBooks()
  }
  get diagnostic() { return JSON.stringify(this.books); }


}
