<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="text-center">MEUS GRUPOS</h4>
<br/>
<div class="divisao"></div>
<c:if test="${not empty lista}">
    <c:forEach items="${lista}" var="grupo">
        <div class="col-sm-4 col-md-4">
            <div class="moldura-div">
                <img class="img-responsive img-rounded center-block imagemEmpresa" src="<c:url value="/grupo/${grupo.id}/img.jpg"/>"/>
                <h4 class="text-center">${grupo.nome}</h4>
                <div class=" btn-group btn-group-justified" role="group">
                    <c:if test="${grupo.empresa.id eq empresaLogada.id}">
                        <a href="<c:url value="/empresa/grupo/${grupo.id}/excluir"/>" class="btn btn-danger" role="button">Remover</a>
                        <a href="<c:url value="/empresa/grupo/${grupo.id}/editar"/>" class="btn btn-warning" role="button">Editar</a>
                    </c:if>
                    <c:if test="${grupo.empresa.id ne empresaLogada.id}">
                        <a href="<c:url value="/grupo/${grupo.id}/${empresaLogada.id}/deixar"/>" class="btn btn-danger" role="button"> Deixar</a>
                    </c:if>
                    <a href="<c:url  value="/grupo/${grupo.id}"/>" class="btn btn-success" role="button">Vizualizar</a>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>
<div class="col-sm-4 col-md-4">
    <div class="moldura-div">
        <img class="img-responsive img-rounded center-block imagemEmpresa"  src="<c:url value="/img/grupo.jpg"/>"/>
        <h4 class="text-center">Novo Grupo</h4>
        <div class=" btn-group btn-group-justified" role="group">
            <a href="<c:url value="/empresa/grupo/novo"/>" class="btn btn-primary" role="button">Novo</a>
        </div>
    </div>
</div>
<c:if test="${not empty autorizacao}">
        <br/>
    <div class="col-md-12 alert alert-danger alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="text-center">${autorizacao}</p>
    </div>
</c:if>
<c:if test="${grupoRemove eq 'sucesso'}">
    <br/>
    <div class="col-md-12 alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="text-center">Grupo removido com sucesso!</p>
    </div>
</c:if>
<c:if test="${grupoRemove eq 'falha'}">
    <br/>
    <div class="col-md-12 alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="text-center">Falha ao remover grupo!</p>
    </div>
</c:if>
