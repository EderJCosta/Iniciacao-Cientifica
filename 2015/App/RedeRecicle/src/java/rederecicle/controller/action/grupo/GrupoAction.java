package rederecicle.controller.action.grupo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import rederecicle.model.criteria.GrupoCriteria;
import rederecicle.model.criteria.PostagemCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Grupo_Imagem;
import rederecicle.model.entity.Postagem;
import rederecicle.model.entity.TipoLixo;
import rederecicle.model.entity.TipoMaterial;

@Controller

public class GrupoAction {

    @RequestMapping(value = "/empresa/grupo/novo", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("opcao", "grupoCreate");
        return mv;
    }

    @RequestMapping(value = "/empresa/grupo/novo", method = RequestMethod.POST)
    public ModelAndView create(Grupo entity, HttpSession session, MultipartFile file) {
        ModelAndView mv = null;
        Map<String, Object> fields = new HashMap<>();
        try {
            fields.put("nome", entity.getNome());
            fields.put("descricao", entity.getDescricao());

            Map<String, String> errors = ServiceLocator.getGrupoService().validateForCreate(fields);

            if (errors.isEmpty()) {
                Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
                entity.setEmpresa(empresa);
                entity.getEmpresaList().add(empresa);
                ServiceLocator.getGrupoService().create(entity);
                Grupo_Imagem imagem = new Grupo_Imagem();
                imagem.setConteudo(file.getBytes());
                ServiceLocator.getGrupoService().setImagem(entity.getId(), imagem);
                mv = new ModelAndView("redirect:/empresa/grupos");
            } else {
                mv = new ModelAndView("home");
                mv.addObject("opcao", "grupoCreate");
                mv.addObject("errors", errors);
                if (errors.isEmpty()) {
                    mv.addObject("erroImg", "Por favor insira uma arquivo válido, respeitando o tamanho limite");
                }
            }
        } catch (Exception e) {
            mv = new ModelAndView("home");
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/grupos", method = RequestMethod.GET)
    public ModelAndView readByCriteria(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(GrupoCriteria.EMPRESA_GRUPOS_ID, empresa.getId());
            criteria.put(GrupoCriteria.STATUS_EQ, Boolean.TRUE);
            List<Grupo> entityList = ServiceLocator.getGrupoService().readByCriteria(criteria, null, null);
            mv.addObject("opcao", "grupoReadByCriteria");
            mv.addObject("lista", entityList);
        } catch (Exception e) {
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/grupo/{id}/editar", method = RequestMethod.GET)
    public ModelAndView editarGrupo(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Grupo grupo = ServiceLocator.getGrupoService().readByID(id);
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            if (grupo != null && empresa.getId() == grupo.getEmpresa().getId()) {
                mv = new ModelAndView("home");
                mv.addObject("opcao", "editarGrupo");
                mv.addObject("grupo", grupo);
            } else {
                mv = new ModelAndView("forward:/empresa/grupos");
                mv.addObject("autorizacao", "Não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/grupo/{id}/editar", method = RequestMethod.POST)
    public ModelAndView editarGrupo(@PathVariable Long id, Grupo grupo, HttpSession session) {
        ModelAndView mv = null;
        try {
            grupo.setStatus(Boolean.TRUE);
            Grupo entity = ServiceLocator.getGrupoService().readByID(id);
            entity.setNome(grupo.getNome());
            entity.setDescricao(grupo.getDescricao());
            ServiceLocator.getGrupoService().update(entity);
            mv = new ModelAndView("redirect:/empresa/grupos");
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/empresa/grupo/{id}/excluir", method = RequestMethod.GET)
    public ModelAndView excluir(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        try {
            Grupo grupo = ServiceLocator.getGrupoService().readByID(id);
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            if (grupo != null && empresa.getId() == grupo.getEmpresa().getId()) {
                grupo.setNome("" + grupo.getId());
                grupo.setStatus(Boolean.FALSE);
                ServiceLocator.getGrupoService().update(grupo);
                mv = new ModelAndView("redirect:/empresa/grupos");
            } else {
                mv = new ModelAndView("forward:/empresa/grupos");
                mv.addObject("autorizacao", "Não possui autorização para esse item!");
            }
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

    @RequestMapping(value = "/grupo/{id}", method = RequestMethod.GET)
    public ModelAndView visualizarGrupo(Long limit, Long offset, @PathVariable Long id, HttpSession session) {
        boolean redirect = false;
        if (limit == null || limit <= 0) {
            limit = 2L;
            redirect = true;
        }
        if (offset == null || offset < 0) {
            offset = 0L;
            redirect = true;
        }

        ModelAndView mv = null;
        if (redirect) {
            mv = new ModelAndView("redirect:/grupo/" + id + "?limit=" + limit + "&offset=" + offset);
        } else {
            Boolean autorizacao = Boolean.FALSE;
            Empresa empresa = new Empresa();
            empresa = (Empresa) session.getAttribute("empresaLogada");
            Grupo grupo = null;
            try {
                empresa = ServiceLocator.getEmpresaService().readByID(empresa.getId());
                grupo = ServiceLocator.getGrupoService().readByID(id);
                if (grupo != null) {
                    for (Grupo grupoItem : empresa.getGrupoSet()) {
                        if (grupoItem.getId() == grupo.getId()) {
                            autorizacao = Boolean.TRUE;
                        }
                    }
                    if (autorizacao) {

                        Map<Long, Object> criteria = new HashMap<>();
                        criteria.put(PostagemCriteria.GRUPO_ID_EQ, grupo.getId());
                        criteria.put(PostagemCriteria.STATUS_EQ, Boolean.TRUE);
                        criteria.put(PostagemCriteria.DATA_ORDERBY, Boolean.TRUE);
                        mv = new ModelAndView("home");
                        List<Postagem> postagemList = ServiceLocator.getPostagemService().readByCriteria(criteria, null, null);
                        mv.addObject("qtdeTotal", postagemList.size());
                        postagemList = ServiceLocator.getPostagemService().readByCriteria(criteria, limit, offset);
                        if (empresa.getTipo().equals("F")) {
                            List<TipoLixo> tipoLixoList = ServiceLocator.getTipoLixoService().readByCriteria(null, null, null);
                            mv.addObject("tipoList", tipoLixoList);
                        } else {
                            List<TipoMaterial> tipoMaterialList = ServiceLocator.getTipoMaterialService().readByCriteria(null, null, null);
                            mv.addObject("tipoList", tipoMaterialList);
                        }
                        mv.addObject("opcao", "visualizarGrupo");
                        mv.addObject("grupo", grupo);
                        mv.addObject("postagemList", postagemList);
                        mv.addObject("membros", grupo.getEmpresaList().size());
                        mv.addObject("limit", limit);
                        mv.addObject("offset", offset);
                        mv.addObject("qtde", postagemList.size());
                    }else{
                        mv = new ModelAndView("redirect:/empresa/grupos");
                    }
                } else {
                    mv = new ModelAndView("redirect:/empresa/grupos");
                }
            } catch (Exception ex) {
                Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return mv;
    }

    @RequestMapping(value = "/grupo/alterar/img", method = RequestMethod.POST)
    public ModelAndView setImagem(Long id, MultipartFile file) {
        ModelAndView mv = null;
        try {
            Grupo_Imagem imagem = new Grupo_Imagem();
            imagem.setConteudo(file.getBytes());
            ServiceLocator.getGrupoService().setImagem(id, imagem);
            mv = new ModelAndView("redirect:/empresa/grupo/" + id + "/editar");
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("redirect:/empresa/grupo/" + id + "/editar");
            mv.addObject("erroImg", "Por favor insira uma arquivo válido, respeitando o tamanho limite");
        }
        return mv;
    }

    @RequestMapping(value = "/grupo/{id}/img.jpg", method = RequestMethod.GET)
    public void streamImagem(@PathVariable Long id, HttpServletResponse response) throws Exception {
        Grupo_Imagem imagem = ServiceLocator.getGrupoService().getImagem(id);
        response.setContentType("image/jpg");
        response.getOutputStream().write(imagem.getConteudo());
        response.flushBuffer();
    }

    @RequestMapping(value = "/grupo/{idGrupo}/{idEmpresa}/adicionar", method = RequestMethod.GET)
    public ModelAndView adicionarEmpresa(@PathVariable Long idGrupo, @PathVariable Long idEmpresa, HttpSession session) {
        ModelAndView mv = null;
        try {
            Grupo grupo = ServiceLocator.getGrupoService().readByID(idGrupo);
            Empresa empresaLogada = (Empresa) session.getAttribute("empresaLogada");
            if (grupo.getEmpresa().getId() == empresaLogada.getId()) {
                Empresa empresa = ServiceLocator.getEmpresaService().readByID(idEmpresa);
                empresa.getGrupoSet().add(grupo);
                ServiceLocator.getEmpresaService().update(empresa);
                mv = new ModelAndView("forward:/empresa/" + idEmpresa);
                mv.addObject("grupoadd", "sucesso");
            } else {
                mv = new ModelAndView("forward:/empresa/" + idEmpresa);
                mv.addObject("grupoadd", "falha");
            }
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("redirect:/home");
        }
        return mv;
    }

    @RequestMapping(value = "/grupo/{idGrupo}/{idEmpresa}/deixar", method = RequestMethod.GET)
    public ModelAndView deixarEmpresa(@PathVariable Long idGrupo, @PathVariable Long idEmpresa, HttpSession session) {
        ModelAndView mv = null;
        try {
            Grupo grupo = ServiceLocator.getGrupoService().readByID(idGrupo);
            Empresa empresaLogada = (Empresa) session.getAttribute("empresaLogada");
            if (grupo.getEmpresa().getId() == empresaLogada.getId() || idEmpresa == empresaLogada.getId()) {
                Empresa empresa = ServiceLocator.getEmpresaService().readByID(idEmpresa);
                empresa.getGrupoSet().remove(grupo);
                ServiceLocator.getEmpresaService().update(empresa);
                mv = new ModelAndView("forward:/empresa/grupos");
                mv.addObject("grupoRemove", "sucesso");
            } else {
                mv = new ModelAndView("forward:/empresa/grupos");
                mv.addObject("grupoRemove", "falha");
            }
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("redirect:/home");
        }
        return mv;
    }

    @RequestMapping(value = "/grupo/{idGrupo}/{idEmpresa}/remover", method = RequestMethod.GET)
    public ModelAndView removerEmpresa(@PathVariable Long idGrupo, @PathVariable Long idEmpresa, HttpSession session) {
        ModelAndView mv = null;
        try {
            Grupo grupo = ServiceLocator.getGrupoService().readByID(idGrupo);
            Empresa empresaLogada = (Empresa) session.getAttribute("empresaLogada");
            if (grupo.getEmpresa().getId() == empresaLogada.getId() || idEmpresa == empresaLogada.getId()) {
                Empresa empresa = ServiceLocator.getEmpresaService().readByID(idEmpresa);
                grupo.getEmpresaList().remove(empresa);
                ServiceLocator.getGrupoService().update(grupo);
                mv = new ModelAndView("forward:/grupo/" + grupo.getId() + "/membros");
                mv.addObject("grupoRemove", "sucesso");
            } else {
                mv = new ModelAndView("forward:/grupo/" + grupo.getId() + "/membros");
                mv.addObject("grupoRemove", "falha");
            }
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
            mv = new ModelAndView("redirect:/home");
        }
        return mv;
    }

    @RequestMapping(value = "/grupo/{id}/membros", method = RequestMethod.GET)
    public ModelAndView visualizarMembrosGrupo(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        Boolean membro = Boolean.FALSE;
        Empresa empresaLogada = (Empresa) session.getAttribute("empresaLogada");
        try {
            empresaLogada = ServiceLocator.getEmpresaService().readByID(empresaLogada.getId());
            Grupo grupo = ServiceLocator.getGrupoService().readByID(id);
            for (Empresa e : grupo.getEmpresaList()) {
                if (e.getId() == empresaLogada.getId()) {
                    membro = Boolean.TRUE;
                }
            }
            if (membro) {
                mv = new ModelAndView("home");
                mv.addObject("opcao", "visualizarGrupoMembros");
                mv.addObject("grupo", grupo);
            } else {
                mv = new ModelAndView("redirect:/empresa/grupos");
            }
        } catch (Exception ex) {
            Logger.getLogger(GrupoAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }
}
