
import biblioteca.model.ServiceLocator;
import biblioteca.model.entity.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Éder Júlio
 */
public class app {

    public static void main(String[] args) throws Exception {
//        User user = new User();
//        user = ServiceLocator.getBaseUserService().signIn("admin", "admin");
        System.out.println(ServiceLocator.getBaseBookService().readByCriteria(null, null, null).size());
    }
}
