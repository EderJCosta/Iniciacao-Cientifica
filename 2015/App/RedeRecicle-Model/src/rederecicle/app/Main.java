package rederecicle.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rederecicle.model.ServiceLocator;
import rederecicle.model.criteria.EmpresaCriteria;
import rederecicle.model.criteria.EnderecoCriteria;
import rederecicle.model.criteria.GrupoCriteria;
import rederecicle.model.criteria.PostagemCriteria;
import rederecicle.model.criteria.SetorCriteria;
import rederecicle.model.criteria.TelefoneCriteria;
import rederecicle.model.criteria.TipoLixoCriteria;
import rederecicle.model.criteria.TipoMaterialCriteria;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Endereco;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Postagem;
import rederecicle.model.entity.Setor;
import rederecicle.model.entity.Telefone;
import rederecicle.model.entity.TipoLixo;
import rederecicle.model.entity.TipoMaterial;

public class Main {

    public static void main(String[] args) throws Exception {
        Empresa empresa = new Empresa();
        Endereco endereco = new Endereco();
        Grupo grupo = new Grupo();
        Postagem postagem = new Postagem();
        Setor setor = new Setor();
        TipoLixo tipolixo = new TipoLixo();

        List<Empresa> empresaList = new ArrayList<>();
        List<Endereco> enderecoList = new ArrayList<>();
        List<Grupo> grupoList = new ArrayList<>();
        List<Postagem> postagemList = new ArrayList<>();
        List<Setor> setorList = new ArrayList<>();
        List<TipoLixo> tipolixoList = new ArrayList<>();
        List<TipoMaterial> tipoMaterialList = new ArrayList<>();

        // TESTE DAO EMPRESA
        empresa.setNome("teste0");
        empresa.setEmail("teste1@gmail.com");
        empresa.setSenha("123");
        empresa.setInscricao_estadual("879876");
        empresa.setTipo("C");
        empresa.setCnpj("12312");

        /*
         ServiceLocator.getEmpresaService().create(empresa);
         System.out.println("create");
         System.out.println(empresa.getId());
         System.out.println(empresa.getNome());
         Long id = empresa.getId();
         empresa = ServiceLocator.getEmpresaService().readByID(id);
         System.out.println("read by id");
         System.out.println(empresa.getId());
         System.out.println(empresa.getNome());
         empresa.setNome("teste1");
         Map<Long, Object> criteriaempresa = new HashMap<>();
         criteriaempresa.put(EmpresaCriteria.CNPJ_EQ, 111L);
         criteriaempresa.put(EmpresaCriteria.EMAIL_EQ, "adsa@gmail");
         criteriaempresa.put(EmpresaCriteria.EMAIL_ILIKE, "ads");
         criteriaempresa.put(EmpresaCriteria.INSCRICAO_EQ, 111L);
         criteriaempresa.put(EmpresaCriteria.NOME_ILIKE, "11");
         criteriaempresa.put(EmpresaCriteria.NOME_EQ, "111");
         criteriaempresa.put(EmpresaCriteria.TELEFONE_EQ, "111");
         criteriaempresa.put(EmpresaCriteria.TIPO_EQ, "F");
         empresaList = ServiceLocator.getEmpresaService().readByCriteria(criteriaempresa);
         System.out.println("criteria " + empresaList.size());
         for (Empresa empresa1 : empresaList) {
  
         System.out.println(empresa1.getNome());
         }
         ServiceLocator.getEmpresaService().update(empresa);
         System.out.println("update");
         System.out.println(empresa.getId());
         System.out.println(empresa.getNome());
         ServiceLocator.getEmpresaService().delete(empresa.getId());
         System.out.println("delete");
        
         */
        // TESTE DAO ENDERECO
        empresa.setId(5L);
        endereco.setNome("padrao");
        endereco.setBairro("centro");
        endereco.setCep("123123123");
        endereco.setCidade("Santa Rita");
        endereco.setEmpresa(empresa);
        endereco.setEstado("Minas Gerais");
        endereco.setLogradouro("Rua Olavio Pirata");
        endereco.setNumero(175L);
        /*
         ServiceLocator.getEnderecoService().create(endereco);
         Long id = endereco.getId();
         System.out.println("create");
         System.out.println(endereco.getNomeEndereco());
         endereco = ServiceLocator.getEnderecoService().readByID(id);
         System.out.println("read by id");
         System.out.println(endereco.getBairro());
         Map<Long,Object> criteriaendereco = new HashMap<>();
         criteriaendereco.put(EnderecoCriteria.BAIRRO_EQ, "centro");
         criteriaendereco.put(EnderecoCriteria.BAIRRO_ILIKE, "ro");
         criteriaendereco.put(EnderecoCriteria.CEP_EQ, 123123L);
         criteriaendereco.put(EnderecoCriteria.CIDADE_EQ, "Santa Rita");
         criteriaendereco.put(EnderecoCriteria.CIDADE_ILIKE, "ita");
         criteriaendereco.put(EnderecoCriteria.ESTADO_EQ, "Minas Gerais");
         criteriaendereco.put(EnderecoCriteria.ESTADO_ILIKE, "ina");
         criteriaendereco.put(EnderecoCriteria.LOGRADOURO_EQ, "Rua Olavio Pirata");
         criteriaendereco.put(EnderecoCriteria.LOGRADOURO_ILIKE, "irata");
         criteriaendereco.put(EnderecoCriteria.NOME_EQ, "padrao");
         criteriaendereco.put(EnderecoCriteria.NOME_ILIKE, "rao");
         criteriaendereco.put(EnderecoCriteria.NUMERO_EQ, 175L);
         enderecoList = ServiceLocator.getEnderecoService().readByCriteria(criteriaendereco);
         System.out.println("criteria "+ enderecoList.size());
         for(Endereco end : enderecoList){
         System.out.println(end.getNomeEndereco());
         System.out.println(end.getCep());
         }
         endereco.setNomeEndereco("zzzzzzzzzzz");
         ServiceLocator.getEnderecoService().update(endereco);
         System.out.println("update");
         System.out.println(endereco.getNomeEndereco());
        
         ServiceLocator.getEnderecoService().delete(endereco.getId());
         System.out.println("delete");
        
      

         //TESTE GRUPO DAO
         grupo.setNome("testeDAO");
         grupo.setDescricao("Grupo para teste DAO");
         grupo.setEmpresa(empresa);

         /*
         ServiceLocator.getGrupoService().create(grupo);
         System.out.println("create");
         System.out.println(grupo.getNome());
         Long id = grupo.getId();
         System.out.println(" ");
         grupo = ServiceLocator.getGrupoService().readByID(id);
         System.out.println("read by id");
         System.out.println(grupo.getNome());
         System.out.println(" ");
         Map<Long,Object> criteria = new HashMap<>();
         criteria.put(GrupoCriteria.NOME_EQ, "testeDAO");
         criteria.put(GrupoCriteria.NOME_ILIKE, "DAO");
         grupoList = ServiceLocator.getGrupoService().readByCriteria(criteria);
         System.out.println("criteria " + grupoList.size());
         for(Grupo gp : grupoList){
         System.out.println(gp.getNome());
         }
         grupo.setNome("GRUPO DAO");
         ServiceLocator.getGrupoService().update(grupo);
         System.out.println("update");
         System.out.println(grupo.getNome());
         ServiceLocator.getGrupoService().delete(id);
         System.out.println("delete");
      
         //TESTE POSTAGEM DAO      
         empresa.setId(7L);
         tipolixo.setId(1L);
         tipoMaterial.setId(1L);
         grupo.setId(1L);
         postagem.setEmpresa(empresa);
         postagem.setTipoMaterial(tipoMaterial);
         postagem.setTipoLixo(tipolixo);
         postagem.setGrupo(grupo);
         postagem.setData("1995-02-25");
         postagem.setDescricao("descricao da postagem realizada");
         postagem.setEmpresa(empresa);
         postagem.setPeso(12L);
         postagem.setStatus(true);
         postagem.setTipo("L");
         postagem.setTitulo("Titulo");
         postagem.setUnidade("kg");

       
         ServiceLocator.getPostagemService().create(postagem);
         System.out.println("create");
         System.out.println(postagem.getTitulo());
         Long id = postagem.getId();
         postagem = ServiceLocator.getPostagemService().readByID(id);
         System.out.println("read by id");
         System.out.println(postagem.getTitulo());
         System.out.println(postagem.getGrupo().getNome());
         Map<Long,Object> criteria = new HashMap<>();
         criteria.put(PostagemCriteria.PESO_EQ, 12L);
         criteria.put(PostagemCriteria.STATUS_EQ, true);
         criteria.put(PostagemCriteria.TIPOLIXO_ID_EQ, tipolixo.getId());
         criteria.put(PostagemCriteria.TIPOMATERIAL_ID_EQ, tipoMaterial.getId());
         criteria.put(PostagemCriteria.TITULO_EQ, "Titulo");
         criteria.put(PostagemCriteria.TITULO_ILIKE, "lo");
         criteria.put(PostagemCriteria.UNIDADE_EQ, "kg");
         criteria.put(PostagemCriteria.GRUPO_ID_EQ, 1L);
         postagemList = ServiceLocator.getPostagemService().readByCriteria(criteria);
         System.out.println("criteria "+ postagemList.size());
         for(Postagem p1 :postagemList){
         System.out.println(p1.getTitulo());
         System.out.println(p1.getEmpresa().getNome());
         System.out.println(p1.getTipoLixo().getNome());
            
         System.out.println(p1.getGrupo().getNome());
         System.out.println(p1.getTipoMaterial().getNome());
         }
         postagem.setTitulo("TITULO DAOOO");
         ServiceLocator.getPostagemService().update(postagem);
         System.out.println("update");
         System.out.println(postagem.getTitulo());
         ServiceLocator.getPostagemService().delete(id);
         System.out.println("delete");
       
         //TESTE SETOR DAO 
         setor.setNome("SETOR DAOOO");
     
         ServiceLocator.getSetorService().create(setor);
         System.out.println("create");
         System.out.println(setor.getNome());
         Long id = setor.getId();
         setor = ServiceLocator.getSetorService().readByID(id);
         System.out.println("read by id");
         System.out.println(setor.getNome());
         Map<Long,Object> criteria = new HashMap<>();
         criteria.put(SetorCriteria.NOME_EQ,"SETOR DAOOO");
         criteria.put(SetorCriteria.NOME_ILIKE,"OR DAOOO");
         setorList = ServiceLocator.getSetorService().readByCriteria(criteria);
         System.out.println("read by criteria "+ setorList.size());
         for(Setor st : setorList){
         System.out.println(st.getNome());
         }
         setor.setNome("DAO SETOR");
         ServiceLocator.getSetorService().update(setor);
         System.out.println("Update");
         System.out.println(setor.getNome());
         ServiceLocator.getSetorService().delete(id);
         System.out.println("delete");
        
        
         //TESTE TIPO_LIXO DAO
         tipolixo.setNome("celular");

        
         ServiceLocator.getTipoLixoService().create(tipolixo);
         System.out.println("create");
         System.out.println(tipolixo.getId());
         tipolixo = ServiceLocator.getTipoLixoService().readByID(tipolixo.getId());
         System.out.println("read by id");
         System.out.println(tipolixo.getNome());
         Map<Long, Object> criteria = new HashMap<>();
         criteria.put(TipoLixoCriteria.NOME_EQ, "celular");
         criteria.put(TipoLixoCriteria.NOME_ILIKE, "ar");
         tipolixoList = ServiceLocator.getTipoLixoService().readByCriteria(criteria);
         System.out.println("criteria " + tipolixoList.size());
         for(TipoLixo tl : tipolixoList){
         System.out.println(tl.getNome());
         }
         tipolixo.setNome("tablet");
         ServiceLocator.getTipoLixoService().update(tipolixo);
         System.out.println("update");
         System.out.println(tipolixo.getNome());
         ServiceLocator.getTipoLixoService().delete(tipolixo.getId());
         System.out.println("delete");
       
        
         tipoMaterial.setNome("madeira");
        
         ServiceLocator.getTipoMaterialService().create(tipoMaterial);
         System.out.println("create");
         System.out.println(tipoMaterial.getId());
         tipoMaterial = ServiceLocator.getTipoMaterialService().readByID(tipoMaterial.getId());
         System.out.println("read by id");
         System.out.println(tipoMaterial.getNome());
         Map<Long, Object> criteria = new HashMap<>();
         criteria.put(TipoMaterialCriteria.NOME_EQ, "madeira");
         criteria.put(TipoMaterialCriteria.NOME_ILIKE, "eira");
         tipoMaterialList = ServiceLocator.getTipoMaterialService().readByCriteria(criteria,null,null);
         System.out.println("criteria "+ tipoMaterialList.size());
         for(TipoMaterial tm : tipoMaterialList){
         System.out.println(tm.getNome());
         }
         tipoMaterial.setNome("plástico");
         ServiceLocator.getTipoMaterialService().update(tipoMaterial);
         System.out.println("update");
         tipoMaterial = ServiceLocator.getTipoMaterialService().readByID(tipoMaterial.getId());
         System.out.println(tipoMaterial.getNome());
        
         ServiceLocator.getTipoMaterialService().delete(tipoMaterial.getId());
         System.out.println("DELETE");
         /*
         ServiceLocator.getPostagemService().create(postagem);
         System.out.println(postagem.getId());

         setor.setNome("setor test");
         ServiceLocator.getSetorService().create(setor);
         System.out.println(setor.getId());

         Map<Long, Object> criteria = new HashMap<>();

         criteria.put(TipoMaterialCriteria.NOME_EQ, "Aça");
         List<TipoMaterial> entityList = ServiceLocator.getTipoMaterialService().readByCriteria(criteria);
         for (TipoMaterial entityList1 : entityList) {
         System.out.println("Tamanho da lista " + entityList.size());
         System.out.println(entityList1.getNome());
         }

         empresa = ServiceLocator.getEmpresaService().readByID(7L);
         System.out.println(empresa.getNome());
         System.out.println(empresa.getCnpj());
         System.out.println(empresa.getEmail());
         System.out.println(empresa.getId());
         System.out.println(empresa.getSenha());
         System.out.println(empresa.getTelefone());
         System.out.println(empresa.getTipo());

         for (Setor setor1 : empresa.getSetorSet()) {
         System.out.println(setor1.getId() + " " + setor1.getNome());
         }
         for (Grupo grupo1 : empresa.getGrupoSet()) {
         System.out.println(grupo1.getNome() + " " + grupo1.getDescricao());
         }

         Map<Long, Object> criteria = new HashMap<>();
         criteria.put(PostagemCriteria.PESO_EQ, 123L);
         criteria.put(PostagemCriteria.TITULO_ILIKE, "1");

         postagemList = ServiceLocator.getPostagemService().readByCriteria(criteria);
         for (Postagem postagem : postagemList) {
         System.out.println("titulo: " + postagem.getTitulo());
         System.out.println("autor:  " + postagem.getEmpresa().getNome());
         if (postagem.getGrupo() != null) {
         System.out.println("grupo: " + postagem.getGrupo().getNome());
         System.out.println("dono do grupo: " + postagem.getGrupo().getEmpresa().getNome());
         System.out.println(" email dono do grupo: " + postagem.getGrupo().getEmpresa().getEmail());
         }

         }
         */

    }

}
