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
import modeloVO.BarrioVO;

/**
 *
 * @author Kevin
 */
public class BarrioDAO extends conexioDAO {
    private BarrioVO barrioVO;
    private long idBarrio;
    private ArrayList<BarrioVO> listaBarrio;

    public BarrioDAO() {
        listaBarrio = new ArrayList<BarrioVO>();
    }

    public BarrioVO getBarrioVO() {
        return barrioVO;
    }

    public void setBarrioVO(BarrioVO barrioVO) {
        this.barrioVO = barrioVO;
    }

    public long getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(long idBarrio) {
        this.idBarrio = idBarrio;
    }

    public ArrayList<BarrioVO> getListaBarrio() {
        return listaBarrio;
    }

    public void setListaBarrio(ArrayList<BarrioVO> listaBarrio) {
        this.listaBarrio = listaBarrio;
    }

    public void AgregarRegistro() {

        try {
            conectar();

            String insertar = "CALL ingresarBarrio(?,?);";

            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertar);

            // pstm.setLong(1, this.agenciaVO.getIdAgencia());
            pstm.setString(1, this.barrioVO.getNombre());
            pstm.setString(2, this.barrioVO.getDescripcion());

            pstm.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Erro:" + e);
        } finally {
            desconectar();
        }
    }

    public void ActualizarRegistro() {
        try {
            conectar();

            String actualiza = "CALL actualizarBarrio(?,?,?);";

            PreparedStatement pstm = this.con.prepareStatement(actualiza);

            pstm.setLong(1, this.barrioVO.getIdBarrio());
            pstm.setString(2, this.barrioVO.getNombre());
            pstm.setString(3, this.barrioVO.getDescripcion());

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
            String consulta = "CALL consultaBarrio();";
            //paso 2.2: Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(consulta);
            //paso 2.3: Ejecutar la sentencia
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                this.barrioVO = new BarrioVO();

                this.barrioVO.setIdBarrio(rs.getLong(1));
                this.barrioVO.setNombre(rs.getString(2));
                this.barrioVO.setDescripcion(rs.getString(3));

                this.listaBarrio.add(this.barrioVO);
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

            String consulta = "SELECT * FROM barrio WHERE idBarrio = ?";

            PreparedStatement pstm = this.con.prepareStatement(consulta);
            pstm.setLong(1, this.idBarrio);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                this.barrioVO = new BarrioVO();

                this.barrioVO.setIdBarrio(rs.getLong(1));
                this.barrioVO.setNombre(rs.getString(2));
                this.barrioVO.setDescripcion(rs.getString(3));

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

            String eliminar = "CALL eliminarBarrio(?);";

            PreparedStatement pstm = this.con.prepareStatement(eliminar);

            pstm.setLong(1, this.idBarrio);

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error. " + e.getMessage());
        } finally {
            desconectar();
        }
    }
}
