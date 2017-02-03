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
            <h1 class="text-center">CADASTRO</h1>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-xs-offset-1 col-sm-5 col-xs-5 col-md-5 col-lg-5">
                        <div class="row">
                            <jsp:include page="/WEB-INF/views/empresa/formEmpresa.jsp"/>
                        </div>
                    </div>
                    <div class="col-xs-offset-1 col-sm-5 col-xs-5 col-md-5 col-lg-5 ">
                        <div class="descricao nome">
                            <h4>NOME</h4>
                            <p>Informe o nome de registro de sua empresa!</p>
                            <p>Exemplos:</p>
                            <ul>
                                <li><p>Recicle Mais</p></li>
                                <li><p>Monpad</p></li>
                                <li><p>Renput</p></li>
                                <li><p>Imega</p></li>
                                <li><p>Maxgrow</p></li>
                            </ul>
                        </div>
                        <div class="descricao email">
                            <h4>E-MAIL</h4>
                            <p>Informe o E-mail do setor da empresa responsável.</p>
                            <p>Exemplos:</p>
                            <ul>
                                <li><p>RecicleMais@Recicle.com.br</p></li>
                                <li><p>Monpad@Monpad.com.br</p></li>
                                <li><p>Renput@Renput.com.br</p></li>
                                <li><p>Imega@Imega.com.br</p></li>
                                <li><p>Maxgrow@Maxgrow.com.br</p></li>
                            </ul>
                        </div>
                        <div class="descricao senha">
                            <h4>SENHA</h4>
                            <p>Informe uma senha segura!</p>
                            <p>Para criar uma senha segura veja algumas dicas:</p>
                            <ol>
                                <li>
                                    <p>Misture letras, símbolos especiais e números.</p>
                                </li>
                                <li>
                                    <p>Use letras maiúsculas e minúsculas.</p>
                                </li>
                                <li>
                                    <p>Use uma quantidade de caracteres superior ao recomendado.</p>
                                </li>
                                <li>
                                    <p>Crie senhas de forma que você utilize as duas mãos para digitar.</p>
                                </li>
                            </ol>
                        </div>
                        <div class="descricao cnpj">
                            <h4>CNPJ</h4>
                            <p class="text-justify">Informe o número do Cadastro Nacional de Pessoa Jurídica(CNPJ) da Empresa.</p>
                            <p>Exemplos:</p>
                            <ul>
                                <li><p>48.445.881/0001-80</p></li>
                            </ul>
                        </div>
                        <div class="descricao inscricao_estadual">
                            <h4>INSCRIÇÃO ESTADUAL</h4>
                            <p class="text-justify">Informe o número da Inscrição Estadual da Empresa. Conforme cadastrado no estado.</p>
                        </div>
                        <div class="descricao descricaocampo">
                            <h4>DESCRIÇÃO</h4>
                            <p class="text-justify">Descreva sua empresa, para que seja identificada com facilidade.</p>
                            <p>Informações que podem ajudar:</p>
                            <ul>
                                <li><p>Setor negócio</p></li>
                                <li><p>Localidade</p></li>
                                <li><p>Premios</p></li>
                            </ul>
                        </div>
                        <div class="descricao default">
                            <h4>RedeRecicle</h4>
                            <p class="text-justify">Encontre parceiros, negócios e ajude a criar um mundo melhor.<br/> Obrigado por se cadastrar em nosso site.</p>
                        </div>

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
