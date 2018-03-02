/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modeloDAO.PersonaDAO;
import modeloDAO.SolicitudDAO;
import modeloDAO.TipoPrendaDAO;
import modeloDAO.TipoServicioDAO;
import modeloVO.PersonaVO;
import modeloVO.SolicitudVO;
import modeloVO.TipoServicioVO;

/**
 *
 * @author Kevin
 */
public class servletSolicitud extends HttpServlet {

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

            PersonaDAO personaDAO;
            PersonaVO personaVO;
          
            
            TipoPrendaDAO tipoPrendaDAO;
            TipoServicioDAO servicioDAO;
            SolicitudDAO solicitudDAO;
            SolicitudVO solicitudVO;

            // Configuracion del formato del ordenador.
            DateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

            int idSolicitud;

            String opc = request.getParameter("opcion").toString();

            int opcion = Integer.parseInt(opc);

            switch (opcion) {

                case 1: // Vista listar Solicitud

                    solicitudDAO = new SolicitudDAO();

                    solicitudDAO.listar();

                    request.setAttribute("solicitud", solicitudDAO.getListaSolicitud());

                    vista = request.getRequestDispatcher("solicitud/listarSolicitud.jsp");

                    break;

                case 2:

                    servicioDAO = new TipoServicioDAO();
                    
                    servicioDAO.listar();
                    
                    request.setAttribute("servicio", servicioDAO.getLista());
                    
                    
                    vista = request.getRequestDispatcher("solicitud/registroSolicitud.jsp");
                
                    break;
                case 3: // Insertar solicitud

                    solicitudDAO = new SolicitudDAO();
                    solicitudVO = new SolicitudVO();

                    int doc = 2;
                    // Insertar valores del form
                    //solicitudVO.setDocumento(Integer.parseInt(request.getParameter("documento")));
                    solicitudVO.setIdPersona(doc);
                    solicitudVO.setEstadoSolicitud(Integer.parseInt(request.getParameter("estado")));
                    solicitudVO.setFechaCreacion(formato.format(new Date()));
                    //solicitudVO.setFechaCreacion(request.getParameter("crear").toString());
                     solicitudVO.setIdServicio(Integer.parseInt(request.getParameter("servicio")));
                    solicitudVO.setFechaRecoleccion(request.getParameter("recoger").toString());
                    solicitudVO.setFechaEntrega(request.getParameter("entrega").toString());
                    solicitudVO.setHoraRecoge(request.getParameter("horaInicio").toString());
                    solicitudVO.setHoraEntrega(request.getParameter("horaEntrega").toString());
                    solicitudVO.setDescripcion(request.getParameter("descripcion").toString());

                    // Inicializar el atributo de la clase aprendizVO
                    solicitudDAO.setSolicitudVO(solicitudVO);

                    solicitudDAO.AgregarRegistro();

                    request.setAttribute("mensaje", "Solicitud" + solicitudVO.getIdPersona() + " Ingresada con exito.");

                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");

                    break;

                case 4: // Vista actualizar estado de la solicitud
                    vista = request.getRequestDispatcher("solicitud/actSolicitud.jsp");
                    break;
                case 5: // Ingresar solicitud y logear cliente

                    personaDAO = new PersonaDAO();
                    personaVO = new PersonaVO();

// Insertar valores del form
                    String documento = (request.getParameter("documento"));

                    if (personaDAO.consultaDocumento(documento)) {

                        int agencia = 1;
                        int barrio = 1;
                        int tiDoc = 1;
                        int rol = 1;
                        int estado = 1;
                   
                        personaVO.setRol(rol);
                        personaVO.setIdAgencia(agencia);
                        personaVO.setBarrio(barrio);
                        personaVO.setIdDocumento(tiDoc);
                         personaVO.setEstado(estado);

                        // personaVO.setIdPersona(id);
                         
                         
                        personaVO.setDocumento((request.getParameter("documento")));
                        personaVO.setNombres(request.getParameter("nombre").toString());

                        personaVO.setNumCelular(request.getParameter("tel"));

                        personaVO.setDireccion(request.getParameter("direccion").toString());
                        personaVO.setCorreo(request.getParameter("correo").toString());
                       

                        // Inicializar el atributo de la clase aprendizVO
                        personaDAO.setPersonaVO(personaVO);

                        personaDAO.registroCliente();
                        
                        if (personaDAO.consultaDocumento(documento) != true){
                        
                    solicitudDAO = new SolicitudDAO();
                    solicitudVO = new SolicitudVO();
                    personaDAO = new PersonaDAO();
                    
                    int estadoSo = 1;
               
                    // int doc = 1;
                    // Insertar valores del form
                    solicitudVO.setIdPersona(personaDAO.recogeId(personaVO.getDocumento()));
                    solicitudVO.setIdServicio(Integer.parseInt(request.getParameter("servicio")));
                    solicitudVO.setEstadoSolicitud(estadoSo);
                  
                    
                    solicitudVO.setFechaCreacion(formato.format(new Date()));
                    //solicitudVO.setFechaCreacion(request.getParameter("crear").toString());
                    solicitudVO.setFechaRecoleccion(request.getParameter("recoger").toString());
                    solicitudVO.setFechaEntrega(request.getParameter("entrega").toString());
                    solicitudVO.setHoraRecoge(request.getParameter("horaInicio").toString());
                    solicitudVO.setHoraEntrega(request.getParameter("horaEntrega").toString());
                    solicitudVO.setDescripcion(request.getParameter("descripcion").toString());

                    // Inicializar el atributo de la clase aprendizVO
                    solicitudDAO.setSolicitudVO(solicitudVO);

                    solicitudDAO.AgregarRegistro();
                    }
                        
                    }

                    

                    request.setAttribute("mensaje", " solicitud de " + personaVO.getNombres() + " registrada con exito");
                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("solicitud/registroSolicitud.jsp");

                    break;

                case 6: // Muiestra formlulario para actualizar

                    idSolicitud = Integer.parseInt(request.getParameter("idSolicitud").toString());

                    solicitudDAO = new SolicitudDAO();

                    solicitudDAO.setIdSolicitud(idSolicitud);

                    solicitudDAO.consultaId();

                    request.setAttribute("solicitudVO", solicitudDAO.getSolicitudVO());

                    vista = request.getRequestDispatcher("solicitud/actEstadoSol.jsp");
                    break;

                case 7: // Actualizar solicitud

                    solicitudVO = new SolicitudVO();
                    solicitudDAO = new SolicitudDAO();

                    solicitudVO.setEstadoSolicitud(Integer.parseInt(request.getParameter("estado")));
                    solicitudVO.setHoraEntrega(request.getParameter("horaEntrega").toString());
                    solicitudVO.setDescripcion(request.getParameter("descripcion").toString());

                    solicitudDAO.setSolicitudVO(solicitudVO);

                    solicitudDAO.AgregarRegistro();

                    request.setAttribute("mensaje", " solicitud " + solicitudVO.getIdSolicitud() + " actualizado con exito");
                    request.setAttribute("opcion", "1");

                    vista = request.getRequestDispatcher("Respuesta.jsp");

                    break;

                case 8:
                               solicitudDAO = new SolicitudDAO();

                    idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));

                    //solicitudDAO.setIdSolicitud(idSolicitud);

                    solicitudDAO.ActualizarRegistro(idSolicitud);

                    request.setAttribute("mensaje", " solicitud cancelada.");
                    
                    // cargar datos
                      solicitudDAO.listar();

                    request.setAttribute("solicitud", solicitudDAO.getListaSolicitud());
                    

                    vista = request.getRequestDispatcher("solicitud/listarSolicitud.jsp");

                    break;
               
                default:
                    request.setAttribute("mensaje", "Esta opcion no esta");

                    request.setAttribute("opcion", "7");

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
