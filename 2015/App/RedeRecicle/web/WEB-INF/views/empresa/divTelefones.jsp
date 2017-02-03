<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="text-center">${empresaLogada.nome} - Telefones</h2>
<div class="divisao"></div>
<div class="conteudo" id="favoritos">
    <c:if test="${not empty telefoneList}">
        <c:forEach items="${telefoneList}" var="telefone">
            <div class="col-sm-5 col-md-4">
                <div class="moldura-div">
                    <h4 class="text-center">${telefone.nome}</h4>
                    <p class="text-center"><b>Número:</b> ${telefone.numero}</p>
                    <div class=" btn-group btn-group-justified" role="group">
                        <c:if test="${empty opcao}">
                            <a href="<c:url value="/cadastro/telefone/${telefone.id}/excluir"/>" class="btn btn-danger btnthumb" role="button">Remover</a>
                            <a href="<c:url value="/cadastro/telefone/${telefone.id}/editar"/>" class="btn btn-warning btnthumb" role="button">Editar</a>
                        </c:if>
                        <c:if test="${not empty opcao}">
                            <a href="<c:url value="/empresa/telefone/${telefone.id}/excluir"/>" class="btn btn-danger btnthumb" role="button">Remover</a>
                            <a href="<c:url value="/empresa/telefone/${telefone.id}/editar"/>" class="btn btn-warning btnthumb" role="button">Editar</a>
                        </c:if> 
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="col-sm-5 col-md-4">
            <div class="moldura-div">
                <h4 class="text-center">Adicionar Telefone</h4>
                <p class="text-center">Adicione novos telefones!</p>
                <div class=" btn-group btn-group-justified" role="group">
                    <c:if test="${empty opcao}">
                        <a href="<c:url value="/cadastro/telefone/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                    </c:if>
                    <c:if test="${not empty opcao}">
                        <a href="<c:url value="/empresa/telefone/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${empty telefoneList}">
        <div class="col-sm-5 col-md-4">
            <div class="moldura-div">
                <h4 class="text-center">Adicionar telefone</h4>
                <p>Não há telefones cadastrados nesta conta. Por favor adicione os números de contato com sua empresa.</p>
                <div class=" btn-group btn-group-justified" role="group">
                    <c:if test="${empty opcao}">
                        <a href="<c:url value="/cadastro/telefone/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                    </c:if>
                    <c:if test="${not empty opcao}">
                        <a href="<c:url value="/empresa/telefone/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:if>
</div>