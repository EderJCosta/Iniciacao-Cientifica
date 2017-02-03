package rederecicle.model.entity;

import java.sql.Timestamp;
import rederecicle.model.base.BaseEntity;

public class Postagem extends BaseEntity{
    
    private String titulo;
    private String descricao;
    private Double peso;
    private Boolean status;
    private Timestamp data;
    private String unidade;
    private Empresa empresa;
    private TipoLixo tipoLixo;
    private TipoMaterial tipoMaterial;
    private Grupo grupo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoLixo getTipoLixo() {
        return tipoLixo;
    }

    public void setTipoLixo(TipoLixo tipoLixo) {
        this.tipoLixo = tipoLixo;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
}
