package biblioteca.model;

import biblioteca.model.base.service.BaseBookService;
import biblioteca.model.base.service.BaseUserService;
import biblioteca.model.service.BookService;
import biblioteca.model.service.UserService;


public class ServiceLocator {

    public static BaseBookService getBaseBookService(){
        return new BookService();
    }
    
    public static BaseUserService getBaseUserService(){
        return new UserService();
    }
    
}
