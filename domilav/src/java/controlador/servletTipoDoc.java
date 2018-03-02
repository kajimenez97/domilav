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
import modeloDAO.TipoDocDAO;
import modeloVO.TipoDocVO;

/**
 *
 * @author Kevin
 */
public class servletTipoDoc extends HttpServlet {

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

           TipoDocVO tipoDocVO;
           TipoDocDAO tipoDocDAO;
            
           int idTipoDoc;
           
            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                
                  case 1:  // Listar tipo Doc      
                    tipoDocDAO = new TipoDocDAO();

                    tipoDocDAO.listar();

                    request.setAttribute("tipoDoc", tipoDocDAO.getListaTipoDoc());

                    vista = request.getRequestDispatcher("tipoDoc/listarTipoDoc.jsp");
                    break;
                case 2:     // Vista ingresar tipoDoc   
                    vista = request.getRequestDispatcher("tipoDoc/ingresarTipoDoc.jsp");
                    break;
                case 3:       // Ingresar tipoDoc 
                    tipoDocDAO = new TipoDocDAO();
                    tipoDocVO = new TipoDocVO();

                    // Insertar valores del form
                    tipoDocVO.setNombre(request.getParameter("nombre").toString());
                    tipoDocVO.setDescripcion(request.getParameter("descripcion").toString());

                    // Inicializar el atributo de la clase aprendizVO
                    tipoDocDAO.setTipoDocVO(tipoDocVO);

                    tipoDocDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "Tipo de documento " + tipoDocVO.getNombre() + " Ingresado con exito.");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 4:     // vista Actualizar tipoDoc   
                    idTipoDoc = Integer.parseInt(request.getParameter("idTipoDoc").toString());

                    tipoDocDAO = new TipoDocDAO();

                    tipoDocDAO.setIdTipoDoc(idTipoDoc);

                    tipoDocDAO.consultaId();

                    request.setAttribute("tipoDocVO", tipoDocDAO.getTipoDocVO());

                    vista = request.getRequestDispatcher("tipoDoc/actualizarTipoDoc.jsp");
                    //vista = request.getRequestDispatcher("index.jsp");
                    break;
                case 5:        //Actualizar tipod doc
                    tipoDocDAO = new TipoDocDAO();
                    tipoDocVO = new TipoDocVO();

                    tipoDocVO.setIdTipoDoc(Integer.parseInt(request.getParameter("idTipoDoc")));
                    tipoDocVO.setNombre(request.getParameter("nombre").toString());
                    tipoDocVO.setDescripcion(request.getParameter("descripcion").toString());

                    tipoDocDAO.setTipoDocVO(tipoDocVO);

                    tipoDocDAO.ActualizarRegistro();

                    request.setAttribute("mensaje", " Tipo de documento " + tipoDocVO.getNombre() + " actualizado con exito");
                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;
                case 6:        // Eliminar tipoDoc
                     idTipoDoc = Integer.parseInt(request.getParameter("idTipoDoc").toString());

                    tipoDocDAO = new TipoDocDAO();

                    tipoDocDAO.setIdTipoDoc(idTipoDoc);

                    tipoDocDAO.eliminar();

                    request.setAttribute("mensaje", " Tipo de documento eliminado.");

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
