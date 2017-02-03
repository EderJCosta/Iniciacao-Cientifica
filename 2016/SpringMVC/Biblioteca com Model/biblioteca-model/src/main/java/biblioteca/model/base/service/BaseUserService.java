/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.base.service;

import biblioteca.model.base.BaseService;
import biblioteca.model.entity.User;

/**
 *
 * @author Éder Júlio
 */
public interface BaseUserService extends BaseService<User>{
    
    public User signIn(String name, String password) throws Exception;
}
