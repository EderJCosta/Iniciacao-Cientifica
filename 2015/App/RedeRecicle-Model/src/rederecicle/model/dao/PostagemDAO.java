package rederecicle.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rederecicle.model.base.BaseDAO;
import rederecicle.model.criteria.PostagemCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Postagem;
import rederecicle.model.entity.TipoLixo;
import rederecicle.model.entity.TipoMaterial;

public class PostagemDAO implements BaseDAO<Postagem> {

    @Override
    public void create(Connection conn, Postagem e) throws Exception {
        String sql = "INSERT INTO postagem( empresaid, tipo_lixoid, tipo_materialid, grupoid, titulo, descricao, peso, status, data, unidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, e.getEmpresa().getId());
        if (e.getTipoLixo() != null) {
            statement.setLong(++i, e.getTipoLixo().getId());
        } else {
            statement.setNull(++i, Types.BIGINT);
        }
        if (e.getTipoMaterial() != null) {
            statement.setLong(++i, e.getTipoMaterial().getId());
        } else {
            statement.setNull(++i, Types.BIGINT);
        }
        if (e.getGrupo() != null) {
            statement.setLong(++i, e.getGrupo().getId());
        } else {
            statement.setNull(++i, Types.BIGINT);
        }

        statement.setString(++i, e.getTitulo());
        statement.setString(++i, e.getDescricao());
        statement.setDouble(++i, e.getPeso());
        statement.setBoolean(++i, e.getStatus());
        statement.setTimestamp(++i, e.getData());
        statement.setString(++i, e.getUnidade());
        ResultSet rs = statement.executeQuery();
        if (rs != null && rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        statement.close();
    }

    @Override
    public Postagem readByID(Connection conn, Long id) throws Exception {
        String sql = "SELECT postagem.*,empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha  empresa_senha,empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo, tipo_lixo.id tipo_lixo_id,tipo_lixo.nome tipo_lixo_nome,tipo_material.id tipo_material_id, tipo_material.nome tipo_material_nome,grupo.id grupo_id, grupo.empresaid grupo_empresa_id,grupo.nome grupo_nome,grupo.descricao grupo_descricao, empresa_grupo.id empresa_grupo_id, empresa_grupo.nome empresa_grupo_nome, empresa_grupo.email empresa_grupo_email,empresa_grupo.senha empresa_grupo_senha,empresa_grupo.descricao empresa_grupo_descricao, empresa_grupo.inscricao_estadual empresa_grupo_inscricao, empresa_grupo.cnpj empresa_grupo_cnpj, empresa_grupo.tipo empresa_grupo_tipo FROM postagem LEFT JOIN empresa ON postagem.empresaid=empresa.id LEFT JOIN grupo ON grupo.id=postagem.grupoid LEFT JOIN tipo_lixo ON tipo_lixo.id=postagem.tipo_lixoid LEFT JOIN tipo_material ON tipo_material.id=postagem.tipo_materialid left join empresa empresa_grupo on empresa_grupo.id=grupo.empresaid where postagem.id=?";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        Postagem entity = null;
        if (rs.next()) {
            entity = new Postagem();
            entity.setId(rs.getLong("id"));
            entity.setTitulo(rs.getString("titulo"));
            entity.setDescricao(rs.getString("descricao"));
            entity.setPeso(rs.getDouble("peso"));
            entity.setStatus(rs.getBoolean("status"));
            entity.setData(rs.getTimestamp("data"));
            entity.setUnidade(rs.getString("unidade"));

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

            TipoLixo tipoLixo = null;
            Long tipo_lixo_id = rs.getLong("tipo_lixo_id");
            if (tipo_lixo_id > 0) {
                tipoLixo = new TipoLixo();
                tipoLixo.setId(rs.getLong("tipo_lixo_id"));
                tipoLixo.setNome(rs.getString("tipo_lixo_nome"));
            }
            entity.setTipoLixo(tipoLixo);

            TipoMaterial tipoMaterial = null;
            Long tipo_material_id = rs.getLong("tipo_material_id");
            if (tipo_material_id > 0) {
                tipoMaterial = new TipoMaterial();
                tipoMaterial.setId(rs.getLong("tipo_material_id"));
                tipoMaterial.setNome(rs.getString("tipo_material_nome"));
            }
            entity.setTipoMaterial(tipoMaterial);

            Grupo grupo = null;
            Long grupo_id = rs.getLong("grupo_id");
            if (grupo_id > 0) {
                grupo = new Grupo();
                grupo.setId(rs.getLong("grupo_id"));
                grupo.setNome(rs.getString("grupo_nome"));
                grupo.setDescricao(rs.getString("grupo_descricao"));

                Empresa empresaGrupo = new Empresa();
                empresaGrupo.setId(rs.getLong("empresa_grupo_id"));
                empresaGrupo.setNome(rs.getString("empresa_grupo_nome"));
                empresaGrupo.setEmail(rs.getString("empresa_grupo_email"));
                empresaGrupo.setSenha(rs.getString("empresa_grupo_senha"));
                empresaGrupo.setDescricao(rs.getString("empresa_grupo_descricao"));
                empresaGrupo.setCnpj(rs.getString("empresa_grupo_cnpj"));
                empresaGrupo.setInscricao_estadual(rs.getString("empresa_grupo_inscricao"));
                empresaGrupo.setTipo(rs.getString("empresa_grupo_tipo"));
                grupo.setEmpresa(empresaGrupo);
            }
            entity.setGrupo(grupo);
        }
        rs.close();
        statement.close();
        return entity;
    }

    @Override
    public List<Postagem> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        String sql = "SELECT distinct postagem.*,empresa.id empresa_id, empresa.nome empresa_nome, empresa.email empresa_email, empresa.senha  empresa_senha,empresa.descricao empresa_descricao, empresa.inscricao_estadual empresa_inscricao_estadual, empresa.cnpj empresa_cnpj, empresa.tipo empresa_tipo,tipo_lixo.id tipo_lixo_id,tipo_lixo.nome tipo_lixo_nome,tipo_material.id tipo_material_id, tipo_material.nome tipo_material_nome,grupo.id grupo_id, grupo.empresaid grupo_empresa_id,grupo.nome grupo_nome,grupo.descricao grupo_descricao, empresa_grupo.id empresa_grupo_id, empresa_grupo.nome empresa_grupo_nome, empresa_grupo.email empresa_grupo_email,empresa_grupo.senha empresa_grupo_senha,empresa_grupo.descricao empresa_grupo_descricao, empresa_grupo.inscricao_estadual empresa_grupo_inscricao, empresa_grupo.cnpj empresa_grupo_cnpj, empresa_grupo.tipo empresa_grupo_tipo FROM postagem LEFT JOIN empresa ON postagem.empresaid=empresa.id LEFT JOIN grupo ON grupo.id=postagem.grupoid LEFT JOIN tipo_lixo ON tipo_lixo.id=postagem.tipo_lixoid LEFT JOIN tipo_material ON tipo_material.id=postagem.tipo_materialid left join empresa empresa_grupo on empresa_grupo.id=grupo.empresaid LEFT JOIN empresa_favoritos on empresa_favoritos.favoritoid=postagem.empresaid WHERE 1=1";
        if (criteria != null && criteria.size() > 0) {
            String titulo_ILIKE = (String) criteria.get(PostagemCriteria.TITULO_ILIKE);
            if (titulo_ILIKE != null) {
                sql += " AND titulo ILIKE '%" + titulo_ILIKE + "%'";
            }
            String titulo_EQ = (String) criteria.get(PostagemCriteria.TITULO_EQ);
            if (titulo_EQ != null) {
                sql += " AND titulo = '" + titulo_EQ + "'";
            }
            Double peso_EQ = (Double) criteria.get(PostagemCriteria.PESO_EQ);
            if (peso_EQ != null && peso_EQ > 0) {
                sql += " AND peso = '" + peso_EQ + "'";
            }
            Double peso_LE = (Double) criteria.get(PostagemCriteria.PESO_LE);
            if (peso_LE != null && peso_LE > 0) {
                sql += " AND peso <= '" + peso_LE + "'";
            }
            String unidade_EQ = (String) criteria.get(PostagemCriteria.UNIDADE_EQ);
            if (unidade_EQ != null) {
                sql += " AND unidade = '" + unidade_EQ + "'";
            }
            Boolean status_EQ = (Boolean) criteria.get(PostagemCriteria.STATUS_EQ);
            if (status_EQ != null) {
                sql += " AND postagem.status = '" + status_EQ + "'";
            }
            Long tipolixo_EQ = (Long) criteria.get(PostagemCriteria.TIPOLIXO_ID_EQ);
            if (tipolixo_EQ != null && tipolixo_EQ > 0) {
                sql += " AND tipo_lixoid = '" + tipolixo_EQ + "'";
            }
            Long tipoMaterial_EQ = (Long) criteria.get(PostagemCriteria.TIPOMATERIAL_ID_EQ);
            if (tipoMaterial_EQ != null && tipoMaterial_EQ > 0) {
                sql += " AND tipo_materialid = '" + tipoMaterial_EQ + "'";
            }
            Long grupo_ID_EQ = (Long) criteria.get(PostagemCriteria.GRUPO_ID_EQ);
            if (grupo_ID_EQ != null && grupo_ID_EQ > 0) {
                sql += " AND grupoid = '" + grupo_ID_EQ + "'";
            }
            Long empresa_ID_EQ = (Long) criteria.get(PostagemCriteria.EMPRESA_ID_EQ);
            if (empresa_ID_EQ != null && empresa_ID_EQ > 0) {
                sql += " AND postagem.empresaid = '"+empresa_ID_EQ+"'";
            }
            
            
            Long feed_EQ = (Long) criteria.get(PostagemCriteria.FEED_EQ);
            if(feed_EQ != null && feed_EQ >0){
                sql += " AND empresa_favoritos.empresaid ='" + feed_EQ + "'";
            }
            Boolean grupo_ISNULL = (Boolean) criteria.get(PostagemCriteria.GRUPO_ISNULL);
            if(grupo_ISNULL!= null && grupo_ISNULL == Boolean.TRUE){
                sql += " AND grupoid ISNULL";
            }
            Boolean data_ORDERBY = (Boolean) criteria.get(PostagemCriteria.DATA_ORDERBY);
            if(data_ORDERBY!= null && data_ORDERBY == Boolean.TRUE){
                sql += " ORDER BY data DESC";
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
        List<Postagem> entityList = new ArrayList<>();
        while (rs.next()) {
            Postagem entity = new Postagem();
            entity.setId(rs.getLong("id"));
            entity.setTitulo(rs.getString("titulo"));
            entity.setDescricao(rs.getString("descricao"));
            entity.setPeso(rs.getDouble("peso"));
            entity.setStatus(rs.getBoolean("status"));
            entity.setData(rs.getTimestamp("data"));
            entity.setUnidade(rs.getString("unidade"));

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

            TipoLixo tipoLixo = null;
            Long tipo_lixo_id = rs.getLong("tipo_lixo_id");
            if (tipo_lixo_id > 0) {
                tipoLixo = new TipoLixo();
                tipoLixo.setId(rs.getLong("tipo_lixo_id"));
                tipoLixo.setNome(rs.getString("tipo_lixo_nome"));
            }
            entity.setTipoLixo(tipoLixo);

            TipoMaterial tipoMaterial = null;
            Long tipo_material_id = rs.getLong("tipo_material_id");
            if (tipo_material_id > 0) {
                tipoMaterial = new TipoMaterial();
                tipoMaterial.setId(rs.getLong("tipo_material_id"));
                tipoMaterial.setNome(rs.getString("tipo_material_nome"));
            }
            entity.setTipoMaterial(tipoMaterial);

            Grupo grupo = null;
            Long grupo_id = rs.getLong("grupo_id");
            if (grupo_id != null && grupo_id > 0) {
                grupo = new Grupo();
                grupo.setId(rs.getLong("grupo_id"));
                grupo.setNome(rs.getString("grupo_nome"));
                grupo.setDescricao(rs.getString("grupo_descricao"));

                Empresa empresaGrupo = new Empresa();
                empresaGrupo.setId(rs.getLong("empresa_grupo_id"));
                empresaGrupo.setNome(rs.getString("empresa_grupo_nome"));
                empresaGrupo.setEmail(rs.getString("empresa_grupo_email"));
                empresaGrupo.setSenha(rs.getString("empresa_grupo_senha"));
                empresaGrupo.setDescricao(rs.getString("empresa_grupo_descricao"));
                empresaGrupo.setCnpj(rs.getString("empresa_grupo_cnpj"));
                empresaGrupo.setInscricao_estadual(rs.getString("empresa_grupo_inscricao"));
                empresaGrupo.setTipo(rs.getString("empresa_grupo_tipo"));
                grupo.setEmpresa(empresaGrupo);
                entity.setGrupo(grupo);
            }
            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;
    }

    @Override
    public void update(Connection conn, Postagem e) throws Exception {
        String sql = "UPDATE postagem SET titulo=?,descricao=?, peso=?, status=?, data=?, unidade=? WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(++i, e.getTitulo());
        statement.setString(++i, e.getDescricao());
        statement.setDouble(++i, e.getPeso());
        statement.setBoolean(++i, e.getStatus());
        statement.setTimestamp(++i, e.getData());
        statement.setString(++i, e.getUnidade());
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM postagem  WHERE  id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

}
