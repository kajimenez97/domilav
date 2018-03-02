<%-- 
    Document   : listarBarrio
    Created on : 19/12/2017, 11:38:58 AM
    Author     : Kevin
--%>

<%@page import="modeloVO.BarrioVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BARRIOS</title>
        <script language="javascript">
            function defineOpcionB(opcion, id) {
                document.forms[0].opcion.value = opcion;
                //id es para actualizar y eliminar
                document.forms[0].idBarrio.value = id;
                //si la opción es eliminar (6), debe preguntar si está seguro.
                if (opcion !== 6) {
                    document.forms[0].submit();
                } else if (confirm("¿esta seguro de borrar al id " + id + "?")) {
                    document.forms[0].submit();
                }


            }
        </script>
    </head>
    <body >
        <%@include file="../templates/header.jsp" %>

        <% ArrayList barrio = (ArrayList) request.getAttribute("barrio");
        %>

        <form method="POST" action="servletBarrio">
            <input type="hidden" name="opcion" value="">
            <input type="hidden" name="idBarrio" value="">

            <table class="table table-striped" >
                <thead >
                    <tr>
                        <th >ID</th>
                        <th >NOMBRE</th>
                        <th >DESCRIPCON</th>

                    </tr>
                </thead>
                <!--Se recorre el listado para mostrarlo en la tabla -->
                <tbody>
                    <%
                        if (barrio != null) {
                            Iterator iterator = barrio.listIterator();
                            while (iterator.hasNext()) {
                                BarrioVO barrioVO = (BarrioVO) iterator.next();
                    %>
                    <tr style="border: 1px solid;">

                        <th ><%=barrioVO.getIdBarrio()%></td>
                        <td><%=barrioVO.getNombre()%></td>
                        <td><%=barrioVO.getDescripcion()%></td>

                        <td>
                            <input type="button" name="editar" value="Editar" onclick="defineOpcionB(4,<%=barrioVO.getIdBarrio()%>)">
                            <input type="button" name="borrar" value="borrar" onclick="defineOpcionB(6,<%=barrioVO.getIdBarrio()%>)">   
                        </td>
                    </tr>
                    <%
                            } // cierre while    
                        } // cierre if
%>

                </tbody>
            </table>
        </form>

        <form method="POST" action="servletBarrio">
            <input type="hidden" name="opcion" value="2">
            <input type="submit" name="insertar"
                   value="Insertar Barrio" >
        </form>
        <%@include file="../templates/footer.jsp" %>
    </body>
</html>
