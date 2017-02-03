package rederecicle.controller.action.tipomaterial;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import rederecicle.model.ServiceLocator;
import rederecicle.model.entity.TipoMaterial;

@Controller
public class TipoMaterialAction {

    @RequestMapping(value = "/material/novo", method = RequestMethod.POST)
    public ModelAndView adicionarTipoMaterial(String nome) {

        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
            Map<String, Object> fields = new HashMap<>();
            fields.put("nome", nome);
            errors = ServiceLocator.getTipoMaterialService().validateForCreate(fields);

            TipoMaterial tipoMaterial = new TipoMaterial();
            tipoMaterial.setNome(nome);
            ServiceLocator.getTipoMaterialService().create(tipoMaterial);
            mv = new ModelAndView("redirect:/home");
        } catch (Exception ex) {
            Logger.getLogger(TipoMaterialAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;

    }

    @RequestMapping(value = "/grupo/material/novo", method = RequestMethod.POST)
    public ModelAndView adicionarTipoMaterialGrupo(String nome, String grupoNome) {
        ModelAndView mv = null;
        Map<String, String> errors = null;
        try {
           Map<String, Object> fields = new HashMap<>();
            fields.put("nome", nome);
            errors = ServiceLocator.getTipoMaterialService().validateForCreate(fields);

            TipoMaterial tipoMaterial = new TipoMaterial();
            tipoMaterial.setNome(nome);
            ServiceLocator.getTipoMaterialService().create(tipoMaterial);
            mv = new ModelAndView("redirect:/grupo/" + grupoNome);
        } catch (Exception ex) {
            Logger.getLogger(TipoMaterialAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

}
