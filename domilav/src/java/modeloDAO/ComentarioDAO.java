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
import modeloVO.ComentarioVO;

/**
 *
 * @author Kevin
 */
public class ComentarioDAO extends conexioDAO {

    private ComentarioVO comentarioVO;
    private long idComentario;
    private ArrayList<ComentarioVO> listaComentario;

    public ComentarioDAO() {
        listaComentario = new ArrayList<ComentarioVO>();
    }

    public ComentarioVO getComentarioVO() {
        return comentarioVO;
    }

    public void setComentarioVO(ComentarioVO comentarioVO) {
        this.comentarioVO = comentarioVO;
    }

    public long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(long idComentario) {
        this.idComentario = idComentario;
    }

    public ArrayList<ComentarioVO> getListaComentario() {
        return listaComentario;
    }

    public void setListaComentario(ArrayList<ComentarioVO> listaComentario) {
        this.listaComentario = listaComentario;
    }

    public void AgregarRegistro() {
        try {
            conectar();

            String insertar = "CALL ingresarComentario(?,?,?)";

            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertar);

            pstm.setInt(1, this.comentarioVO.getIdPersona());
            pstm.setString(2, this.comentarioVO.getDescripcion());
            pstm.setString(3, this.comentarioVO.getFecha());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error:" + e);
        } finally {
            desconectar();
        }
    }

    public void ActualizarRegistro() {
        try {
            conectar();

            String actualiza = "CALL actualizarComentario(?,?,?);";

            PreparedStatement pstm = this.con.prepareStatement(actualiza);

            pstm.setString(1, this.comentarioVO.getDescripcion());
            pstm.setString(2, this.comentarioVO.getFecha());

            pstm.setLong(3, this.comentarioVO.getIdComentario());

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
            String consulta = "SELECT * FROM comentario";
            //paso 2.2: Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(consulta);
            //paso 2.3: Ejecutar la sentencia
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                this.comentarioVO = new ComentarioVO();

                this.comentarioVO.setIdComentario(rs.getLong(1));
                this.comentarioVO.setDescripcion(rs.getString(3));
                this.comentarioVO.setFecha(rs.getString(4));
                this.comentarioVO.setIdPersona(rs.getInt(2));
          

                this.listaComentario.add(this.comentarioVO);
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        } finally {
            //paso 3: Desconectar de la BD
            desconectar();
        }
    }

    public void consultaId() {
        try {
            conectar();

            String consulta = "SELECT * FROM comentario WHERE idComentario = ?";

            PreparedStatement pstm = this.con.prepareStatement(consulta);
            pstm.setLong(1, this.idComentario);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                this.comentarioVO = new ComentarioVO();

                this.comentarioVO.setIdComentario(rs.getInt(1));
                this.comentarioVO.setIdPersona(rs.getInt(2));
                this.comentarioVO.setDescripcion(rs.getString(3));
                this.comentarioVO.setFecha(rs.getString(4));
   
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public void eliminar() {
        try {
            conectar();

            String eliminar = "DELETE FROM comentario WHERE idComentario = ? ";

            PreparedStatement pstm = this.con.prepareStatement(eliminar);

            pstm.setLong(1, this.idComentario);

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error. " + e.getMessage());
        } finally {
            desconectar();
        }
    }
}
