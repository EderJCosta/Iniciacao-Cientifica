<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container-fluid">
    <ul class="nav nav-pills nav-stacked">
        <li <c:if test="${opcao eq 'home'}"> class="active"</c:if>  role="presentation"><a  href="<c:url value="/home"/>">Home</a></li>
        <li <c:if test="${opcao eq 'add'}"> class="active"</c:if> role="presentation"><a  href="<c:url value="/book/create"/>">Adicionar Livros</a></li>
        <li <c:if test="${opcao eq 'list'}"> class="active"</c:if>role="presentation"><a  href="<c:url value="/book/list"/>">Listar Livros</a></li>
    </ul>
</div>