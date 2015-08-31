/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

import Dao.CAT.UsuarioDao;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Size;

/**
 *
 * @author jean
 */
@ManagedBean
public class Usuario {
    
    
  
  //  boolean existeUser;


    private String rut;
    
    private String nombre;
    private String correo;
    private String password;
    private String tipoUsuario;

    
    public Usuario(){
        
    }
    
    public Usuario(String rut, String nombre, String correo, String password) {
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    
   //  public boolean ExisteUser() {
   //     UsuarioDao dao= new UsuarioDao();
   //     return dao.existeRut(tipoUsuario);
   // }

  //  public void setExisteUser(boolean existeUser) {
  //      this.existeUser = existeUser;
  //  }
    
    
    
}
