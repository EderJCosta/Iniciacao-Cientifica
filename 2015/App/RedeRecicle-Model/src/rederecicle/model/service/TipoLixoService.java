package rederecicle.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.ServiceLocator;
import rederecicle.model.base.service.BaseTipoLixoService;
import rederecicle.model.criteria.TipoLixoCriteria;
import rederecicle.model.dao.TipoLixoDAO;
import rederecicle.model.entity.TipoLixo;

public class TipoLixoService implements BaseTipoLixoService {

    @Override
    public void create(TipoLixo e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TipoLixoDAO dao = new TipoLixoDAO();
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
    public TipoLixo readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        TipoLixo entity = null;
        try {
            TipoLixoDAO dao = new TipoLixoDAO();
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
    public List<TipoLixo> readByCriteria(Map<Long, Object> criteria,Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<TipoLixo> entityList = null;
        try {
            TipoLixoDAO dao = new TipoLixoDAO();
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
    public void update(TipoLixo e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TipoLixoDAO dao = new TipoLixoDAO();
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
            TipoLixoDAO dao = new TipoLixoDAO();
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
            errors.put("nome", "Campo Obrigatório");
        } else {
            Map<Long, Object> criteria = new HashMap<Long, Object>();
            criteria.put(TipoLixoCriteria.NOME_EQ, nome);
            Long id = (Long) fields.get("id");

            if (id != null && id > 0) {
                criteria.put(TipoLixoCriteria.ID_NE, id);
            }

            List<TipoLixo> tipoLixoList = ServiceLocator.getTipoLixoService().readByCriteria(criteria,null,null);

            if (!tipoLixoList.isEmpty()) {
                errors.put("nome", "Este Nome já se encontra em uso.");
            }
        }
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        return this.validateForCreate(fields);
    }

}
