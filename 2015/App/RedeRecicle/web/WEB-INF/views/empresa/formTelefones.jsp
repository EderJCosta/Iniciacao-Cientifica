<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${opcao eq 'novoTelefone' }">
    <h2 class="text-center">Novo Telefone</h2>
</c:if>
<c:if test="${opcao eq 'editarTelefone'}">
    <h2 class="text-center">Editar Telefone</h2>
</c:if>
<div class="divisao"></div>
<form <c:if test="${cadastro_telefone eq 'editar'}"> action="<c:url value="/cadastro/telefone/${telefone.id}/editar"/>" </c:if>  <c:if test="${cadastro_telefone eq 'novo'}"> action="<c:url value="/cadastro/telefone/novo"/>" </c:if> <c:if test="${opcao eq 'novoTelefone'}"> action="<c:url value="/empresa/telefone/novo"/>" </c:if> <c:if test="${opcao eq 'editarTelefone'}"> action="<c:url value="/empresa/telefone/${telefone.id}/editar"/>" </c:if> method="POST" > 
    <c:if test="">
    </c:if>
    <label for="nome" target="nome" class="gatilho">Nome do Departamento: </label>
    <div class="input-group">
        <input class="form-control gatilho" id="nome" target="nome" type="text" name="nome" value="${telefone.nome}" required/>
        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-flag"></i></span>      
    </div>
    <c:if test="${not empty errors.nome}">
        <div class="alert-danger">
            <span>${errors.nome}</span><br/>
        </div>
    </c:if>
    <label for="numero"  target="numero" class="gatilho">Número: </label>
    <div class="input-group">
        <input class="form-control gatilho" id="numeroTelefone"  target="numero" type="text" name="numero" value="${telefone.numero}" required/>
        <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-asterisk"></i></span>
    </div>
    <c:if test="${not empty errors.numero }">
        <div class="alert-danger">
            <span>${errors.numero}</span><br/>
        </div>
    </c:if>
    <div class="modal-footer">
        <input type="submit" class="btn btn-success" <c:if test="${cadastro_telefone eq 'novo' or opcao eq 'novoTelefone'}"> value="Cadastrar" </c:if> <c:if test="${cadastro_telefone eq 'editar' or opcao eq 'editarTelefone'}"> value="editar" </c:if> />
    </div>
</form>
<div class="divisao"></div>