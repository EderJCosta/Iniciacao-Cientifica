<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="text-center"> ${grupo.nome} | Membros</h2>
<c:if test="${grupoRemove eq 'sucesso'}">
    <div class="col-sm-12 alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        Membro removido com sucesso!
    </div>
    <br/>
</c:if>
<c:if test="${grupoRemove eq 'falha'}">
    <div class="col-md-12 alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        Falha ao remover Membro!
    </div>
    <br/>
</c:if>
    <br/>
<br/>
<div class="moldura-div">
    <div class="row">
        <div class="col-xs-5 col-sm-2">
            <img class="img-responsive img-rounded center-block imagemEmpresa" src="<c:url value="/empresa/${empresaLogada.id}/img.jpg"/>"/>
        </div>
        <div class="col-xs-7 col-sm-10">
            <h3 class="text-center"><a href="<c:url value="/empresa/${empresaLogada.id}"/>">${empresaLogada.nome}</a></h3>
            <p class="text-justify"><b>Descrição: </b> ${empresaLogada.descricao}</p>
            <div class="divisao"></div>
            <p class="text-justify"><b>Email: </b> ${empresaLogada.email}</p>
        </div>
        <div class="col-sm-12" role="group">
            <a href="<c:url value="/empresa/${empresaLogada.id}"/>" class="pull-right btn btn-success"> Visualizar</a>
        </div>
    </div>
</div>
<div class="divisao"></div>
<c:if test="${not empty grupo.empresaList}">
    <c:forEach items="${grupo.empresaList}" var="empresaItem">
        <c:if test="${empresaItem.id ne empresaLogada.id}">
            <div class="moldura-div">
                <div class="row">
                    <div class="col-xs-5 col-sm-2">
                        <img class="img-responsive img-rounded center-block imagemEmpresa" src="<c:url value="/empresa/${empresaItem.id}/img.jpg"/>"/>
                    </div>
                    <div class="col-xs-7 col-sm-10">
                        <h3 class="text-center"><a href="<c:url value="/empresa/${empresaItem.id}"/>">${empresaItem.nome}</a></h3>
                        <p class="text-justify"><b>Descrição: </b> ${empresaItem.descricao}</p>
                        <div class="divisao"></div>
                        <p class="text-justify"><b>Email: </b> ${empresaItem.email}</p>
                    </div>
                    <div class="col-sm-12" role="group">
                        <a href="<c:url value="/empresa/${empresaItem.id}"/>" class="pull-right btn btn-success"> Visualizar</a>
                        <c:if test="${empresaLogada.id eq grupo.empresa.id}">
                            <a href="<c:url value="/grupo/${grupo.id}/${empresaItem.id}/remover"/>" class="pull-right btn btn-danger"> Remover</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
</c:if>

