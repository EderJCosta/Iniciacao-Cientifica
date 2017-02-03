<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${opcao eq 'alterarConta'}">
    <h2 class="text-center"> Imagem da Empresa</h2>
    <img class="center-block img-responsive img-rounded imagemEmpresa"src="<c:url value="/empresa/${empresaLogada.id}/img.jpg"/>"/>
    <form action="<c:url value="/empresa/alterar/img"/>" enctype="multipart/form-data" method="POST" onsubmit="return checkSize(2097152)">
        <div class="form-group">
            <label for="file">Enviar imagem</label>
            <div class="input-group">
                <input type="hidden" name="id" value="${empresaLogada.id}"/>
                <input class="" type="file" id="upload" name="file"/>
            </div>
            <br/>
            <div class="alert alert-warning">
                <p class="help-block">Tamanho do arquivo não deverá maior que 2MB!</p>
            </div>
        </div>
        <div class="form-group">
            <button class="pull-right btn btn-primary">Enviar</button>
        </div>
    </form>
    <br/>
    <br/>
    <div class="divisao"></div>
    <h2 class="text-center"> Dados da Empresa</h2>   
</c:if>
<form <c:if test="${opcao ne 'alterarConta'}"> action="<c:url value="/empresa/novo"/>"  </c:if> <c:if test="${opcao eq 'alterarConta'}"> action="<c:url value="/empresa/alterar"/>" </c:if>  method="POST" >
    <c:if test="${opcao eq 'alterarConta'}">
        <input type="hidden" name="id" value="${empresa.id}"/>
    </c:if>
    <label for="nome"  target="nome" class="gatilho">Nome da empresa: </label>
    <div class=" input-group gatilho" target="nome">
        <input class="form-control" id="nome" type="text" name="nome" value="${empresa.nome}" />
        <span class="input-group-addon" id="basic-addon1">
            <i class="glyphicon glyphicon-home"></i>
        </span>
    </div>
    <c:if test="${not empty errors.nome }">
        <div class="alert-danger">
            <span class="text-center"> ${errors.nome}</span><br/>
        </div>
    </c:if>
    <label for="oemail" target="email" class="gatilho"> Email: </label>
    <div class="input-group gatilho" target="email">
        <input class="form-control" id="oemail" type="email" name="email" value="${empresa.email}" />
        <span class="input-group-addon" id="basic-addon1">
            <i class="glyphicon glyphicon-envelope"></i>
        </span>
    </div>
    <c:if test="${not empty errors.email }">
        <div class="alert-danger">
            <span>${errors.email}</span><br/>
        </div>
    </c:if>
    <c:if test="${opcao ne 'alterarConta'}">
        <label for="asenha" target="nome" class="gatilho">Senha: </label>
        <div class="input-group gatilho" target="senha">
            <input class="form-control" id="asenha" type="password" name="senha" value="" />
            <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-lock"></i></span>
        </div>
        <c:if test="${not empty errors.senha }">
            <div class="alert-danger">
                <span>${errors.senha}</span><br/>
            </div>
        </c:if>
    </c:if>
    <label for="descricaocampo"  target="descricaocampo" class="gatilho">Descrição da empresa: </label>
    <div class="form-group gatilho" target="descricaocampo">
        <textarea name="descricao" id="descricaocampo" class="form-control gatilho" maxlength="240">${empresa.descricao}</textarea>
    </div>
    <c:if test="${not empty errors.descricao }">
        <div class="alert-danger">
            <span>${errors.descricao}</span><br/>
        </div>
    </c:if>
    <label for="tipo" class="control-label">Area Empresa</label>
    <div class="input-group">
        <select name="tipo" id="tipo" class="form-control">
            <option value="F">Fabricante</option>
            <option value="R">Reciclante</option>
            <option value="C">Comprador</option>
        </select>
        <span class="input-group-addon" id="basic-addon1">
            <i class="glyphicon glyphicon-bullhorn"></i>
        </span>
    </div>
    <c:if test="${opcao ne 'alterarConta'}">
        <label for="cnpj" target="cnpj" class="gatilho">CNPJ: </label>
        <div class="input-group">
            <input class="form-control gatilho"  target="cnpj" id="cnpj" type="text" name="cnpj" value="${empresa.cnpj}" />
            <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-warning-sign"></i></span>
        </div>
        <c:if test="${not empty errors.cnpj }">
            <div class="alert-danger">
                <span>${errors.cnpj}</span><br/>
            </div>
        </c:if>
        <label for="inscricao_estadual" target="inscricao_estadual" class="gatilho">Inscrição Estadual: </label>
        <div class="input-group">
            <input class="form-control gatilho" target="inscricao_estadual" id="inscricao_estadual" type="text" name="inscricao_estadual" value="${empresa.inscricao_estadual}" />
            <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-warning-sign"></i></span>
        </div>
        <c:if test="${not empty errors.inscricao }">
            <div class="alert-danger">
                <span>${errors.inscricao}</span><br/>
            </div>
        </c:if>
    </c:if>
    <div class="modal-footer">
        <input type="submit" class="btn btn-primary"  <c:if test="${opcao ne 'alterarConta'}"> value="Cadastrar" </c:if> <c:if test="${opcao eq 'alterarConta'}"> value="Editar" </c:if> />
    </div>
</form>