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
import modeloVO.RolVO;

/**
 *
 * @author Sena
 */
public class RolDAO extends conexioDAO{
    private RolVO rolVO;
  private long idRol;
 private ArrayList<RolVO> listaRol;
    
    public RolDAO(){
        listaRol = new ArrayList<RolVO>();
    }

    public RolVO getRolVO() {
        return rolVO;
    }

    public void setRolVO(RolVO rolVO) {
        this.rolVO = rolVO;
    }

    public long getIdRol() {
        return idRol;
    }

    public void setIdRol(long idRol) {
        this.idRol = idRol;
    }

    public ArrayList<RolVO> getListaRol() {
        return listaRol;
    }

    public void setListaRol(ArrayList<RolVO> listaRol) {
        this.listaRol = listaRol;
    }



    
    public void AgregarRegistro() {
        
        try {
          conectar();
          
            String insertar = "CALL ingresarRol(?,?);";
             
            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertar);
            
           // pstm.setLong(1, this.agenciaVO.getIdAgencia());
            pstm.setString(1, this.rolVO.getNombre());
            pstm.setString(2, this.rolVO.getDescripcion());

            
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
      
      String actualiza = "CALL actualizarRol(?,?,?);";
      
      PreparedStatement pstm = this.con.prepareStatement(actualiza);
      
      pstm.setLong(1,this.rolVO.getIdRol());
      pstm.setString(2,this.rolVO.getNombre());
      pstm.setString(3,this.rolVO.getDescripcion());

      
     
      
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
        String consulta = "CALL consultaRol();";
        //paso 2.2: Preparar la sentencia
        PreparedStatement pstm = this.con.prepareStatement(consulta);
        //paso 2.3: Ejecutar la sentencia
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
        this.rolVO = new RolVO();
        
        this.rolVO.setIdRol(rs.getLong(1));
        this.rolVO.setNombre(rs.getString(2));
        this.rolVO.setDescripcion(rs.getString(3));
       
        this.listaRol.add(this.rolVO);
        }
        } catch (SQLException e) {
        System.out.println("Error:" + e.getMessage());
        } finally {
        //paso 3: Desconectar de la BD
        desconectar();
        }
}
  /*  public ArrayList<RolVO> listarobj() {
        RolVO rolVO = new RolVO();
        
        try {
        //Paso 1: Conectar a la base de datos
        conectar();
        //Paso 2: Ejecutar transacción
        //Paso 2.1: Declarar la sentencia
        String consulta = "CALL consultaRol();";
        //paso 2.2: Preparar la sentencia
        PreparedStatement pstm = this.con.prepareStatement(consulta);
        //paso 2.3: Ejecutar la sentencia
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
        rolVO = new RolVO();
        
        rolVO.setIdRol(rs.getLong(1));
        rolVO.setNombre(rs.getString(2));
        rolVO.setDescripcion(rs.getString(3));
       
        this.listaRol.add(rolVO);
        }
        } catch (SQLException e) {
        System.out.println("Error:" + e.getMessage());
        } finally {
        //paso 3: Desconectar de la BD
        desconectar();
        }
        return ;
}
    */
    public RolVO consultaId(){
      try{
          conectar();
          
          String consulta = "SELECT * FROM rol WHERE idRol = ?";
          
          PreparedStatement pstm = this.con.prepareStatement(consulta);
          pstm.setLong(1, this.idRol);
          
          ResultSet rs = pstm.executeQuery();
          
          
          while(rs.next()){
              
              this.rolVO = new RolVO();
              
              this.rolVO.setIdRol(rs.getLong(1));
              this.rolVO.setNombre(rs.getString(2));
              this.rolVO.setDescripcion(rs.getString(3));

          }
          
          
      }catch(SQLException e){
          System.out.println("Error: "+e.getMessage());
      }finally{
          desconectar();
      }
      return this.rolVO;
  }
    
    
      
    public RolVO consultaActuaId(int idRol){
        
        RolVO rolVO = new RolVO();
        
       String consulta = "SELECT nombreRol ,descripcion FROM rol WHERE idRol = ?";
        
      try{
          conectar();
          
          
          
          PreparedStatement pstm = this.con.prepareStatement(consulta);
          pstm.setLong(1, idRol);
          
          ResultSet rs = pstm.executeQuery();
          
          
          while(rs.next()){
              
              rolVO.setIdRol(rs.getLong(1));
              rolVO.setNombre(rs.getString(2));
              rolVO.setDescripcion(rs.getString(3));

          }
          
          
      }catch(SQLException e){
          System.out.println("Error: "+e.getMessage());
      }finally{
          desconectar();
      }
      return rolVO;
  }
    
    
    public void eliminar(){
      try {
        conectar();
        
        String eliminar = "CALL eliminarRol(?);";
        
        PreparedStatement pstm = this.con.prepareStatement(eliminar);
        
        pstm.setLong(1, this.idRol);
        
        pstm.executeUpdate();
        
      } catch (Exception e) {
        System.out.println("Error. "+e.getMessage());
      }finally{
        desconectar();
      }
    }
}
