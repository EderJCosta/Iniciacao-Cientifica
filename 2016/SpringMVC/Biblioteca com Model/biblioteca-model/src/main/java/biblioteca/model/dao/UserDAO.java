/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.model.base.BaseDAO;
import biblioteca.model.criteria.UserCriteria;
import biblioteca.model.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Éder Júlio
 */
public class UserDAO implements BaseDAO<User> {

    @Override
    public void create(Connection conn, User entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User readById(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT * FROM public.\"user\" WHERE 1=1";
        if (criteria != null && criteria.size() > 0) {
            String name_EQ = (String) criteria.get(UserCriteria.NAME_EQ);
            if (name_EQ != null) {
                sql += " AND name = '" + name_EQ + "'";
            }
           
            String password_EQ = (String) criteria.get(UserCriteria.PASSWORD_EQ);
            if (password_EQ != null) {
                sql += " AND password  = '" + password_EQ + "'";
            }
        }
        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset > 0) {
            sql += " offset " + offset;
        }
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<User> entityList = new ArrayList<>();
        while (rs.next()) {
            User entity = new User();
            entity.setId(rs.getLong("id"));
            entity.setName(rs.getString("name"));
            entity.setPassword(rs.getString("password"));
            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;
    }

    @Override
    public void update(Connection conn, User entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
