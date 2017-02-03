<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="text-center">CONFIGURAÇÕES DA CONTA</h2>
<br/>
<div class="divisao"></div>
<br/>
<div class="row">
    <div class=" col-xs-6 col-md-3">
        <a href="<c:url value="/empresa/endereco"/>" class="config thumbnail">
            <span class="glyphicon glyphicon-road"></span><br/>
            Endereço<br/>
        </a>
    </div>  
    <div class="col-xs-6 col-md-3">
        <a href="<c:url value="/empresa/telefone"/>" class="config thumbnail">
            <span class="glyphicon glyphicon-phone"></span><br/>
            Telefone<br/>
        </a>
    </div>
    <div class="col-xs-6 col-md-3">
        <a href="<c:url value="/empresa/alterar"/>" class="config thumbnail">
            <span class="glyphicon glyphicon-briefcase"></span><br/>
            Empresa<br/>
        </a>
    </div>
    <div class="col-xs-6 col-md-3">
        <a href="<c:url value="/empresa/senha/alterar"/>" class="config thumbnail">
            <span class="glyphicon glyphicon-lock"></span><br/>
            Alterar Senha<br/>
        </a>
    </div>
</div>
