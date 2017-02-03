/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Éder Júlio
 */
public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean shallPass = false;
        String uri = request.getRequestURI();
        String context = "/Biblioteca";
        String name = (String) request.getSession().getAttribute("name");
        if (name != null) {
            shallPass = true;
        } else {
            if (uri.startsWith(context + "/resources/css/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/resources/js/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/resources/fonts/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/resources/img/")) {
                shallPass = true;
            }
            if (!shallPass) {
                //Livres
                if (uri.equals(context)) {
                    shallPass = true;
                }
                if (uri.equals(context + "/about")) {
                    shallPass = true;
                }
                if (uri.equals(context + "/home")) {
                    shallPass = true;
                }
            }
        }

        if (!shallPass) {
            response.sendRedirect(context + "/");
        }
        return shallPass;
    }

}
