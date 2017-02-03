package rederecicle.model.base.service;

import java.util.List;
import java.util.Map;
import rederecicle.model.base.BaseService;
import rederecicle.model.entity.Empresa;
import rederecicle.model.entity.Empresa_Imagem;

public interface BaseEmpresaService extends BaseService<Empresa> {

    public Empresa login(String email, String senha) throws Exception;

    public void setImagem(Long id, Empresa_Imagem imagem) throws Exception;

    public Empresa_Imagem getImagem(Long id) throws Exception;
}
