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
import rederecicle.model.criteria.EmpresaCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Empresa_Imagem;
import rederecicle.model.entity.Endereco;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Setor;
import rederecicle.model.entity.Telefone;

public class EmpresaDAO implements BaseDAO<Empresa> {

    @Override
    public void create(Connection conn, Empresa e) throws Exception {
        String sql = "INSERT INTO empresa(nome, email, senha, descricao,  inscricao_estadual, cnpj, tipo)VALUES (?, ?, ?, ?, ?, ?, ?)RETURNING id;";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getEmail());
        statement.setString(++i, e.getSenha());
        statement.setString(++i, e.getDescricao());
        statement.setString(++i, e.getInscricao_estadual());
        statement.setString(++i, e.getCnpj());
        statement.setString(++i, e.getTipo());
        ResultSet rs = statement.executeQuery();
        if (rs != null && rs.next()) {
            e.setId(rs.getLong("id"));
        }
        rs.close();
        statement.close();
        this.updateSetorList(conn, e);
        this.updateGrupoList(conn, e);
        this.updateFavoritos(conn, e);
    }

    @Override
    public Empresa readByID(Connection conn, Long id) throws Exception {
        String sql = "SELECT empresa.*,telefone.id telefone_id,telefone.nome telefone_nome, telefone.numero telefone_numero,endereco.id endereco_id,endereco.nome endereco_nome ,endereco.estado endereco_estado,endereco.cidade endereco_cidade, endereco.cep endereco_cep, endereco.bairro endereco_bairro,endereco.logradouro endereco_logradouro,endereco.numero endereco_numero,setor.id setor_id, setor.nome setor_nome,favorito.id favorito_id,favorito.nome favorito_nome,favorito.email  favorito_email,favorito.senha favorito_senha, favorito.descricao favorito_descricao,favorito.inscricao_estadual favorito_inscricao_estadual, favorito.cnpj favorito_cnpj, favorito.tipo favorito_tipo,grupo.id grupo_id, grupo.nome grupo_nome, grupo.descricao grupo_descricao, grupo.empresaid grupo_proprietarioid,proprietario.nome proprietario_nome FROM empresa LEFT JOIN setor_empresa ON setor_empresa.empresaid=empresa.id LEFT JOIN setor ON setor.id=setor_empresa.setorid LEFT JOIN telefone ON telefone.empresaid=empresa.id LEFT JOIN endereco ON endereco.empresaid=empresa.id LEFT JOIN empresa_favoritos ON empresa.id=empresa_favoritos.empresaid  LEFT JOIN empresa favorito ON favorito.id=empresa_favoritos.favoritoid LEFT JOIN grupo_empresa ON grupo_empresa.empresaid=empresa.id LEFT JOIN grupo ON grupo.id=grupo_empresa.grupoid LEFT JOIN empresa proprietario ON proprietario.id=grupo.empresaid WHERE empresa.id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        ResultSet rs = statement.executeQuery();
        Empresa entity = null;
        while (rs.next()) {
            if (entity == null) {
                entity = new Empresa();
                entity.setId(rs.getLong("id"));
                entity.setNome(rs.getString("nome"));
                entity.setEmail(rs.getString("email"));
                entity.setSenha(rs.getString("senha"));
                entity.setDescricao(rs.getString("descricao"));
                entity.setInscricao_estadual(rs.getString("inscricao_estadual"));
                entity.setCnpj(rs.getString("cnpj"));
                entity.setTipo(rs.getString("tipo"));

            }
            Long setorID = rs.getLong("setor_id");
            if (setorID != null && setorID > 0) {
                Setor setor = new Setor();
                setor.setId(setorID);
                setor.setNome(rs.getString("setor_nome"));
                entity.getSetorSet().add(setor);
            }
            Long grupoID = rs.getLong("grupo_id");
            if (grupoID != null && grupoID > 0) {
                Grupo grupo = new Grupo();
                grupo.setId(grupoID);
                grupo.setNome(rs.getString("grupo_nome"));
                grupo.setDescricao(rs.getString("grupo_descricao"));
                Empresa empresaproprietaria = new Empresa();
                empresaproprietaria.setId(rs.getLong("grupo_proprietarioid"));
                empresaproprietaria.setNome(rs.getString("proprietario_nome"));
                grupo.setEmpresa(empresaproprietaria);
                entity.getGrupoSet().add(grupo);
            }
            Long favorito_id = rs.getLong("favorito_id");
            if (favorito_id != null && favorito_id > 0 ) {
                Empresa favorito = new Empresa();
                favorito.setId(rs.getLong("favorito_id"));
                favorito.setNome(rs.getString("favorito_nome"));
                favorito.setEmail(rs.getString("favorito_email"));
                favorito.setSenha(rs.getString("favorito_senha"));
                favorito.setDescricao(rs.getString("favorito_descricao"));
                favorito.setInscricao_estadual(rs.getString("favorito_inscricao_estadual"));
                favorito.setCnpj(rs.getString("favorito_cnpj"));
                favorito.setTipo(rs.getString("favorito_tipo"));
                entity.getEmpresaFavoritasSet().add(favorito);
            }
            Long endereco_id = rs.getLong("endereco_id");
            if(endereco_id != null && endereco_id > 0){
                Endereco endereco = new Endereco();
                endereco.setId(endereco_id);
                endereco.setNome(rs.getString("endereco_nome"));
                endereco.setEstado(rs.getString("endereco_estado"));
                endereco.setCidade(rs.getString("endereco_cidade"));
                endereco.setCep(rs.getString("endereco_cep"));
                endereco.setBairro(rs.getString("endereco_bairro"));
                endereco.setLogradouro(rs.getString("endereco_logradouro"));
                endereco.setNumero(rs.getLong("endereco_numero"));
                entity.getEnderecoSet().add(endereco);
            }
            Long telefone_id = rs.getLong("telefone_id");
            if(telefone_id != null && telefone_id > 0){
                Telefone telefone = new Telefone();
                telefone.setId(telefone_id);
                telefone.setNome(rs.getString("telefone_nome"));
                telefone.setNumero(rs.getString("telefone_numero"));
                entity.getTelefoneSet().add(telefone);
            }
            
        }
        rs.close();
        statement.close();
        return entity;
    }

    @Override
    public List<Empresa> readByCriteria(Connection conn, Map<Long, Object> criteria, Long limit, Long offset) throws Exception {

        String sql = "SELECT empresa.* FROM empresa WHERE 1=1";
        if (criteria != null && criteria.size() > 0) {
            String nome_ILIKE = (String) criteria.get(EmpresaCriteria.NOME_ILIKE);
            if (nome_ILIKE != null) {
                sql += " AND nome ILIKE '%" + nome_ILIKE + "%'";
            }
            String nome_EQ = (String) criteria.get(EmpresaCriteria.NOME_EQ);
            if (nome_EQ != null) {
                sql += " AND nome = '" + nome_EQ + "'";
            }
            String email_ILIKE = (String) criteria.get(EmpresaCriteria.EMAIL_ILIKE);
            if (email_ILIKE != null) {
                sql += " AND email ILIKE '%" + email_ILIKE + "%'";
            }
            String email_EQ = (String) criteria.get(EmpresaCriteria.EMAIL_EQ);
            if (email_EQ != null) {
                sql += " AND email = '" + email_EQ + "'";
            }
            String tipo_EQ = (String) criteria.get(EmpresaCriteria.TIPO_EQ);
            if (tipo_EQ != null) {
                sql += " AND tipo = '" + tipo_EQ + "'";
            }
            String telefone_EQ = (String) criteria.get(EmpresaCriteria.TELEFONE_EQ);
            if (telefone_EQ != null) {
                sql += " AND telefone = '" + telefone_EQ + "'";
            }
            Long id_NE = (Long) criteria.get(EmpresaCriteria.ID_NE);
            if (id_NE != null && id_NE > 0) {
                sql += " AND id <> '" + id_NE + "'";
            }
            String inscricao_EQ = (String) criteria.get(EmpresaCriteria.INSCRICAO_EQ);
            if (inscricao_EQ != null && !inscricao_EQ.isEmpty()) {
                sql += " AND inscricao_estadual = '" + inscricao_EQ + "'";
            }
            String cnpj_EQ = (String) criteria.get(EmpresaCriteria.CNPJ_EQ);
            if (cnpj_EQ != null && !cnpj_EQ.isEmpty()) {
                sql += " AND cnpj = '" + cnpj_EQ + "'";
            }
            String senha_EQ = (String) criteria.get(EmpresaCriteria.SENHA_EQ);
            if (senha_EQ != null) {
                sql += " AND senha = '" + senha_EQ + "'";
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
        List<Empresa> entityList = new ArrayList<>();
        while (rs.next()) {
            Empresa entity = new Empresa();
            entity.setId(rs.getLong("id"));
            entity.setNome(rs.getString("nome"));
            entity.setEmail(rs.getString("email"));
            entity.setSenha(rs.getString("senha"));
            entity.setDescricao(rs.getString("descricao"));
            entity.setCnpj(rs.getString("cnpj"));
            entity.setInscricao_estadual(rs.getString("inscricao_estadual"));
            entity.setTipo(rs.getString("tipo"));
            entityList.add(entity);
        }
        rs.close();
        statement.close();
        return entityList;
    }

    @Override
    public void update(Connection conn, Empresa e) throws Exception {
        int i = 0;
        String sql = "UPDATE empresa SET nome=?, email=?, senha=?, descricao=?, inscricao_estadual=?, cnpj=?, tipo=? WHERE id=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(++i, e.getNome());
        statement.setString(++i, e.getEmail());
        statement.setString(++i, e.getSenha());
        statement.setString(++i, e.getDescricao());
        statement.setString(++i, e.getInscricao_estadual());
        statement.setString(++i, e.getCnpj());
        statement.setString(++i, e.getTipo());
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();
        this.updateSetorList(conn, e);
        this.updateGrupoList(conn, e);
        this.updateFavoritos(conn, e);
    }

    @Override
    public void delete(Connection conn, Long id) throws Exception {
        String sql = "DELETE FROM empresa WHERE id=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, id);
        statement.execute();
        statement.close();
    }

    private void updateSetorList(Connection conn, Empresa e) throws Exception {
        String sql = "DELETE FROM setor_empresa WHERE empresaid=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        int i = 0;
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();

        sql = "INSERT INTO setor_empresa(empresaid,setorid)VALUES(?,?);";
        statement = conn.prepareStatement(sql);
        for (Setor setor : e.getSetorSet()) {
            i = 0;
            statement.setLong(++i, e.getId());
            statement.setLong(++i, setor.getId());
            statement.execute();
        }
        statement.close();
    }

    private void updateGrupoList(Connection conn, Empresa e) throws Exception {
        String sql = "DELETE FROM grupo_empresa WHERE empresaid=?";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();

        sql = "INSERT INTO grupo_empresa(empresaid,grupoid)VALUES(?,?);";
        statement = conn.prepareStatement(sql);
        for (Grupo grupo : e.getGrupoSet()) {
            i = 0;
            statement.setLong(++i, e.getId());
            statement.setLong(++i, grupo.getId());
            statement.execute();
        }
        statement.close();
    }

    private void updateFavoritos(Connection conn, Empresa e) throws SQLException {
        String sql = "DELETE FROM empresa_favoritos WHERE empresaid=?;";
        int i = 0;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setLong(++i, e.getId());
        statement.execute();
        statement.close();

        sql = "INSERT INTO empresa_favoritos(empresaid, favoritoid) VALUES (?, ?);";
        statement = conn.prepareStatement(sql);
        for (Empresa favorito : e.getEmpresaFavoritasSet()) {
            i = 0;
            statement.setLong(++i, e.getId());
            statement.setLong(++i, favorito.getId());
            statement.execute();
        }
        statement.close();
    }
      public void setImagem(Connection conn, Long id, Empresa_Imagem imagem) throws Exception {
        String sql = "DELETE FROM empresa_imagem WHERE empresaid=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.execute();
        ps.close();
        sql = "INSERT INTO empresa_imagem(empresaid, imagem) VALUES (?, ?);";
        ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ps.setBytes(2, imagem.getConteudo());
        ps.execute();
        ps.close();
    }
      
    public Empresa_Imagem getImagem(Connection conn, Long id) throws Exception {
        Empresa_Imagem imagem = null;
        String sql = "SELECT empresaid, imagem FROM empresa_imagem WHERE empresaid=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            imagem = new Empresa_Imagem();
            imagem.setConteudo(rs.getBytes("imagem"));
        }
        rs.close();
        ps.close();
        return imagem;
    }

}
