<%-- 
    Document   : listarAgencia
    Created on : 30/11/2017, 05:56:54 PM
    Author     : kev_i
--%>

<%@page import="modeloVO.AgenciaVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AGENCIAS</title>
    <script src="../js/opcion.js" type="text/javascript"></script>
  </head>
  <body>
    <%@include file="../templates/header.jsp" %>
    <% ArrayList agencia = (ArrayList) request.getAttribute("agencia");
    %>
    
    

   <!-- <form method="POST" action="servletAgencia">
      <input type="hidden" name="opcion" value="">
      <input type="hidden" name="idAgencia" value="">-->
      


      <table class="table table-striped" >
        <thead >
          <tr>
            <th >ID</th>
            <th >NOMBRE</th>
            <th >DIRECCION</th>
            <th >TELEFONO</th>
            <th >FIJO</th>
            <th >CORREO</th>
          </tr>
        </thead>
        <!--Se recorre el listado para mostrarlo en la tabla -->
        <tbody>
          <%
            if (agencia != null) {
              Iterator iterator = agencia.listIterator();
              while (iterator.hasNext()) {
                AgenciaVO agenciaVO = (AgenciaVO) iterator.next();
          %>
          <tr style="border: 1px solid;">

            <th ><%=agenciaVO.getIdAgencia()%></td>
            <td><%=agenciaVO.getNombreAgen()%></td>
            <td><%=agenciaVO.getDireccionAgen()%></td>
            <td><%=agenciaVO.getTelUno()%></td>
            <td><%=agenciaVO.getTelDos()%></td>
            <td><%=agenciaVO.getCorreoAgen()%></td>
            <td>
              <form method="POST" action="servletAgencia">
      <input type="hidden" name="opcion" value="4">
      <input type="hidden" name="idAgencia" value="<%=agenciaVO.getIdAgencia()%>">
      <input type="submit" name="actAgencia" value="actualizar">
      
              </form>
              
             <form method="POST" action="servletAgencia">
      <input type="hidden" name="opcion" value="6">
      <input type="hidden" name="idAgencia" value="<%=agenciaVO.getIdAgencia()%>">
      <input type="submit" name="elmAgencia" value="Eliminar">
      <form>
         
            </td>
          </tr>
          <%
              } // cierre while    
            } // cierre if
%>

        </tbody>
      </table>
              <!--   </form>-->

  <form action="servletAgencia" method="POST">
                    <input type="hidden" name="opcion" value="2">
                    <input type="submit" name="agencia" value="Ingresar Agencia">
                </form>








<%@include file="../templates/footer.jsp" %>
  </body>

</html>
