/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.NCFAS;

import Model.NCFAS.Usuario;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Marcelo Verdugo
 */

@Named(value = "mbsesionBean")
@ViewScoped
public class MbSesion {
    
    
     int tipo;
    private String nombreusuario;
    private List<Usuario> usuarios;
    private  HttpServletRequest httpservletrequest;
    private  FacesMessage facemassage;
    private FacesContext facecontext;
    
    public MbSesion(){
        
         FacesContext facesContext = FacesContext.getCurrentInstance();
        httpservletrequest=(HttpServletRequest)facesContext.getExternalContext().getRequest();
        if(httpservletrequest.getSession().getAttribute("sesionusuario")!=null){
            nombreusuario=httpservletrequest.getSession().getAttribute("sesionusuario").toString();
        }
    
    }
    
    public String cerrarSesion(){
        httpservletrequest.getSession().removeAttribute("sesionusuario");
        return "index";
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

   

   

    
    
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public HttpServletRequest getHttpservletrequest() {
        return httpservletrequest;
    }

    public void setHttpservletrequest(HttpServletRequest httpservletrequest) {
        this.httpservletrequest = httpservletrequest;
    }

    public FacesMessage getFacemassage() {
        return facemassage;
    }

    public void setFacemassage(FacesMessage facemassage) {
        this.facemassage = facemassage;
    }

    public FacesContext getFacecontext() {
        return facecontext;
    }

    public void setFacecontext(FacesContext facecontext) {
        this.facecontext = facecontext;
    }
    
    
    
}
