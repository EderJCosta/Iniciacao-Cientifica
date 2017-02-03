package rederecicle.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rederecicle.model.base.BaseDAO;
import rederecicle.model.criteria.TelefoneCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Telefone;

public class TelefoneDAO implements BaseDAO<Telefone> {

    @Override
    public void create(Connection conn, Telefone e) throws Exception {
        String sql = "INSERT INTO telefone(empresaid, nome, numero)VALUES (?, ?, ?)RETURNING id;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, e.getEmpresa().getId());
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getNumero());
        ResultSet rs = statement.executeQuery();
        if (rs != null && rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        statement.close();
    }

    @Override
    public Telefone readByID(Connection conn, Long id) throws Exception {
        String sql = "SELECT telefone.*,empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha  empresa_senha,empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo FROM telefone LEFT JOIN empresa ON empresa.id=telefone.empresaid WHERE telefone.id=?";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        Telefone entity = null;
        if (rs != null && rs.next()) {
            entity = new Telefone();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setNumero(rs.getString("numero"));

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

        }
        rs.close();
        statement.close();
        return entity;
    }

    @Override
    public List<Telefone> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT telefone.*,empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha  empresa_senha,empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo FROM telefone LEFT JOIN empresa ON empresa.id=telefone.empresaid WHERE 1=1";
        if(criteria != null && criteria.size() >0){
            Long empresa_ID_EQ = (Long) criteria.get(TelefoneCriteria.EMPRESA_ID_EQ);
            if(empresa_ID_EQ != null && empresa_ID_EQ >0){
                sql += " AND empresa.id ='" +empresa_ID_EQ +"'"; 
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
        List<Telefone> entityList = new ArrayList<>();
        while (rs != null && rs.next()) {
            Telefone entity = new Telefone();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setNumero(rs.getString("numero"));

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
    public void update(Connection conn, Telefone e) throws Exception {
        String sql = "UPDATE telefone SET  nome=?, numero=? WHERE id=?";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getNumero());
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM telefone WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

}
