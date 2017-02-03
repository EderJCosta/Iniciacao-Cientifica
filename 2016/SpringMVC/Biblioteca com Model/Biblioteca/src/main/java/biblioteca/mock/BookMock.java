/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.mock;

import biblioteca.model.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Éder Júlio
 */
public final class BookMock {
    public static List<Book> getBookList(){
        List<Book> bookList = new ArrayList<>();
        bookList.add( new Book("Livro 1", "Autor 1", "Descrição 1"));
        bookList.add( new Book("Livro 2", "Autor 2", "Descrição 2"));
        bookList.add( new Book("Livro 3", "Autor 3", "Descrição 3"));
        return bookList;
    }
}
