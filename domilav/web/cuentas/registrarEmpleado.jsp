<%-- 
    Document   : registrarEmpleado
    Created on : 6/12/2017, 12:14:43 PM
    Author     : kev_i
--%>

<%@page import="modeloVO.AgenciaVO"%>
<%@page import="modeloVO.PersonaVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="modeloDAO.conexioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
    </head>
    <body>
 <%@include file="../templates/header.jsp" %>
        <h1>REGISTRAR EMPLEADO</h1>

        <%
            ArrayList agencia = (ArrayList) request.getAttribute("lista");
            if (agencia != null) {
                    Iterator iterator = agencia.listIterator();
                    
        %>

        <form action="servletUser" method="POST" class="form-horizontal">
            <div class="form-group">

                <input type="hidden" name="opcion" value="6" >

                <label for="nombreEmpl" class="control-label col-sm-2">Nombres: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="nombreEmpl" name="nombreEmpl" >
                </div>
            </div>

            <!-- Lista desplegable de agencia -->
            <div class="form-group">
                <label for="agencia" class="control-label col-sm-2">Agencia: </label>
                <div class="col-sm-10">
                    <select name="agencia">
                       <%
                       while (iterator.hasNext()) {
                        AgenciaVO agenciaVO = (AgenciaVO) iterator.next();
                       %>
            
                       <option  value="<%=agenciaVO.getIdAgencia()%>"><%=agenciaVO.getNombreAgen()%> </option>
                              <%
                    }
                }
            %>
                    </select>
                </div>
            </div>

      




            <div class="form-group">
                <label for="apellidos" class="control-label col-sm-2">Apellidos: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="apellidos" name="apellidos" >
                </div>
            </div>

            <div class="form-group">
                <label for="documento" class="control-label col-sm-2">Documento: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="documento" name="documento" >
                </div>
            </div>

            <div class="form-group">
                <label for="genero" class="control-label col-sm-2">Genero: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="genero" name="genero" >
                </div>
            </div>

            <div class="form-group">

                <label for="celular" class="control-label col-sm-2">Celular: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="celular" name="celular" >
                </div>
            </div>

            <div class="form-group">
                <label for="fijo" class="control-label col-sm-2">Telefono: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="fijo" name="fijo" >
                </div>
            </div>

            <div class="form-group">

                <label for="direccion" class="control-label col-sm-2">Direccion: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="direccion" name="direccion" >
                </div>
            </div>

            <div class="form-group">
                <label for="correo" class="control-label col-sm-2">Correo: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="correo" name="correo" >
                </div>
            </div>

            <div class="form-group">
                <label for="pass" class="control-label col-sm-2">password: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="pass" name="pass" >
                </div>
            </div>

            <div class="form-group">
                <label for="estado" class="control-label col-sm-2">Estado: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="estado" name="estado" >
                </div>
            </div>



            <input type="submit" value="aceptar" >

        </form>

    </body>
</html>
