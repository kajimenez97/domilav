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
import modeloDAO.AgenciaDAO;
import modeloVO.AgenciaVO;

/**
 *
 * @author Kevin
 */
public class servletAgencia extends HttpServlet {

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

            AgenciaDAO agenciaDAO;

            AgenciaVO agenciaVO;

            int idAgencia;

            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                case 1: // Listar agencia
                    agenciaDAO = new AgenciaDAO();

                    agenciaDAO.listar();

                    request.setAttribute("agencia", agenciaDAO.getLista());
                    request.setAttribute("Actualizar", false);

                    vista = request.getRequestDispatcher("agencia/listarAgencia.jsp");
                    break;
                case 2: // Vista formulario de ingreso de agencia
                    vista = request.getRequestDispatcher("agencia/ingresarAgencia.jsp");
                    break;
                case 3: // Ingresar agencia
                    agenciaDAO = new AgenciaDAO();
                    agenciaVO = new AgenciaVO();

                    // Insertar valores del form
                    agenciaVO.setNombreAgen(request.getParameter("nombreAgen").toString());
                    agenciaVO.setDireccionAgen(request.getParameter("direccion").toString());
                    agenciaVO.setTelUno(Integer.parseInt(request.getParameter("telefono")));
                    agenciaVO.setTelDos(Integer.parseInt(request.getParameter("telefonoDos").toString()));
                    agenciaVO.setCorreoAgen(request.getParameter("correo").toString());

                    // Inicializar el atributo de la clase aprendizVO
                    agenciaDAO.setAgenciaVO(agenciaVO);

                    agenciaDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "Agencia " + agenciaVO.getNombreAgen() + " Ingresada con exito.");

                    request.setAttribute("opcion", "1");

                    agenciaDAO.listar();
                    
                    vista = request.getRequestDispatcher("agencia/ingresarAgencia.jsp");
                    break;
                case 4: // vista actuaizar agencia
                    idAgencia = Integer.parseInt(request.getParameter("idAgencia").toString());

                    agenciaDAO = new AgenciaDAO();

                    agenciaDAO.setIdAgencia(idAgencia);

                    agenciaDAO.consultaId();

                    request.setAttribute("agenciaVO", agenciaDAO.getAgenciaVO());

                    vista = request.getRequestDispatcher("agencia/actualizarAgencia.jsp");
                    break;
                case 5: // Actuaizar agencia
                    agenciaVO = new AgenciaVO();

                    agenciaDAO = new AgenciaDAO();

                    agenciaVO.setIdAgencia(Integer.parseInt(request.getParameter("idAgencia").toString()));
                    agenciaVO.setNombreAgen(request.getParameter("nombreAgen").toString());
                    agenciaVO.setDireccionAgen(request.getParameter("direccion").toString());
                    agenciaVO.setTelUno(Integer.parseInt(request.getParameter("telefono").toString()));
                    agenciaVO.setTelDos(Integer.parseInt(request.getParameter("telefonoDos").toString()));
                    agenciaVO.setCorreoAgen(request.getParameter("correo").toString());

                    agenciaDAO.setAgenciaVO(agenciaVO);

                    agenciaDAO.ActualizarRegistro();

                    request.setAttribute("mensaje", " Agencia " + agenciaVO.getNombreAgen() + " actualizado con exito");
                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 6: // Eliminar Agencia
                    idAgencia = Integer.parseInt(request.getParameter("idAgencia").toString());

                    agenciaDAO = new AgenciaDAO();

                    agenciaDAO.setIdAgencia(idAgencia);

                    agenciaDAO.eliminar();

                    request.setAttribute("mensaje", " Agencia eliminada");

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
