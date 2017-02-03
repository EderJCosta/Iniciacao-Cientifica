<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>RedeRecicle</title>

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
        <div class="back">
            <div class="container-fluid">
                <jsp:include page="/WEB-INF/views/navbar.jsp"/>
                <jsp:include page="/WEB-INF/views/loginModal.jsp"/>
                <div class="row">
                    <div class="col-md-12 carouselRR">

                        <div id="carouselRR" class="carousel slide carouselRR" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carouselRR" data-slide-to="0" class="active"></li>
                                <li data-target="#carouselRR" data-slide-to="1"></li>
                                <li data-target="#carouselRR" data-slide-to="2"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner " role="listbox">
                                <div class="item active">
                                    <img class="img-responsive img-carouselRR " src="<c:url value="/img/carousel/1.jpg"/>" alt="1">
                                    <div class="carousel-caption">
                                        Faça parte da RedeRecicle. Em conjunto ajudando o meio ambiente!
                                    </div>
                                </div>
                                <div class="item">
                                    <img class="img-responsive" src="<c:url value="/img/carousel/2.jpg"/>" alt="2">
                                    <div class="carousel-caption">
                                        Evolução preservando o meio ambiente!
                                    </div>
                                </div>
                                <div class="item">
                                    <img  class="img-responsive" src="<c:url value="/img/carousel/3.jpg"/>" alt="3">
                                    <div class="carousel-caption">
                                        Uma rede para um meio ambiente preservado e um mundo tecnológico!
                                    </div>
                                </div>
                            </div>

                            <!-- Controls -->
                            <a class="left carousel-control" href="#carouselRR" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Voltar</span>
                            </a>
                            <a class="right carousel-control" href="#carouselRR" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Próximo</span>
                            </a>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="descriçãoRR col-md-10">
                        <div class="row">
                            <div class="col-md-4 descriçãoRR">
                                <h2 class="text-center">Fabricantes</h2>
                                <p class="text-center">
                                    Encontre empresas de reciclagem para o lixo eletrônico de sua empresa! 
                                </p>
                            </div>
                            <div class="col-md-4 descriçãoRR">
                                <h2 class="text-center">Reciclantes</h2>
                                <p class="text-center">
                                    Ache mais facilmente lixo eletrônico e encontre compradores para o material já reciclado!
                                </p>
                            </div>
                            <div class="col-md-4 descriçãoRR">
                                <h2 class="text-center">Compradores</h2>
                                <p class="text-center">
                                    Fique de olho na comunicação, ache todo tipo de material reciclado!
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1"></div>
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
