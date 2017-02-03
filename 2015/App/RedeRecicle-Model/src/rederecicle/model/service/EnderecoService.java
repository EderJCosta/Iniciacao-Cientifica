package rederecicle.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.base.service.BaseEnderecoService;
import rederecicle.model.dao.EnderecoDAO;
import rederecicle.model.entity.Endereco;

public class EnderecoService implements BaseEnderecoService {

    @Override
    public void create(Endereco e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EnderecoDAO dao = new EnderecoDAO();
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
    public Endereco readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Endereco entity = null;
        try {
            EnderecoDAO dao = new EnderecoDAO();
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
    public List<Endereco> readByCriteria(Map<Long, Object> criteria,Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Endereco> entityList = null;
        try {
            EnderecoDAO dao = new EnderecoDAO();
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
    public void update(Endereco e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EnderecoDAO dao = new EnderecoDAO();
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
            EnderecoDAO dao = new EnderecoDAO();
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

        String nome_Endereco = (String) fields.get("nome");
        if (nome_Endereco == null || nome_Endereco.isEmpty()) {
            errors.put("nome", "Campo obrigatório.");
        }
        String logradouro = (String) fields.get("logradouro");
        if (logradouro == null || logradouro.isEmpty()) {
            errors.put("logradouro", "Campo obrigatório.");
        }
        String bairro = (String) fields.get("bairro");
        if (bairro == null || bairro.isEmpty()) {
            errors.put("bairro", "Campo obrigatório.");
        }
        String cidade = (String) fields.get("cidade");
        if (cidade == null || cidade.isEmpty()) {
            errors.put("cidade", "Campo obrigatório.");
        }
        String estado = (String) fields.get("estado");
        if (estado == null || estado.isEmpty()) {
            errors.put("estado", "Campo obrigatório.");
        }
        String cep = (String) fields.get("cep");
        if (cep == null || cep.isEmpty()) {
            errors.put("cep", "Campo obrigatório.");
        }
        Long numero = (Long) fields.get("numero");
        if (numero == null || numero == 0) {
            errors.put("numero", "Campo obrigatório.");
        }
        
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
        return this.validateForCreate(fields);
    }

}
