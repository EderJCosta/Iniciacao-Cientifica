<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Cadastro | RedeRecicle</title>

        <!-- Bootstrap -->
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/css/estilo.css"/>" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="<c:url value="/js/html5shiv.min.js"/>"></script>
        <script src="<c:url value="/js/respond.min.js"/>"></script>
        <![endif]-->
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <jsp:include page="/WEB-INF/views/navbar.jsp"/>
                <jsp:include page="/WEB-INF/views/loginModal.jsp"/>
            </div>
            <h1 class="text-center">Imagem Da Empresa</h1>
            <div class="col-md-offset-3 col-md-6">
                <img class="center-block img-responsive img-rounded imagemEmpresa" <c:if test="${not empty img}"> src="<c:url value="/img/empresa.jpg"/>" </c:if>  <c:if test="${empty img}"> src="<c:url value="/empresa/${empresaLogada.id}/img.jpg"/>" </c:if> />
                <form action="<c:url value="/cadastro/imagem"/>" enctype="multipart/form-data" method="POST" onsubmit="return checkSize(2097152)">
                    <div class="form-group">
                        <label for="file">Enviar imagem</label>
                        <div class="input-group">
                            <input type="hidden" name="id" value="${empresaLogada.id}"/>
                            <input class="" type="file" id="upload" name="file"/>
                        </div>
                        <br/>
                        <div class="alert alert-warning">
                            <p class="help-block">Tamanho do arquivo não deverá maior que 2MB!</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <button class="pull-right btn btn-primary">Enviar</button>
                    </div>
                </form>                
            </div>
            <div class="col-md-12">
                <div class="divisao"></div>
                <a class="conteudo pull-right btn btn-success <c:if test="${not empty img}"> disabled </c:if>" href="<c:url value="/home"/>">Finalizar Cadastro</a>
                </div>          
            </div>
        <jsp:include page="/WEB-INF/views/footer.jsp"/> 


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/js/jquery.maskedinput.min.js"/>"></script>
        <script src="<c:url value="/js/funcoes.js"/>"></script>
    </body>
</html>
