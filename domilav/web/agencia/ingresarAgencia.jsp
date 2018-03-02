<%-- 
    Document   : ingresarAgencia
    Created on : 30/11/2017, 05:56:32 PM
    Author     : kev_i
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>INGREASAR</title>
  
  </head>
  <body>
    <%@include file="../templates/header.jsp" %>
    <form id="forAgencia"  action="servletAgencia" method="POST" class="form-horizontal" >
      <div class="form-group">
        <input type="hidden" name="opcion" value="3">
        <label for="nombreAgen" class="control-label col-sm-2">Nombre: </label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="nombreAgen" name="nombreAgen"  required >
        </div>
      </div>

      <div class="form-group">
        <label for="direccion" class="control-label col-sm-2">Direccion: </label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="direccion" name="direccion" required >

        </div>
      </div>
      <div class="form-group">           
        <label for="telefono" class="control-label col-sm-2">Telefono: </label>
        <div class="col-sm-10">
          <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="">
        </div>
      </div>
      <div class="form-group">
        <label for="telefonoDos" class="control-label col-sm-2">Fijo: </label>
        <div class="col-sm-10">
          <input type="number" class="form-control" id="telefonoDos" name="telefonoDos" required >
        </div>
      </div>
      <div class="form-group">       

        <label for="correo" class="control-label col-sm-2">correo: </label>
        <div class="col-sm-10">
          <input type="email" class="form-control" id="correo" name="correo" required >

        </div>
      </div>   
      <div class="form-group">    
        <div class="col-sm-offset-2 col-sm-10">

          <input type="submit" id="enviar" name="guardar" value="confirmar"  />

          <input type="button" id="inicio" name="inicio" value="agencia" onclick="defineOpcion(1)" />
        </div>
      </div>
    </form>
    
    <% String resp = (String) request.getAttribute("mensaje");


    if(resp != null){  %>
    
    <div class="alert alert-success">
  <strong>Success!</strong> <%=resp%>
</div>
    
    <%
    }
    %>
    
    
     <%@include file="../templates/footer.jsp" %>
  </body>
</html>
