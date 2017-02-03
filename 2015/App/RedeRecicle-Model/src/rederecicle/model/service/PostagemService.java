package rederecicle.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.base.service.BasePostagemService;
import rederecicle.model.dao.PostagemDAO;
import rederecicle.model.entity.Postagem;

public class PostagemService implements BasePostagemService {

    @Override
    public void create(Postagem e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PostagemDAO dao = new PostagemDAO();
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
    public Postagem readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Postagem entity = null;
        try {
            PostagemDAO dao = new PostagemDAO();
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
    public List<Postagem> readByCriteria(Map<Long, Object> criteria,Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Postagem> entityList = null;
        try {
            PostagemDAO dao = new PostagemDAO();
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
    public void update(Postagem e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            PostagemDAO dao = new PostagemDAO();
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
            PostagemDAO dao = new PostagemDAO();
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

        String titulo = (String) fields.get("titulo");
        if (titulo == null || titulo.isEmpty()) {
            errors.put("titulo", "Campo obrigatório.");
        }
        String descricao = (String) fields.get("descricao");
        if (descricao == null || descricao.isEmpty()) {
            errors.put("descricao", "Campo obrigatório.");
        }
        String unidade = (String) fields.get("unidade");
        if (unidade == null || unidade.isEmpty()) {
            errors.put("unidade", "Campo obrigatório.");
        }
        String tipo = (String) fields.get("tipo");
        if (tipo == null || tipo.isEmpty()) {
            errors.put("tipo", "Campo obrigatório.");
        }
        Long peso = (Long) fields.get("peso");
        if (peso == null || peso == 0) {
            errors.put("peso", "Campo obrigatório.");
        }
        Boolean status = (Boolean) fields.get("status");
        if (status == null) {
            errors.put("status", "Campo obrigatório.");
        }
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        return this.validateForCreate(fields);
    }

}
