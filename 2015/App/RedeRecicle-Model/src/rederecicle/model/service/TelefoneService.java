package rederecicle.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.base.service.BaseTelefoneService;
import rederecicle.model.dao.TelefoneDAO;
import rederecicle.model.entity.Telefone;

public class TelefoneService implements BaseTelefoneService {

    @Override
    public void create(Telefone e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TelefoneDAO dao = new TelefoneDAO();
            dao.create(conn, e);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public Telefone readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Telefone entity = null;
        try {
            TelefoneDAO dao = new TelefoneDAO();
            entity = dao.readByID(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
        return entity;
    }

    @Override
    public List<Telefone> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Telefone> entityList = new ArrayList<>();
        try {
            TelefoneDAO dao = new TelefoneDAO();
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
    public void update(Telefone e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TelefoneDAO dao = new TelefoneDAO();
            dao.update(conn, e);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TelefoneDAO dao = new TelefoneDAO();
            dao.delete(conn, id);
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            conn.rollback();
            conn.close();
            throw ex;
        }
    }

    @Override
    public Map<String, String> validateForCreate(Map<String, Object> fields) throws Exception {
       Map<String, String> errors = new HashMap<String, String>();

        String nome = (String) fields.get("nome");
        if (nome == null || nome.isEmpty()) {
            errors.put("nome", "Campo obrigatório.");
        }
        String numero = (String) fields.get("numero");
        if (numero == null || numero.isEmpty()) {
            errors.put("numero", "Campo obrigatório.");
        }        
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        return this.validateForCreate(fields);
    }

}
