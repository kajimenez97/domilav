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
import modeloDAO.TipoPrendaDAO;
import modeloVO.TipoPrendaVO;

/**
 *
 * @author Kevin
 */
public class servletTipoPren extends HttpServlet {

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

            TipoPrendaDAO tipoPrendaDAO;
            TipoPrendaVO tipoPrendaVO;
          
            int idTipoPrenda;

            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                case 1: // Listar tipó prenda
                    tipoPrendaDAO = new TipoPrendaDAO();

                    tipoPrendaDAO.listar();

                    request.setAttribute("tipoPrenda", tipoPrendaDAO.getListaTipoPrenda());

                    vista = request.getRequestDispatcher("tipoPrenda/listarPrenda.jsp");
                    break;
                case 2: // Muestra formulario insertar tipo prendo
                    vista = request.getRequestDispatcher("tipoPrenda/registrarPrenda.jsp");
                    break;
                case 3: // Registrar tipo prenda

                    tipoPrendaDAO = new TipoPrendaDAO();
                    tipoPrendaVO = new TipoPrendaVO();

                    // Insertar valores del form
                    tipoPrendaVO.setNombre(request.getParameter("nombre").toString());
                    tipoPrendaVO.setDescripcion(request.getParameter("descripcion").toString());

                    // Inicializar el atributo de la clase tipoPrendaVO
                    tipoPrendaDAO.setTipoPrendaVO(tipoPrendaVO);

                    tipoPrendaDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "tipoPrenda " + tipoPrendaVO.getNombre() + " Ingresada con exito.");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 4: //muestra formulario para actualizar
                    idTipoPrenda = Integer.parseInt(request.getParameter("idTipoPrenda").toString());

                    tipoPrendaDAO = new TipoPrendaDAO();

                    tipoPrendaDAO.setIdTipoPrenda(idTipoPrenda);

                    tipoPrendaDAO.consultaId();

                    request.setAttribute("tipoPrenda", tipoPrendaDAO.getTipoPrendaVO());

                    vista = request.getRequestDispatcher("tipoPrenda/actPrenda.jsp");

                    break;
                case 5: // actuaizar rol

                    tipoPrendaDAO = new TipoPrendaDAO();
                    tipoPrendaVO = new TipoPrendaVO();

                    tipoPrendaVO.setIdTipoPrenda(Integer.parseInt(request.getParameter("idTipoPrenda")));
                    tipoPrendaVO.setNombre(request.getParameter("nombre").toString());
                    tipoPrendaVO.setDescripcion(request.getParameter("descripcion").toString());

                    tipoPrendaDAO.setTipoPrendaVO(tipoPrendaVO);

                    tipoPrendaDAO.ActualizarRegistro();

                    request.setAttribute("mensaje", " Rol " + tipoPrendaVO.getNombre() + " actualizado con exito");
                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");

                    break;

                case 6: // Eliminar rol
                    idTipoPrenda = Integer.parseInt(request.getParameter("idTipoPrenda").toString());

                   tipoPrendaDAO = new TipoPrendaDAO();

                    tipoPrendaDAO.setIdTipoPrenda(idTipoPrenda);

                    tipoPrendaDAO.eliminar();

                    request.setAttribute("mensaje", " tipo Prenda eliminado.");

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
