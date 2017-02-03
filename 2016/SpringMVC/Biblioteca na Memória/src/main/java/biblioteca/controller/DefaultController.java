/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ã‰der JÃºlio
 */
@Controller
public class DefaultController {

    @RequestMapping(method = RequestMethod.GET, value = "/about")
    public ModelAndView getAbout() throws Exception {
        ModelAndView mv = new ModelAndView("library/about");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/home")
    public ModelAndView getHome(String name, String password, HttpSession session) throws Exception {
        ModelAndView mv = null;
        if (name.equals(password)) {
            session.setAttribute("name", name);
            mv = new ModelAndView("user/home");
        } else {
            mv = new ModelAndView("redirect:/");
        }
        mv.addObject("opcao", "home");
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public ModelAndView getHome() throws Exception {
        ModelAndView mv = new ModelAndView("user/home");
        mv.addObject("opcao", "home");
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public ModelAndView getIndex(HttpSession session) throws Exception {
        session.removeAttribute("name");
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }

}
