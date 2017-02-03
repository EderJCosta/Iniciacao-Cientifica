<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="text-center">FAVORITOS</h4>
<br/>
<div class="divisao"></div>
<c:forEach items="${favoritoList}" var="favorito">
    <c:if test="${empresaLogada.id ne favorito.id}">
        <div class="col-sm-4 col-md-4">
            <div class="moldura-div">
                <img class="img-responsive img-rounded center-block imagemEmpresa" src="<c:url value="/empresa/${favorito.id}/img.jpg"/>"/>
                <h4 class="text-center">${favorito.nome}</h4>
                <div class="btn-group btn-group-justified" role="group">
                    <a href="<c:url value="/empresa/favorito/${favorito.id}/excluir"/>" class=" btn btn-danger"> Remover</a>
                    <a href="<c:url value="/empresa/${favorito.id}"/>" class=" btn btn-success"> Visualizar</a>
                </div>
            </div>
        </div>

    </c:if>
</c:forEach>
