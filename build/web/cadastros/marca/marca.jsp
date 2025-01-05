<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
<jsp:include page="/topbar.jsp"/>


        <h2>Marcas de Tintas</h2>
        <table id="datatable" class="display">
            <thead>
                <tr>
                    <th align="left">ID</th>
                    <th align="left">Nome</th>
                    <th align="right"></th>
                    <th align="right"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="marca" items="${marcas}">
                    <tr>
                        <td align="left">${marca.idMarca}</td>
                        <td align="left">${marca.nomeMarca}</td>
                        <td align="center">
                               <a href=
            "${pageContext.request.contextPath}/MarcaExcluir?idMarca=${marca.idMarca}">
                            Excluir</a></td>
                            <td align="center">
                                <a href=
            "${pageContext.request.contextPath}/MarcaCarregar?idMarca=${marca.idMarca}">
                            Alterar</a></td>       
                    </tr>       
                </c:forEach>
            </tbody>
        </table>
        
        <div align="center">
            <a href="${pageContext.request.contextPath}/MarcaNovo">Novo</a>
            <a href="index.jsp">Voltar � Pagina Inicial</a>
        </div>
            
<script>
        $(document).ready(function() {
            console.log('entrei ready');
            //Carregamos a datatable
            //$("#datatable").DataTable({});
            $('#datatable').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 at� 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sURL": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "�ltimo"
                    }
                }

            });
        });
</script>

<%@ include file="/footer.jsp" %>