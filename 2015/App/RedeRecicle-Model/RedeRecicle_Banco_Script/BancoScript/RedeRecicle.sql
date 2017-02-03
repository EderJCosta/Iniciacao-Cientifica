CREATE TABLE Setor (
  id    SERIAL NOT NULL, 
  nome varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE Empresa (
  id                  SERIAL NOT NULL, 
  nome               varchar(255) NOT NULL UNIQUE, 
  email              varchar(255) NOT NULL UNIQUE, 
  senha              varchar(255) NOT NULL, 
  descricao          varchar(255) NOT NULL, 
  inscricao_estadual varchar(255) NOT NULL UNIQUE, 
  cnpj               varchar(255) NOT NULL UNIQUE, 
  tipo               varchar(255) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Setor_Empresa (
  Empresaid int4 NOT NULL, 
  Setorid   int4 NOT NULL, 
  PRIMARY KEY (Empresaid, 
  Setorid));
CREATE TABLE Endereco (
  id          SERIAL NOT NULL, 
  Empresaid  int4 NOT NULL, 
  nome       varchar(255) NOT NULL, 
  estado     varchar(255) NOT NULL, 
  cidade     varchar(255) NOT NULL, 
  cep        varchar(255) NOT NULL, 
  bairro     varchar(255) NOT NULL, 
  logradouro varchar(255) NOT NULL, 
  numero     int4 NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Postagem (
  id               SERIAL NOT NULL, 
  empresaid       int4 NOT NULL, 
  tipo_Lixoid     int4, 
  tipo_Materialid int4, 
  Grupoid         int4, 
  titulo          varchar(255) NOT NULL, 
  descricao       varchar(255) NOT NULL, 
  peso            float4 NOT NULL, 
  status          bool NOT NULL, 
  data            timestamp NOT NULL, 
  unidade         varchar(255) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Tipo_Lixo (
  id    SERIAL NOT NULL, 
  nome varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE Tipo_Material (
  id    SERIAL NOT NULL, 
  nome varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE Grupo (
  id         SERIAL NOT NULL, 
  empresaid int4 NOT NULL, 
  nome      varchar(255) NOT NULL UNIQUE, 
  descricao varchar(255) NOT NULL, 
  status    bool NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Grupo_Empresa (
  Grupoid   int4 NOT NULL, 
  Empresaid int4 NOT NULL, 
  PRIMARY KEY (Grupoid, 
  Empresaid));
CREATE TABLE Telefone (
  id         SERIAL NOT NULL, 
  Empresaid int4 NOT NULL, 
  nome      varchar(255) NOT NULL, 
  numero    varchar(255) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Empresa_Imagem (
  Empresaid int4 NOT NULL, 
  imagem    bytea NOT NULL);
CREATE TABLE Empresa_Favoritos (
  Empresaid  int4 NOT NULL, 
  Favoritoid int4 NOT NULL, 
  PRIMARY KEY (Empresaid, 
  Favoritoid));
CREATE TABLE Grupo_Imagem (
  Grupoid int4 NOT NULL, 
  imagem  bytea NOT NULL);
ALTER TABLE Setor_Empresa ADD CONSTRAINT FKSetor_Empr583173 FOREIGN KEY (Setorid) REFERENCES Setor (id);
ALTER TABLE Setor_Empresa ADD CONSTRAINT FKSetor_Empr252456 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Postagem ADD CONSTRAINT FKPostagem994753 FOREIGN KEY (empresaid) REFERENCES Empresa (id);
ALTER TABLE Postagem ADD CONSTRAINT FKPostagem265615 FOREIGN KEY (tipo_Lixoid) REFERENCES Tipo_Lixo (id);
ALTER TABLE Postagem ADD CONSTRAINT FKPostagem8543 FOREIGN KEY (tipo_Materialid) REFERENCES Tipo_Material (id);
ALTER TABLE Grupo_Empresa ADD CONSTRAINT FKGrupo_Empr217290 FOREIGN KEY (Grupoid) REFERENCES Grupo (id);
ALTER TABLE Grupo_Empresa ADD CONSTRAINT FKGrupo_Empr127858 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Endereco ADD CONSTRAINT FKEndereco361900 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Grupo ADD CONSTRAINT FKGrupo952887 FOREIGN KEY (empresaid) REFERENCES Empresa (id);
ALTER TABLE Postagem ADD CONSTRAINT FKPostagem369930 FOREIGN KEY (Grupoid) REFERENCES Grupo (id);
ALTER TABLE Telefone ADD CONSTRAINT FKTelefone922327 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Empresa_Imagem ADD CONSTRAINT FKEmpresa_Im554838 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Empresa_Favoritos ADD CONSTRAINT FKEmpresa_Fa215165 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Empresa_Favoritos ADD CONSTRAINT FKEmpresa_Fa290548 FOREIGN KEY (Favoritoid) REFERENCES Empresa (id);
ALTER TABLE Grupo_Imagem ADD CONSTRAINT FKGrupo_Imag670148 FOREIGN KEY (Grupoid) REFERENCES Grupo (id);
