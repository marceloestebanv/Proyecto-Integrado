package Bean.NCFAS;

import Dao.NCFAS.NcfasDAO;
import Model.NCFAS.Item;
import java.util.List;
import Model.NCFAS.Ncfas;
import Model.NCFAS.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;



@ManagedBean 
@Named(value = "ncfasbean")
@SessionScoped
@RequestScoped


public class NcfasBean {
    
    
    List<Ncfas> ncfases;
    List<Ncfas> ncfases2;
    Ncfas ncfas;
    private Usuario usuario;
    private String nombrefamilia;
    private int parteproceso;
    public int id=40;
    private String date1;
    private String date2;

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
    
    
    
    public Ncfas getNcfas() {
        return ncfas;
    }

    public void setNcfas(Ncfas ncfas) {
        this.ncfas = ncfas;
    }

    
    public List<Ncfas> getNcfases() {
        return ncfases;
    }

    public void setNcfases(List<Ncfas> ncfases) {
        this.ncfases = ncfases;
    }

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setIdncfas(int id) {
        this.id = id;
    }


    public String getNombrefamilia() {
        return nombrefamilia;
    }

    public void setNombrefamilia(String nombrefamilia) {
        this.nombrefamilia = nombrefamilia;
    }

    public int getParteproceso() {
        return parteproceso;
    }

    public void setParteproceso(int parteproceso) {
        this.parteproceso = parteproceso;
    }
    
     public NcfasBean() {
        System.out.println("entramos al contructor del NCFAS");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        
    }

    public void registrarNcfas() throws Exception {
 
    NcfasDAO dao;
    FacesContext contex = FacesContext.getCurrentInstance();
        
        System.out.println(nombrefamilia);
        System.out.println(parteproceso);
        
    try {
            dao = new NcfasDAO();
           dao.ingresarNcafas(nombrefamilia,parteproceso/*,usuario*/);
           contex.getExternalContext().redirect("/Proyecto/faces/nuevositemsncfas_1.xhtml");
        } catch (Exception e) {
            throw e;
        }
    }

 public void modificar(){
    NcfasDAO linkDAO= new NcfasDAO();
        linkDAO.modificarNcfas(ncfas);
        ncfas= new Ncfas();
    }
    
 public void eliminar(){
        System.out.println("nombre familia:" +ncfas.getNombrefamilia());
    NcfasDAO linkDAO= new NcfasDAO();
        linkDAO.eliminarNcfas(ncfas);
        ncfas= new Ncfas();
    }
/*    public void mostrarNcfases() throws Exception {
    NcfasDAO dao;
    
        try {
          dao = new NcfasDAO();
          ncfases = dao.mostrarListNcfas();
        } catch (Exception e) {
            throw e;
        }
    }*/
    /*public  void  () throws Exception{
        /*try {
        NcfasDAO linkDAO= new NcfasDAO();
        ncfases=linkDAO.mostrarNcfas();
        } catch (Exception e) {
        }
    return ncfases;
        
    }
    
     
        NcfasDAO dao;
    
        try {
            dao = new NcfasDAO();
            ncfases = dao.mostrarNcfas();
        } catch (Exception e) {
            throw e;
        }
    
}
    public void leerID(Ncfas ncfas){
    
        int idncfas=0;
        idncfas=ncfas.getIdncfas();
        NcfasDAO linkDAO= new NcfasDAO();
        linkDAO.leerIdncfas();
        return ncfases;
    }*/
 public void outcome() throws Exception{
        FacesContext contex = FacesContext.getCurrentInstance();
        try{
           // ChartView algo = new ChartView();
            //algo.createLineModels1(id);
            contex.getExternalContext().redirect("/Proyecto/faces/result.xhtml");
	}catch(Exception e){
            throw e;
        }
    }   

 
    public List<Ncfas> listarNcfas() throws Exception {
       NcfasDAO linkDAO= new NcfasDAO();
        ncfases=linkDAO.mostrarNcfases();
        return ncfases;
    }
    
    public List<Ncfas> listarNcfasporinicial() throws Exception {
       NcfasDAO linkDAO= new NcfasDAO();
        ncfases=linkDAO.mostrarNcfasesIniciales();
        return ncfases;
    }
    
    public List<Ncfas> listarNcfasporfinal() throws Exception {
       NcfasDAO linkDAO= new NcfasDAO();
        ncfases=linkDAO.mostrarNcfasesFinales();
        return ncfases;
    }
    
    public List<Ncfas> listarNcfasfechas() throws Exception {
       NcfasDAO linkDAO= new NcfasDAO();
        ncfases=linkDAO.mostrarNcfasesporFechas(date1,date2);
        return ncfases;
    }
    
     public List<Ncfas> listarNcfasInicialYFinal() throws Exception {
       int n;
       List<Ncfas> listaTemp;
       List<Ncfas> listaCompleta;
       listaCompleta = new ArrayList();
       Ncfas tempNcfas;
       NcfasDAO linkDAO= new NcfasDAO();
       //OBTENEMOS TODOS LOS NCFAS FINALES DE LA BASE DE DATOS
       ncfases=linkDAO.mostrarNcfasesFinales();
       // CREAMOS UNA VARIABLE ENTERO CON EL DOBLE DE LOS NCFAS FINALES OBTENIDOS
       n=ncfases.size()*2;
       
       
        NcfasDAO linkDAO2= new NcfasDAO();
        listaTemp = new ArrayList();
       //OBTENEMOS LOS NCFAS INICIALES A PARTIR DE LOS NCFAS FINALES OBTENIDOS 
        for(Ncfas ncfas: ncfases){
            tempNcfas = new Ncfas();
            tempNcfas=linkDAO2.obtenerNcfasporNombreFamilia(ncfas.getNombrefamilia());
            listaTemp.add(tempNcfas);
        }
        
        //LLENAMOS UNA LISTA CON LOS NCFAS FINALES Y LOS NCFAS INICIALES
        for(Ncfas ncfas: ncfases){
            listaCompleta.add(ncfas);
        }
        for(Ncfas ncfas: listaTemp){
            listaCompleta.add(ncfas);
        }
        return listaCompleta;
    }
    
}
