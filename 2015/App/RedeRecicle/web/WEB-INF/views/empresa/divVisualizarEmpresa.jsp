<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-3">
        <img class="img-responsive img-rounded imagemEmpresa"src="<c:url value="/empresa/${empresa.id}/img.jpg"/>"/>
        <h4>${empresa.nome}</h4>
        <p><b>Email:</b> <a href="#">${empresa.email}</a></p>
    </div>
    <div class="col-md-9">
        <div class="divisao"></div>
        <label for="descrição">Descrição:</label>
        <p id="descrição">${empresa.descricao}</p>
        <div class="divisao"></div>
        <c:forEach items="${empresa.enderecoSet}" var="endereco">
            <label>Endereço: ${endereco.nome}</label>
            <p id="endereco"> <b>Estado:</b>${endereco.estado} <b>Cidade:</b> ${endereco.cidade} <b>Cep:</b> ${endereco.cep}   <b>Bairro:</b> ${endereco.bairro}   <b><br/>Logradouro:</b> ${endereco.logradouro}   <b>Número:</b> ${endereco.numero}</p>
            <div class="divisao"></div>
        </c:forEach>
        <label for="telefones">Telefones:</label>
        <c:forEach items="${empresa.telefoneSet}" var="telefone">
            <p id="telefones"><b>Departamento:</b> ${telefone.nome}, <b>Número:</b> ${telefone.numero}</p>
        </c:forEach>
    </div>
    <div class="col-md-12">
        <c:if test="${empresaLogada.id ne empresa.id}">
            <a class=" pull-leftcol-md-10" href="<c:url value="/empresa/favorito/${empresa.id}"/>"><span class="glyphicon glyphicon-star-empty"></span> Adicionar aos favoritos</a>
        </c:if>
        <c:if test="${empresa.id ne empresaLogada.id}">
            <div class="dropdown pull-right">
                <button class="btn btn-default dropdown-toggle btn btn-success" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Adicionar ao grupo:
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <c:if test="${not empty grupoList}">
                        <c:forEach items="${grupoList}" var="grupo">
                            <li>
                                <a href="<c:url value="/grupo/${grupo.id}/${empresa.id}/adicionar"/>">${grupo.nome}</a>
                            </li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty grupoList}">
                        <li>
                            <a href="<c:url value="/empresa/grupo/novo"/>">Novo Grupo</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </c:if>
    </div>   
</div>
<c:if test="${grupoadd eq 'sucesso'}">
    <br/>
    <div class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="text-center">Empresa adicionada ao grupo com sucesso!</p>
    </div>
</c:if>
<c:if test="${grupoadd eq 'falha'}">
    <br/>
    <div class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="text-center">Falha ao adicionar empresa ao grupo!</p>
    </div>
</c:if>
<c:if test="${not empty addFavorito}">
    <br/>
    <div class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="text-center">${addFavorito}</p>
    </div>
</c:if>
<div class="divisao"></div>
<jsp:include page="/WEB-INF/views/empresa/divPostagem.jsp"/>