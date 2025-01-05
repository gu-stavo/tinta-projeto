<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<jsp:include page="/topbar.jsp"/>


<form name="cadastrarcor" action="CorCadastrar"
        method="POST">
    
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro de Cor</th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>ID: </td>
            <td><input type="text" name="idcor" id="idcor" value="${cor.idCor}" readonly="readonly" /></td></tr>
            <tr><td>Nome: </td>
            <td><input type="text" name="nomecor" id="nomecor" value="${cor.nomeCor}"
                       size="50" maxlength="50" /></td></tr>
            <tr><td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar" />
                    <input type="reset" name="limpar" id="limpar" value="Limpar" />
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar � P�gina Inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
</form>
<%@include file="/footer.jsp"%>
    
