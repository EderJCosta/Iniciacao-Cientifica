<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="text-center">Alterar Senha</h2>
<br/>
<div class="divisao"></div>
<br/>
<form class="" action="<c:url value="/empresa/senha/alterar"/>" method="POST">
    <div class="form-group">
        <label for="senhaAntiga">Senha Antiga: </label>
        <div class="input-group">
            <input class="form-control" id="senhaAntiga" type="password" name="senhaAntiga" value="" />
            <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-lock"></i></span>
        </div>
        <c:if test="${not empty senhaAtinga }">
            <span>${senhaAtinga}</span><br/>
        </c:if>
    </div>
    <div class="form-group">
        <label for="senhaNova">Nova Senha: </label>
        <div class="input-group">
            <input class="form-control" id="senhaNova" type="password" name="senhaNova" value="" />
            <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-lock"></i></span>
        </div>
        <c:if test="${not empty senhaNova }">
            <span>${senhaNova}</span><br/>
        </c:if>
    </div>
    <div class="form-group">
        <label for="senhaConfirmada">Confirmação da senha: </label>
        <div class="input-group ">
            <input class="form-control" id="senhaConfirmada" type="password" name="senhaConfirmada" value="" />
            <span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-lock"></i></span>
        </div>
        <c:if test="${not empty senhaNova }">
            <span>${senhaNova}</span><br/>
        </c:if>
    </div>
    <button type="submit" class="pull-right btn btn-primary">Alterar</button>

</form>
<c:if test="${conclusao eq 'sucesso'}">
    <div class="col-md-12 alert alert-success">
        Senha alterada com sucesso
    </div>
</c:if>
<c:if test="${conclusao eq 'falha'}">
    <div class="col-md-12 alert alert-danger">
        Não foi possivel alterar a senha. Verefique o erro e tente novamente.
    </div>
</c:if>
<br/>
<br/>
<div class="divisao"></div>
<br/>
