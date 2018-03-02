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
import modeloDAO.BarrioDAO;
import modeloVO.BarrioVO;

/**
 *
 * @author Kevin
 */
public class servletBarrio extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {

        }
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

           BarrioVO barrioVO;
           BarrioDAO barrioDAO;

            int idBarrio;

            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                  case 1: // Vista listar barrio
                    barrioDAO = new BarrioDAO();
                    barrioDAO.listar();
                    request.setAttribute("barrio", barrioDAO.getListaBarrio());
                   
                    vista = request.getRequestDispatcher("barrio/listarBarrio.jsp");
                    break;
                    
                     case 2: // vista insertar Barrio
                    vista = request.getRequestDispatcher("barrio/ingresarBarrio.jsp");
                    break;
                case 3: // Insertar Barrio
                    barrioDAO = new BarrioDAO();
                    barrioVO = new BarrioVO();

                    // Insertar valores del form
                    barrioVO.setNombre(request.getParameter("nombre").toString());
                    barrioVO.setDescripcion(request.getParameter("descripcion").toString());

                    // Inicializar el atributo de la clase aprendizVO
                    barrioDAO.setBarrioVO(barrioVO);

                    barrioDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "Barrio" + barrioVO.getNombre() + " Ingresada con exito.");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 4:    // Vista Actualizar Barrio
                    idBarrio = Integer.parseInt(request.getParameter("idBarrio").toString());

                    barrioDAO = new BarrioDAO();

                    barrioDAO.setIdBarrio(idBarrio);

                    barrioDAO.consultaId();

                    request.setAttribute("barrioVO", barrioDAO.getBarrioVO());

                    vista = request.getRequestDispatcher("barrio/actualizarBarrio.jsp");
                    break;
                case 5: // Actualizar Barrio
                    barrioVO = new BarrioVO();

                    barrioDAO = new BarrioDAO();

                    barrioVO.setIdBarrio(Integer.parseInt(request.getParameter("idBarrio").toString()));
                    barrioVO.setNombre(request.getParameter("nombre").toString());
                    barrioVO.setDescripcion(request.getParameter("descripcion").toString());

                    barrioDAO.setBarrioVO(barrioVO);

                    barrioDAO.ActualizarRegistro();

                    request.setAttribute("mensaje", " Barrio" + barrioVO.getNombre() + " actualizado con exito");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 6: // Eliminar Barrio
                    idBarrio = Integer.parseInt(request.getParameter("idBarrio").toString());

                    barrioDAO = new BarrioDAO();

                    barrioDAO.setIdBarrio(idBarrio);

                    barrioDAO.eliminar();

                    request.setAttribute("mensaje", " Barrio eliminado");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
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
