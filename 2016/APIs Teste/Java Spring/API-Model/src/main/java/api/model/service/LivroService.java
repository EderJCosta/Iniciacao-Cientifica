/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model.service;

import api.model.dao.LivroDAO;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import api.model.ConnectionManager;
import api.model.base.service.BaseLivroService;
import api.model.entity.Livro;

/**
 *
 * @author eder
 */
public class LivroService implements BaseLivroService {

    @Override
    public void create(Livro entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LivroDAO dao = new LivroDAO();
            dao.create(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Livro readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Livro entity = null;
        try {
            LivroDAO dao = new LivroDAO();
            entity = dao.readById(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return entity;
    }

    @Override
    public List<Livro> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Livro> entityList = null;
        try {
            LivroDAO dao = new LivroDAO();
            entityList = dao.readByCriteria(conn, criteria, limit, offset);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return entityList;
    }

    @Override
    public void update(Livro entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LivroDAO dao = new LivroDAO();
            dao.update(conn, entity);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            LivroDAO dao = new LivroDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
