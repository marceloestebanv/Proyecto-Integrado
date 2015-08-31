/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;

import Dao.CAT.UsuarioDao;
import Model.CAT.Usuario;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;



/**
 *
 * @author jean
 */
@ManagedBean(name = "loginControlador")
//@SessionScoped

public class LoginControlador {

    private Usuario usuario;
      private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private String testLogin;
    

  
    public LoginControlador() {
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        usuario=new Usuario();   
    }
    
    public void autenticar () throws ServletException, IOException{
        
        
        FacesContext contex = FacesContext.getCurrentInstance();
        
        System.out.println("usuario: "+usuario.getRut());
         System.out.println("pass: "+usuario.getPassword());
        UsuarioDao valida= new UsuarioDao();
        
        if (valida.existePersona(usuario)){
            System.out.println("existe en la bd"); 
            
            Usuario usuarioLocal=valida.getUsuario(usuario.getRut());
            
             httpServletRequest.getSession().setAttribute("sessionUsuario",usuario.getRut());
             httpServletRequest.getSession().setAttribute("sessionNombre",usuarioLocal.getNombre());
             httpServletRequest.getSession().setAttribute("sessionPass",usuarioLocal.getPassword());
             
             //el tipo de usuario se obtiene del dao pasandole el rut
             httpServletRequest.getSession().setAttribute("sessionTipoUs",valida.getTipoUsuario(usuario.getRut()));
             
           //  httpServletRequest.getSession().setAttribute("sessionTipoUsuario",usuario.getTipoUsuario());
        
             
             
             //esta linea redirige a cat-a
           contex.getExternalContext().redirect("/Proyecto/faces/welcomePrimefaces.xhtml");
            
        }else{
            
            
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario no encontrado."));
    
           
        }
        
        
    }
    
    
    public String logout() throws IOException {
        
        httpServletRequest.getSession().removeAttribute("sessionUsuario");
        httpServletRequest.getSession().removeAttribute("sessionNombre");
        httpServletRequest.getSession().removeAttribute("sessionPass");
        httpServletRequest.getSession().removeAttribute("sessionTipoUsuario");
       
         httpServletRequest.getSession(false).invalidate();
        
 
      
     //   facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Session cerrada correctamente", null);
     //   faceContext.addMessage(null, facesMessage);
         
         
 return "index";
   
}
    
    
      public Usuario getUsuario() throws IOException {
        
       
        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
        {
            usuario.setRut(httpServletRequest.getSession().getAttribute("sessionUsuario").toString());
            usuario.setNombre( httpServletRequest.getSession().getAttribute("sessionNombre").toString());
            usuario.setPassword(httpServletRequest.getSession().getAttribute("sessionPass").toString());
        }
           
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
 

    
}
