/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controller.book;

import biblioteca.mock.BookMock;
import biblioteca.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Éder Júlio
 */
@Controller
public class BookController {

    @RequestMapping(method = RequestMethod.GET, value = "/book/create")
    public ModelAndView getBookCreate() throws Exception {
        ModelAndView mv = new ModelAndView("user/bookForm");
        mv.addObject("opcao", "add");
        return mv;
    }

    @RequestMapping(value = "/book/create", method = RequestMethod.POST)
    public ModelAndView getBookCreate(Book book,HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/book/list");
        List<Book> bookList;
        if (session.getAttribute("books") == null) {
            bookList = BookMock.getBookList();
            session.setAttribute("books", BookMock.getBookList());
        }else{
             bookList = (List<Book>) session.getAttribute("books");
        }
        bookList.add(book);
        session.setAttribute("books", bookList);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/list")
    public ModelAndView getBookList(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("user/bookTable");
        if (session.getAttribute("books") == null) {
            mv.addObject("bookList", BookMock.getBookList());
            session.setAttribute("books", BookMock.getBookList());
        }else{
             mv.addObject("bookList", session.getAttribute("books"));
        }
        mv.addObject("opcao", "list");
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/book/{id}")
    public ModelAndView getBook(@PathVariable int id,HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/user/book");
        List<Book> bookList = (List<Book>) session.getAttribute("books");
        mv.addObject("book", bookList.get(id));
        return mv;
    }

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getBookEdit(@PathVariable int id, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("user/bookForm");
        List<Book> bookList = (List<Book>) session.getAttribute("books");
        mv.addObject("book", bookList.get(id));
        session.setAttribute("id", id);
        return mv;
    }
    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editBook(@PathVariable int id,Book book,HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/book/list");
        List<Book> bookList = (List<Book>) session.getAttribute("books");
        bookList.set(id, book);
        return mv;
    }
//    Não foi verificado a authorização para deletar, qualquer usuario logado pode deletar um livro
    @RequestMapping(value = "/book/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBook(@PathVariable int id,HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/book/list");
        List<Book> bookList = (List<Book>) session.getAttribute("books");
        bookList.remove(id);
        session.setAttribute("books", bookList);
        return mv;
    }


}
