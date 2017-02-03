package rederecicle.controller.action;

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
import rederecicle.controller.action.empresa.EmpresaAction;
import rederecicle.controller.action.endereco.EnderecoAction;
import rederecicle.model.ServiceLocator;
import rederecicle.model.criteria.EnderecoCriteria;
import rederecicle.model.criteria.TelefoneCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Empresa_Imagem;
import rederecicle.model.entity.Endereco;
import rederecicle.model.entity.Telefone;

@Controller
public class Default {

    @RequestMapping(value = "/Cadastro", method = RequestMethod.GET)
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("cadastro");
        return mv;
    }

    @RequestMapping(value = "/Home", method = RequestMethod.POST)
    public ModelAndView login(String email, String senha, HttpSession session) {
        ModelAndView mv = null;
            try {
                Empresa empresaLogada = ServiceLocator.getEmpresaService().login(email, senha);
                if (empresaLogada != null) {
                    empresaLogada = ServiceLocator.getEmpresaService().readByID(empresaLogada.getId());
                    Map<Long, Object> criteria = new HashMap<>();
                    criteria.put(EnderecoCriteria.EMPRESA_ID_EQ, empresaLogada.getId());
                    List<Endereco> enderecoList = ServiceLocator.getEnderecoService().readByCriteria(criteria, null, null);
                    if (!enderecoList.isEmpty()) {
                        criteria = new HashMap<>();
                        criteria.put(TelefoneCriteria.EMPRESA_ID_EQ, empresaLogada.getId());
                        List<Telefone> telefoneList = ServiceLocator.getTelefoneService().readByCriteria(criteria, null, null);
                        if (!telefoneList.isEmpty()) {
                            Empresa_Imagem imagem = ServiceLocator.getEmpresaService().getImagem(empresaLogada.getId());
                            if(imagem != null){
                                mv = new ModelAndView("redirect:/home"); //Logar
                            }else{
                                mv = new ModelAndView("redirect:/cadastro/imagem");//imagem vazia
                            }
                        } else {
                            System.out.println("telefone vazio");
                            mv = new ModelAndView("redirect:/cadastro/telefone");//telefone vazio
                        }
                    } else {
                        System.out.println("endereco vazio");
                        mv = new ModelAndView("redirect:/cadastro/endereco");//endereco vazio
                    }
                    session.setAttribute("empresaLogada", empresaLogada);
                } else {
                    mv = new ModelAndView("redirect:/");
                }
            } catch (Exception ex) {
                Logger.getLogger(Default.class.getName()).log(Level.SEVERE, null, ex);
            }
        return mv;
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHome(){
        ModelAndView mv = new ModelAndView("forward:/postagem/feed");
        return mv;
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public ModelAndView logout(String email, String senha, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/");
        session.removeAttribute("empresaLogada");
        return mv;
    }
    
    @RequestMapping(value = "/sobre", method = RequestMethod.GET)
    public ModelAndView getSobre(){
        ModelAndView mv =  new ModelAndView("sobre");
        return mv;
    }
}
