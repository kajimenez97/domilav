/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sena
 */
public class conexioDAO {
    
    public Connection con;

    
    public void conectar(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/domilav","root","");
    
    }catch(Exception e){
        System.out.println("Error: "+e.getMessage());
    }
    
    }   
    public void desconectar(){
      try{
        if(this.con != null && !this.con.isClosed()){
          this.con.close();
        }
        }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
        }
      }
    
}



