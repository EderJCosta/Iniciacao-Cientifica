package rederecicle.controller.action.endereco;

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
import rederecicle.model.criteria.EnderecoCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Endereco;

@Controller
public class EnderecoAction {

    //                      CADASTRO 
    @RequestMapping(value = "/cadastro/endereco", method = RequestMethod.GET)
    public ModelAndView cadastrarEnderecos(HttpSession session) {
        ModelAndView mv = new ModelAndView("cadastroEndereco");
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(EnderecoCriteria.EMPRESA_ID_EQ, empresa.getId());
            List<Endereco> enderecoList = ServiceLocator.getEnderecoService().readByCriteria(criteria, null, null);
            mv.addObject("cadastro", "incompleto");
            mv.addObject("enderecoList", enderecoList);
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

    @RequestMapping(value = "/cadastro/endereco/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("cadastroEndereco");
        mv.addObject("cadastro", "incompleto");
        mv.addObject("cadastro_endereco", "novo");
        return mv;
    }

    @RequestMapping(value = "/cadastro/endereco/novo", method = RequestMethod.POST)
    public ModelAndView create(Endereco entity, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", entity.getNome());
            fields.put("estado", entity.getEstado());
            fields.put("cidade", entity.getCidade());
            fields.put("cep", entity.getCep());
            fields.put("bairro", entity.getBairro());
            fields.put("logradouro", entity.getLogradouro());
            fields.put("numero", entity.getNumero());
            errors = ServiceLocator.getEnderecoService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
                entity.setEmpresa(empresa);
                ServiceLocator.getEnderecoService().create(entity);
                mv = new ModelAndView("redirect:/cadastro/endereco");
            } else {
                mv = new ModelAndView("cadastroEndereco");
                mv.addObject("cadastro", "incompleto");
        mv.addObject("cadastro_endereco", "novo");
            }

        } catch (Exception e) {
            mv = new ModelAndView("cadastroEndereco");
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/endereco/{id}/editar", method = RequestMethod.GET)
    public ModelAndView editarEnderecoCadastro(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Endereco endereco = ServiceLocator.getEnderecoService().readByID(id);
            if (endereco != null && endereco.getEmpresa().getId() == empresa.getId()) {
                mv = new ModelAndView("cadastroEndereco");
                mv.addObject("cadastro", "incompleto");
                mv.addObject("cadastro_endereco", "editar");
                mv.addObject("endereco", endereco);
            } else {
                mv = new ModelAndView("forward:/cadastro/endereco");
                mv.addObject("autorizacao", "Não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/endereco/{id}/editar", method = RequestMethod.POST)
    public ModelAndView editarEnderecoCadastro(@PathVariable Long id, Endereco entity) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", entity.getNome());
            fields.put("estado", entity.getEstado());
            fields.put("cidade", entity.getCidade());
            fields.put("cep", entity.getCep());
            fields.put("bairro", entity.getBairro());
            fields.put("logradouro", entity.getLogradouro());
            fields.put("numero", entity.getNumero());
            errors = ServiceLocator.getEnderecoService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                ServiceLocator.getEnderecoService().update(entity);
                mv = new ModelAndView("redirect:/cadastro/endereco");
            }
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("forward:/cadastro/endereco");
            mv.addObject("edicao", "Edição falhou!");
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/endereco/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView excluirEnderecoCadastro(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Endereco endereco = ServiceLocator.getEnderecoService().readByID(id);
            if (endereco != null && endereco.getEmpresa().getId() == empresa.getId()) {
                ServiceLocator.getEnderecoService().delete(id);
                mv = new ModelAndView("forward:/cadastro/endereco");
                mv.addObject("exclusao", "Item excluido com sucesso!");
            } else {
                mv = new ModelAndView("forward:/cadastro/endereco");
                mv.addObject("autorizacao", "Não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    //                          Configurações
    @RequestMapping(value = "/empresa/endereco", method = RequestMethod.GET)
    public ModelAndView getEnderecos(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(EnderecoCriteria.EMPRESA_ID_EQ, empresa.getId());
            List<Endereco> enderecoList = ServiceLocator.getEnderecoService().readByCriteria(criteria, null, null);
            mv.addObject("opcao", "enderecos");
            mv.addObject("enderecoList", enderecoList);

        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/endereco/novo", method = RequestMethod.GET)
    public ModelAndView createEndereco(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        try {
            mv.addObject("solicitacao", "novo");
            mv.addObject("opcao", "novoEndereco");
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/endereco/novo", method = RequestMethod.POST)
    public ModelAndView createEndereco(Endereco entity, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", entity.getNome());
            fields.put("estado", entity.getEstado());
            fields.put("cidade", entity.getCidade());
            fields.put("cep", entity.getCep());
            fields.put("bairro", entity.getBairro());
            fields.put("logradouro", entity.getLogradouro());
            fields.put("numero", entity.getNumero());
            errors = ServiceLocator.getEnderecoService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
                entity.setEmpresa(empresa);
                ServiceLocator.getEnderecoService().create(entity);
                mv = new ModelAndView("redirect:/empresa/endereco");
            } else {
                mv = new ModelAndView("home");
                mv.addObject("opcao", "novoEndereco");
                mv.addObject("endereco", entity);
                mv.addObject("errors", errors);
            }

        } catch (Exception e) {
            mv = new ModelAndView("cadastroEndereco");
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/endereco/{id}/editar", method = RequestMethod.GET)
    public ModelAndView editarEndereco(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Endereco endereco = ServiceLocator.getEnderecoService().readByID(id);
            if (endereco != null && endereco.getEmpresa().getId() == empresa.getId()) {
                mv = new ModelAndView("home");
                mv.addObject("opcao", "editarEndereco");
                mv.addObject("solicitacao", "editar");
                mv.addObject("endereco", endereco);
            } else {
                mv = new ModelAndView("forward:/empresa/endereco");
                mv.addObject("autorizacao", "não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }
    
    @RequestMapping(value = "/empresa/endereco/{id}/editar", method = RequestMethod.POST)
    public ModelAndView editarEnderecoC(@PathVariable Long id, Endereco entity) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", entity.getNome());
            fields.put("estado", entity.getEstado());
            fields.put("cidade", entity.getCidade());
            fields.put("cep", entity.getCep());
            fields.put("bairro", entity.getBairro());
            fields.put("logradouro", entity.getLogradouro());
            fields.put("numero", entity.getNumero());
            errors = ServiceLocator.getEnderecoService().validateForCreate(fields);

            if (errors != null && errors.isEmpty()) {
                ServiceLocator.getEnderecoService().update(entity);
                mv = new ModelAndView("redirect:/empresa/endereco");
            }
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("forward:/empresa/endereco");
            mv.addObject("edicao", "Edição falhou!");
        }
        return mv;
    }
    
    @RequestMapping(value = "/empresa/endereco/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView excluirEndereco(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Endereco endereco = ServiceLocator.getEnderecoService().readByID(id);
            if (endereco != null && endereco.getEmpresa().getId() == empresa.getId()) {
                ServiceLocator.getEnderecoService().delete(id);
                mv = new ModelAndView("forward:/empresa/endereco");
                mv.addObject("exclusao", "Item excluido com sucesso!");
            } else {
                mv = new ModelAndView("forward:/empresa/endereco");
                mv.addObject("autorizacao", "não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EnderecoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }
    

}
