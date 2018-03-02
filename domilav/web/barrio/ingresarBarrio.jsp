<%-- 
    Document   : ingresarBarrio
    Created on : 19/12/2017, 11:39:12 AM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INGRESAR</title>
        
    </head>
    <body >
         <%@include file="../templates/header.jsp" %>

                <form id="forRol"  action="servletBarrio" method="POST" class="form-horizontal" >
                    <div class="form-group">
                        <input type="hidden" name="opcion" value="3">
                        <label for="nombre" class="control-label col-sm-2">Nombre: </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="nombre" name="nombre"  required >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="descripcion" class="control-label col-sm-2">Descripcion: </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="descripcion" name="descripcion" required >

                        </div>
                    </div>
                    <input type="submit" value="enviar" name="guardar">
                </form>

            <%@include file="../templates/footer.jsp" %>
    </body>
</html>
