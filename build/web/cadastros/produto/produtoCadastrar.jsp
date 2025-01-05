<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<jsp:include page="/topbar.jsp"/>


<form name="cadastrarproduto" action="ProdutoCadastrar" method="POST">
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro de Produto</th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>ID: </td>
            <td><input type="text" name="idproduto" id="idproduto" value="${produto.idProduto}" readonly="readonly" /></td></tr>
            <tr><td>Nome: </td>
            <td><input type="text" name="nomeproduto" id="nomeproduto" value="${produto.nomeProduto}"
                           size="50" maxlength="50" /></td></tr>
            <tr><td>Preço: </td>
            <td><input type="text" name="preco" id="preco" value="${produto.preco}"
                           size="50" maxlength="50" /></td></tr>
            <tr>
                <td>Cor: </td>
                <td>
                <select name="idcor" id="idcor">
                    <option value="">Selecione</option>
                    <c:forEach var="cor" items="${cores}">
                        <option value="${cor.idCor}"
                            ${produto.cor.idCor == cor.idCor ? "selected" : ""}>
                            ${cor.nomeCor}
                        </option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td>Marca: </td>
                <td>
                <select name="idmarca" id="idmarca">
                    <option value="">Selecione</option>
                    <c:forEach var="marca" items="${marcas}">
                        <option value="${marca.idMarca}"
                            ${produto.marca.idMarca == marca.idMarca ? "selected" : ""}>
                            ${marca.nomeMarca}
                        </option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td>Composição da Tinta: </td>
                <td>
                <select name="idcomposicao" id="idcomposicao">
                    <option value="">Selecione</option>
                    <c:forEach var="composicao" items="${composicoes}">
                        <option value="${composicao.idComposicao}"
                            ${produto.composicao.idComposicao == composicao.idComposicao ? "selected" : ""}>
                            ${composicao.nomeComposicao}
                        </option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr>
                <td>Tipo de Pagamento Aceito: </td>
                <td>
                <select name="idtipo" id="idtipo">
                    <option value="">Selecione</option>
                    <c:forEach var="tipo" items="${tipos}">
                            <option value="${tipo.idTipo}"
                            ${produto.tipoPagamento.idTipo == tipo.idTipo ? "selected" : ""}>
                            ${tipo.nomeTipo}
                        </option>
                    </c:forEach>
                </select>
                </td>
            </tr>
            <tr><td>
                    <input type="hidden" name="situacao" id="situacao" value="${produto.situacao}" readonly="readonly" />
                </td></tr>
            <tr><td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar" />
                    <input type="reset" name="limpar" id="limpar" value="Limpar" />
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar à Pagina Inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
</form>
<%@include file="/footer.jsp" %>