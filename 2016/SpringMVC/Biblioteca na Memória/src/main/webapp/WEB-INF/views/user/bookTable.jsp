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
                    <h2 class="text-center">LISTAGEM DE LIVROS</h2>
                    <br/><br/><br/>
                    <table class="table table-hover">
                        <tr>
                            <th>Nome do Livro</th>
                            <th>Autor</th>
                            <th>descrição</th>
                            <th>Ações</th>
                        </tr>
                        <c:forEach items="${bookList}" var="book" varStatus="index">
                            <tr >
                                <td>${book.name}</td>
                                <td>${book.author}</td>
                                <td>${book.description}</td>
                                <td>
                                    <a class="btn btn-primary" href="<c:url value="/book/${index.index}"/>" >V</a> 
                                    <a class="btn btn-primary" href="<c:url value="/book/edit/${index.index}"/>" >E</a> 
                                    <a class="btn btn-primary" href="<c:url value="/book/delete/${index.index}"/>">X</a>     
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>



        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <!-- Include all comiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    </body>
</html>