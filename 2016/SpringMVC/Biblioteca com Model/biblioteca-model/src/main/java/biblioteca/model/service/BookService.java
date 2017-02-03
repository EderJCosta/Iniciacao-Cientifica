/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.service;


import biblioteca.model.ConnectionManager;
import biblioteca.model.base.service.BaseBookService;
import biblioteca.model.dao.BookDAO;
import biblioteca.model.entity.Book;
import java.sql.Connection;
import java.util.List;
import java.util.Map;


/**
 *
 * @author eder
 */
public class BookService implements BaseBookService {

    @Override
    public void create(Book entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            BookDAO dao = new BookDAO();
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
    public Book readById(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Book entity = null;
        try {
            BookDAO dao = new BookDAO();
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
    public List<Book> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Book> entityList = null;
        try {
            BookDAO dao = new BookDAO();
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
    public void update(Book entity) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            BookDAO dao = new BookDAO();
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
            BookDAO dao = new BookDAO();
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
