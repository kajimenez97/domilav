/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeloDAO.AgenciaDAO;
import modeloDAO.PersonaDAO;
import modeloDAO.RolDAO;
import modeloVO.PersonaVO;

/**
 *
 * @author Kevin
 */
public class servletUser extends HttpServlet {

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
                case 1: // login
                    vista = request.getRequestDispatcher("login.jsp");

                    break;
                case 2:
                    personaDAO = new PersonaDAO();
                    personaVO = new PersonaVO();

                    String user = request.getParameter("user").toString();
                    String pass = request.getParameter("pass".toString());
                    
                     int id = personaDAO.recogeIdUser(user);

                    if (personaDAO.autenticacion(user, pass)) {

                       
                        if(id == 1){
                               vista = request.getRequestDispatcher("index.jsp");
                        }else{
                               vista = request.getRequestDispatcher("templates/header.jsp");
                        }
                     

                        
                        
                        
                    } else {
                        vista = request.getRequestDispatcher("login.jsp");
                    }
                    break;

                case 4: // listar
                    personaDAO = new PersonaDAO();

                    personaDAO.listar();

                    request.setAttribute("persona", personaDAO.getListaPersona());

                    vista = request.getRequestDispatcher("cuentas/listarPersona.jsp");
                    break;

                case 5: // vista registrar empleado

                    agenciaDAO = new AgenciaDAO();

                    agenciaDAO.listar();

                    request.setAttribute("lista", agenciaDAO.getLista());
                    vista = request.getRequestDispatcher("cuentas/registrarEmpleado.jsp");

                    break;

                case 6:
                    // Insertar empleado
                    personaDAO = new PersonaDAO();
                    personaVO = new PersonaVO();

// Insertar valores del form
                    //int agencia = 1;
                    int barrio = 1;
                    int tiDoc = 1;
                    int rol = 1;
                    String usu = "hola";
                    // int id= 4;

                    personaVO.setIdAgencia(Integer.parseInt(request.getParameter("agencia")));
                    //personaVO.setIdAgencia(Integer.parseInt(request.getParameter("agencia")));
                    personaVO.setRol(rol);
                    personaVO.setBarrio(barrio);
                    personaVO.setIdDocumento(tiDoc);
                    personaVO.setUsuario(usu);

                    // personaVO.setIdPersona(id);
                    personaVO.setDocumento((request.getParameter("documento")));
                    personaVO.setNombres(request.getParameter("nombreEmpl").toString());
                    personaVO.setApellidos(request.getParameter("apellidos").toString());
                    personaVO.setGenero(request.getParameter("genero"));
                    personaVO.setNumCelular(request.getParameter("celular"));
                    personaVO.setNumFijo(request.getParameter("fijo"));
                    personaVO.setPassword(request.getParameter("pass").toString());
                    personaVO.setDireccion(request.getParameter("direccion").toString());
                    personaVO.setCorreo(request.getParameter("correo").toString());
                    personaVO.setEstado(Integer.parseInt(request.getParameter("estado")));
                   
                    

                    // Inicializar el atributo de la clase aprendizVO
                    personaDAO.setPersonaVO(personaVO);

                    personaDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "Emplead@ " + personaVO.getNombres() + " Ingresado con exito.");

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
