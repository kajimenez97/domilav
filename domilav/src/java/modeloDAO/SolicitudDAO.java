/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modeloVO.SolicitudVO;

/**
 *
 * @author Kevin
 */
public class SolicitudDAO extends conexioDAO {

    private SolicitudVO solicitudVO;
    private long idSolicitud;
    private ArrayList<SolicitudVO> listaSolicitud;

    public SolicitudDAO() {
        listaSolicitud = new ArrayList<SolicitudVO>();
    }

    public SolicitudVO getSolicitudVO() {
        return solicitudVO;
    }

    public void setSolicitudVO(SolicitudVO solicitudVO) {
        this.solicitudVO = solicitudVO;
    }

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public ArrayList<SolicitudVO> getListaSolicitud() {
        return listaSolicitud;
    }

    public void setListaSolicitud(ArrayList<SolicitudVO> listaSolicitud) {
        this.listaSolicitud = listaSolicitud;
    }

    // Modificar metodos
    public void AgregarRegistro() {

        try { // Registro logueado
            conectar();

            String insertar = "CALL registroSolicitud(?,?,?,?,?,?,?,?,?);";

            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertar);

            pstm.setLong(1, this.solicitudVO.getIdPersona());
            pstm.setLong(2, this.solicitudVO.getEstadoSolicitud());
           

            pstm.setString(3, this.solicitudVO.getFechaCreacion());
            pstm.setString(4, this.solicitudVO.getFechaRecoleccion());
            pstm.setString(5, this.solicitudVO.getFechaEntrega());

            pstm.setString(6, this.solicitudVO.getHoraEntrega());
            pstm.setString(7, this.solicitudVO.getHoraRecoge());
            pstm.setString(8, this.solicitudVO.getDescripcion());
            pstm.setLong(9, this.solicitudVO.getIdServicio());
            
            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error:" + e);
        } finally {
            desconectar();
        }
    }

    public void ActualizarRegistro(int id) {
        try {
            conectar();

            String actualiza = " UPDATE solicitud SET estadoSolicitud = 2 WHERE idSolicitud = "+id+"; ";

            PreparedStatement pstm = this.con.prepareStatement(actualiza);

            // pstm.setInt(1, this.solicitudVO.getDocumento());
           // pstm.setLong(1, this.solicitudVO.getEstadoSolicitud());

            /*pstm.setString(3, this.solicitudVO.getFechaCreacion());
             pstm.setString(4, this.solicitudVO.getFechaRecoleccion());
             pstm.setString(5, this.solicitudVO.getFechaEntrega());
             pstm.setString(6, this.solicitudVO.getHoraEntrega());
             pstm.setString(7, this.solicitudVO.getHoraRecoge());
             pstm.setString(8, this.solicitudVO.getDescripcion());*/
          //  pstm.setLong(1, this.solicitudVO.getIdSolicitud());

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            desconectar();
        }

    }

    public void listar() {
        try {
            //Paso 1: Conectar a la base de datos
            conectar();
            //Paso 2: Ejecutar transacción
            //Paso 2.1: Declarar la sentencia
            // String consulta = "SELECT idSolicitud,PERSONA_Documento,estadoSolicitud,fechaCreaSolicitud,fechaRecoleccion,"+
            //"fechaEntrega,horaInicialRecoleccion,horaFinalRecoleccion,descripcion"+"FROM solicitud;";
            String consulta = ("CALL listarSolicitud();");

//paso 2.2: Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(consulta);
            //paso 2.3: Ejecutar la sentencia
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                this.solicitudVO = new SolicitudVO();

                this.solicitudVO.setIdSolicitud(rs.getLong(1));
                this.solicitudVO.setNombre(rs.getString(2));
                this.solicitudVO.setNombreEstado(rs.getString(3));
                this.solicitudVO.setNombreServicio(rs.getString(4));

                this.solicitudVO.setFechaCreacion(rs.getString(5));
                this.solicitudVO.setFechaRecoleccion(rs.getString(6));
                this.solicitudVO.setFechaEntrega(rs.getString(7));

                this.solicitudVO.setHoraEntrega(rs.getString(8));
                this.solicitudVO.setHoraRecoge(rs.getString(9));
                this.solicitudVO.setDescripcion(rs.getString(10));

                this.listaSolicitud.add(this.solicitudVO);
            }
        } catch (SQLException e) {
            System.out.println("Error  al listar :" + e.getMessage());
        } finally {
            //paso 3: Desconectar de la BD
            desconectar();
        }
    }

    public void consultaId() {
        try {
            conectar();

            String consulta = "SELECT * FROM solicitud WHERE idSolicitud = ?";

            PreparedStatement pstm = this.con.prepareStatement(consulta);
            pstm.setLong(1, this.idSolicitud);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                this.solicitudVO = new SolicitudVO();

                
                this.solicitudVO.setIdSolicitud(rs.getInt(1));
                this.solicitudVO.setIdPersona(rs.getInt(1));
                this.solicitudVO.setEstadoSolicitud(rs.getInt(2));
                this.solicitudVO.setIdServicio(rs.getInt(3));

                this.solicitudVO.setFechaCreacion(rs.getString(4));
                this.solicitudVO.setFechaRecoleccion(rs.getString(5));
                this.solicitudVO.setFechaEntrega(rs.getString(6));

                this.solicitudVO.setHoraEntrega(rs.getString(7));
                this.solicitudVO.setHoraRecoge(rs.getString(9));
                this.solicitudVO.setDescripcion(rs.getString(10));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
    }
    public long consultaIdRecoger(int id) {
        try {
            conectar();

            String consulta = "SELECT * FROM solicitud WHERE idSolicitud ="+id+";";

            PreparedStatement pstm = this.con.prepareStatement(consulta);
           

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                this.solicitudVO = new SolicitudVO();

                pstm.setLong(1, this.solicitudVO.getIdPersona());
                
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
        return this.solicitudVO.getIdSolicitud();
    }

    public void eliminar() {
        try {
            conectar();

            String eliminar = "DELETE FROM solicitud WHERE idSolicitud = ? ";

            PreparedStatement pstm = this.con.prepareStatement(eliminar);

            pstm.setLong(1, this.idSolicitud);

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error. " + e.getMessage());
        } finally {
            desconectar();
        }
    }
    
  
    

}
