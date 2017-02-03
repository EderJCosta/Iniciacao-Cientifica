package rederecicle.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ConnectionManager;
import rederecicle.model.ServiceLocator;
import rederecicle.model.base.service.BaseEmpresaService;
import rederecicle.model.criteria.EmpresaCriteria;
import rederecicle.model.dao.EmpresaDAO;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Empresa_Imagem;

public class EmpresaService implements BaseEmpresaService {

    @Override
    public void create(Empresa e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpresaDAO dao = new EmpresaDAO();
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
    public Empresa readByID(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Empresa entity = null;
        try {
            EmpresaDAO dao = new EmpresaDAO();
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
    public List<Empresa> readByCriteria(Map<Long, Object> criteria,Long limit, Long offset) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        List<Empresa> entityList = null;
        try {
            EmpresaDAO dao = new EmpresaDAO();
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
    public void update(Empresa e) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpresaDAO dao = new EmpresaDAO();
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
            EmpresaDAO dao = new EmpresaDAO();
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
            criteria.put(EmpresaCriteria.NOME_EQ, nome);
            Long id = (Long) fields.get("id");
            if (id != null && id > 0) {
                criteria.put(EmpresaCriteria.ID_NE, id);
            }
            List<Empresa> EmpresaList = ServiceLocator.getEmpresaService().readByCriteria(criteria,null,null);
            if (!EmpresaList.isEmpty()) {
                errors.put("nome", "Este Nome já se encontra em uso.");
            }
        }
        String email = (String) fields.get("email");
        if (email == null || email.isEmpty()) {
            errors.put("email", "Campo obrigatório.");
        } else {
            Map<Long, Object> criteria = new HashMap<Long, Object>();
            criteria.put(EmpresaCriteria.EMAIL_EQ, email);
            Long id = (Long) fields.get("id");
            if (id != null && id > 0) {
                criteria.put(EmpresaCriteria.ID_NE, id);
            }
            List<Empresa> EmpresaList = ServiceLocator.getEmpresaService().readByCriteria(criteria,null,null);
            if (!EmpresaList.isEmpty()) {
                errors.put("email", "Este Email já se encontra em uso.");
            }
        }
        String inscricao = (String) fields.get("inscricao");
        if (inscricao == null || inscricao.isEmpty()) {
            errors.put("inscricao", "Campo obrigatório.");
        } else {
            Map<Long, Object> criteria = new HashMap<Long, Object>();
            criteria.put(EmpresaCriteria.INSCRICAO_EQ, inscricao);
            Long id = (Long) fields.get("id");
            if (id != null && id > 0) {
                criteria.put(EmpresaCriteria.ID_NE, id);
            }
            List<Empresa> EmpresaList = ServiceLocator.getEmpresaService().readByCriteria(criteria,null,null);
            if (!EmpresaList.isEmpty()) {
                errors.put("inscricao", "Esta Inscrição já está cadastrada.");
            }
        }
        String cnpj = (String) fields.get("cnpj");
        if (cnpj == null || cnpj.isEmpty()) {
            errors.put("cnpj", "Campo obrigatório.");
        } else {
            Map<Long, Object> criteria = new HashMap<Long, Object>();
            criteria.put(EmpresaCriteria.CNPJ_EQ, cnpj);
            Long id = (Long) fields.get("id");
            if (id != null && id > 0) {
                criteria.put(EmpresaCriteria.ID_NE, id);
            }
            List<Empresa> EmpresaList = ServiceLocator.getEmpresaService().readByCriteria(criteria,null,null);
            if (!EmpresaList.isEmpty()) {
                errors.put("cnpj", "Este CNPJ já está cadastrado.");
            }
        }
        String senha = (String) fields.get("senha");
        if(senha == null || senha.isEmpty()){
            errors.put("senha", "Campo obrigatório!");
        }
        String descricao = (String) fields.get("descricao");
        if(descricao == null || descricao.isEmpty()){
            errors.put("descricao", "Campo obrigatório!");
        }
        String tipo = (String) fields.get("tipo");
        if(tipo == null || tipo.isEmpty()){
            errors.put("tipo", "Campo obrigatório!");
        }
        return errors;
    }

    @Override
    public Map<String, String> validateForUpdate(Map<String, Object> fields) throws Exception {
       return this.validateForCreate(fields);
    }

    @Override
    public Empresa login(String email, String senha) throws Exception {
      Empresa empresaLogada = null;
      if(email != null && !email.isEmpty() && senha != null && !senha.isEmpty()){
          Map<Long,Object> criteria = new HashMap<>();
          criteria.put(EmpresaCriteria.EMAIL_EQ, email);
          criteria.put(EmpresaCriteria.SENHA_EQ, senha);
          
          List<Empresa> entityList = this.readByCriteria(criteria, null,null);
          
          if(entityList != null && entityList.size() == 1){
              Empresa empresaRetornado = entityList.get(0);
              if(empresaRetornado.getEmail().equals(email) && empresaRetornado.getSenha().equals(senha)){
                  empresaLogada = empresaRetornado;
              }
          }
      }
      return  empresaLogada;
    }
    
    @Override
    public void setImagem(Long id, Empresa_Imagem imagem) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            EmpresaDAO dao = new EmpresaDAO();
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
    public Empresa_Imagem getImagem(Long id) throws Exception {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Empresa_Imagem imagem = null;
        try {
            EmpresaDAO dao = new EmpresaDAO();
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
