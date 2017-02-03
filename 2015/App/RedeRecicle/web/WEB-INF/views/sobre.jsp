<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>RedeRecicle | Sobre</title>

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
            <jsp:include page="/WEB-INF/views/navbar.jsp"/>
            <jsp:include page="/WEB-INF/views/loginModal.jsp"/>
            <div class="row">
                <div class="col-md-offset-1 col-md-7">
                    <h1 class="text-center margemSobre">Origem</h1>
                    <img class="img-responsive img-circle center-block" src="<c:url value="/img/logo/logo.jpg"/>" alt="logo"/>
                    <p class="text-justify">
                        Segundo a organização internacional do trabalho (OIT), estima que a geração mundial de lixo eletrônico 
                        alcance dezenas de milhões de toneladas anuais, contendo 
                        estes diversos contaminantes nocivos à saúde humana como também ao meio ambiente. 
                    </p>
                    <p class="text-justify">
                        O centro de tecnologia mineral (CETEM) constata que 70% dos metais pesados encontrados em lixões e 
                        aterros controlados tem origem de equipamentos eletrônicos descartados, 
                        esses equipamentos contaminam o solo e lençóis freáticos, colocando em risco o meio ambiente.
                    </p>
                    <p class="text-justify">
                        RedeRecicle foi desenvolvido em paralelo à uma pesquisa científica realizada pela 
                        <a href="<c:url value="http://www.fai-mg.br/portal/"/>">Fai - Centro de Ensino Superior em Gestão, Tecnologia e Educação</a>
                        em conjunto com a <a href="<c:url value="http://www.fapemig.br/"/>">Fundação de Amparo à Pesquisa do estado de Minas Gerais - FAPEMIG</a>, como protótipo para 
                        facilitar o trabalho de reciclagem entre as empresas. Tem o objetivo de aproximar as partes 
                        do processo industrial que regem a reciclagem 
                        tecnológica. Possui interação na forma de rede social visando a conexão entre empresas. 
                    </p>
                </div>
                <div class="col-md-4">
                    </br></br></br></br></br></br></br>
                    <h3>Links Externos:</h3>
                    <div class="list-group">
                        <a href="<c:url value="http://www.fai-mg.br/portal/"/>" class="list-group-item ">Fai - Centro de Ensino Superior em Gestão, Tecnologia e Educação</a>
                        <a href="<c:url value="http://www.fapemig.br/"/>" class="list-group-item">FAPEMIG - Fundação de Amparo à Pesquisa do estado de Minas Gerais</a>
                    </div>
                </div>
                <jsp:include page="/WEB-INF/views/footer.jsp"/>
            </div>
            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="<c:url value="/js/jquery.min.js"/>"></script>
            <!-- Include all compiled plugins (below), or include individual files as needed -->
            <script src="<c:url value="/js/bootstrap.min.js"/>"></script>

    </body>
</html>
