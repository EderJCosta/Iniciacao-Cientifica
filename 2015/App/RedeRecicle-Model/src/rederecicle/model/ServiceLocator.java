package rederecicle.model;

import rederecicle.model.base.service.BaseEmpresaService;
import rederecicle.model.base.service.BaseEnderecoService;
import rederecicle.model.base.service.BaseGrupoService;
import rederecicle.model.base.service.BasePostagemService;
import rederecicle.model.base.service.BaseSetorService;
import rederecicle.model.base.service.BaseTelefoneService;
import rederecicle.model.base.service.BaseTipoLixoService;
import rederecicle.model.base.service.BaseTipoMaterialService;
import rederecicle.model.service.EmpresaService;
import rederecicle.model.service.EnderecoService;
import rederecicle.model.service.GrupoService;
import rederecicle.model.service.PostagemService;
import rederecicle.model.service.SetorService;
import rederecicle.model.service.TelefoneService;
import rederecicle.model.service.TipoLixoService;
import rederecicle.model.service.TipoMaterialService;

public class ServiceLocator {

    public static BaseEmpresaService getEmpresaService() {
        return new EmpresaService();
    }

    public static BaseEnderecoService getEnderecoService() {
        return new EnderecoService();
    }

    public static BaseGrupoService getGrupoService() {
        return new GrupoService();
    }

    public static BasePostagemService getPostagemService() {
        return new PostagemService();
    }

    public static BaseSetorService getSetorService() {
        return new SetorService();
    }
    
    public static BaseTipoLixoService getTipoLixoService(){
        return new TipoLixoService();
    }
    
    public static BaseTipoMaterialService getTipoMaterialService(){
        return new TipoMaterialService();
    }
    
    public static BaseTelefoneService getTelefoneService(){
        return new TelefoneService();
    }
}
