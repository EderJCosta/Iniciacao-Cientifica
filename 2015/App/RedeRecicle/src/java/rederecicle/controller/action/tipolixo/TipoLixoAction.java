package rederecicle.controller.action.tipolixo;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rederecicle.model.ServiceLocator;
import rederecicle.model.entity.TipoLixo;

@Controller
public class TipoLixoAction {

    @RequestMapping(value = "/lixo/novo", method = RequestMethod.POST)
    public ModelAndView adicionarTipoLixo(String nome) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", nome);
            errors = ServiceLocator.getTipoLixoService().validateForCreate(fields);

            TipoLixo tipoLixo = new TipoLixo();
            tipoLixo.setNome(nome);
            ServiceLocator.getTipoLixoService().create(tipoLixo);
            mv = new ModelAndView("redirect:/home");
        } catch (Exception ex) {
            mv = new ModelAndView("redirect:/home");
        }
        return mv;
    }

    @RequestMapping(value = "/grupo/lixo/novo", method = RequestMethod.POST)
    public ModelAndView adicionarTipoLixoGrupo(String nome, Long grupoid) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", nome);
            errors = ServiceLocator.getTipoLixoService().validateForCreate(fields);
            if (errors.isEmpty()) {
                TipoLixo tipoLixo = new TipoLixo();
                tipoLixo.setNome(nome);
                ServiceLocator.getTipoLixoService().create(tipoLixo);
                mv = new ModelAndView("redirect:/grupo/" + grupoid);
            }else{
               mv = new ModelAndView("redirect:/grupo/" + grupoid); 
            }

        } catch (Exception ex) {
            mv = new ModelAndView("redirect:/grupo/" + grupoid);
        }
        return mv;
    }

}
