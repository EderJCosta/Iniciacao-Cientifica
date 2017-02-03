<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Biblioteca</title>

        <!-- Bootstrap -->
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="resources/css/estilo.css"/>" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/library/navbar.jsp"/>
        <div class="row">
            <div class="col-md-2 col-sm-6 col-xs-12 sidebarNav">
                <jsp:include page="/WEB-INF/views/user/sideNavUL.jsp"/>
            </div>
            <div class="col-md-10 col-sm-6 col-xs-12">
                <div class="container padding">
                    <c:if test="${empty book}"> 
                        <h2 class="text-center">CADASTRO DE LIVRO</h2>
                    </c:if>
                    <c:if test="${not empty book}"> 
                        <h2 class="text-center">EDIÇÃO DE LIVRO</h2>
                    </c:if>
                    <form <c:if test="${empty book}"> action="<c:url value="/book/create"/>"</c:if> method="POST">
                            <div class="form-group">
                                <label for="name">Nome do Livro</label>
                                <input  name="name" type="text" class="form-control" id="name" placeholder="Nome" value="${book.name}">
                        </div>
                        <div class="form-group">
                            <label for="author">Autor</label>
                            <input  name="author" type="text" class="form-control" id="author" placeholder="Autor" value="${book.author}">
                        </div>
                        <div class="form-group">
                            <label for="description">description</label>
                            <textarea name="description" type="text" class="form-control" id="description" placeholder="Descrição">${book.description}</textarea>
                        </div>
                        <c:if test="${not empty book}"> 
                            <button type="submit" class="btn btn-primary">Editar</button> 
                        </c:if>
                        <c:if test="${empty book}"> 
                            <button type="submit" class="btn btn-primary">Cadastrar</button> 
                        </c:if>
                    </form>
                </div>
            </div>
        </div>



        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <!-- Include all comiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    </body>
</html>