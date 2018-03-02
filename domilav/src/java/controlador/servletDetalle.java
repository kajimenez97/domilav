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
import modeloDAO.DetallePrendaDAO;
import modeloDAO.SolicitudDAO;
import modeloDAO.TipoPrendaDAO;
import modeloVO.DetallePrendaVO;
import modeloVO.SolicitudVO;

/**
 *
 * @author Kevin
 */
public class servletDetalle extends HttpServlet {

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

            SolicitudDAO solicitudDAO;
            SolicitudVO solicitudVO;
            TipoPrendaDAO tipoPrendaDAO;
            DetallePrendaDAO detallePrendaDAO;
            DetallePrendaVO detallePrendaVO;

            int idDetalle;

            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                case 1:
                    break;

                case 2: // Listar detalle prenda

                    tipoPrendaDAO = new TipoPrendaDAO();
                    tipoPrendaDAO.listar();
                    request.setAttribute("prenda", tipoPrendaDAO.getListaTipoPrenda());

                    solicitudDAO = new SolicitudDAO();
                    idDetalle = Integer.parseInt(request.getParameter("idSolicitud").toString());

                    solicitudDAO.setIdSolicitud(idDetalle);
                    solicitudDAO.consultaId();
                    
                    request.setAttribute("solicitud", solicitudDAO.getSolicitudVO());
                   

                    vista = request.getRequestDispatcher("solicitud/detalleSolicitud.jsp");

                    break;
                case 3:

                    detallePrendaDAO = new DetallePrendaDAO();
                    detallePrendaVO = new DetallePrendaVO();

                //    int solicitud = 1;
                    // Insertar valores del form
                    detallePrendaVO.setIdSolicitud(Integer.parseInt(request.getParameter("idSolicitud")));
                    detallePrendaVO.setIdTipoPrenda(Integer.parseInt(request.getParameter("prenda")));

                    detallePrendaVO.setColor(request.getParameter("color").toString());
                    detallePrendaVO.setMaterial(request.getParameter("material").toString());
                    detallePrendaVO.setDescripcion(request.getParameter("descripcion").toString());

                    // Inicializar el atributo de la clase aprendizVO
                    detallePrendaDAO.setDetallePrendaVO(detallePrendaVO);

                    detallePrendaDAO.AgregarRegistro();

                    //request.setAttribute("mensaje", "Solicitud" + detallePrendaVO.getIdTipoPrenda() + " Ingresada con exito.");

                   // request.setAttribute("opcion", "1");
                    vista = request.getRequestDispatcher("solicitud/detalleSolicitud.jsp");

                    break;

                case 4:
                    break;
                case 5:
                    break;

                case 6:
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
