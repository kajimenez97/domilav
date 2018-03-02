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
import modeloVO.TipoDocVO;

/**
 *
 * @author kev_i
 */
public class TipoDocDAO extends conexioDAO{

    private TipoDocVO tipoDocVO;
    private long idTipoDoc;
    private ArrayList<TipoDocVO> listaTipoDoc;

    public TipoDocDAO() {
        listaTipoDoc = new ArrayList<TipoDocVO>();
    }

    public TipoDocVO getTipoDocVO() {
        return tipoDocVO;
    }

    public void setTipoDocVO(TipoDocVO tipoDocVO) {
        this.tipoDocVO = tipoDocVO;
    }

    public long getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public ArrayList<TipoDocVO> getListaTipoDoc() {
        return listaTipoDoc;
    }

    public void setListaTipoDoc(ArrayList<TipoDocVO> listaTipoDoc) {
        this.listaTipoDoc = listaTipoDoc;
    }

     public void AgregarRegistro() {
        
        try {
          conectar();
          
            String insertar = "CALL ingresarTipoDoc(?,?);";
             
            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertar);
            
           // pstm.setLong(1, this.agenciaVO.getIdAgencia());
            pstm.setString(1, this.tipoDocVO.getNombre());
            pstm.setString(2, this.tipoDocVO.getDescripcion());

            
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
      
      String actualiza = "CALL actualizarTipoDoc(?,?,?);";
      
      PreparedStatement pstm = this.con.prepareStatement(actualiza);
      
      pstm.setLong(1,this.tipoDocVO.getIdTipoDoc());
      pstm.setString(2,this.tipoDocVO.getNombre());
      pstm.setString(3,this.tipoDocVO.getDescripcion());

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
        String consulta = "SELECT * FROM tipodocumento;";
        //paso 2.2: Preparar la sentencia
        PreparedStatement pstm = this.con.prepareStatement(consulta);
        //paso 2.3: Ejecutar la sentencia
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
        this.tipoDocVO = new TipoDocVO();
        
        this.tipoDocVO.setIdTipoDoc(rs.getLong(1));
        this.tipoDocVO.setNombre(rs.getString(2));
        this.tipoDocVO.setDescripcion(rs.getString(3));
       
        this.listaTipoDoc.add(this.tipoDocVO);
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
          
          String consulta = "SELECT * FROM tipoDocumento WHERE idTipoDoc = ?";
          
          PreparedStatement pstm = this.con.prepareStatement(consulta);
          pstm.setLong(1, this.idTipoDoc);
          
          ResultSet rs = pstm.executeQuery();
          
          while(rs.next()){
              this.tipoDocVO = new TipoDocVO();
              this.tipoDocVO.setIdTipoDoc(rs.getLong(1));
              this.tipoDocVO.setNombre(rs.getString(2));
              this.tipoDocVO.setDescripcion(rs.getString(3));
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
        
        String eliminar = "CALL eliminarTipoDoc(?);";
        
        PreparedStatement pstm = this.con.prepareStatement(eliminar);
        
        pstm.setLong(1, this.idTipoDoc);
        
        pstm.executeUpdate();
        
      } catch (SQLException e) {
        System.out.println("Error: "+e.getMessage());
      }finally{
        desconectar();
      }
    }
    
    
}
