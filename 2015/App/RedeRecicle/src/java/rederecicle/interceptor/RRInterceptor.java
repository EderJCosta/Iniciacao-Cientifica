package rederecicle.interceptor;

import javafx.scene.control.Alert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rederecicle.model.ServiceLocator;
import rederecicle.model.entity.Empresa;

public class RRInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean shallPass = false;
        String uri = request.getRequestURI();
        String context = "/RedeRecicle";
        Empresa empresaLogada = (Empresa) request.getSession().getAttribute("empresaLogada");
        if (empresaLogada != null && !empresaLogada.getTelefoneSet().isEmpty() && !empresaLogada.getEnderecoSet().isEmpty()) {
            shallPass = true;
        } else {
            if (uri.startsWith(context + "/css/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/js/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/fonts/")) {
                shallPass = true;
            }
            if (uri.startsWith(context + "/img/")) {
                shallPass = true;
            }
            if (!shallPass) {
                //Livres
                if (uri.equals(context)) {
                    shallPass = true;
                }
                if (uri.equals(context + "/sobre")) {
                    shallPass = true;
                }
                if (uri.equals(context + "/empresa/novo")) {
                    shallPass = true;
                }
                if (uri.equals(context + "/Home")) {
                    shallPass = true;
                }
                if (empresaLogada != null && (empresaLogada.getTelefoneSet().isEmpty() || empresaLogada.getEnderecoSet().isEmpty())) {
                    if (uri.equals(context + "/cadastro/telefone")) {
                        shallPass = true;
                    }
                    if (uri.equals(context + "/cadastro/telefone/novo")) {
                        shallPass = true;
                    }
                    if (uri.equals(context + "/cadastro/endereco")) {
                        shallPass = true;
                    }
                    if (uri.equals(context + "/cadastro/endereco/novo")) {
                        shallPass = true;
                    }
//                    if (uri.equals(context + "/cadastro/imagem")) {
//                        shallPass = true;
//                    }
                    if (uri.equals(context + "/Logout")) {
                        shallPass = true;
                    }
                }
            }
        }

        if (!shallPass) {
            response.sendRedirect(context + "/");
        }
        return shallPass;
    }

}
