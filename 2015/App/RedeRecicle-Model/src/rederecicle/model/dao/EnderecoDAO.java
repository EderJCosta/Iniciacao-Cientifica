package rederecicle.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rederecicle.model.base.BaseDAO;
import rederecicle.model.criteria.EnderecoCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Endereco;

public class EnderecoDAO implements BaseDAO<Endereco> {

    @Override
    public void create(Connection conn, Endereco e) throws Exception {
        String sql = "INSERT INTO endereco(nome, logradouro, bairro, cidade, cep, estado, numero, empresaid)VALUES (?,?,?,?,?,?,?,?) RETURNING id;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getLogradouro());
        statement.setString(++i, e.getBairro());
        statement.setString(++i, e.getCidade());
        statement.setString(++i, e.getCep());
        statement.setString(++i, e.getEstado());
        statement.setLong(++i, e.getNumero());
        statement.setLong(++i, e.getEmpresa().getId());
        ResultSet rs = statement.executeQuery();
        if (rs != null && rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        statement.close();
    }

    @Override
    public Endereco readByID(Connection conn, Long id) throws Exception {
        String sql = "Select endereco.*, empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha empresa_senha ,empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo  from endereco join empresa  on empresa.id=endereco.empresaid WHERE endereco.id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        Endereco entity = null;
        while (rs.next()) {
            entity = new Endereco();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setLogradouro(rs.getString("logradouro"));
            entity.setBairro(rs.getString("bairro"));
            entity.setCidade(rs.getString("cidade"));
            entity.setCep(rs.getString("cep"));
            entity.setEstado(rs.getString("estado"));
            entity.setNumero(rs.getLong("numero"));

            Empresa empresa = new Empresa();
            entity.setEmpresa(empresa);
            empresa.setId(rs.getLong("empresa_id"));
            empresa.setNome(rs.getString("empresa_nome"));
            empresa.setEmail(rs.getString("empresa_email"));
            empresa.setDescricao(rs.getString("empresa_descricao"));
            empresa.setSenha(rs.getString("empresa_senha"));
            empresa.setCnpj(rs.getString("empresa_cnpj"));
            empresa.setInscricao_estadual(rs.getString("empresa_inscricao_estadual"));
            empresa.setTipo(rs.getString("empresa_tipo"));
        }
        rs.close();
        statement.close();
        return entity;
    }

    @Override
    public List<Endereco> readByCriteria(Connection conn, Map<Long, Object> criteria,Long limit, Long offset) throws Exception {

        String sql = "Select endereco.*, empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha  empresa_senha,empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo from endereco join empresa  on empresa.id=endereco.empresaid WHERE 1=1";
        if (criteria != null && criteria.size() > 0) {
            String nome_ILIKE = (String) criteria.get(EnderecoCriteria.NOME_ILIKE);
            if (nome_ILIKE != null) {
                sql += " AND nome_endereco ILIKE '%" + nome_ILIKE + "%'";
            }
            String nome_EQ = (String) criteria.get(EnderecoCriteria.NOME_EQ);
            if (nome_EQ != null) {
                sql += " AND nome_endereco = '" + nome_EQ + "'";
            }
            String estado_ILIKE = (String) criteria.get(EnderecoCriteria.ESTADO_ILIKE);
            if (estado_ILIKE != null) {
                sql += " AND estado ILIKE '%" + estado_ILIKE + "%'";
            }
            String estado_EQ = (String) criteria.get(EnderecoCriteria.ESTADO_EQ);
            if (estado_EQ != null) {
                sql += " AND estado = '" + estado_EQ + "'";
            }
            String cidade_ILIKE = (String) criteria.get(EnderecoCriteria.CIDADE_ILIKE);
            if (cidade_ILIKE != null) {
                sql += " AND cidade ILIKE '%" + cidade_ILIKE + "%'";
            }
            String cidade_EQ = (String) criteria.get(EnderecoCriteria.CIDADE_EQ);
            if (cidade_EQ != null) {
                sql += " AND cidade = '" + cidade_EQ + "'";
            }
            String bairro_ILIKE = (String) criteria.get(EnderecoCriteria.BAIRRO_ILIKE);
            if (bairro_ILIKE != null) {
                sql += " AND bairro ILIKE '%" + bairro_ILIKE + "%'";
            }
            String bairro_EQ = (String) criteria.get(EnderecoCriteria.BAIRRO_EQ);
            if (bairro_EQ != null) {
                sql += " AND bairro = '" + bairro_EQ + "'";
            }
            String logradouro_ILIKE = (String) criteria.get(EnderecoCriteria.LOGRADOURO_ILIKE);
            if (logradouro_ILIKE != null) {
                sql += " AND logradouro ILIKE '%" + logradouro_ILIKE + "%'";
            }
            String logradouro_EQ = (String) criteria.get(EnderecoCriteria.LOGRADOURO_EQ);
            if (logradouro_EQ != null) {
                sql += " AND logradouro = '" + logradouro_EQ + "'";
            }
            Long cep_EQ = (Long) criteria.get(EnderecoCriteria.CEP_EQ);
            if (cep_EQ != null) {
                sql += " AND cep = '" + cep_EQ + "'";
            }
            Long numero_EQ = (Long) criteria.get(EnderecoCriteria.NUMERO_EQ);
            if (numero_EQ != null) {
                sql += " AND numero = '" + numero_EQ + "'";
            }
            Long empresa_ID_EQ = (Long) criteria.get(EnderecoCriteria.EMPRESA_ID_EQ);
            if(empresa_ID_EQ != null && empresa_ID_EQ >0){
                sql += " AND empresa.id ='" +empresa_ID_EQ+"'"; 
            }
        }
        if(limit != null && limit > 0){
            sql += " limit " + limit;
        }
        if(offset != null && offset > 0){
            sql += " offset " + offset;
        }
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Endereco> entityList = new ArrayList<>();
        while (rs.next()) {
            Endereco entity = new Endereco();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setLogradouro(rs.getString("logradouro"));
            entity.setBairro(rs.getString("bairro"));
            entity.setCidade(rs.getString("cidade"));
            entity.setCep(rs.getString("cep"));
            entity.setEstado(rs.getString("estado"));
            entity.setNumero(rs.getLong("numero"));

            Empresa empresa = new Empresa();
            empresa.setId(rs.getLong("empresa_id"));
            empresa.setNome(rs.getString("empresa_nome"));
            empresa.setEmail(rs.getString("empresa_email"));
            empresa.setSenha(rs.getString("empresa_senha"));
            empresa.setDescricao(rs.getString("empresa_descricao"));
            empresa.setCnpj(rs.getString("empresa_cnpj"));
            empresa.setInscricao_estadual(rs.getString("empresa_inscricao_estadual"));
            empresa.setTipo(rs.getString("empresa_tipo"));
            entity.setEmpresa(empresa);
            entityList.add(entity);
        }

        rs.close();
        statement.close();
        return entityList;
    }

    @Override
    public void update(Connection conn, Endereco e) throws Exception {
        String sql = "UPDATE endereco SET nome=?, logradouro=?, bairro=?, cidade=?, cep=?, estado=?, numero=? WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getLogradouro());
        statement.setString(++i, e.getBairro());
        statement.setString(++i, e.getCidade());
        statement.setString(++i, e.getCep());
        statement.setString(++i, e.getEstado());
        statement.setLong(++i, e.getNumero());
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM endereco WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

}
