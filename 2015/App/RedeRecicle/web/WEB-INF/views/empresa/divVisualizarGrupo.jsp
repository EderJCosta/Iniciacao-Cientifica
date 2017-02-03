<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-3">
        <img class="img-responsive img-rounded imagemEmpresa"src="<c:url value="/grupo/${grupo.id}/img.jpg"/>"/>
        <h4 class="text-center">${grupo.nome}</h4>
    </div>
    <div class="col-md-9">
        <div class="divisao"></div>
        <label for="descrição">Descrição:</label>
        <p id="descrição">${grupo.descricao}</p>
        <div class="divisao"></div>
        <p><b>Proprietário:</b> ${grupo.empresa.nome}</p>
        <p><b>Email:</b> <a href="#">${grupo.empresa.email}</a></p>
    </div>
    <div class="col-md-12">
        <a class=" pull-right btn btn-primary" href="<c:url value="/grupo/${grupo.id}/membros"/>">Membros (${membros})</a>
    </div>
</div>
<div class="divisao"></div>
<c:if test="${empresaLogada.tipo ne 'C'}">
    <div id="postarOn" class="moldura-div postar">
        <div class="input-group">
            <div class="input-group-addon">Nova Postagem <span class="glyphicon glyphicon-menu-down"></span></div>
            <input  type="text" class="form-control" id="exampleInputAmount" placeholder="Clique para postar!">
        </div>
    </div>

    <div id="postar" class="postar"> 
        <div class="moldura-div">
            <h4 class="text-center">NOVA POSTAGEM</h4>
            <form class="form-horizontal" action="<c:url value="/grupo/postagem/novo"/>" method="POST">
                <input name="grupoid" type="hidden" value="${grupo.id}"/>
                <input name="empresaid" type="hidden" value="${empresaLogada.id}"/>
                <div class="form-group">
                    <label for="titulo" class="col-sm-2 control-label">Título:</label>
                    <div class="col-sm-10">
                        <input type="text"  name="titulo" class="form-control" id="titulo" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="descricao" class="col-sm-2 control-label">Descrição:</label>
                    <div class="col-sm-10">
                        <textarea class="form-control col-sm-10" name="descricao" id="descricao" maxlength="255"></textarea>
                    </div>
                </div>
                <div class="form-group semMargembott">
                    <label for="peso" class="col-sm-2 control-label">Peso:</label>
                    <div class="col-sm-2">
                        <input type="text" name="peso" class="form-control" id="peso" >
                    </div>
                    <label for="unidade" class="col-sm-2 control-label">Unidade:</label>
                    <div class="col-sm-2">
                        <select name="unidade" id="unidade" class="form-control">
                            <option value="mg">Miligrama(mg)</option>
                            <option value="g">Grama(g)</option>
                            <option value="kg">Kilo(kg)</option>
                            <option value="t">Tonelada(t)</option>
                        </select>
                    </div>
                    <label for="tipo" class="col-sm-2 control-label">Tipo</label>
                    <div class="col-sm-2 semMargembott">
                        <select name="tipo" id="tipo" class="form-control">
                            <c:forEach items="${tipoList}" var="lista">
                                <option value="${lista.id}">${lista.nome}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${empresaLogada.tipo eq 'F' or empresaLogada.tipo eq 'R'}">
                            <a href="<c:url value="#"/>" data-toggle="modal" data-target=".tipoADD"><span class="well well-sm glyphicon glyphicon-plus semMargembott" aria-hidden="true"></span></a>
                            </c:if>
                            <c:if test="${empresaLogada.tipo eq 'C'}">
                            <a href="<c:url value="#"/>" data-toggle="modal" data-target=".tipoADD"><span class="well well-sm glyphicon glyphicon-plus semMargembott" aria-hidden="true"></span></a>
                            </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-8 semMargembott">
                        <a id="postarOff" href="<c:url value="#"/>"><span class=" pull-left well well-sm glyphicon glyphicon-menu-up" aria-hidden="true"></span></a>
                    </div>
                    <div class="col-sm-2 semMargembott">
                        <button type="submit" class=" pull-right btn btn-primary">Postar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</c:if>
<jsp:include page="/WEB-INF/views/empresa/divPostagem.jsp"/>
<nav>
    <ul class="pager">
        <li class="previous <c:if test="${offset==0}">disabled</c:if> "><a  <c:if test="${offset>0}"> href="<c:url value="/grupo/${grupo.id}?limit=${limit}&offset=${offset - limit}"/>"</c:if> ><span aria-hidden="true">&larr;</span> Voltar</a></li>
        <li class="next <c:if test="${qtde != limit || qtde == qtdeTotal}">disabled</c:if>"><a <c:if test="${qtde == limit && qtde != qtdeTotal}"> href="<c:url value="/grupo/${grupo.id}?limit=${limit}&offset=${offset + limit}"/>" </c:if>> Carregar<span aria-hidden="true">&rarr;</span></a></li>
        </ul>
    </nav>

    <div class="modal fade tipoADD" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class=" text-center modal-title" id="exampleModalLabel">Adicionar Tipo</h4>
                </div>
                <div class="modal-body">
                        <form <c:if test="${empresaLogada.tipo eq 'F'}"> action="<c:url value="/grupo/lixo/novo"/>" </c:if> <c:if test="${empresaLogada.tipo ne 'F'}"> action="<c:url value="/grupo/material/novo"/>" </c:if> method="POST">
                        <div class="form-group">
                            <label for="nomeTipo" class="control-label">Nome: </label>
                                <input type="hidden" name="grupoid" value="${grupo.id}"/>
                        <input name="nome" type="text" class="form-control" id="nomeTipo"/>
                    </div>
                    <p class="text-right">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                    </p>
                </form>
            </div>
        </div>
    </div>
</div>