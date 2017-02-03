package rederecicle.controller.action.postagem;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import rederecicle.model.ServiceLocator;
import rederecicle.model.criteria.PostagemCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Postagem;
import rederecicle.model.entity.TipoLixo;
import rederecicle.model.entity.TipoMaterial;

@Controller
public class PostagemAction {

    @RequestMapping(value = "/postagem/feed", method = RequestMethod.GET)
    public ModelAndView create(Long limit, Long offset, HttpSession session) {
        boolean redirect = false;
        if (limit == null || limit <= 0) {
            limit = 5L;
            redirect = true;
        }
        if (offset == null || offset < 0) {
            offset = 0L;
            redirect = true;
        }
        ModelAndView mv = new ModelAndView("home");
        if (redirect) {
            mv = new ModelAndView("redirect:/postagem/feed?limit=" + limit + "&offset=" + offset);
        } else {
            List<Postagem> feedPostagem = null;
            Empresa empresa = new Empresa();
            empresa = (Empresa) session.getAttribute("empresaLogada");
            try {
                if (empresa.getTipo().equals("F")) {
                    List<TipoLixo> tipoLixoList = ServiceLocator.getTipoLixoService().readByCriteria(null, null, null);
                    mv.addObject("tipoList", tipoLixoList);
                } else {
                    List<TipoMaterial> tipoMaterialList = ServiceLocator.getTipoMaterialService().readByCriteria(null, null, null);
                    mv.addObject("tipoList", tipoMaterialList);
                }
                Map<Long, Object> criteria = new HashMap<>();
                criteria.put(PostagemCriteria.FEED_EQ, empresa.getId());
                criteria.put(PostagemCriteria.STATUS_EQ, Boolean.TRUE);
                criteria.put(PostagemCriteria.GRUPO_ISNULL, Boolean.TRUE);
                criteria.put(PostagemCriteria.DATA_ORDERBY, Boolean.TRUE);
                feedPostagem = ServiceLocator.getPostagemService().readByCriteria(criteria, null, null);
                mv.addObject("qtdeTotal", feedPostagem.size());
                feedPostagem = ServiceLocator.getPostagemService().readByCriteria(criteria, limit, offset);
                mv.addObject("postagemList", feedPostagem);
                mv.addObject("limit", limit);
                mv.addObject("offset", offset);
                mv.addObject("qtde", feedPostagem.size());
            } catch (Exception ex) {
                Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            mv.addObject("opcao", "postagemCreate");
        }
        return mv;
    }

    @RequestMapping(value = "/postagem/feed", method = RequestMethod.POST)
    public ModelAndView create(Postagem entity, HttpSession session, Long tipo) throws Exception {
        ModelAndView mv = null;
        try {
            Calendar date = Calendar.getInstance();
            Timestamp data = new Timestamp(date.getTime().getTime());
            entity.setData(data);
            entity.setStatus(Boolean.TRUE);
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            entity.setEmpresa(empresa);
            if (empresa.getTipo().equals("F")) {
                TipoLixo tipoLixo = ServiceLocator.getTipoLixoService().readByID(tipo);
                entity.setTipoLixo(tipoLixo);
            } else {
                TipoMaterial tipoMaterial = ServiceLocator.getTipoMaterialService().readByID(tipo);
                entity.setTipoMaterial(tipoMaterial);
            }
            entity.setGrupo(null);
            if (entity.getUnidade().equals("t")) {
                entity.setPeso(entity.getPeso() * 1000000);
            }
            if (entity.getUnidade().equals("kg")) {
                entity.setPeso(entity.getPeso() * 1000);
            }
            if (entity.getUnidade().equals("mg")) {
                entity.setPeso(entity.getPeso() / 1000);
            }
            ServiceLocator.getPostagemService().create(entity);
            mv = new ModelAndView("redirect:/postagem/feed");
        } catch (Exception ex) {
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return mv;
    }

    @RequestMapping(value = "/grupo/postagem/novo", method = RequestMethod.POST)
    public ModelAndView adicionarPostagemGrupo(Postagem entity, HttpSession session, Long tipo, Long grupoid) throws Exception {
        ModelAndView mv = null;
        try {
            Calendar date = Calendar.getInstance();
            Timestamp data = new Timestamp(date.getTime().getTime());
            entity.setData(data);
            entity.setStatus(Boolean.TRUE);
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            entity.setEmpresa(empresa);
            if (empresa.getTipo().equals("F")) {
                TipoLixo tipoLixo = ServiceLocator.getTipoLixoService().readByID(tipo);
                entity.setTipoLixo(tipoLixo);
            } else {
                TipoMaterial tipoMaterial = ServiceLocator.getTipoMaterialService().readByID(tipo);
                entity.setTipoMaterial(tipoMaterial);
            }
            Grupo grupo = new Grupo();
            grupo = ServiceLocator.getGrupoService().readByID(grupoid);
            entity.setGrupo(grupo);
            if (entity.getUnidade().equals("t")) {
                entity.setPeso(entity.getPeso() * 1000000);
            }
            if (entity.getUnidade().equals("kg")) {
                entity.setPeso(entity.getPeso() * 1000);
            }
            if (entity.getUnidade().equals("mg")) {
                entity.setPeso(entity.getPeso() / 1000);
            }
            ServiceLocator.getPostagemService().create(entity);
            mv = new ModelAndView("redirect:/grupo/" + grupoid);
        } catch (Exception ex) {
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

        return mv;
    }

    @RequestMapping(value = "/postagem/pesquisa", method = RequestMethod.GET)
    public ModelAndView pesquisarPostagens(HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        Empresa empresa = new Empresa();
        try {
            empresa = (Empresa) session.getAttribute("empresaLogada");
            if (empresa.getTipo().equals("F")) {
                List<TipoLixo> tipoLixoList = ServiceLocator.getTipoLixoService().readByCriteria(null, null, null);
                mv.addObject("tipoList", tipoLixoList);
            } else {
                List<TipoMaterial> tipoMaterialList = ServiceLocator.getTipoMaterialService().readByCriteria(null, null, null);
                mv.addObject("tipoList", tipoMaterialList);
            }
            mv.addObject("opcao", "postagemReadByCriteria");
        } catch (Exception ex) {
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/postagem/pesquisa", method = RequestMethod.POST)
    public ModelAndView pesquisarPostagem(HttpSession session, Double peso, String unidade, Long tipo) {

        ModelAndView mv = new ModelAndView("home");

        Map<Long, Object> criteria = new HashMap<>();
        Empresa empresa = new Empresa();
        List<Postagem> postagemList = null;
        try {
            empresa = (Empresa) session.getAttribute("empresaLogada");
            if (empresa.getTipo().equals("F")) {
                List<TipoLixo> tipoLixoList = ServiceLocator.getTipoLixoService().readByCriteria(null, null, null);
                mv.addObject("tipoList", tipoLixoList);
            } else {
                List<TipoMaterial> tipoMaterialList = ServiceLocator.getTipoMaterialService().readByCriteria(null, null, null);
                mv.addObject("tipoList", tipoMaterialList);
            }
            if (peso != null) {
                if (unidade.equals("t")) {
                    peso = peso * 1000000;
                }
                if (unidade.equals("kg")) {
                    peso = peso * 1000;
                }
                if (unidade.equals("mg")) {
                    peso = peso / 1000;
                }
                criteria.put(PostagemCriteria.PESO_LE, peso);
            }

            if (tipo != null) {
                criteria.put(PostagemCriteria.TIPOLIXO_ID_EQ, tipo);
            }
            criteria.put(PostagemCriteria.GRUPO_ISNULL, Boolean.TRUE);
            criteria.put(PostagemCriteria.STATUS_EQ, Boolean.TRUE);

            postagemList = ServiceLocator.getPostagemService().readByCriteria(criteria, null, null);
            mv.addObject("opcao", "postagemReadByCriteria");
            mv.addObject("peso", peso);
            mv.addObject("unidade", unidade);
            mv.addObject("tipo", tipo);
            mv.addObject("postagemList", postagemList);
            for (Postagem postagem : postagemList) {
                System.out.println(postagem.getPeso());
            }

        } catch (Exception ex) {
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

    @RequestMapping(value = "/postagem/finalizadas", method = RequestMethod.GET)
    public ModelAndView visualizarPostagensFinalizadas(HttpSession session) {
        ModelAndView mv = null;
        try {
            Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(PostagemCriteria.EMPRESA_ID_EQ, empresa.getId());
            criteria.put(PostagemCriteria.STATUS_EQ, Boolean.FALSE);
            List<Postagem> postagemList = ServiceLocator.getPostagemService().readByCriteria(criteria, null, null);
            mv = new ModelAndView("home");
            mv.addObject("opcao", "postagensFinalizadas");
            mv.addObject("postagemList", postagemList);
        } catch (Exception ex) {
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/postagem/{id}/alterar/status", method = RequestMethod.GET)
    public ModelAndView alterarStatusPostagem(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
        try {
            Postagem postagem = ServiceLocator.getPostagemService().readByID(id);
            if (postagem.getEmpresa().getId() == empresa.getId()) {
                if (postagem.getStatus() == Boolean.TRUE) {
                    System.out.println("v");
                    postagem.setStatus(Boolean.FALSE);
                    mv = new ModelAndView("redirect:/home");
                } else {
                    System.out.println("f");
                    postagem.setStatus(Boolean.TRUE);
                    mv = new ModelAndView("redirect:/postagem/finalizadas");
                }

                ServiceLocator.getPostagemService().update(postagem);
                postagem = ServiceLocator.getPostagemService().readByID(id);

            } else {
                if (postagem.getStatus() == Boolean.TRUE) {
                    mv = new ModelAndView("redirect:/home");
                } else {
                    mv = new ModelAndView("redirect:/postagem/finalizadas");
                }
                mv.addObject("autorizacao", "Voce não possui autorização para modificar esse item!");
            }

        } catch (Exception ex) {
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/grupo/{idGrupo}/postagem/{id}/alterar/status", method = RequestMethod.GET)
    public ModelAndView alterarStatusPostagemGrupo(@PathVariable Long idGrupo, @PathVariable Long id, HttpSession session) {
        ModelAndView mv = null;
        Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
        try {
            Postagem postagem = ServiceLocator.getPostagemService().readByID(id);
            if (postagem.getEmpresa() != null && postagem.getEmpresa().getId() == empresa.getId()) {
                if (postagem.getStatus() == Boolean.TRUE) {
                    postagem.setStatus(Boolean.FALSE);
                    mv = new ModelAndView("redirect:/grupo/" + idGrupo);
                } else {
                    postagem.setStatus(Boolean.TRUE);
                    mv = new ModelAndView("redirect:/postagem/finalizadas");
                }
                ServiceLocator.getPostagemService().update(postagem);
            } else {
                if (postagem.getStatus() == Boolean.TRUE) {
                    mv = new ModelAndView("redirect:/grupo/" + idGrupo);
                } else {
                    mv = new ModelAndView("redirect:/postagem/finalizadas");
                }
                mv.addObject("autorizacao", "Voce não possui autorização para modificar esse item!");
            }

        } catch (Exception ex) {
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/postagem/{id}/editar", method = RequestMethod.GET)
    public ModelAndView editarPostagem(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView("home");
        Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
        try {
            Postagem postagem = ServiceLocator.getPostagemService().readByID(id);
            if (empresa.getId() != postagem.getEmpresa().getId()) {
                postagem = null;
                mv = new ModelAndView("redirect:/home");
            } else {
                if (empresa.getTipo().equals("F")) {
                    List<TipoLixo> tipoLixoList = ServiceLocator.getTipoLixoService().readByCriteria(null, null, null);
                    mv.addObject("tipoList", tipoLixoList);
                } else {
                    List<TipoMaterial> tipoMaterialList = ServiceLocator.getTipoMaterialService().readByCriteria(null, null, null);
                    mv.addObject("tipoList", tipoMaterialList);
                }
                mv.addObject("postagem", postagem);
                mv.addObject("opcao", "editarPostagem");
            }
        } catch (Exception ex) {
            mv = new ModelAndView("redirect:/home");
        }
        return mv;
    }

    @RequestMapping(value = "/postagem/{id}/editar", method = RequestMethod.POST)
    public ModelAndView editarPostagem(Postagem entity, Long grupoid, Long tipo, HttpSession session) {
        ModelAndView mv = null;
        Empresa empresa = (Empresa) session.getAttribute("empresaLogada");
        try {
            if (grupoid != 0) {
                entity.setGrupo(ServiceLocator.getGrupoService().readByID(grupoid));
                mv = new ModelAndView("redirect:/grupo/" + grupoid);
            } else {
                entity.setGrupo(null);
                mv = new ModelAndView("redirect:/home");
            }
            if (empresa.getTipo().equals("F")) {
                TipoLixo tipoLixo = ServiceLocator.getTipoLixoService().readByID(tipo);
                entity.setTipoLixo(tipoLixo);
            } else {
                TipoMaterial tipoMaterial = ServiceLocator.getTipoMaterialService().readByID(tipo);
                entity.setTipoMaterial(tipoMaterial);
            }
            entity.setStatus(Boolean.TRUE);
            Calendar date = Calendar.getInstance();
            Timestamp data = new Timestamp(date.getTime().getTime());
            entity.setData(data);
            if (entity.getUnidade().equals("t")) {
                entity.setPeso(entity.getPeso() * 1000000);
            }
            if (entity.getUnidade().equals("kg")) {
                entity.setPeso(entity.getPeso() * 1000);
            }
            if (entity.getUnidade().equals("mg")) {
                entity.setPeso(entity.getPeso() / 1000);
            }
            ServiceLocator.getPostagemService().update(entity);
        } catch (Exception ex) {
            mv = new ModelAndView("redirect:/postagem/" + entity.getId() + "/editar");
            Logger.getLogger(PostagemAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

}
