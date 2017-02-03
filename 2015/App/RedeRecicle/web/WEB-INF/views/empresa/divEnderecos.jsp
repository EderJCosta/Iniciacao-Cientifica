<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="text-center">${empresaLogada.nome} - Endereços</h2>
<div class="divisao"></div>
<c:if test="${not empty enderecoList}">
    <c:forEach items="${enderecoList}" var="endereco">
        <div class="<c:if test="${not empty opcao}"> col-sm-6 col-md-4</c:if>  <c:if test="${empty opcao}"> col-sm-6 col-md-4</c:if>">
            <div class="moldura-div">
                <h4 class="text-center">${endereco.nome}</h4>
                <p class="text-moldura">${endereco.estado}, ${endereco.cidade}, ${endereco.cep}</p>
                <p class="text-moldura">${endereco.bairro}, ${endereco.logradouro}, ${endereco.numero}</p>
                <div class=" btn-group btn-group-justified" role="group">
                    <c:if test="${ empty opcao}">
                        <a href="<c:url value="/cadastro/endereco/${endereco.id}/excluir"/>" class="btn btn-danger btnthumb" role="button">Remover</a> 
                        <a href="<c:url value="/cadastro/endereco/${endereco.id}/editar"/>" class="btn btn-warning btnthumb" role="button">Editar</a>
                    </c:if>
                    <c:if test="${not empty opcao}">
                        <a href="<c:url value="/empresa/endereco/${endereco.id}/excluir"/>" class="btn btn-danger btnthumb" role="button">Remover</a> 
                        <a href="<c:url value="/empresa/endereco/${endereco.id}/editar"/>" class="btn btn-warning btnthumb" role="button">Editar</a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="<c:if test="${not empty opcao}"> col-sm-6 col-md-4</c:if>  <c:if test="${empty opcao}"> col-sm-6 col-md-4</c:if>">
        <div class="moldura-div">
            <h4 class="text-center">Adicionar</h4>
            <p class="text-center">Adicione novos <br/> endereços!</p>
            <c:if test="${empty opcao}">
                <div class=" btn-group btn-group-justified" role="group">
                    <a href="<c:url value="/cadastro/endereco/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                </div>
            </c:if>
            <c:if test="${not empty opcao}">
                <div class=" btn-group btn-group-justified" role="group">
                    <a href="<c:url value="/empresa/endereco/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                </div>
            </c:if>
            </p>
        </div>
    </div>
</c:if>
<c:if test="${empty enderecoList}">
    <div class="<c:if test="${not empty opcao}"> col-sm-6 col-md-4</c:if>  <c:if test="${empty opcao}"> col-sm-6 col-md-4</c:if>">
        <div class="moldura-div">
            <h4 class="text-center">Adicionar Endereço</h4>
            <p>Não há Endereços cadastrados nesta conta. Por favor adicione endereços.</p>
            <c:if test="${empty opcao}">
                <div class=" btn-group btn-group-justified" role="group">
                    <a href="<c:url value="/cadastro/endereco/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                </div>
            </c:if>
            <c:if test="${not empty opcao}">
                <div class=" btn-group btn-group-justified" role="group">
                    <a href="<c:url value="/empresa/endereco/novo"/>" class="btn btn-primary btnthumb" role="button">Novo</a>
                </div>
            </c:if>
        </div>
    </div>
</c:if>
<c:if test="${not empty autorizacao}">
    <div class="col-md-12 alert alert-danger">
        <p class="text-center">${autorizacao}</p>
    </div>
</c:if>
<c:if test="${not empty exclusao}">
    <div class="col-md-12 alert alert-danger">
        <p class="text-center">${exclusao}</p>
    </div>
</c:if>