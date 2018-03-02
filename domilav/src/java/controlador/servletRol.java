
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeloDAO.RolDAO;
import modeloVO.RolVO;

/**
 *
 * @author Kevin
 */
public class servletRol extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            RequestDispatcher vista = null;

            RolDAO rolDAO;
            RolVO rolVO;
          
            int idRol;

            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                case 1:{ // Listar rol
                    rolDAO = new RolDAO();

                    rolDAO.listar();

                    request.setAttribute("rol", rolDAO.getListaRol());

                    vista = request.getRequestDispatcher("roles/listarRol.jsp");
                }
                    break;
                
                case 2:{ // Muestra formulario insertar rol
                    vista = request.getRequestDispatcher("roles/ingresarRol.jsp");
                    break;
                }
                
                case 3:{ // Registrar rol

                    rolDAO = new RolDAO();
                    rolVO = new RolVO();

                    // Insertar valores del form
                    rolVO.setNombre(request.getParameter("nombre"));
                    rolVO.setDescripcion(request.getParameter("descripcion"));

                    // Inicializar el atributo de la clase aprendizVO
                    rolDAO.setRolVO(rolVO);

                    rolDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "Agencia " + rolVO.getNombre() + " Ingresada con exito.");

            
                    vista = request.getRequestDispatcher("ingresarRol.jsp");
                    break;
                }
                case 4: //muestra formulario para actualizar
                   /* idRol = Integer.parseInt(request.getParameter("idRol").toString());

                    rolDAO = new RolDAO();

                    rolDAO.setIdRol(idRol);

                    rolDAO.consultaId();

                    request.setAttribute("rolVO", rolDAO.getRolVO());

                    vista = request.getRequestDispatcher("roles/actualizarRol.jsp");*/

                    break;
                case 5:{ // actuaizar rol

                    rolDAO = new RolDAO();
                    rolVO = new RolVO();

                    rolVO.setIdRol(Integer.parseInt(request.getParameter("idRol")));
                    rolVO.setNombre(request.getParameter("nombre").toString());
                    rolVO.setDescripcion(request.getParameter("descripcion").toString());

                    rolDAO.setRolVO(rolVO);

                    rolDAO.ActualizarRegistro();

                    request.setAttribute("mensaje", " Rol " + rolVO.getNombre() + " actualizado con exito");
                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");

                    
                    
                    break;
                }

                case 6:{ // Eliminar rol
                    idRol = Integer.parseInt(request.getParameter("idRol").toString());

                    rolDAO = new RolDAO();

                    rolDAO.setIdRol(idRol);

                    rolDAO.eliminar();

                    request.setAttribute("mensaje", " Rol eliminado.");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");

                    break;
                }
                default:
                    request.setAttribute("mensaje", "Esta opcion no esta");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
            }

            vista.forward(request, response);

        } catch (NumberFormatException | IOException e) {
            System.out.println("Error aqui: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
