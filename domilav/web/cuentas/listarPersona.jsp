<%-- 
    Document   : listarPersona
    Created on : 8/12/2017, 12:05:14 PM
    Author     : kev_i
--%>

<%@page import="modeloVO.PersonaVO"%>
<%@page import="modeloVO.RolVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EMPLEADO</title>
  </head>
  <body>
      <%@include file="../templates/header.jsp" %>
  <% ArrayList persona = (ArrayList) request.getAttribute("persona");
               
  // ArrayList rol = (ArrayList) request.getAttribute("rol");
                %>

                <form method="POST" action="servletUser">
                    <input type="hidden" name="opcion" value="">
                    <input type="hidden" name="idPersona" value="">
                    
                    <table class="table table-striped" >
                        <thead >
                            <tr>
                                <th >ID</th>
                                <th >rol</th>
                                <th >nombres</th>
                                <th>apellidos</th>

                            </tr>
                        </thead>
                        <!--Se recorre el listado para mostrarlo en la tabla -->
                        <tbody>
                            <%
                                if (persona != null) {
                                    Iterator iterator = persona.listIterator();
                                 //   Iterator iteratorR = rol.listIterator();
                                    while (iterator.hasNext()) {
                                        PersonaVO personaVO = (PersonaVO) iterator.next();
                                     //   RolVO rolVO = (RolVO) iteratorR.next();
                            %>
                            <tr style="border: 1px solid;">

                                <th><%=personaVO.getIdPersona()%></td>
                                <td><%=personaVO.getNombreRol()%></td>
                                <td><%=personaVO.getNombres()%></td>
                                <td><%=personaVO.getApellidos()%></td>

                                <td>
                                    <input type="button" name="editar" value="Editar" >
                                    <input type="button" name="borrar" value="borrar" >   
                                </td>
                            </tr>
                            <%
                                    } // cierre while    
                                } // cierre if
%>
                      
                        </tbody>
                    </table>
                </form>
                      <%@include file="../templates/footer.jsp" %>
  </body>
</html>
