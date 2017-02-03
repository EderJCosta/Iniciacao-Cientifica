/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controller.book;

import biblioteca.model.ServiceLocator;
import biblioteca.model.entity.Book;
import biblioteca.model.entity.User;
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
        User user = new User();
        user.setId(1l);
        book.setUser(user);
        ServiceLocator.getBaseBookService().create(book);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/book/list")
    public ModelAndView getBookList(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("user/bookTable");
        mv.addObject("bookList", ServiceLocator.getBaseBookService().readByCriteria(null, null, null));
        mv.addObject("opcao", "list");
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/book/{id}")
    public ModelAndView getBook(@PathVariable Long id,HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/user/book");
        mv.addObject("book", ServiceLocator.getBaseBookService().readById(id));
        return mv;
    }

    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getBookEdit(@PathVariable Long id, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("user/bookForm");
        mv.addObject("book", ServiceLocator.getBaseBookService().readById(id));
        return mv;
    }
    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editBook(@PathVariable Long id,Book book,HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/book/list");
        ServiceLocator.getBaseBookService().update(book);
        return mv;
    }
//    Não foi verificado a authorização para deletar, qualquer usuario logado pode deletar um livro
    @RequestMapping(value = "/book/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBook(@PathVariable Long id,HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/book/list");
        ServiceLocator.getBaseBookService().delete(id);
        return mv;
    }


}
