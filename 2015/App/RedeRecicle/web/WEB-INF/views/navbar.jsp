<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navcor navbar-fixed-top semRadius ">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:if test="${empty empresaLogada}">
                <a class="navbar-brand" href="<c:url value="/"/>"><span class="glyphicon glyphicon-leaf"></span> RedeRecicle</a>
            </c:if>
            <c:if test="${not empty empresaLogada}">
                <a class="navbar-brand" href="<c:if test="${cadastro ne 'incompleto'}"><c:url value="/home"/></c:if> <c:if test="${cadastro eq 'incompleto'}"><c:url value=""/></c:if>"><span class="glyphicon glyphicon-leaf"></span> RedeRecicle</a>
            </c:if>
            <a class="menuNavBar navbar-brand pull-right"  href="<c:url value="/Empresa/${empresaLogada.nome}"/>">${empresaLogada.nome}</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:if test="${empty empresaLogada}">

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" data-toggle="modal" data-target="#loginForm" >Entrar</a></li>
                    <li><a href="<c:url value="/empresa/novo"/>">Cadastrar</a></li>
                    <li><a href="<c:url value="/sobre"/>">Sobre</a></li>
                </ul>
            </c:if>
            <c:if test="${not empty empresaLogada}">
                <ul class="nav navbar-nav navbar-right menuNavBar">
                    <li  <c:if test="${opcao eq 'postagemCreate'}">class="active"</c:if>><a href="<c:url value="/postagem/feed"/>">Feed de Postagens</a></li>
                    <li  <c:if test="${opcao eq 'postagensFinalizadas'}">class="active"</c:if>><a href="<c:url value="/postagem/finalizadas"/>">Postagens Finalizadas</a></li>
                    <li  <c:if test="${opcao eq 'postagemReadByCriteria'}">class="active"</c:if>><a  href="<c:url value="/postagem/pesquisa"/>">Pesquisar Postagens</a></li>
                    <li  <c:if test="${opcao eq 'empresasReadByCriteria'}">class="active"</c:if>><a  href="<c:url value="/Empresa/Pesquisa"/>">Pesquisar Empresa</a></li>
                    <li  <c:if test="${opcao eq 'favoritosReadById'}">class="active"</c:if>><a href="<c:url value="/Empresa/Favoritos"/>">Favoritos</a></li>
                    <li  <c:if test="${opcao eq 'grupoReadByCriteria'}">class="active"</c:if>><a href="<c:url value="/empresa/grupos"/>">Meus Grupos</a></li>
                    <li><a <c:if test="${cadastro ne 'incompleto'}"> href="<c:url value="/configuracoes"/>" </c:if> <c:if test="${cadastro eq 'incompleto'}"> href="#" </c:if>  >Configurações</a></li>
                    <li><a href="<c:url value="/Logout"/>">Sair</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right menuNavBarFull">
                    <li class="dropdown">
                        <a href="<c:url value="#"/>" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${empresaLogada.nome} <span class="glyphicon glyphicon-cog "></span></a>
                        <ul class="dropdown-menu dropdown-menu-navbar-rr">
                            <c:if test="${cadastro ne 'incompleto'}">
                                <li>
                                    <a  href="<c:url value="/configuracoes"/>">Configurações</a>
                                </li>
                            </c:if>
                            <li><a href="<c:url value="/Logout"/>">Sair</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid  -->
</nav>