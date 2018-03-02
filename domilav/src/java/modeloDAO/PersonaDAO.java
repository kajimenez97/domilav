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
import modeloVO.PersonaVO;
import modeloVO.RolVO;

/**
 *
 * @author kev_i
 */
public class PersonaDAO extends conexioDAO {

    public RolVO rolVO;
    private PersonaVO personaVO;
    private long idPersona;
    private ArrayList<PersonaVO> listaPersona;

    public PersonaDAO() {
        listaPersona = new ArrayList<PersonaVO>();
    }

    public PersonaVO getPersonaVO() {
        return personaVO;
    }

    public void setPersonaVO(PersonaVO personaVO) {
        this.personaVO = personaVO;
    }

    public long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(long idPersona) {
        this.idPersona = idPersona;
    }

    public ArrayList<PersonaVO> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(ArrayList<PersonaVO> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public boolean autenticacion(String user, String pass) {
        try {
            conectar();
            String consulta = "SELECT * FROM persona WHERE usuario = ? AND passwo = ?;";

            PreparedStatement pst = this.con.prepareStatement(consulta);

            pst.setString(1, user);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();

            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error login:" + e.getMessage());
        } finally {
            desconectar();
        }
        return false;
    }

    public void AgregarRegistro() {
        try {
            conectar();

            String insertarPersona = "CALL insertarUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pstm = this.con.prepareStatement(insertarPersona);

            // pstm.setLong(1, this.personaVO.getIdPersona());
            pstm.setString(1, this.personaVO.getDocumento());
            pstm.setInt(2, this.personaVO.getIdDocumento());
            pstm.setInt(3, this.personaVO.getIdAgencia());
            pstm.setInt(4, this.personaVO.getBarrio());
            pstm.setInt(5, this.personaVO.getRol());

            pstm.setString(6, this.personaVO.getNombres());
            pstm.setString(7, this.personaVO.getApellidos());
            pstm.setString(8, this.personaVO.getGenero());
            pstm.setString(9, this.personaVO.getNumCelular());

            pstm.setString(10, this.personaVO.getNumFijo());
            pstm.setString(11, this.personaVO.getDireccion());
            pstm.setString(12, this.personaVO.getCorreo());
            pstm.setString(13, this.personaVO.getPassword());
            pstm.setInt(14, this.personaVO.getEstado());
            pstm.setString(15, this.personaVO.getUsuario());

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en esta parte: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public void registroCliente() {
        try {
            conectar();

            String insertarPersona = "CALL registroCliente(?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pstm = this.con.prepareStatement(insertarPersona);

            // pstm.setLong(1, this.personaVO.getIdPersona());
            pstm.setString(1, this.personaVO.getDocumento());
            pstm.setInt(2, this.personaVO.getIdDocumento());
            pstm.setInt(3, this.personaVO.getIdAgencia());
            pstm.setInt(4, this.personaVO.getBarrio());
            pstm.setInt(5, this.personaVO.getRol());

            pstm.setString(6, this.personaVO.getNombres());

            pstm.setString(7, this.personaVO.getNumCelular());
            pstm.setString(8, this.personaVO.getDireccion());
            pstm.setString(9, this.personaVO.getCorreo());

            pstm.setInt(10, this.personaVO.getEstado());

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en esta parte: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public void listar() {
        try {
            conectar();

            String consulta = "CALL listarPersona()";

            PreparedStatement pstm = this.con.prepareStatement(consulta);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                this.personaVO = new PersonaVO();

                this.personaVO.setIdPersona(rs.getInt(1));
                this.personaVO.setNombreRol(rs.getString(2));
                //this.personaVO.setRol(rs.getInt(2));
                this.personaVO.setNombres(rs.getString(3));
                this.personaVO.setApellidos(rs.getString(4));

                this.listaPersona.add(this.personaVO);

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
    }
    public void listarEmpleado() {
        try {
            conectar();

            String consulta = "CALL listarEmpleado()";

            PreparedStatement pstm = this.con.prepareStatement(consulta);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                this.personaVO = new PersonaVO();

           
                this.personaVO.setNombres(rs.getString(1));
                this.personaVO.setApellidos(rs.getString(2));
                this.personaVO.setDocumento(rs.getString(3));
                this.personaVO.setDireccion(rs.getString(4));
                this.personaVO.setNumCelular(rs.getString(5));
                this.personaVO.setNombreAgen(rs.getString(6));
                this.personaVO.setNombreEstado(rs.getString(7));

                this.listaPersona.add(this.personaVO);

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public void consultaId() {
        try {
            conectar();

            String consulta = "SELECT * FROM persona WHERE idPersona = ?";

            PreparedStatement pstm = this.con.prepareStatement(consulta);
            pstm.setLong(1, this.idPersona);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                this.personaVO = new PersonaVO();

                this.personaVO.setIdPersona(rs.getLong(1));
                this.personaVO.setDocumento(rs.getString(2));
                this.personaVO.setIdAgencia(rs.getInt(3));
                this.personaVO.setIdDocumento(rs.getInt(4));
                this.personaVO.setBarrio(rs.getInt(5));
                this.personaVO.setRol(rs.getInt(6));
                this.personaVO.setNombres(rs.getString(7));
                this.personaVO.setApellidos(rs.getString(8));
                this.personaVO.setGenero(rs.getString(9));
                this.personaVO.setNumCelular(rs.getString(10));
                this.personaVO.setNumFijo(rs.getString(11));
                this.personaVO.setDireccion(rs.getString(12));
                this.personaVO.setCorreo(rs.getString(13));
                this.personaVO.setPassword(rs.getString(14));
                this.personaVO.setEstado(rs.getInt(15));

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

    public boolean consultaDocumento(String doc) {
        try {
            conectar();

            conectar();
            String consulta = "SELECT * FROM persona WHERE documento = ?;";

            PreparedStatement pst = this.con.prepareStatement(consulta);

            pst.setString(1, doc);

            ResultSet rs = pst.executeQuery();

            if (rs.absolute(1)) {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
        return true;
    }

    public long recogeId(String doc) {

        try {
            conectar();
            int id;
            conectar();
            String consulta = "SELECT idPersona FROM persona WHERE documento = " + doc + ";";

            PreparedStatement pst = this.con.prepareStatement(consulta);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                this.personaVO = new PersonaVO();

                this.personaVO.setIdPersona(rs.getLong(1));

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
        return personaVO.getIdPersona();
    }
    public int recogeIdUser(String user) {

        try {
       
            conectar();
            String consulta = "SELECT ROL_idRol FROM persona WHERE usuario = '" + user + "';";

            PreparedStatement pst = this.con.prepareStatement(consulta);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                this.personaVO = new PersonaVO();

                this.personaVO.setRol(rs.getInt(1));

            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            desconectar();
        }
        return personaVO.getRol();
    }

    public void ActualizarRegistro() {
        try {
            conectar();

            String actualiza = "CALL ActualizarUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pstm = this.con.prepareStatement(actualiza);

            pstm.setLong(1, this.personaVO.getIdPersona());
            pstm.setString(2, this.personaVO.getNombres());
            pstm.setString(3, this.personaVO.getApellidos());

            pstm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            desconectar();
        }

    }

    public void eliminar() {
        try {
            conectar();

            String eliminar = "CALL eliminarPersona;";

            PreparedStatement pstm = this.con.prepareStatement(eliminar);

            pstm.setLong(1, this.idPersona);

            pstm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error. " + e.getMessage());
        } finally {
            desconectar();
        }
    }

} // Fin clase personaDAPO
