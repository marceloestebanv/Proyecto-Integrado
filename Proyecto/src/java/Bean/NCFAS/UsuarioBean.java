/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.NCFAS;


import Dao.NCFAS.DimensionesDao;
import Dao.NCFAS.PruebaWekaDao;
import Dao.NCFAS.UsuarioDao;
import Model.NCFAS.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jean
 */
@Named(value = "usuarioBean")
@ViewScoped
public class UsuarioBean {

    /**
     * Creates a new instance of UsuarioBean
     */
    
    int tipo;
    Usuario usuario;
    List<Usuario> usuarios;
    private  HttpServletRequest httpservletrequest;
    private  FacesMessage facemassage;
    private FacesContext facecontext;
    
    
    public int getTipo() {
        return tipo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
    
    public UsuarioBean() {
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //httpservletrequest=(HttpServletRequest)facesContext.getExternalContext().getRequest();
        //ExternalContext externalContext = facesContext.getExternalContext();
        //Map params = externalContext.getRequestParameterMap();
        usuario = new Usuario();
    }

    
    public void insertar() throws Exception{
        
        System.out.println("nombre usuario bean:" +usuario.getNombre());
        System.out.println("nombre usuario bean:" +usuario.getCorreo());
        System.out.println("nombre usuario bean:" +usuario.getRut());
        System.out.println("nombre usuario bean:" +usuario.getPassword());
        System.out.println("tipo " +tipo);
    UsuarioDao linkDAO= new UsuarioDao();
        linkDAO.insertarUsuario(usuario,tipo);
        usuario= new Usuario();
        
    }
    public void modificar(){
    UsuarioDao linkDAO= new UsuarioDao();
        linkDAO.modificarUsuario(usuario);
        usuario= new Usuario();
    }
    public void eliminar(){
            System.out.println("nombre usuario bean:" +usuario.getRut());
            
    UsuarioDao linkDAO= new UsuarioDao();
        if(linkDAO.tipoUsuario(usuario)==2){
            linkDAO.eliminarAsistente(usuario);
            linkDAO.eliminarUsuario(usuario);
            usuario= new Usuario();
        } if(linkDAO.tipoUsuario(usuario)==3){
            linkDAO.eliminarPsicologo(usuario);
            linkDAO.eliminarUsuario(usuario);
            usuario= new Usuario();
        }
        else{
            linkDAO.eliminarUsuario(usuario);
        }
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> listarUsuarios() throws Exception {
       UsuarioDao linkDAO= new UsuarioDao();
        usuarios=linkDAO.mostrarUsuarios();
        return usuarios;
    }
    

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
   public String autenticar(){
       
        FacesContext contex = FacesContext.getCurrentInstance(); 
        httpservletrequest=(HttpServletRequest)contex.getExternalContext().getRequest();
        System.out.println("usuario: "+usuario.getRut());
        System.out.println("pass: "+usuario.getPassword());
        UsuarioDao valida= new UsuarioDao();        
        if (valida.existePersona(usuario)){
            usuario=valida.retornaUsuario(usuario);
            System.out.println("pass: "+usuario.getNombre());
            System.out.println("existe en la bd");
                if(valida.tipoUsuario(usuario)==1){
                    httpservletrequest.getSession().setAttribute("sesionusuario", usuario.getNombre());
                    facemassage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario encontrado", null);
                    return "administradorV2";
                  // contex.getExternalContext().redirect("/Proyecto/faces/administradorV2.xhtml?rut=" +usuario.getRut());
                   
                } else if(valida.tipoUsuario(usuario)==2 || valida.tipoUsuario(usuario)==3 ){
                    httpservletrequest.getSession().setAttribute("sesionusuario", usuario.getNombre());
                    facemassage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario encontrado", null);
                    return "sistemaindex";
                   //contex.getExternalContext().redirect("/Proyecto/faces/sistema.xhtml?rut=" +usuario.getRut());
                } 
                
                
        }else{
        facemassage = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario no encontrado", null);
        return "index";
        }   
        return "index";
    }
      
   /*public void pruebaweka() throws Exception{
       PruebaWekaDao dao = new PruebaWekaDao();
       dao.obtenerAsociaciones();
   }*/
   /* public void obtenerValores() throws SQLException, ClassNotFoundException{
     Integer listaValores[];
     listaValores = new Integer[8];
    DimensionesDao dao;  
        dao = new DimensionesDao();
        listaValores=dao.obtenerValoresDim1();
    }*/
   public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
  
}