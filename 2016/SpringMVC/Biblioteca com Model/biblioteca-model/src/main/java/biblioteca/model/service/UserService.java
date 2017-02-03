/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.service;

import biblioteca.model.ConnectionManager;
import biblioteca.model.criteria.UserCriteria;
import biblioteca.model.dao.UserDAO;
import biblioteca.model.entity.User;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import biblioteca.model.base.service.BaseUserService;

/**
 *
 * @author Éder Júlio
 */
public class UserService implements BaseUserService {

    @Override
    public void create(User entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User readById(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<User> entityList = null;
        try {
            UserDAO dao = new UserDAO();
            entityList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return entityList;
    }

    @Override
    public void update(User entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User signIn(String name, String password) throws Exception {
        User user = null;
        if (name != null && !name.isEmpty() && password != null && !password.isEmpty()) {
            Map<Long, Object> criteria = new HashMap<>();
            criteria.put(UserCriteria.NAME_EQ, name);
            criteria.put(UserCriteria.PASSWORD_EQ, password);
            
            List<User> entityList = this.readByCriteria(criteria, null, null);

            if (entityList != null && entityList.size() == 1) {
                User userReturn = entityList.get(0);
                if (userReturn.getName().equals(name) && userReturn.getPassword().equals(password)) {
                    user = userReturn;
                }
            }
        }
        return user;
    }

}
