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
            <jsp:include page="/WEB-INF/views/navbar.jsp"/>

            <jsp:include page="/WEB-INF/views/loginModal.jsp"/>

            <div class="container-fluid">
                <div class="row">
                    <div class=" col-sm-3 col-md-2  menuLateral" >

                        <img class="img-responsive img-rounded center-block imagemEmpresa" src="<c:url value="/empresa/${empresaLogada.id}/img.jpg"/>"/>
                        <h4 class="text-center"><a href="<c:url value="/empresa/${empresaLogada.id}"/>">${empresaLogada.nome}</a></h4>

                        <ul class="nav navLateral">
                            <li  <c:if test="${opcao eq 'postagemCreate'}">class="active"</c:if>><a href="<c:url value="/postagem/feed"/>">Feed de Postagens</a></li>
                            <li  <c:if test="${opcao eq 'postagensFinalizadas'}">class="active"</c:if>><a href="<c:url value="/postagem/finalizadas"/>">Postagens Finalizadas</a></li>
                            <li  <c:if test="${opcao eq 'postagemReadByCriteria'}">class="active"</c:if>><a href="<c:url value="/postagem/pesquisa"/>">Pesquisar Postagens</a></li>
                            <li  <c:if test="${opcao eq 'empresasReadByCriteria'}">class="active"</c:if>><a href="<c:url value="/empresa/Pesquisa"/>">Pesquisar Empresa</a></li>
                            <li  <c:if test="${opcao eq 'favoritosReadById'}">class="active"</c:if>><a href="<c:url value="/empresa/favoritos"/>">Favoritos</a></li>
                            <li  <c:if test="${opcao eq 'grupoReadByCriteria'}">class="active"</c:if>><a href="<c:url value="/empresa/grupos"/>">Meus Grupos</a></li>
                            </ul>
                        </div>
                        <div class="col-xs-12 col-sm-offset-4 col-md-offset-3 col-sm-8 col-md-8 conteudo">

                        <c:if test="${opcao eq 'postagemCreate'}">
                            <jsp:include page="/WEB-INF/views/empresa/formPostagem.jsp"/>
                        </c:if>
                        <!-- formulário pesquisar postagem  -->
                        <c:if test="${opcao eq 'postagemReadByCriteria'}">
                            <jsp:include page="/WEB-INF/views/empresa/formPesqPost.jsp"/>
                        </c:if>
                        <!-- formulário pesquisar empresas  -->
                        <c:if test="${opcao eq 'empresasReadByCriteria'}">
                            <jsp:include page="/WEB-INF/views/empresa/formPesquisar.jsp"/>
                        </c:if>
                        <!-- Div Favoritos  -->
                        <c:if test="${opcao eq 'favoritosReadById'}">
                            <jsp:include page="/WEB-INF/views/empresa/divFavoritos.jsp"/>
                        </c:if>
                        <!-- formulário Grupo  -->
                        <c:if test="${opcao eq 'grupoCreate' or opcao eq 'editarGrupo'}">
                            <jsp:include page="/WEB-INF/views/empresa/formGrupo.jsp"/>
                        </c:if>
                        <!-- Div Grupos  -->
                        <c:if test="${opcao eq 'grupoReadByCriteria'}">
                            <jsp:include page="/WEB-INF/views/empresa/divGrupos.jsp"/>
                        </c:if>
                        <c:if test="${opcao eq 'empresasReadById'}">
                            <jsp:include page="/WEB-INF/views/empresa/divVisualizarEmpresa.jsp"/>
                        </c:if>
                        <c:if test="${opcao eq 'configuracoes'}">
                            <jsp:include page="/WEB-INF/views/empresa/divConfig.jsp"/>
                        </c:if>
                        <c:if test="${opcao eq 'enderecos'}">
                            <jsp:include page="/WEB-INF/views/empresa/divEnderecos.jsp"/>
                        </c:if>
                        <c:if test="${opcao eq 'novoEndereco' or opcao eq 'editarEndereco'}">
                            <div class="col-md-offset-2 col-md-6">
                                <jsp:include page="/WEB-INF/views/empresa/formEndereco.jsp"/>
                            </div>
                        </c:if>
                        <c:if test="${opcao eq 'telefones'}">
                            <jsp:include page="/WEB-INF/views/empresa/divTelefones.jsp"/>
                        </c:if>
                        <c:if test="${opcao eq 'novoTelefone' or opcao eq 'editarTelefone'}">
                            <div class="col-md-offset-2 col-md-6">
                                <jsp:include page="/WEB-INF/views/empresa/formTelefones.jsp"/>
                            </div>
                        </c:if>
                        <c:if test="${opcao eq 'alterarsenha'}">
                            <div class="col-md-offset-2 col-md-6">
                                <jsp:include page="/WEB-INF/views/empresa/divAlterarSenha.jsp"/>
                            </div>
                        </c:if>
                        <c:if test="${opcao eq 'alterarConta'}">
                            <div class="col-md-offset-2 col-md-6">
                                <jsp:include page="/WEB-INF/views/empresa/formEmpresa.jsp"/>
                            </div>
                        </c:if>
                        <c:if test="${opcao eq 'visualizarGrupo'}">
                            <jsp:include page="/WEB-INF/views/empresa/divVisualizarGrupo.jsp"/>
                        </c:if>
                        <c:if test="${opcao eq 'visualizarGrupoMembros'}">
                            <jsp:include page="/WEB-INF/views/empresa/divMembrosGrupo.jsp"/>
                        </c:if>
                        <c:if test="${opcao eq 'postagensFinalizadas'}">
                            <jsp:include page="/WEB-INF/views/empresa/divPostagem.jsp"/>
                            <c:if test="${empty postagemList}">
                                <div class="col-sm-12 alert alert-success alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <p class="text-center">Não existe nenhuma postagem, finalizada</p>
                                </div>
                                <br/>
                            </c:if>
                        </c:if>
                        <c:if test="${opcao eq 'editarPostagem'}">
                            <jsp:include page="/WEB-INF/views/empresa/formEditarpostagem.jsp"/>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-sm-offset-1 col-md-offset-1"></div>
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
