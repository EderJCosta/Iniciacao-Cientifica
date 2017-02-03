package rederecicle.model.entity;

import java.util.ArrayList;
import java.util.List;
import rederecicle.model.base.BaseEntity;

public class Grupo extends BaseEntity {

    private String nome;
    private String descricao;
    private Empresa empresa;
    private List<Empresa> empresaList;
    private Boolean status;

    public Grupo() {
        empresaList = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
