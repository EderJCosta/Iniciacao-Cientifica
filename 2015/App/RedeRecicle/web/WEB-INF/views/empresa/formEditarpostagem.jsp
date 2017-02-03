<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h4 class="text-center">EDTIAR POSTAGEM</h4>
<div class="divisao"></div>
<form class="form-horizontal" action="<c:url value="/postagem/${postagem.id}/editar"/>" method="POST" onsubmit="return validacao_peso()">
    <input name="empresaid" type="hidden" value="${empresaLogada.id}"/>
    <input name="grupoid" type="hidden" value="<c:if test="${ not empty postagem.grupo}"> ${postagem.grupo.id} </c:if> <c:if test="${ empty postagem.grupo}">${0}</c:if> "/>
    <div class="form-group">
        <label for="titulo" class="col-sm-2 control-label">Título:</label>
        <div class="col-sm-10">
            <input type="text"  name="titulo" class="form-control" value="${postagem.titulo}" id="titulo" >
        </div>
    </div>
    <div class="form-group">
        <label for="descricao" class="col-sm-2 control-label">Descrição:</label>
        <div class="col-sm-10">
            <textarea class="form-control col-sm-10" name="descricao" id="descricao" maxlength="255">${postagem.descricao}</textarea>
        </div>
    </div>
    <div class="form-group semMargembott">
        <label for="peso" class="col-sm-2 control-label">Peso:</label>
        <div class="col-sm-2">
            <input type="text" name="peso" class="form-control" id="peso" value=" <c:if test="${postagem.unidade eq 't'}">${postagem.peso / 1000000}</c:if> <c:if test="${postagem.unidade eq 'kg'}">${postagem.peso / 1000}</c:if> <c:if test="${postagem.unidade eq 'g'}"> ${postagem.peso} </c:if> <c:if test="${postagem.unidade eq 'mg'}"> <fmt:formatNumber type="number" maxFractionDigits="2" value="${postagem.peso * 1000}"/> </c:if> " >
            </div>
            <label for="unidade" class="col-sm-2 control-label">Unidade:</label>
            <div class="col-sm-2">
                <select name="unidade" id="unidade" class="form-control">
                                <option value="${postagem.unidade}">(${postagem.unidade}) Selecione</option>
                <option value="mg">Miligrama(mg)</option>
                <option value="g">Grama(g)</option>
                <option value="kg">Kilo(kg)</option>
                <option value="t">Tonelada(t)</option>
            </select>
        </div>
        <label for="tipo" class="col-sm-2 control-label">Tipo</label>
        <div class="col-sm-2 semMargembott">
            <select name="tipo" id="tipo" class="form-control">
                <option value="${postagem.tipoLixo.id}">(${postagem.tipoLixo.nome}) Selecione</option>
                <c:forEach items="${tipoList}" var="lista">
                    <option value="${lista.id}">${lista.nome}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="divisao"></div>
    <button type="submit" class="margemCadastro pull-right btn btn-primary">Editar</button> 
</div>
</form>
