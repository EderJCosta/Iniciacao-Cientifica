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
        <div class=" back">
            <jsp:include page="/WEB-INF/views/navbar.jsp"/>
            <jsp:include page="/WEB-INF/views/loginModal.jsp"/>
            <div class="container-fluid">
                <h1 class="text-center">Etapa 3: Cadastro de Telefones</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="divisao"></div>
                        <c:if test="${cadastro eq 'incompleto' and cadastro_telefone ne 'novo' and cadastro_telefone ne 'editar'}">
                            <jsp:include page="/WEB-INF/views/empresa/divTelefones.jsp"/>
                            <div class="col-sm-12 col-md-12">
                                <div class="divisao"></div>
                                <a class="conteudo pull-right btn btn-success <c:if test="${empty telefoneList}"> disabled </c:if>" href="<c:url value="/cadastro/imagem"/>">Próxima Etapa</a>
                                </div>
                        </c:if>
                        <c:if test="${cadastro eq 'incompleto' and cadastro_telefone eq 'novo' or cadastro_telefone eq 'editar'}">
                            <div class="col-xs-offset-1 col-md-5 col-sm-5">
                                <jsp:include page="/WEB-INF/views/empresa/formTelefones.jsp"/>
                            </div>
                            <div class="col-sm-5 col-md-5 col-xs-offset-1">
                                <div class="descricao nome">
                                    <h4>NOME DO SETOR:</h4>
                                    <p class="text-justify">Informe o Estado em que a empresa se localiza.</p>
                                    <p>Exemplos:</p>
                                    <ul>
                                        <li><p>Marketing</p></li>
                                        <li><p>Vendas</p></li>
                                        <li><p>Adm. Comercial</p></li>
                                        <li><p>Finanças</p></li>
                                    </ul>
                                </div>
                                <div class="descricao numero">
                                    <h4>NÚMERO</h4>
                                    <p class="text-justify">Informe o Número do edificio em que a empresa se localiza.</p>
                                    <p>Exemplos:</p>
                                    <ul>
                                        <li><p>(35) 0000-0000</p></li>
                                        <li><p>(35) 0000-0001</p></li>
                                        <li><p>(35) 0000-0002</p></li>
                                        <li><p>(35) 0000-0003</p></li>
                                    </ul>
                                </div>
                                <div class="descricao default">
                                    <h4>RedeRecicle</h4>
                                    <p class="text-justify">Encontre parceiros, negócios e ajude a criar um mundo melhor.<br/> Obrigado por se cadastrar em nosso site.</p>
                                </div>
                                <div class="divisao"></div>
                            </div>
                        </c:if>
                    </div>
                </div>
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
