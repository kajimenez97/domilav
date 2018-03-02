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
import modeloVO.AgenciaVO;

/**
 *
 * @author Sena
 */
public class AgenciaDAO extends conexioDAO {
    
     private AgenciaVO agenciaVO;
  private long idAgencia;
 private ArrayList<AgenciaVO> lista;
    
    public AgenciaDAO(){
        lista = new ArrayList<AgenciaVO>();
    }

    public AgenciaVO getAgenciaVO() {
        return agenciaVO;
    }

    public void setAgenciaVO(AgenciaVO agenciaVO) {
        this.agenciaVO = agenciaVO;
    }

   
    public long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(long idAgencia) {
        this.idAgencia = idAgencia;
    }

    public ArrayList<AgenciaVO> getLista() {
        return lista;
    }

    public void setLista(ArrayList<AgenciaVO> lista) {
        this.lista = lista;
    }
 
    
    public void AgregarRegistro() {
        
        try {
          conectar();
          
            String insertarAgen = "CALL ingresarAgencia(?,?,?,?,?)"; /*"insert into agencia(nombreAgen,dire ccionAgen,telefonoUno,telefonoDos,correo)values(?,?,?,?,?);"*/
             
            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertarAgen);
            
            //pstm.setLong(1, this.agenciaVO.getIdAgencia());
            pstm.setString(1, this.agenciaVO.getNombreAgen());
            pstm.setString(2, this.agenciaVO.getDireccionAgen());
            pstm.setInt(3, this.agenciaVO.getTelUno());
            pstm.setInt(4, this.agenciaVO.getTelDos());
            pstm.setString(5, this.agenciaVO.getCorreoAgen());
            
            pstm.executeUpdate();
            
        } catch (SQLException e) {
           
           System.out.println("Erro:"+e);
        }finally{
          desconectar();
        }
    }
    
    public void ActualizarRegistro() {
    try{
      conectar();
      
      String actualiza = "UPDATE agencia SET "
              + "nombreAgen = ?,"
              + "direccionAgen = ?,"
              + "telefonoUno = ?, "
              + "telefonoDos= ?,"
              + "correo = ?"
              +"WHERE idAgencia = ?;";
      
      PreparedStatement pstm = this.con.prepareStatement(actualiza);
      
      pstm.setString(1,this.agenciaVO.getNombreAgen());
      pstm.setString(2,this.agenciaVO.getDireccionAgen());
      pstm.setInt(3,this.agenciaVO.getTelUno());
      pstm.setInt(4,this.agenciaVO.getTelDos());
      pstm.setString(5,this.agenciaVO.getCorreoAgen());
      
      pstm.setLong(6,this.agenciaVO.getIdAgencia());
      
      pstm.executeUpdate();
      
    }catch (SQLException e){
        System.out.println("Error: "+e);
    } finally{
    desconectar();
  }
      
  }
    
    public void listar() {
        try {
        //Paso 1: Conectar a la base de datos
        conectar();
        //Paso 2: Ejecutar transacción
        //Paso 2.1: Declarar la sentencia
        String consulta = "SELECT * FROM agencia";
        //paso 2.2: Preparar la sentencia
        PreparedStatement pstm = this.con.prepareStatement(consulta);
        //paso 2.3: Ejecutar la sentencia
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
        this.agenciaVO = new AgenciaVO();
        
        this.agenciaVO.setIdAgencia(rs.getLong(1));
        this.agenciaVO.setNombreAgen(rs.getString(2));
        this.agenciaVO.setDireccionAgen(rs.getString(3));
        this.agenciaVO.setTelUno(rs.getInt(4));
        this.agenciaVO.setTelDos(rs.getInt(5));
        this.agenciaVO.setCorreoAgen(rs.getString(6));
       
        this.lista.add(this.agenciaVO);
        }
        } catch (SQLException e) {
        System.out.println("Error:" + e.getMessage());
        } finally {
        //paso 3: Desconectar de la BD
        desconectar();
        }
}
    
    public void consultaId(){
      try{
          conectar();
          
          String consulta = "SELECT * FROM agencia WHERE idAgencia = ?";
          
          PreparedStatement pstm = this.con.prepareStatement(consulta);
          pstm.setLong(1, this.idAgencia);
          
          ResultSet rs = pstm.executeQuery();
          
          
          while(rs.next()){
              
              this.agenciaVO = new AgenciaVO();
              
              this.agenciaVO.setIdAgencia(rs.getInt(1));
              this.agenciaVO.setNombreAgen(rs.getString(2));
              this.agenciaVO.setDireccionAgen(rs.getString(3));
              this.agenciaVO.setTelUno(rs.getInt(4));
              this.agenciaVO.setTelDos(rs.getInt(5));
              this.agenciaVO.setCorreoAgen(rs.getString(6));
          }
          
          
      }catch(SQLException e){
          System.out.println("Error: "+e.getMessage());
      }finally{
          desconectar();
      }
  }
    
    
    public void eliminar(){
      try {
        conectar();
        
        String eliminar = "DELETE FROM agencia WHERE idAgencia = ? ";
        
        PreparedStatement pstm = this.con.prepareStatement(eliminar);
        
        pstm.setLong(1, this.idAgencia);
        
        pstm.executeUpdate();
        
      } catch (SQLException e) {
        System.out.println("Error. "+e.getMessage());
      }finally{
        desconectar();
      }
    }
    
    
 
}
