package rederecicle.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.ServiceLocator;
import rederecicle.model.base.service.BaseSetorService;
import rederecicle.model.criteria.SetorCriteria;
import rederecicle.model.dao.SetorDAO;
import rederecicle.model.entity.Setor;

public class SetorService implements BaseSetorService {

    @Override
    public void create(Setor e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            SetorDAO dao = new SetorDAO();
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
    public Setor readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Setor entity = null;
        try {
            SetorDAO dao = new SetorDAO();
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
    public List<Setor> readByCriteria(Map<Long, Object> criteria,Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Setor> entityList = null;
        try {
            SetorDAO dao = new SetorDAO();
            entityList = dao.readByCriteria(conn, criteria,limit, offset);
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
    public void update(Setor e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            SetorDAO dao = new SetorDAO();
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
            SetorDAO dao = new SetorDAO();
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
        if(nome == null || nome.isEmpty()){
            errors.put("Nome", "Campo obrigatório.");
        }else{
            Map<Long, Object> criteria = new HashMap<Long,Object>();
            criteria.put(SetorCriteria.NOME_EQ, nome);
            Long id = (Long) fields.get("id");
            if(id != null && id >0){
                criteria.put(SetorCriteria.ID_NE, id);
            }
            List<Setor> setorList = ServiceLocator.getSetorService().readByCriteria(criteria,null,null);
            if(!setorList.isEmpty()){
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
