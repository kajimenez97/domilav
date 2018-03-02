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
import modeloVO.TipoPrendaVO;

/**
 *
 * @author Kevin
 */
public class TipoPrendaDAO extends conexioDAO {
    private TipoPrendaVO tipoPrendaVO;
  private long idTipoPrenda;
 private ArrayList<TipoPrendaVO> listaTipoPrenda;
    
    public TipoPrendaDAO(){
        listaTipoPrenda = new ArrayList<TipoPrendaVO>();
    }

    public TipoPrendaVO getTipoPrendaVO() {
        return tipoPrendaVO;
    }

    public void setTipoPrendaVO(TipoPrendaVO tipoPrendaVO) {
        this.tipoPrendaVO = tipoPrendaVO;
    }

    public long getIdTipoPrenda() {
        return idTipoPrenda;
    }

    public void setIdTipoPrenda(long idTipoPrenda) {
        this.idTipoPrenda = idTipoPrenda;
    }

    public ArrayList<TipoPrendaVO> getListaTipoPrenda() {
        return listaTipoPrenda;
    }

    public void setListaTipoPrenda(ArrayList<TipoPrendaVO> listaTipoPrenda) {
        this.listaTipoPrenda = listaTipoPrenda;
    }

    
    public void AgregarRegistro() {
        
        try {
          conectar();
          
            String insertar = "CALL ingresar(?,?,?,?)"; /*"insert into agencia(nombreAgen,dire ccionAgen,telefonoUno,telefonoDos,correo)values(?,?,?,?,?);"*/
             
            // Preparar la sentencia
            PreparedStatement pstm = this.con.prepareStatement(insertar);
            
            //pstm.setLong(1, this.tipoPrendaVO.getIdTipoPrenda());
            pstm.setString(1, this.tipoPrendaVO.getNombre());
            pstm.setString(2, this.tipoPrendaVO.getDescripcion());
            
            
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
      
      String actualiza = "CALL actTipoPrenda(?,?,?,?,?);";
      
      /*UPDATE agencia SET "
              + "nombreAgen = ?,"
              + "direccionAgen = ?,"
              + "telefonoUno = ?, "
              + "telefonoDos= ?,"
              + "correo = ?"
              +"WHERE idAgencia = ?;*/
      
      PreparedStatement pstm = this.con.prepareStatement(actualiza);
      
      pstm.setLong(1,this.tipoPrendaVO.getIdTipoPrenda());
      pstm.setString(2,this.tipoPrendaVO.getNombre());
      pstm.setString(5,this.tipoPrendaVO.getDescripcion());
     
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
        String consulta = "SELECT * FROM tipoPrenda";
        //paso 2.2: Preparar la sentencia
        PreparedStatement pstm = this.con.prepareStatement(consulta);
        //paso 2.3: Ejecutar la sentencia
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            
        this.tipoPrendaVO = new TipoPrendaVO();
        this.tipoPrendaVO.setIdTipoPrenda(rs.getInt(1));
        this.tipoPrendaVO.setNombre(rs.getString(2));
        this.tipoPrendaVO.setDescripcion(rs.getString(3));
       
        this.listaTipoPrenda.add(this.tipoPrendaVO);
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
          
          String consulta = "SELECT * FROM tipoPrenda WHERE idTipoPrenda = ?";
          
          PreparedStatement pstm = this.con.prepareStatement(consulta);
          pstm.setLong(1, this.idTipoPrenda);
          
          ResultSet rs = pstm.executeQuery();
          
          
          while(rs.next()){
              
              this.tipoPrendaVO = new TipoPrendaVO();
              
              this.tipoPrendaVO.setIdTipoPrenda(rs.getInt(1));
              this.tipoPrendaVO.setNombre(rs.getString(2));
              this.tipoPrendaVO.setDescripcion(rs.getString(3));

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
        
        String eliminar = "DELETE FROM tipoPrenda WHERE idTipoPrenda = ? ";
        
        PreparedStatement pstm = this.con.prepareStatement(eliminar);
        
        pstm.setLong(1, this.idTipoPrenda);
        
        pstm.executeUpdate();
        
      } catch (Exception e) {
        System.out.println("Error. "+e.getMessage());
      }finally{
        desconectar();
      }
    }
}
