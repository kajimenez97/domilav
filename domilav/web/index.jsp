<%-- 
    Document   : index
    Created on : 30/11/2017, 05:16:01 PM
    Author     : kev_i
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PERMATEX</title>
       
    </head>
    <body>

        <%@include file="templates/header.jsp" %>
        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
                 
               <form action="servletUser" method="POST">
                    <input type="hidden" name="opcion" value="1">
                    <input type="submit" name="login" value="Login">
                </form>
               
               <form action="servletSolicitud" method="POST">
                    <input type="hidden" name="opcion" value="2">
                    <input type="submit" name="solicitud" value="registrar solicitud">
                </form>
               <form action="servletSolicitud" method="POST">
                    <input type="hidden" name="opcion" value="9">
                    <input type="submit" name="solicitud" value="Insertar solicitud">
                </form>
               
              
               <form action="servletUser" method="POST">
                    <input type="hidden" name="opcion" value="4">
                    <input type="submit" name="persona" value="listar persona">
                </form>
               <form action="servletUser" method="POST">
                    <input type="hidden" name="opcion" value="5">
                    <input type="submit" name="persona" value="ingresar persona">
                </form>
               <form action="servletSolicitud" method="POST">
                    <input type="hidden" name="opcion" value="1">
                    <input type="submit" name="persona" value="Listar Solicitud">
                </form>
               <form action="servletDetalle" method="POST">
                    <input type="hidden" name="opcion" value="2">
                    <input type="submit" name="persona" value="Insertar detalle">
                </form>
               
               
               <form action="servletServicio" method="POST">
                                    <input type="hidden" name="opcion" value="1">
                                    <input type="submit" name="tipoServ" value="Gestion Servicio">
                                </form>
               
             
            
              
               
               <a href="listarRol.jsp">roles</a>
               
            </div>
            <div class="col-sm-1"></div>
        </div>

        <%@include file="templates/footer.jsp" %>
    </body >




</html>
