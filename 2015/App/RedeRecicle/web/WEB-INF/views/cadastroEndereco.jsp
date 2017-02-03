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
        <div class="back">
            <jsp:include page="/WEB-INF/views/navbar.jsp"/>
            <jsp:include page="/WEB-INF/views/loginModal.jsp"/>

            <div class="container-fluid">

                <h1 class="text-center ">Etapa 2: Cadastro de endereços</h1>

                <div class="row">
                    <c:if test="${cadastro_endereco ne 'editar' and cadastro_endereco ne 'novo' and cadastro eq 'incompleto'}">
                        <div class="col-md-12">
                            <div class="divisao"></div>
                            <jsp:include page="/WEB-INF/views/empresa/divEnderecos.jsp"/>
                        </div>
                        <div class="col-sm-12 col-md-12">
                            <div class="divisao"></div>
                            <a class="conteudo pull-right btn btn-success <c:if test="${empty enderecoList}">  disabled </c:if>" href="<c:url value="/cadastro/telefone"/>">Próxima Etapa</a>
                            </div>
                    </c:if>

                    <c:if test="${cadastro eq 'incompleto' and cadastro_endereco eq 'editar' or cadastro_endereco eq 'novo'}">
                        <div class="col-xs-offset-1 col-md-5">
                            <jsp:include page="/WEB-INF/views/empresa/formEndereco.jsp"/>
                        </div>
                        <div class="col-xs-offset-1 col-md-5">
                            <div class="divisao"></div>
                            <div class="descricao nome">
                                <h4>NOME DA LOCALIDADE:</h4>
                                <p class="text-justify">Informe o Estado em que a empresa se localiza.</p>
                                <p>Exemplos:</p>
                                <ul>
                                    <li><p>Minas Gerais</p></li>
                                    <li><p>Rio De Janeiro</p></li>
                                    <li><p>São Paulo</p></li>
                                    <li><p>Amazonas</p></li>
                                </ul>
                            </div>
                            <div class="descricao estado">
                                <h4>ESTADO</h4>
                                <p class="text-justify">Informe o Estado em que a empresa se localiza.</p>
                                <p>Exemplos:</p>
                                <ul>
                                    <li><p>Minas Gerais</p></li>
                                    <li><p>Rio De Janeiro</p></li>
                                    <li><p>São Paulo</p></li>
                                    <li><p>Amazonas</p></li>
                                </ul>
                            </div>
                            <div class="descricao cidade">
                                <h4>CIDADE</h4>
                                <p class="text-justify">Informe a cidade em que a empresa se localiza.</p>
                                <p>Exemplos:</p>
                                <ul>
                                    <li><p>Belo Horizonte</p></li>
                                    <li><p>Rio De Janeiro</p></li>
                                    <li><p>São Paulo</p></li>
                                    <li><p>Manaus</p></li>
                                </ul>
                            </div>
                            <div class="descricao cep">
                                <h4>CEP</h4>
                                <p class="text-justify">Informe o cep em que a empresa se localiza. Observação apenas números</p>
                                <p>Exemplos:</p>
                                <ul>
                                    <li><p>37545000</p></li>
                                </ul>
                            </div>
                            <div class="descricao bairro">
                                <h4>BAIRRO</h4>
                                <p class="text-justify">Informe o bairro em que a empresa se localiza.</p>
                                <p>Exemplos:</p>
                                <ul>
                                    <li><p>Centro</p></li>
                                    <li><p>Vista Alegre</p></li>
                                    <li><p>Guarulhos</p></li>
                                    <li><p>Morumbi</p></li>
                                </ul>
                            </div>
                            <div class="descricao logradouro">
                                <h4>LOGRADOURO</h4>
                                <p class="text-justify">Informe a rua em que a empresa se localiza.</p>
                                <p>Exemplos:</p>
                                <ul>
                                    <li><p>Avenida Paulista</p></li>
                                    <li><p>Rua Monsenhor Marcelo Pereira</p></li>
                                    <li><p>Rua Governador Geraldo Pimenta</p></li>
                                    <li><p>Rua Machado Filho</p></li>
                                </ul>
                            </div>
                            <div class="descricao numero">
                                <h4>NÚMERO</h4>
                                <p class="text-justify">Informe o Número do edificio em que a empresa se localiza.</p>
                                <p>Exemplos:</p>
                                <ul>
                                    <li><p>71</p></li>
                                    <li><p>14</p></li>
                                    <li><p>72</p></li>
                                    <li><p>21</p></li>
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
        <jsp:include page="/WEB-INF/views/footer.jsp"/>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/js/jquery.maskedinput.min.js"/>"></script>
        <script src="<c:url value="/js/funcoes.js"/>"></script>
    </body>
</html>
