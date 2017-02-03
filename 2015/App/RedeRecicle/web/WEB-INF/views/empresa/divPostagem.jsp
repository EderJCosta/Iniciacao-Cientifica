<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty postagemList}">
    <c:forEach items="${postagemList}" var="postagem">
        <div class="moldura-div">
            <div class="row">
                <div class="col-xs-5 col-sm-2">
                    <img class="img-responsive img-rounded center-block imagemEmpresa" src="<c:url value="/empresa/${postagem.empresa.id}/img.jpg"/>"/>
                </div>
                <div class="col-xs-7 col-sm-10">
                    <p><a href="<c:url value="/empresa/${postagem.empresa.id}"/>">${postagem.empresa.nome}</a><c:if test="${not empty postagem.grupo}"> em:<a href="<c:url value="/grupo/${postagem.grupo.id}"/>">${postagem.grupo.nome}</a></c:if> <b class="text-center">${postagem.titulo}</b> <b class="pull-right"><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${postagem.data}" />  </b></p>
                    <p class="text-justify">${postagem.descricao}</p>
                    <div class="divisao"></div>
                    <p class="col-xs-6">
                        <b>PESO: </b>
                        <c:if test="${postagem.unidade eq 't'}">
                            ${postagem.peso / 1000000}
                        </c:if>
                        <c:if test="${postagem.unidade eq 'kg'}">
                            ${postagem.peso / 1000}
                        </c:if>
                        <c:if test="${postagem.unidade eq 'g'}">
                            ${postagem.peso}
                        </c:if>
                        <c:if test="${postagem.unidade eq 'mg'}">
                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${postagem.peso * 1000}"/>
                        </c:if>
                        (${postagem.unidade})
                    </p>
                    <c:if test="${ not empty postagem.tipoLixo}">
                        <p class="col-xs-6"><b>TIPO: </b> ${postagem.tipoLixo.nome}</p>
                    </c:if>
                    <c:if test="${ not empty postagem.tipoMaterial}">
                        <p class="col-xs-6"><b>TIPO: </b> ${postagem.tipoMaterial.nome}</p>
                    </c:if>
                    <c:if test="${postagem.status eq 'true' and postagem.empresa.id eq empresaLogada.id}">
                        <a <c:if test="${empty postagem.grupo}">href="<c:url value="/postagem/${postagem.id}/alterar/status"/>"</c:if> <c:if test="${not empty postagem.grupo}">href="<c:url value="/grupo/${postagem.grupo.id}/postagem/${postagem.id}/alterar/status"/>"</c:if> class="pull-right btn btn-primary">Finalizar Postagem</a>
                    </c:if>
                    <c:if test="${postagem.status eq 'false'}">
                        <a <c:if test="${empty postagem.grupo}">href="<c:url value="/postagem/${postagem.id}/alterar/status"/>"</c:if> <c:if test="${not empty postagem.grupo}">href="<c:url value="/grupo/${postagem.grupo.id}/postagem/${postagem.id}/alterar/status"/>"</c:if> class="pull-right btn btn-primary">Reativar Postagem</a>
                    </c:if>
                    <c:if test="${postagem.empresa.id eq empresaLogada.id}">
                        <a href="<c:url value="/postagem/${postagem.id}/editar"/>" class="pull-right btn btn-primary">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>
