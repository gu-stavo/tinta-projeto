<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<jsp:include page="/topbar.jsp"/>


        <h2>Produtos</h2>
        <div class="col-8 panel-body">
            <table id="datatable" class="table table-striped table-bordered basic-datatable">
                <thead> 
                    <tr>
                        <th align="left">ID</th>
                        <th align="left">Produto</th>
                        <th align="left">Preco</th>
                        <th align="left">Cor</th>
                        <th align="left">Marca</th>
                        <th align="left">Composicao</th>
                        <th align="left">Tipo de Pagamento</th>
                        <th align="right">Excluir(Ativar ou Inativar)</th>
                        <th align="right">Alterar Cadastro</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="produto" items="${produtos}"> 
                        <tr>
                            <td align="left">${produto.idProduto}</td>
                            <td align="left">${produto.nomeProduto}</td>
                            <td align="left">${produto.preco}</td>
                            <td align="left">${produto.cor.nomeCor}</td>
                            <td align="left">${produto.marca.nomeMarca}</td>
                            <td align="left">${produto.composicao.nomeComposicao}</td>
                            <td align="left">${produto.tipoPagamento.nomeTipo}</td>
                            <td align="center"> 
                                <a href="${pageContext.request.contextPath}/ProdutoExcluir?idProduto=${produto.idProduto}"> 
                                    <button class="btn btn-group-lg
                                            <c:out value="${produto.situacao == 'A' ? 'btn-danger': 'btn-success'}"/>">
                                        <c:out value="${produto.situacao == 'A' ? 'Inativar': 'Ativar'}"/>
                                    </button>
                                </a>
                            </td>
                            <td align="center">
                                <a href="${pageContext.request.contextPath}/ProdutoCarregar?idProduto=${produto.idProduto}"> 
                                    <button class="btn btn-group-lg btn-success"/>Alterar</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
        <div align="center"> 
            <a href="${pageContext.request.contextPath}/ProdutoNovo">Novo</a>
            <a href="index.jsp">Voltar à Página Inicial</a>
        </div>

<script>
    $(document).ready(function () {
        console.log('entrei ready');
        //Carregamos a datatable
        //$("datatable").DataTble({});
        $('#datatable').DataTable({
            "oLanguage": {
                "sProcessing": "Processando...",
                "sLenghtMenu": "Mostrar _MENU_ registros",
                "sZeroRecords": "Nenhum registro encontrado",
                "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                "sInfoFiltered": "",
                "sInfoPostFix": "",
                "sSearch": "Buscar:",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "Primeiro",
                    "sPrevious": "Anterior",
                    "sNext": "Seguinte",
                    "sLast": "Último"
                }
            }
        });
    });
</script>



<%@include file="/footer.jsp" %>