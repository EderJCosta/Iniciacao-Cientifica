CREATE TABLE Setor (
  id    BIGSERIAL NOT NULL, 
  nome varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE Empresa (
  id                  BIGSERIAL NOT NULL, 
  nome               varchar(255) NOT NULL UNIQUE, 
  email              varchar(255) NOT NULL UNIQUE, 
  senha              varchar(255) NOT NULL, 
  inscricao_estadual int4 NOT NULL UNIQUE, 
  cnpj               int4 NOT NULL UNIQUE, 
  tipo               char(1) NOT NULL, 
  telefone           varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE Setor_Empresa (
  Empresaid int4 NOT NULL, 
  Setorid   int4 NOT NULL, 
  PRIMARY KEY (Empresaid, 
  Setorid));
CREATE TABLE Endereco (
  id             BIGSERIAL NOT NULL, 
  nome_Endereco varchar(255) NOT NULL UNIQUE, 
  logradouro    varchar(255) NOT NULL, 
  bairro        varchar(255) NOT NULL, 
  cidade        varchar(255) NOT NULL, 
  cep           int4 NOT NULL, 
  estado        varchar(255) NOT NULL, 
  numero        int4 NOT NULL, 
  Empresaid     int4 NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Postagem (
  id               BIGSERIAL NOT NULL, 
  empresaid       int4 NOT NULL, 
  tipo_Lixoid     int4, 
  tipo_Materialid int4, 
  titulo          varchar(255) NOT NULL, 
  descricao       varchar(255) NOT NULL, 
  peso            int4 NOT NULL, 
  status          bool NOT NULL, 
  data            date NOT NULL, 
  unidade         char(2) NOT NULL, 
  tipo            char(1) NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Tipo_Lixo (
  id    BIGSERIAL NOT NULL, 
  nome varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE Tipo_Material (
  id    BIGSERIAL NOT NULL, 
  nome varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id));
CREATE TABLE Grupo (
  nome      varchar(255) NOT NULL UNIQUE, 
  descricao varchar(255) NOT NULL, 
  id         SERIAL NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Grupo_Empresa (
  Grupoid   int4 NOT NULL, 
  Empresaid int4 NOT NULL, 
  PRIMARY KEY (Grupoid, 
  Empresaid));
ALTER TABLE Setor_Empresa ADD CONSTRAINT FKSetor_Empr583173 FOREIGN KEY (Setorid) REFERENCES Setor (id);
ALTER TABLE Setor_Empresa ADD CONSTRAINT FKSetor_Empr252456 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Postagem ADD CONSTRAINT FKPostagem994753 FOREIGN KEY (empresaid) REFERENCES Empresa (id);
ALTER TABLE Postagem ADD CONSTRAINT FKPostagem265615 FOREIGN KEY (tipo_Lixoid) REFERENCES Tipo_Lixo (id);
ALTER TABLE Postagem ADD CONSTRAINT FKPostagem8543 FOREIGN KEY (tipo_Materialid) REFERENCES Tipo_Material (id);
ALTER TABLE Grupo_Empresa ADD CONSTRAINT FKGrupo_Empr217290 FOREIGN KEY (Grupoid) REFERENCES Grupo (id);
ALTER TABLE Grupo_Empresa ADD CONSTRAINT FKGrupo_Empr127858 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
ALTER TABLE Endereco ADD CONSTRAINT FKEndereco361900 FOREIGN KEY (Empresaid) REFERENCES Empresa (id);
