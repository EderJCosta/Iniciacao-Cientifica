package rederecicle.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.ServiceLocator;
import rederecicle.model.base.service.BaseTipoMaterialService;
import rederecicle.model.criteria.TipoMaterialCriteria;
import rederecicle.model.dao.TipoMaterialDAO;
import rederecicle.model.entity.TipoMaterial;

public class TipoMaterialService implements BaseTipoMaterialService {

    @Override
    public void create(TipoMaterial e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TipoMaterialDAO dao = new TipoMaterialDAO();
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
    public TipoMaterial readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        TipoMaterial entity = null;
        try {
            TipoMaterialDAO dao = new TipoMaterialDAO();
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
    public List<TipoMaterial> readByCriteria(Map<Long, Object> criteria,Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<TipoMaterial> entityList = null;
        try {
            TipoMaterialDAO dao = new TipoMaterialDAO();
            entityList = dao.readByCriteria(conn, criteria,limit,offset);
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
    public void update(TipoMaterial e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            TipoMaterialDAO dao = new TipoMaterialDAO();
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
            TipoMaterialDAO dao = new TipoMaterialDAO();
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
            errors.put("Nome", "Campo Obrigatório.");
        } else {
            Map<Long, Object> criteria = new HashMap<Long, Object>();
            criteria.put(TipoMaterialCriteria.NOME_EQ, nome);
            Long id = (Long) fields.get("id");
            if (id != null && id > 0) {
                criteria.put(TipoMaterialCriteria.ID_NE, id);
            }
            List<TipoMaterial> tipoMaterialList = ServiceLocator.getTipoMaterialService().readByCriteria(criteria,null,null);
            if (!tipoMaterialList.isEmpty()) {
                errors.put("nome", " Este Nome já se encontra em uso.");
            }
        }
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        return  this.validateForCreate(fields);
    }

}
