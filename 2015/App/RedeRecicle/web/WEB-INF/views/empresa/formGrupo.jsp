<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="criarGrupo">
    <c:if test="${opcao eq 'grupoCreate'}">
        <h4 class="text-center">Criar Grupo</h4>
    </c:if>
    <c:if test="${opcao eq 'editarGrupo'}">
        <h2 class="text-center">Editar Grupo</h2>
        <h3 class="text-center"> Imagem do Grupo</h3>
        <img class="center-block img-responsive img-rounded imagemEmpresa"src="<c:url value="/grupo/${grupo.id}/img.jpg"/>"/>
        <div class="col-sm-offset-2">
            <form action="<c:url value="/grupo/alterar/img"/>" method="POST" enctype="multipart/form-data" onsubmit="return checkSize(2097152)">
                <div class="form-group">
                    <label for="file">Enviar imagem</label>
                    <input name="id" type="hidden" value="${grupo.id}"/>
                    <input type="file" id="upload" name="file"/>
                    <c:if test="${not empty erroImg}">
                        <div class="alert alert-warning alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            ${erroImg}
                        </div>
                    </c:if>
                    <p class="help-block">Tamanho do arquivo não deverá maior que 2MB</p>
                </div>
                <div class="form-group">
                    <button type="submit" class="pull-right btn btn-primary">Enviar</button>
                </div>
            </form>
            <br>
            <br>
            <div class="divisao"></div>
        </div>
    </c:if>
    <h3 class="text-center">Dados do Grupo</h3>
    <form class="form-horizontal" enctype="multipart/form-data" method="POST" onsubmit="return checkSize(2097152)">
        <c:if test="${opcao eq 'grupoCreate'}">
            <div class="form-group">
                <label for="file" class="col-sm-2 control-label">Imagem:</label>
                <div class="col-sm-10">
                    <input type="file" id="upload" name="file"/>
                    <c:if test="${not empty erroImg}">
                        <div class="alert alert-warning alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            ${erroImg}
                        </div>
                    </c:if>
                    <p class="help-block">Tamanho do arquivo não deverá maior que 2MB. Arquivo do tipo JPG.</p>
                </div>
            </div>
        </c:if>
        <div class="form-group">
            <label for="nome" class="col-sm-2 control-label">Nome:</label>
            <div class="col-sm-10">
                <input type="text" name="nome" class="form-control" id="nome" value="${grupo.nome}"/>
                <c:if test="${not empty errors.nome }">
                    <div class="alert-danger">
                        <span>${errors.nome}</span>
                        <br/>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label for="descricao" class="col-sm-2 control-label">Descrição:</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="descricao" id="descricao" rows="4" cols="108">${grupo.descricao}</textarea>
                <c:if test="${not empty errors.descricao }">
                    <div class="alert-danger">
                        <span>${errors.descricao}</span>
                        <br/>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:if test="${opcao eq 'grupoCreate'}">
                    <button type="submit" class=" pull-right btn btn-primary">Criar</button>
                </c:if>
                <c:if test="${opcao eq 'editarGrupo'}">
                    <button type="submit" class=" pull-right btn btn-primary pull-right">Editar</button>
                </c:if>
            </div>
        </div>
    </form>
</div>
<div class="divisao"></div>
