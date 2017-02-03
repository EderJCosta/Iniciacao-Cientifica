package rederecicle.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.ServiceLocator;
import rederecicle.model.base.service.BaseGrupoService;
import rederecicle.model.criteria.GrupoCriteria;
import rederecicle.model.dao.GrupoDAO;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Grupo_Imagem;

public class GrupoService implements BaseGrupoService {

    @Override
    public void create(Grupo e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GrupoDAO dao = new GrupoDAO();
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
    public Grupo readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Grupo entity = null;
        try {
            GrupoDAO dao = new GrupoDAO();
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
    public List<Grupo> readByCriteria(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Grupo> entityList = null;
        try {
            GrupoDAO dao = new GrupoDAO();
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
    public void update(Grupo e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GrupoDAO dao = new GrupoDAO();
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
            GrupoDAO dao = new GrupoDAO();
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
        } else {
            Map<Long, Object> criteria = new HashMap<Long, Object>();
            criteria.put(GrupoCriteria.NOME_EQ, nome);
            Long id = (Long) fields.get("id");

            if (id != null && id > 0) {
                criteria.put(GrupoCriteria.ID_NE, id);
            }
            List<Grupo> grupoList = ServiceLocator.getGrupoService().readByCriteria(criteria, null, null);
            if (!grupoList.isEmpty()) {
                errors.put("nome", "Este Nome já se encontra em uso.");
            }

        }
        String descricao = (String) fields.get("descricao");
        if(descricao == null || descricao.isEmpty()){
            errors.put("descricao", "Campo obrigatório.");
        }
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        return this.validateForCreate(fields);
    }

    @Override
    public void setImagem(Long id, Grupo_Imagem imagem) throws Exception {
       Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            GrupoDAO dao = new GrupoDAO();
            dao.setImagem(conn, id, imagem);
            conn.commit();
            conn.close();

        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
    }

    @Override
    public Grupo_Imagem getImagem(Long id) throws Exception {
       Connection conn = ConnectionManager.getInstance().getConnection();
        Grupo_Imagem imagem = null;
        try {
            GrupoDAO dao = new GrupoDAO();
            imagem = dao.getImagem(conn, id);
            conn.commit();
            conn.close();

        } catch (Exception e) {
            conn.rollback();
            conn.close();
            throw e;
        }
        return imagem;
    }

}
