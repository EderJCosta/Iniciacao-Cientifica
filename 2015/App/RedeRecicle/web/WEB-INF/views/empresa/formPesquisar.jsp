<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h4 class="text-center">PESQUISAR EMPRESAS</h4>
<form class="form-horizontal" method="POST">
    <div class="form-group">
        <label for="nome" class="col-sm-1 control-label">Nome:</label>
        <div class="col-sm-5">
            <input value="${itemNome}" name="nome" type="text" class="form-control" id="nome" required>
        </div>
        <label for="tipo" class="col-sm-2 control-label">Area da empresa:</label>
        <div class="col-sm-4">
            <select name="tipo" id="unidade" class="form-control">
                <option value="F">Fabricante</option>
                <option value="R">Reciclante</option>
                <option value="C">Comprador</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="pull-right btn btn-primary">Pesquisar</button>
        </div>
    </div>
</form>
<div class="divisao"></div>
<c:if test="${not empty empresaList}">
    <c:forEach items="${empresaList}" var="empresaItem">
        <div class="moldura-div">
            <div class="row">
                <div class="col-xs-5 col-sm-2">
                    <img class="img-responsive img-rounded center-block imagemEmpresa" src="<c:url value="/empresa/${empresaItem.id}/img.jpg"/>"/>
                </div>
                <div class="col-xs-7 col-sm-10">
                    <p class="text-center"><a href="<c:url value="/empresa/${empresaItem.id}"/>">${empresaItem.nome}</a></p>
                    <p class="text-justify"><b>Descrição: </b> Aenean lacinia bibendum nulla sed consectetur. Vestibulum id ligula porta felis euismod semper. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. ${empresaItem.descricao}</p>
                    <div class="divisao"></div>
                    <p class="text-justify"><b>Email: </b> ${empresaItem.email}</p>
                </div>
                <div class="col-sm-12">
                    <a href="<c:url value="/empresa/${empresaItem.id}"/>" class="pull-right btn btn-success"> Visualizar</a>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>
<c:if test="${not empty itemNome}">
    <c:if test="${empty empresaList}">
        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <p class="text-center">Nenhum resultado foi encontrado!  :(</p>
        </div>
    </c:if>
</c:if>
<c:if test="${empty itemNome}">
    <c:if test="${empty empresaList}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <p class="text-center">Procure por empresas cadastradas no RedeRecicle!!</p>
        </div>
    </c:if>
</c:if>