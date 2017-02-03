package rederecicle.controller.action.empresa;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import rederecicle.model.ServiceLocator;
import rederecicle.model.criteria.EmpresaCriteria;
import rederecicle.model.criteria.EnderecoCriteria;
import rederecicle.model.criteria.GrupoCriteria;
import rederecicle.model.criteria.PostagemCriteria;
import rederecicle.model.criteria.TelefoneCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Empresa_Imagem;
import rederecicle.model.entity.Endereco;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Postagem;
import rederecicle.model.entity.Telefone;

@Controller
public class EmpresaAction {

    @RequestMapping(value = "/empresa/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("cadastroEmpresa");
        return mv;
    }

    @RequestMapping(value = "/empresa/novo", method = RequestMethod.POST)
    public ModelAndView create(Empresa entity, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", entity.getNome());
            fields.put("email", entity.getEmail());
            fields.put("senha", entity.getSenha());
            fields.put("tipo", entity.getTipo());
            fields.put("cnpj", entity.getCnpj());
            fields.put("inscricao", entity.getInscricao_estadual());
            fields.put("descricao", entity.getDescricao());
            fields.put("setorSet", entity.getSetorSet());
            fields.put("grupoSet", entity.getGrupoSet());
            errors = ServiceLocator.getEmpresaService().validateForCreate(fields);
            if (errors != null && errors.isEmpty()) {
                entity.getEmpresaFavoritasSet().add(entity);
                ServiceLocator.getEmpresaService().create(entity);
                mv = new ModelAndView("redirect:/cadastro/endereco");
                session.setAttribute("empresaLogada", entity);
            } else {
                mv = new ModelAndView("cadastroEmpresa");
                mv.addObject("empresa", entity);
                mv.addObject("errors", errors);
            }
        } catch (Exception e) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, e);
            mv = new ModelAndView("cadastroEmpresa");
            mv.addObject("empresa", entity);
            mv.addObject("errors", errors);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
    public ModelAndView visualizarEmpresa(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        try {
            Map<Long, Object> criteria = new HashMap<>();
            Empresa empresa = ServiceLocator.getEmpresaService().readByID(id);
            criteria = new HashMap<>();
            criteria.put(PostagemCriteria.EMPRESA_ID_EQ, empresa.getId());
            criteria.put(PostagemCriteria.STATUS_EQ, Boolean.TRUE);
            criteria.put(PostagemCriteria.DATA_ORDERBY, Boolean.TRUE);
            List<Postagem> postagemList = ServiceLocator.getPostagemService().readByCriteria(criteria, null, null);
            criteria = new HashMap<>();
            Empresa empresaLogada = (Empresa) session.getAttribute("empresaLogada");
            criteria.put(GrupoCriteria.EMPRESA_ID_EQ, empresaLogada.getId());
            List<Grupo> grupoList = ServiceLocator.getGrupoService().readByCriteria(criteria, null, null);
            mv.addObject("empresa", empresa);
            mv.addObject("postagemList", postagemList);
            mv.addObject("grupoList", grupoList);
            mv.addObject("opcao", "empresasReadById");

        } catch (Exception ex) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/Pesquisa", method = RequestMethod.GET)
    public ModelAndView readByCriteria() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("opcao", "empresasReadByCriteria");
        return mv;
    }

    @RequestMapping(value = "/empresa/Pesquisa", method = RequestMethod.POST)
    public ModelAndView readByCriteria(String nome, String tipo) {
        ModelAndView mv = new ModelAndView("home");
        Map<Long, Object> criteria = new HashMap<>();
        criteria.put(EmpresaCriteria.NOME_ILIKE, nome);
        criteria.put(EmpresaCriteria.TIPO_EQ, tipo);
        try {
            List<Empresa> empresaList = ServiceLocator.getEmpresaService().readByCriteria(criteria, null, null);
            mv.addObject("empresaList", empresaList);
            mv.addObject("itemNome", nome);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("opcao", "empresasReadByCriteria");
        return mv;
    }

//                  FAVORITOS   
    @RequestMapping(value = "/empresa/favorito/{id}", method = RequestMethod.GET)
    public ModelAndView adicionarFavorito(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
            Empresa favorita = ServiceLocator.getEmpresaService().readByID(id);
            empresa.getEmpresaFavoritasSet().add(favorita);
            ServiceLocator.getEmpresaService().update(empresa);
            mv = new ModelAndView("forward:/empresa/" + id);
            mv.addObject("addFavorito", "Empresa adicionada aos favoritos!");
        } catch (Exception ex) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("forward:/empresa/" + id);
            mv.addObject("addFavorito", "Erro ao adicionar empresa aos favoritos!");
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/favoritos", method = RequestMethod.GET)
    public ModelAndView getFavoritos(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
            mv.addObject("favoritoList", empresa.getEmpresaFavoritasSet());
            mv.addObject("opcao", "favoritosReadById");
        } catch (Exception ex) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/favorito/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView excluirFavorito(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
            Empresa favorita = ServiceLocator.getEmpresaService().readByID(id);
            empresa.getEmpresaFavoritasSet().remove(favorita);
            ServiceLocator.getEmpresaService().update(empresa);
            mv = new ModelAndView("redirect:/empresa/favoritos");
            mv.addObject("deleteFavorito", "Empresa removida dos favoritos!");
        } catch (Exception ex) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("redirect:/empresa/favoritos");
            mv.addObject("deleteFavorito", "Falha ao remover empresa dos favoritos!");
        }
        return mv;
    }

    //                          Configurações
    @RequestMapping(value = "/configuracoes", method = RequestMethod.GET)
    public ModelAndView getConfiguracoes() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("opcao", "configuracoes");
        return mv;
    }

    @RequestMapping(value = "/empresa/senha/alterar", method = RequestMethod.GET)
    public ModelAndView mudarSenha(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("opcao", "alterarsenha");
        return mv;
    }

    @RequestMapping(value = "/empresa/senha/alterar", method = RequestMethod.POST)
    public ModelAndView mudarSenha(HttpSession session, String senhaAntiga, String senhaNova, String senhaConfirmada) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            if (senhaAntiga.equals(empresa.getSenha())) {
                if (senhaNova.equals(senhaConfirmada)) {

                    Map<String, Object> fields = new HashMap<>();
                    fields.put("id", empresa.getId());
                    fields.put("nome", empresa.getNome());
                    fields.put("email", empresa.getEmail());
                    fields.put("senha", empresa.getSenha());
                    fields.put("tipo", empresa.getTipo());
                    fields.put("cnpj", empresa.getCnpj());
                    fields.put("inscricao", empresa.getInscricao_estadual());
                    fields.put("descricao", empresa.getDescricao());
                    errors = ServiceLocator.getEmpresaService().validateForCreate(fields);
                    if (errors.isEmpty()) {
                        empresa.setSenha(senhaNova);
                        ServiceLocator.getEmpresaService().update(empresa);
                        mv = new ModelAndView("home");
                        mv.addObject("opcao", "alterarsenha");
                        mv.addObject("conclusao", "sucesso");
                    }
                } else {
                    mv = new ModelAndView("home");
                    mv.addObject("opcao", "alterarsenha");
                    mv.addObject("conclusao", "falha");
                    mv.addObject("senhaNova", "Senhas Diferentes");
                }
            } else {
                mv = new ModelAndView("home");
                mv.addObject("opcao", "alterarsenha");
                mv.addObject("conclusao", "falha");
                mv.addObject("senhaAtinga", "Senha não esta correta.");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("home");
            mv.addObject("opcao", "alterarsenha");
            mv.addObject("conclusao", "falha");
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/alterar", method = RequestMethod.GET)
    public ModelAndView alterarEmpresa(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
        mv.addObject("empresa", empresa);
        mv.addObject("opcao", "alterarConta");
        return mv;
    }

    @RequestMapping(value = "/empresa/alterar", method = RequestMethod.POST)
    public ModelAndView alterarEmpresa(Empresa entity, HttpSession session) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        System.out.println("fora");
        try {
            Empresa empresa = ServiceLocator.getEmpresaService().readByID(entity.getId());
            entity.setEmpresaFavoritasSet(empresa.getEmpresaFavoritasSet());
            entity.setGrupoSet(empresa.getGrupoSet());
            entity.setSenha(empresa.getSenha());
            entity.setCnpj(empresa.getCnpj());
            entity.setInscricao_estadual(empresa.getInscricao_estadual());

            Map<String, Object> fields = new HashMap<>();
            fields.put("id", entity.getId());
            fields.put("nome", entity.getNome());
            fields.put("email", entity.getEmail());
            fields.put("senha", entity.getSenha());
            fields.put("tipo", entity.getTipo());
            fields.put("cnpj", entity.getCnpj());
            fields.put("inscricao", entity.getInscricao_estadual());
            fields.put("descricao", entity.getDescricao());
            errors = ServiceLocator.getEmpresaService().validateForCreate(fields);
            if (errors != null && errors.isEmpty()) {
                ServiceLocator.getEmpresaService().update(entity);
                entity = ServiceLocator.getEmpresaService().readByID(entity.getId());
                session.setAttribute("empresaLogada", entity);
                
                mv = new ModelAndView("redirect:/home");
                System.out.println("if");
            } else {
                mv = new ModelAndView("home");
                mv.addObject("empresa", entity);
                mv.addObject("opcao", "alterarConta");
                mv.addObject("errors", errors);
            }
        } catch (Exception e) {
            System.out.println("catch");
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, e);
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/imagem", method = RequestMethod.GET)
    public ModelAndView adicionarImagem(HttpSession session) {
        ModelAndView mv = new ModelAndView("cadastroImagem");
        mv.addObject("cadastro", "incompleto");
        try {
            Empresa empresaLogada = (Empresa) session.getAttribute("empresaLogada");
            Empresa_Imagem imagem = ServiceLocator.getEmpresaService().getImagem(empresaLogada.getId());
            if(imagem == null){
                mv.addObject("img", "0");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpresaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/cadastro/imagem", method = RequestMethod.POST)
    public ModelAndView adicionarImagem(Long id, MultipartFile file) throws Exception {
        Empresa_Imagem imagem = new Empresa_Imagem();
        imagem.setConteudo(file.getBytes());
        ServiceLocator.getEmpresaService().setImagem(id, imagem);
        return new ModelAndView("redirect:/cadastro/imagem");
    }

    @RequestMapping(value = "/empresa/alterar/img", method = RequestMethod.POST)
    public ModelAndView setImagem(Long id, MultipartFile file) throws Exception {
        Empresa_Imagem imagem = new Empresa_Imagem();
        imagem.setConteudo(file.getBytes());
        ServiceLocator.getEmpresaService().setImagem(id, imagem);
        return new ModelAndView("redirect:/empresa/alterar");
    }

    @RequestMapping(value = "/empresa/{id}/img.jpg", method = RequestMethod.GET)
    public void streamImagem(@PathVariable Long id, HttpServletResponse response) throws Exception {
        Empresa_Imagem imagem = ServiceLocator.getEmpresaService().getImagem(id);
        response.setContentType("image/jpg");
        response.getOutputStream().write(imagem.getConteudo());
        response.flushBuffer();
    }
}
