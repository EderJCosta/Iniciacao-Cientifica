package rederecicle.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rederecicle.model.base.BaseDAO;
import rederecicle.model.criteria.GrupoCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Grupo_Imagem;

public class GrupoDAO implements BaseDAO<Grupo> {

    @Override
    public void create(Connection conn, Grupo e) throws Exception {
        String sql = "INSERT INTO grupo(empresaid, nome, descricao, status) VALUES (?, ?, ?, ?) RETURNING id;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, e.getEmpresa().getId());
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getDescricao());
        statement.setBoolean(++i, true);
        ResultSet rs = statement.executeQuery();
        if (rs != null && rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        statement.close();
        this.updateGrupo_Empresa(conn, e);
    }

    @Override
    public Grupo readByID(Connection conn, Long id) throws Exception {
        String sql = "SELECT grupo.*, empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha  empresa_senha,empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo, membro.id membro_id, membro.nome membro_nome, membro.email membro_email, membro.senha  membro_senha,membro.descricao membro_descricao, membro.inscricao_estadual membro_inscricao_estadual, membro.cnpj membro_cnpj, membro.tipo membro_tipo FROM grupo LEFT JOIN empresa ON empresa.id=grupo.empresaid LEFT JOIN grupo_empresa ge on ge.grupoid=grupo.id left join empresa membro on membro.id=ge.empresaid WHERE grupo.id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        Grupo entity = null;
        while (rs.next()) {
            if (entity == null) {
                entity = new Grupo();
                entity.setId(rs.getLong("id"));
                entity.setNome(rs.getString("nome"));
                entity.setDescricao(rs.getString("descricao"));
                entity.setStatus(rs.getBoolean("status"));

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
            Long membro_ID = rs.getLong("membro_id");
            if (membro_ID != null && membro_ID  > 0) {
                Empresa membro = new Empresa();
                membro.setId(rs.getLong("membro_id"));
                membro.setNome(rs.getString("membro_nome"));
                membro.setEmail(rs.getString("membro_email"));
                membro.setSenha(rs.getString("membro_senha"));
                membro.setDescricao(rs.getString("membro_descricao"));
                membro.setCnpj(rs.getString("membro_cnpj"));
                membro.setInscricao_estadual(rs.getString("membro_inscricao_estadual"));
                membro.setTipo(rs.getString("membro_tipo"));
                entity.getEmpresaList().add(membro);
            }
        }
        rs.close();
        statement.close();

        return entity;
    }

    @Override
    public List<Grupo> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT distinct grupo.*, empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha  empresa_senha, empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo FROM grupo LEFT JOIN empresa ON empresa.id=grupo.empresaid left join grupo_empresa ge on ge.grupoid=grupo.id  WHERE 1=1";
        if (criteria != null && criteria.size() > 0) {
            String nome_ILIKE = (String) criteria.get(GrupoCriteria.NOME_ILIKE);
            if (nome_ILIKE != null) {
                sql += " AND grupo.nome ILIKE '%" + nome_ILIKE + "%'";
            }
            String nome_EQ = (String) criteria.get(GrupoCriteria.NOME_EQ);
            if (nome_EQ != null) {
                sql += " AND grupo.nome = '" + nome_EQ + "'";
            }
            Long empresa_ID_EQ = (Long) criteria.get(GrupoCriteria.EMPRESA_ID_EQ);
            if (empresa_ID_EQ != null && empresa_ID_EQ > 0) {
                sql += " AND empresa.id = '" + empresa_ID_EQ + "'";
            }
            Long id_NE = (Long) criteria.get(GrupoCriteria.ID_NE);
            if (id_NE != null && id_NE > 0) {
                sql += " AND grupo.id <> '" + id_NE + "'";
            }
            Boolean status_EQ = (Boolean) criteria.get(GrupoCriteria.STATUS_EQ);
            if (status_EQ != null) {
                sql += " AND grupo.status = '" + status_EQ + "'";
            }
            Long empresa_Grupos_ID = (Long) criteria.get(GrupoCriteria.EMPRESA_GRUPOS_ID);
            if (empresa_Grupos_ID != null && empresa_Grupos_ID > 0) {
                sql += " AND ge.empresaid = '" + empresa_Grupos_ID + "'";
            }
        }
        if (limit != null && limit > 0) {
            sql += " limit " + limit;
        }
        if (offset != null && offset > 0) {
            sql += " offset " + offset;
        }
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Grupo> entityList = new ArrayList<>();
        while (rs.next()) {
            Grupo entity = new Grupo();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setDescricao(rs.getString("descricao"));
            entity.setStatus(rs.getBoolean("status"));

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
    public void update(Connection conn, Grupo e) throws Exception {
        String sql = "UPDATE grupo SET  nome=?, descricao=?, status=? WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getDescricao());
        statement.setBoolean(++i, e.getStatus());
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();
        this.updateGrupo_Empresa(conn, e);
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM grupo  WHERE  id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

    private void updateGrupo_Empresa(Connection conn, Grupo e) throws SQLException {
        String sql = "DELETE FROM grupo_empresa WHERE grupoid=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();

        sql = "INSERT INTO grupo_empresa(empresaid,grupoid)VALUES(?,?);";
        statement = conn.prepareStatement(sql);
        for (Empresa empresa : e.getEmpresaList()) {
            i = 0;
            statement.setLong(++i, empresa.getId());
            statement.setLong(++i, e.getId());
            statement.execute();
        }
        statement.close();
    }

    public void setImagem(Connection conn, Long id, Grupo_Imagem imagem) throws Exception {
        String sql = "DELETE FROM grupo_imagem WHERE grupoid=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
        sql = "INSERT INTO grupo_imagem(grupoid, imagem) VALUES (?, ?);";
        ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.setBytes(2, imagem.getConteudo());
        ps.execute();
        ps.close();
    }

    public Grupo_Imagem getImagem(Connection conn, Long id) throws Exception {
        Grupo_Imagem imagem = null;
        String sql = "SELECT grupoid, imagem FROM grupo_imagem WHERE grupoid=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            imagem = new Grupo_Imagem();
            imagem.setConteudo(rs.getBytes("imagem"));
        }
        rs.close();
        ps.close();
        return imagem;
    }

}
