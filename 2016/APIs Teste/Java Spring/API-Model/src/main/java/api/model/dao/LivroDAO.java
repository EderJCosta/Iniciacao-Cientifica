package api.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import api.model.base.BaseDAO;
import api.model.entity.Livro;

public class LivroDAO implements BaseDAO<Livro> {

    @Override
    public void create(Connection conn, Livro entity) throws Exception {
        String sql = "INSERT INTO livro(nome, autor_fk, categoria_fk) VALUES (?,?,?) RETURNING id;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getNome());
        statement.setLong(++i, entity.getAutor().getId());
        statement.setLong(++i, entity.getCategoria().getId());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            entity.setId(rs.getLong("id"));
        }
        rs.close();
        statement.close();
    }

    @Override
    public Livro readById(Connection conn, Long id) throws Exception {
        String sql = "SELECT * from livro WHERE livro.id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        Livro entity = null;
        if (rs.next()) {
            entity = new Livro();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setCategoria(null);
            entity.setAutor(null);
        }
        rs.close();
        statement.close();
        return entity;
    }

    @Override
    public List<Livro> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT * from livro WHERE 1=1";
        
        
         if(limit != null && limit > 0){
            sql += " limit " + limit;
        }
        if(offset != null && offset > 0){
            sql += " offset " + offset;
        }
        int i = 0;
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Livro> entityList = new ArrayList<>();
        while (rs.next()) {
            Livro entity = new Livro();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entityList.add(entity);   
        }
        rs.close();
        statement.close();
        return entityList;
    }

    @Override
    public void update(Connection conn, Livro entity) throws Exception {
        String sql = "UPDATE livro SET  nome=? WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, entity.getNome());
        statement.setLong(++i, entity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM livro WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

}
