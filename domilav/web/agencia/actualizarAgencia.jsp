<%-- 
    Document   : actualizarAgencia
    Created on : 30/11/2017, 05:57:12 PM
    Author     : kev_i
--%>

<%@page import="modeloVO.AgenciaVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Actualizar</title>
  </head>
  <body>
    <%     AgenciaVO agenciaVO = (AgenciaVO) request.getAttribute("agenciaVO");
    %>
<%@include file="../templates/header.jsp" %>

    <form id="forAgencia"  action="servletAgencia" method="POST" class="form-horizontal" >
      <div class="form-group">
        <input type="hidden" name="opcion" value="5">
        <input type="hidden" name="idAgencia" id="idAgencia" value="<%=agenciaVO.getIdAgencia()%>" > 
        <label for="nombreAgen" class="control-label col-sm-2">Nombre: </label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="nombreAgen" name="nombreAgen"   value="<%=agenciaVO.getNombreAgen()%>" required >
        </div>
      </div>

      <div class="form-group">
        <label for="direccion" class="control-label col-sm-2">Direccion: </label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="direccion" name="direccion" value="<%=agenciaVO.getDireccionAgen()%>" required >

        </div>
      </div>
      <div class="form-group">           
        <label for="telefono" class="control-label col-sm-2">Telefono: </label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="telefono" name="telefono" value="<%=agenciaVO.getTelUno()%>">
        </div>
      </div>
      <div class="form-group">
        <label for="telefonoDos" class="control-label col-sm-2">Telefono: </label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="telefonoDos" name="telefonoDos" value="<%=agenciaVO.getTelDos()%>" required >
        </div>
      </div>
      <div class="form-group">       

        <label for="correo" class="control-label col-sm-2">correo: </label>
        <div class="col-sm-10">
          <input type="email" class="form-control" id="correo" name="correo" value="<%=agenciaVO.getCorreoAgen()%>" required >

        </div>
      </div>   
      <div class="form-group">    
        <div class="col-sm-offset-2 col-sm-10">
          <input type="submit" name="confirmar" value="confirmar">
          <input type="button" id="opcion" name="volver" value="listar" onclick="defineOpcion(7)">
        </div>
      </div>
    </form>
          <%@include file="../templates/footer.jsp" %>
  </body>
</html>
