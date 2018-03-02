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
import modeloDAO.PersonaDAO;
import modeloVO.PersonaVO;

/**
 *
 * @author Kevin
 */
public class servletEmpleado extends HttpServlet {

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
            AgenciaDAO agenciaDAO;

            PersonaDAO personaDAO;
            PersonaVO personaVO;

            String opc = request.getParameter("opcion").toString();
           
            int opcion = Integer.parseInt(opc);

            switch (opcion) {
                case 1: // listar empleado
                       personaDAO = new PersonaDAO();

                    personaDAO.listarEmpleado();

                    request.setAttribute("empleado", personaDAO.getListaPersona());

                    vista = request.getRequestDispatcher("cuentas/listarEmpl.jsp");

                    break;
                case 2: // vista registrar empleado
                     agenciaDAO = new AgenciaDAO();
                     
                    agenciaDAO.listar();

                    request.setAttribute("lista", agenciaDAO.getLista());
                    
                    
                    
                    vista = request.getRequestDispatcher("cuentas/registrarEmpleado.jsp");
                    
                    
             
                    break;
               /* case 3:
                      // Insertar empleado
                    personaDAO = new PersonaDAO();
                    personaVO = new PersonaVO();

// Insertar valores del form
                    //int agencia = 1;
                    int barrio = 1;
                    int tiDoc = 1;
                    int rol = 2;
                  
                    personaVO.setIdAgencia(Integer.parseInt(request.getParameter("agencia")));
                    //personaVO.setIdAgencia(Integer.parseInt(request.getParameter("agencia")));
                    personaVO.setRol(rol);
                    personaVO.setBarrio(barrio);
                    personaVO.setIdDocumento(tiDoc);
             
                    personaVO.setDocumento(Integer.parseInt(request.getParameter("documento")));
                    personaVO.setNombres(request.getParameter("nombreEmpl").toString());
                    personaVO.setApellidos(request.getParameter("apellidos").toString());
                   
                    personaVO.setNumCelular(Integer.parseInt(request.getParameter("celular")));
                  
               
                    personaVO.setDireccion(request.getParameter("direccion").toString());
                    personaVO.setCorreo(request.getParameter("correo").toString());
                    personaVO.setEstado(request.getParameter("estado").toString());

                    // Inicializar el atributo de la clase aprendizVO
                    personaDAO.setPersonaVO(personaVO);

                    personaDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "Emplead@ " + personaVO.getNombres() + " Ingresado con exito.");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");
                    break;*/
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
            System.out.println("Error servlet: " + e.getMessage());
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
