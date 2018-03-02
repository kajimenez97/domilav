<%-- 
    Document   : actualizarBarrio
    Created on : 19/12/2017, 11:39:51 AM
    Author     : Kevin
--%>


<%@page import="modeloVO.BarrioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BARRIO</title>
    </head>
    <body>
        <%@include file="../templates/header.jsp" %>


        <%     BarrioVO barrioVO = (BarrioVO) request.getAttribute("barrioVO");
        %>

        <form id="forRol"  action="servletBarrio" method="POST" class="form-horizontal" >
            <div class="form-group">
                <input type="hidden" name="opcion" value="5">
                <input type="hidden" name="idBarrio" id="idBarrio" value="<%=barrioVO.getIdBarrio()%>" > 
                <label for="nombre" class="control-label col-sm-2">Nombre: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="nombre" name="nombre" value="<%=barrioVO.getNombre()%>" required >
                </div>
            </div>

            <div class="form-group">
                <label for="descripcion" class="control-label col-sm-2">Descripcion: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="<%=barrioVO.getDescripcion()%>" required >

                </div>
            </div>
            <input type="submit" value="enviar" name="guardar">
        </form>
        <%@include file="../templates/footer.jsp" %>
    </body>
</html>
