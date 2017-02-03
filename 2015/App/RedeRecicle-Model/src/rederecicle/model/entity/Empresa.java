package rederecicle.model.entity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import rederecicle.model.base.BaseEntity;

public class Empresa extends BaseEntity{
    
    private String nome;
    private String email;
    private String senha;
    private String descricao;
    private String inscricao_estadual;
    private String cnpj;
    private String tipo;
    private Set<Setor> setorSet;
    private Set<Grupo> grupoSet;
    private Set<Empresa> empresaFavoritasSet;
    private Set<Endereco> enderecoSet;
    private Set<Telefone> telefoneSet;
    
    
    public Empresa() {
        this.setorSet = new LinkedHashSet<>();
        this.grupoSet = new LinkedHashSet<>();
        this.empresaFavoritasSet = new LinkedHashSet<>();
        this.enderecoSet = new LinkedHashSet<>();
        this.telefoneSet = new LinkedHashSet<>();
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(String inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Setor> getSetorSet() {
        return setorSet;
    }

    public void setSetorSet(Set<Setor> setorSet) {
        this.setorSet = setorSet;
    }

    public Set<Grupo> getGrupoSet() {
        return grupoSet;
    }

    public void setGrupoSet(Set<Grupo> grupoSet) {
        this.grupoSet = grupoSet;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Empresa> getEmpresaFavoritasSet() {
        return empresaFavoritasSet;
    }

    public void setEmpresaFavoritasSet(Set<Empresa> empresaFavoritasSet) {
        this.empresaFavoritasSet = empresaFavoritasSet;
    }

    public Set<Endereco> getEnderecoSet() {
        return enderecoSet;
    }

    public void setEnderecoSet(Set<Endereco> enderecoSet) {
        this.enderecoSet = enderecoSet;
    }

    public Set<Telefone> getTelefoneSet() {
        return telefoneSet;
    }

    public void setTelefoneSet(Set<Telefone> telefoneSet) {
        this.telefoneSet = telefoneSet;
    }  
}
