package api.model;

import api.model.base.service.BaseLivroService;
import api.model.service.LivroService;


public class ServiceLocator {

    public static BaseLivroService getBaseLivroService(){
        return new LivroService();
    }
    
}
