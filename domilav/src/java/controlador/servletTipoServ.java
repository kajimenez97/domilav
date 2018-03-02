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
import modeloDAO.TipoServicioDAO;
import modeloVO.TipoServicioVO;

/**
 *
 * @author Kevin
 */
public class servletTipoServ extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletTipoServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletTipoServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response); try {

            RequestDispatcher vista = null;

            TipoServicioDAO tipoServicioDAO;

            TipoServicioVO tipoServicioVO;

            int idTipoServ;

            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                case 1: // Listar tipo Servicio
                    tipoServicioDAO = new TipoServicioDAO();

                    tipoServicioDAO.listar();

                    request.setAttribute("servicio", tipoServicioDAO.getLista());
                    request.setAttribute("Actualizar", false);

                    vista = request.getRequestDispatcher("servicios/listarServicio.jsp");
                    break;
                case 2: // Vista formulario de ingreso de tipo Servicio
                    vista = request.getRequestDispatcher("servicios/ingresarServicio.jsp");
                    break;
                case 3: // Ingresar tipo Servicio
                    tipoServicioDAO = new TipoServicioDAO();
                    tipoServicioVO = new TipoServicioVO();

                    
                    // Insertar valores del form
                    tipoServicioVO.setNombre(request.getParameter("nombreServ").toString());
                    tipoServicioVO.setTiempo(request.getParameter("tiempo").toString());
                    tipoServicioVO.setPrecio(Float.parseFloat(request.getParameter("precio")));
                    tipoServicioVO.setDescripcion(request.getParameter("descripcion").toString());
                  
                    // Inicializar el atributo de la clase aprendizVO
                    tipoServicioDAO.setTipoServicioVO(tipoServicioVO);

                    tipoServicioDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "tipo Servicio " + tipoServicioVO.getNombre() + " Ingresada con exito.");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 4: // vista actuaizar tipo Servicio
                    idTipoServ = Integer.parseInt(request.getParameter("idServicio").toString());

                    tipoServicioDAO = new TipoServicioDAO();

                    tipoServicioDAO.setIdTipoServicio(idTipoServ);

                    tipoServicioDAO.consultaId();

                    request.setAttribute("tipoServicioVO", tipoServicioDAO.getTipoServicioVO());

                    vista = request.getRequestDispatcher("servicios/actServicio.jsp");
                    break;
                case 5: // Actuaizar agencia
                    tipoServicioVO = new TipoServicioVO();

                    tipoServicioDAO = new TipoServicioDAO();
                    
                    tipoServicioVO.setNombre(request.getParameter("nombreServ").toString());
                    tipoServicioVO.setTiempo(request.getParameter("tiempo").toString());
                    tipoServicioVO.setPrecio(Float.parseFloat(request.getParameter("precio")));
                    tipoServicioVO.setDescripcion(request.getParameter("descripcion").toString());
               
                    tipoServicioDAO.setTipoServicioVO(tipoServicioVO);

                    tipoServicioDAO.ActualizarRegistro();

                    request.setAttribute("mensaje", " tipo Servicio " + tipoServicioVO.getNombre() + " actualizado con exito");
                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 6: // Eliminar Agencia
                    idTipoServ = Integer.parseInt(request.getParameter("idServicio").toString());

                    tipoServicioDAO = new TipoServicioDAO();

                    tipoServicioDAO.setIdTipoServicio(idTipoServ);

                    tipoServicioDAO.eliminar();

                    request.setAttribute("mensaje", " tipo Servicio eliminada");

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
