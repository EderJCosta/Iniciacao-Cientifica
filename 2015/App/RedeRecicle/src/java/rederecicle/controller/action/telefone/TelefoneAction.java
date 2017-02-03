package rederecicle.controller.action.telefone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rederecicle.model.ServiceLocator;
import rederecicle.model.criteria.TelefoneCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Telefone;

@Controller
public class TelefoneAction {

    @RequestMapping(value = "/cadastro/telefone", method = RequestMethod.GET)
    public ModelAndView create(HttpSession session) {
        ModelAndView mv = new ModelAndView("cadastroTelefone");
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(TelefoneCriteria.EMPRESA_ID_EQ, empresa.getId());
            List<Telefone> telefoneList = ServiceLocator.getTelefoneService().readByCriteria(criteria, null, null);
            mv.addObject("telefoneList", telefoneList);
            mv.addObject("cadastro", "incompleto");
        } catch (Exception ex) {
            Logger.getLogger(TelefoneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/telefone/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("cadastroTelefone");
        mv.addObject("cadastro", "incompleto");
        mv.addObject("cadastro_telefone", "novo");
        return mv;
    }

    @RequestMapping(value = "/cadastro/telefone/novo", method = RequestMethod.POST)
    public ModelAndView create(Telefone telefone, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", telefone.getNome());
            fields.put("numero", telefone.getNumero());

            errors = ServiceLocator.getTelefoneService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
                telefone.setEmpresa(empresa);
                ServiceLocator.getTelefoneService().create(telefone);
                mv = new ModelAndView("redirect:/cadastro/telefone");
                empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
                session.setAttribute("empresaLogada", empresa);
            }
        } catch (Exception e) {
            mv = new ModelAndView("redirect:/cadastro/telefone");
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/telefone/{id}/editar", method = RequestMethod.GET)
    public ModelAndView editarTelefoneCadastro(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Telefone telefone = ServiceLocator.getTelefoneService().readByID(id);
            if (telefone != null && telefone.getEmpresa().getId() == empresa.getId()) {
                mv = new ModelAndView("cadastroTelefone");
                mv.addObject("cadastro", "incompleto");
                mv.addObject("cadastro_telefone", "editar");
                mv.addObject("telefone", telefone);
            } else {
                mv = new ModelAndView("forward:/cadastro/telefone");
                mv.addObject("autorizacao", "não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/telefone/{id}/editar", method = RequestMethod.POST)
    public ModelAndView editarTelefoneCadastro(@PathVariable Long id, Telefone telefone, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", telefone.getNome());
            fields.put("numero", telefone.getNumero());

            errors = ServiceLocator.getTelefoneService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
                telefone.setEmpresa(empresa);
                ServiceLocator.getTelefoneService().update(telefone);
                mv = new ModelAndView("redirect:/cadastro/telefone");
            }
        } catch (Exception e) {
            mv = new ModelAndView("redirect:/cadastro/telefone");
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/telefone/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView excluirEnderecoCadastro(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Telefone telefone = ServiceLocator.getTelefoneService().readByID(id);
            if (telefone != null && telefone.getEmpresa().getId() == empresa.getId()) {
                ServiceLocator.getTelefoneService().delete(id);
                mv = new ModelAndView("forward:/cadastro/telefone");
                mv.addObject("exclusao", "Item excluido com sucesso!");
                empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
                session.setAttribute("empresaLogada", empresa);
            } else {
                mv = new ModelAndView("forward:/cadastro/telefone");
                mv.addObject("autorizacao", "não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    //                              Configurações
    @RequestMapping(value = "/empresa/telefone", method = RequestMethod.GET)
    public ModelAndView getTelefones(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(TelefoneCriteria.EMPRESA_ID_EQ, empresa.getId());
            List<Telefone> telefoneList = ServiceLocator.getTelefoneService().readByCriteria(criteria, null, null);
            mv.addObject("telefoneList", telefoneList);
            mv.addObject("opcao", "telefones");
        } catch (Exception ex) {
            Logger.getLogger(TelefoneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/telefone/novo", method = RequestMethod.GET)
    public ModelAndView cadastrarTelefone() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("opcao", "novoTelefone");
        mv.addObject("solicitacao", "novo");
        return mv;
    }

    @RequestMapping(value = "/empresa/telefone/novo", method = RequestMethod.POST)
    public ModelAndView cadastrarTelefone(Telefone telefone, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", telefone.getNome());
            fields.put("numero", telefone.getNumero());

            errors = ServiceLocator.getTelefoneService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
                telefone.setEmpresa(empresa);
                ServiceLocator.getTelefoneService().create(telefone);
                mv = new ModelAndView("redirect:/empresa/telefone");
                empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
                session.setAttribute("empresaLogada", empresa);
            }
        } catch (Exception e) {
            mv = new ModelAndView("redirect:/empresa/telefone");
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/telefone/{id}/editar", method = RequestMethod.GET)
    public ModelAndView editarTelefone(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Telefone telefone = ServiceLocator.getTelefoneService().readByID(id);
            if (telefone != null && telefone.getEmpresa().getId() == empresa.getId()) {
                mv = new ModelAndView("home");
                mv.addObject("opcao", "editarTelefone");
                mv.addObject("solicitacao", "novo");
                mv.addObject("telefone", telefone);
            } else {
                mv = new ModelAndView("forward:/empresa/telefone");
                mv.addObject("autorizacao", "não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/telefone/{id}/editar", method = RequestMethod.POST)
    public ModelAndView editarTelefone(@PathVariable Long id, Telefone telefone, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", telefone.getNome());
            fields.put("numero", telefone.getNumero());

            errors = ServiceLocator.getTelefoneService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
                telefone.setEmpresa(empresa);
                ServiceLocator.getTelefoneService().update(telefone);
                mv = new ModelAndView("redirect:/empresa/telefone");
            }
        } catch (Exception e) {
            mv = new ModelAndView("redirect:/empresa/telefone");
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/telefone/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView excluirEndereco(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Telefone telefone = ServiceLocator.getTelefoneService().readByID(id);
            if (telefone != null && telefone.getEmpresa().getId() == empresa.getId()) {
                ServiceLocator.getTelefoneService().delete(id);
                mv = new ModelAndView("forward:/empresa/telefone");
                mv.addObject("exclusao", "Item excluido com sucesso!");
                empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
                session.setAttribute("empresaLogada", empresa);
            } else {
                mv = new ModelAndView("forward:/empresa/telefone");
                mv.addObject("autorizacao", "não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(TelefoneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

}
