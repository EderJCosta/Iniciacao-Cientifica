/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controller;

import biblioteca.model.ServiceLocator;
import biblioteca.model.entity.User;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            try {
                User userLogged = ServiceLocator.getBaseUserService().signIn(name, password);
                if (userLogged != null) {
                    session.setAttribute("user", userLogged);
                    mv = new ModelAndView("user/home");
                    mv.addObject("opcao", "home");
                } else {
                    mv = new ModelAndView("redirect:/");
                }
            } catch (Exception ex) {
                Logger.getLogger(Default.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        session.removeAttribute("user");
        ModelAndView mv = new ModelAndView("redirect:/");
        return mv;
    }

}
