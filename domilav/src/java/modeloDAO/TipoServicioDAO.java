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
import modeloVO.TipoServicioVO;

/**
 *
 * @author Kevin
 */
public class TipoServicioDAO extends conexioDAO {
     private TipoServicioVO tipoServicioVO;
  private long idTipoServicio;
 private ArrayList<TipoServicioVO> lista;

 public TipoServicioDAO(){
     lista = new ArrayList<TipoServicioVO>();
 }
 
 
 
 
    public TipoServicioVO getTipoServicioVO() {
        return tipoServicioVO;
    }

    public void setTipoServicioVO(TipoServicioVO tipoServicioVO) {
        this.tipoServicioVO = tipoServicioVO;
    }

    public long getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(long idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public ArrayList<TipoServicioVO> getLista() {
        return lista;
    }

    public void setLista(ArrayList<TipoServicioVO> lista) {
        this.lista = lista;
    }

  
  
    public void AgregarRegistro() {
        
        try {
          conectar();
          
            String insertar = "CALL ingresarServicio(?,?,?,?);";
             
            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertar);
            
            //pstm.setLong(1, this.tipoPrendaVO.getIdTipoPrenda());
            pstm.setString(1, this.tipoServicioVO.getNombre());
            pstm.setString(2, this.tipoServicioVO.getTiempo());
            pstm.setFloat(3, this.tipoServicioVO.getPrecio());
            
            pstm.setString(4, this.tipoServicioVO.getDescripcion());
            
            
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
      
      String actualiza = "CALL actServicio(?,?,?,?,?);";
      
      PreparedStatement pstm = this.con.prepareStatement(actualiza);
      
      pstm.setLong(1,this.tipoServicioVO.getIdTipoServicio());
      pstm.setString(2,this.tipoServicioVO.getNombre());
      pstm.setString(3,this.tipoServicioVO.getTiempo());
      pstm.setFloat(4,this.tipoServicioVO.getPrecio());
      pstm.setString(5,this.tipoServicioVO.getDescripcion());
     
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
        String consulta = "SELECT * FROM tiposervicio";
        //paso 2.2: Preparar la sentencia
        PreparedStatement pstm = this.con.prepareStatement(consulta);
        //paso 2.3: Ejecutar la sentencia
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
        this.tipoServicioVO = new TipoServicioVO();
        
        this.tipoServicioVO.setIdTipoServicio(rs.getLong(1));
        this.tipoServicioVO.setNombre(rs.getString(2));
        this.tipoServicioVO.setTiempo(rs.getString(3));
        this.tipoServicioVO.setPrecio(rs.getFloat(4));
        this.tipoServicioVO.setDescripcion(rs.getString(5));
      
       
        this.lista.add(this.tipoServicioVO);
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
          
          String consulta = "SELECT * FROM tipoServicio WHERE idTipoServicio = ?";
          
          PreparedStatement pstm = this.con.prepareStatement(consulta);
          pstm.setLong(1, this.idTipoServicio);
          
          ResultSet rs = pstm.executeQuery();
          
          
          while(rs.next()){
              
              this.tipoServicioVO = new TipoServicioVO();
              
              this.tipoServicioVO.setIdTipoServicio(rs.getInt(1));
              this.tipoServicioVO.setNombre(rs.getString(2));
              this.tipoServicioVO.setDescripcion(rs.getString(3));
              this.tipoServicioVO.setTiempo(rs.getString(4));
             
              this.tipoServicioVO.setPrecio(rs.getFloat(6));
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
        
        String eliminar = "DELETE FROM tipoServicio WHERE idTipoServicio = ? ";
        
        PreparedStatement pstm = this.con.prepareStatement(eliminar);
        
        pstm.setLong(1, this.idTipoServicio);
        
        pstm.executeUpdate();
        
      } catch (SQLException e) {
        System.out.println("Error. "+e.getMessage());
      }finally{
        desconectar();
      }
    }
}
