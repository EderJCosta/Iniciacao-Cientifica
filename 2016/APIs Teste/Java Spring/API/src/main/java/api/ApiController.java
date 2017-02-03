package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import api.model.ServiceLocator;
import api.model.entity.Livro;

/**
 *
 * @author eder
 */
@Controller
@RestController
public class ApiController {
    
    @RequestMapping(method = RequestMethod.GET, value = "/livros")
    public List<Livro> getSearchResultViaAjax() throws Exception {

        List<Livro> lista = new ArrayList<>();
        Map<Long,Object> criteria = new HashMap<>();
        lista = ServiceLocator.getBaseLivroService().readByCriteria(criteria, null,null);
        return lista;
    }
  
}
