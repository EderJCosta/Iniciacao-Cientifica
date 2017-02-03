package rederecicle.model.base.service;

import rederecicle.model.base.BaseService;
import rederecicle.model.entity.Grupo;
import rederecicle.model.entity.Grupo_Imagem;

public interface BaseGrupoService extends BaseService<Grupo>{
    
    public void setImagem(Long id, Grupo_Imagem imagem) throws Exception;

    public Grupo_Imagem getImagem(Long id) throws Exception;
    
}
