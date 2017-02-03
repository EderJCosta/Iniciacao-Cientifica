package rederecicle.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rederecicle.model.base.BaseDAO;
import rederecicle.model.criteria.TipoLixoCriteria;
import rederecicle.model.entity.TipoLixo;

public class TipoLixoDAO implements BaseDAO<TipoLixo>{

    @Override
    public void create(Connection conn, TipoLixo e) throws Exception {
        String sql = "INSERT INTO tipo_lixo(nome) VALUES (?) RETURNING id;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, e.getNome());
        ResultSet rs = statement.executeQuery();
        if(rs != null && rs.next()){
            e.setId(rs.getLong("id"));
        }
        rs.close();
        statement.close();
    }

    @Override
    public TipoLixo readByID(Connection conn, Long id) throws Exception {
        String sql = "SELECT id,nome FROM tipo_lixo WHERE id=?";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        TipoLixo entity = null;
        if(rs.next()){
            entity = new TipoLixo();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
        }
    rs.close();
    statement.close();
    return  entity;
    }

    @Override
    public List<TipoLixo> readByCriteria(Connection conn, Map<Long, Object> criteria,Long limit, Long offset) throws Exception {
        String sql = "SELECT id,nome FROM tipoproduto WHERE 1=1";
        if(criteria != null && criteria.size() > 0){
            String nome_ILIKE = (String) criteria.get(TipoLixoCriteria.NOME_ILIKE);
            if(nome_ILIKE != null){
                sql += " AND nome ILIKE '%" + nome_ILIKE +"%'";
            }
            String nome_EQ = (String) criteria.get(TipoLixoCriteria.NOME_EQ);
            if(nome_EQ != null){
                sql += " AND nome = '" + nome_EQ +"'";
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
        List<TipoLixo> entityList = new ArrayList<>();
        while(rs.next()){
            TipoLixo entity = new TipoLixo();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;
    }

    @Override
    public void update(Connection conn, TipoLixo e) throws Exception {
        String sql = "UPDATE tipo_lixo SET nome=? WHERE id=?";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, e.getNome());
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
       String sql = "DELETE FROM tipo_lixo WHERE id=?";
       int i =0;
       PreparedStatement statement = conn.prepareStatement(sql);
       statement.setLong(++i, id);
       statement.execute();
       statement.close();
    }
    
}
