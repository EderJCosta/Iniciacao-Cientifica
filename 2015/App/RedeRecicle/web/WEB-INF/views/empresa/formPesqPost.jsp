<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="text-center">PESQUISAR POSTAGENS</h4>
<form id="pesquisarPost" action="<c:url value="/postagem/pesquisa"/>" class="form-horizontal" method="POST" onsubmit="return validacao_peso()">
    <div class="form-group">
        <label for="peso" class="col-sm-1 control-label">Peso até:</label>
        <div class="col-sm-3">
            <input value="" type="text" class="form-control" name="peso" id="peso" required >
        </div>
        <label for="unidade" class="col-sm-1 control-label">Unidade:</label>
        <div class="col-sm-3">
            <select name="unidade" id="unidade" class="form-control">
                <option value="${unidade}">Selecione</option>
                <option value="mg">Miligrama(mg)</option>
                <option value="g">Grama(g)</option>
                <option value="kg">Kilo(kg)</option>
                <option value="t">Tonelada(t)</option>
            </select>
        </div>
        <label for="tipo" class="col-sm-1 control-label">Tipo: </label>
        <div class="col-sm-3">
            <select name="tipo" id="tipo" class="form-control">
                <option value="${tipo}">Selecione</option>
                <c:forEach items="${tipoList}" var="lista">
                    <option value="${lista.id}">${lista.nome}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="pull-right btn btn-primary">Pesquisar</button>
        </div>
    </div>
</form>
<div id="divisao" class="divisao"></div>
   <jsp:include page="/WEB-INF/views/empresa/divPostagem.jsp"/>
<c:if test="${not empty peso}">
    <c:if test="${empty postagemList}">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <p class="text-center">Nenhum resultado encontrado! :(</p>
        </div>
    </c:if>
</c:if>
<c:if test="${empty peso}">
    <c:if test="${empty postagemList}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <p class="text-center">Pesquisa de postagem! Procure por postagens em todo o site! </p>
        </div>
    </c:if>
</c:if>