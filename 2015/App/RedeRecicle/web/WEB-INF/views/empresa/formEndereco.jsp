<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${opcao eq 'novoEndereco'}">
    <h2 class="text-center">Novo Endereço</h2>
</c:if>

<c:if test="${opcao eq 'editarEndereco'}">
    <h2 class="text-center">Editar Endereço</h2>
</c:if>

<form <c:if test="${cadastro_endereco eq 'editar' or solicitacao eq 'editar'}"></c:if> <c:if test="${cadastro_endereco eq 'novo'}"> action="<c:url value="/cadastro/endereco/novo"/>" </c:if>  <c:if test="${solicitacao eq 'novo'}"> action="<c:url value="/empresa/endereco/novo"/>" </c:if> method="POST"> 
    <c:if test="">
    </c:if>
    <label for="nome" target="nome" class="gatilho">Nome da Localidade: </label>
    <div class="input-group">
        <input class="form-control gatilho" id="nome" target="nome" type="text" name="nome" value="${endereco.nome}" required />
        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-flag"></i></span>      
    </div>
    <c:if test="${not empty errors.nome}">
        <div class="alert-danger">
            <span>${errors.nome}</span><br/>
        </div>
    </c:if>
    <label for="estado" target="estado" class="gatilho">Estado: </label>
    <div class="input-group">
        <select name="estado" id="estado" class="form-control" required>
            <option value="${endereco.estado}">Selecione</option>
            <option value="Acre">Acre</option>
            <option value="Alagoas">Alagoas</option>
            <option value="Amapá">Amapá</option>
            <option value="Amazonas">Amazonas</option>
            <option value="Bahia">Bahia</option>
            <option value="Ceará">Ceará</option>
            <option value="Distrito Federal">Distrito Federal</option>
            <option value="Espirito Santo">Espirito Santo</option>
            <option value="Goiás">Goiás</option>
            <option value="Maranhão">Maranhão</option>
            <option value="Mato Grosso do Sul">Mato Grosso do Sul</option>
            <option value="Mato Grosso">Mato Grosso</option>
            <option value="Minas Gerais">Minas Gerais</option>
            <option value="Pará">Pará</option>
            <option value="Paraíba">Paraíba</option>
            <option value="Paraná">Paraná</option>
            <option value="Pernambuco">Pernambuco</option>
            <option value="Piauí">Piauí</option>
            <option value="Rio de Janeiro">Rio de Janeiro</option>
            <option value="Rio Grande do Norte">Rio Grande do Norte</option>
            <option value="Rio Grande do Sul">Rio Grande do Sul</option>
            <option value="Rondônia">Rondônia</option>
            <option value="Roraima">Roraima</option>
            <option value="Santa Catarina">Santa Catarina</option>
            <option value="São Paulo">São Paulo</option>
            <option value="Sergipe">Sergipe</option>
            <option value="Tocantins">Tocantins</option>
        </select>
        <span class="input-group-addon" id="basic-addon1">
            <i class="glyphicon glyphicon-bullhorn"></i>
        </span>
    </div>
    <c:if test="${not empty errors.estado }">
        <div class="alert-danger">
            <span>${errors.estado}</span><br/>
        </div>
    </c:if>
    <label for="cep" target="cep" class="gatilho">CEP:</label>
    <div class="input-group">
        <input class="form-control gatilho" id="cep" target="cep" type="text" name="cep" value="${endereco.cep}" required/>
        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-road"></i></span>
    </div>
    <c:if test="${not empty errors.cep }">
        <div class="alert-danger">
            <span>${errors.cep}</span><br/>
        </div>
    </c:if>
    <label for="cidade" target="cidade" class="gatilho">Cidade:</label>
    <div class="input-group">
        <input class="form-control gatilho" id="cidade"  target="cidade" type="text" name="cidade" value="${endereco.cidade}" required />
        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-road"></i></span>
    </div>
    <c:if test="${not empty errors.cidade }">
        <div class="alert-danger">
            <span>${errors.cidade}</span><br/>
        </div>
    </c:if>
    <label for="bairro"  target="bairro" class="gatilho">Bairro: </label>
    <div class="input-group">
        <input class="form-control gatilho" id="bairro"  target="bairro" type="text" name="bairro" value="${endereco.bairro}" required/>
        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-flag"></i></span>
    </div>
    <c:if test="${not empty errors.bairro }">
        <div class="alert-danger">
            <span>${errors.bairro}</span><br/>
        </div>
    </c:if>
    <label for="logradouro"  target="logradouro" class="gatilho">Logradouro: </label>
    <div class="form-group">
        <div class="input-group">
            <input class="form-control gatilho" id="logradouro" target="logradouro" type="text" name="logradouro" value="${endereco.logradouro}" required/>
            <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-road"></i></span>
        </div>
    </div>
    <c:if test="${not empty errors.logradouro }">
        <div class="alert-danger">
            <span>${errors.logradouro}</span><br/>
        </div>
    </c:if>
    <label for="numero"  target="numero" class="gatilho">Número: </label>
    <div class="form-group">
        <div class="input-group">
            <input class="form-control gatilho" id="numero"  target="numero" type="text" name="numero" value="${endereco.numero}" required/>
            <span class="input-group-addon" id="basic-addon1">
                <i class="glyphicon glyphicon-asterisk"></i>
            </span>
        </div>
    </div>
    <c:if test="${not empty errors.numero }">
        <div class="alert-danger">
            <span>${errors.numero}</span><br/>
        </div>
    </c:if>
    <div class="form-group">
        <button type="submit" class="pull-right margemCadastro  btn btn-primary"><c:if test="${cadastro_endereco eq 'novo' or solicitacao eq 'novo'}"> Cadastrar </c:if> <c:if test="${cadastro_endereco eq 'editar' or solicitacao eq 'editar'}"> Editar </c:if> </button>
    </div>
</form>