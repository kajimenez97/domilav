/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloVO;

/**
 *
 * @author Sena
 */
public class AgenciaVO {
    
  private String nombreAgen, direccionAgen,correoAgen;
  private int  telUno, telDos;
  
  private long idAgencia;

    public String getNombreAgen() {
        return nombreAgen;
    }

    public void setNombreAgen(String nombreAgen) {
        this.nombreAgen = nombreAgen;
    }

    public String getDireccionAgen() {
        return direccionAgen;
    }

    public void setDireccionAgen(String direccionAgen) {
        this.direccionAgen = direccionAgen;
    }

    public String getCorreoAgen() {
        return correoAgen;
    }

    public void setCorreoAgen(String correoAgen) {
        this.correoAgen = correoAgen;
    }

    public int getTelUno() {
        return telUno;
    }

    public void setTelUno(int telUno) {
        this.telUno = telUno;
    }

    public int getTelDos() {
        return telDos;
    }

    public void setTelDos(int telDos) {
        this.telDos = telDos;
    }

    public long getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(long idAgencia) {
        this.idAgencia = idAgencia;
    }
  
    
  
  
  
}
