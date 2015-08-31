package Bean.NCFAS;

import Dao.NCFAS.DAOException;
import Dao.NCFAS.IdDAO;
import Dao.NCFAS.ItemDao;
import Model.NCFAS.Item;
import Model.NCFAS.Ncfas;
import java.util.List;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;



@ManagedBean 
@Named(value = "itembean")
@RequestScoped
@ViewScoped

public class ItemBean implements Serializable{
    
    private Item item;
    private Ncfas ncfas;
    private Integer[] valores;
    private Integer[] valoresID;
    private List<Item> items; 
    private int idamandar;

    public int getIdamandar() {
        return idamandar;
    }

    public void setIdamandar(int idamandar) {
        this.idamandar = idamandar;
    }

    
    public Integer[] getValoresID() {
        return valoresID;
    }

    public void setValoresID(Integer[] valoresID) {
        this.valoresID = valoresID;
    }
    
    
    
    public Ncfas getNcfas() {
        return ncfas;
    }

    public void setNcfas(Ncfas ncfas) {
        this.ncfas = ncfas;
    }
       

    public Integer[] getValores() {
        return valores;
    }

    public void setValores(Integer[] valores) {
        this.valores = valores;
    }

    public ItemBean(Integer[] valores) {
        this.valores = valores;
    }

    public ItemBean(List<Item> items) {
        this.items = items;
    }
    

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    

    public ItemBean() {
        System.out.println("entramos al contructor");
        valores = new Integer[59];
        valoresID = new Integer[4];

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        //Integer categorySelected = new Integer((String) params.get("username" ));
        
        for (int i = 1; i < valoresID.length; i++) {
            valoresID[i] = 0;
        }
        
        for (int i = 1; i < valores.length; i++) {
            valores[i] = 0;
        }
    }
  

    public void registrarEntorno() throws Exception {
        int bandera=0;
        for (int i = 1; i<=7; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==7){
               bandera=1;
           dao.ingresarItems1(valores[i],bandera);   
           }else{
           dao.ingresarItems1(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}

    public void registrarCompetencias() throws Exception {
        int bandera=0;
        for (int i = 8; i<=15; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==15){
               bandera=1;
           dao.ingresarItems2(valores[i],bandera);   
           }else{
           dao.ingresarItems2(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}
    public void registrarInteracciones() throws Exception {
        int bandera=0;
        for (int i = 16; i<=23; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==23){
               bandera=1;
           dao.ingresarItems3(valores[i],bandera);   
           }else{
           dao.ingresarItems3(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}
    public void registrarSeguridad() throws Exception {
        int bandera=0;
        for (int i = 24; i<=31; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==31){
               bandera=1;
           dao.ingresarItems4(valores[i],bandera);   
           }else{
           dao.ingresarItems4(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}
    public void registrarBienestar() throws Exception {
        int bandera=0;
        for (int i = 32; i<=38; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==38){
               bandera=1;
           dao.ingresarItems5(valores[i],bandera);   
           }else{
           dao.ingresarItems5(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}
    public void registrarVidaSocial() throws Exception {
        int bandera=0;
        for (int i = 39; i<=44; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==44){
               bandera=1;
           dao.ingresarItems6(valores[i],bandera);   
           }else{
           dao.ingresarItems6(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}
    public void registrarAutonomia() throws Exception {
        int bandera=0;
        for (int i = 45; i<=50; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==50){
               bandera=1;
           dao.ingresarItems7(valores[i],bandera);   
           }else{
           dao.ingresarItems7(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}
    public void registrarSalud() throws Exception {
        int bandera=0;
        for (int i = 51; i<=58; i++) {
            System.out.println(valores[i]);
        ItemDao dao;
        try {       
           dao = new ItemDao();
           if(i==58){
               bandera=1;
           dao.ingresarItems8(valores[i],bandera);   
           }else{
           dao.ingresarItems8(valores[i],bandera);
           }
           } catch (Exception e) {
            throw e;}
        }
}
    

public void obtenerUltimoid() throws Exception {
    
    ItemDao dao;
    dao= new ItemDao();
    try { 
        dao.obtenerIdNcfas();
    }catch(Exception e){
        
        throw e;
    }}

public void mostrarID(int idncfas) throws Exception{

    System.out.println("estamos guarand esta shit ->"+idncfas);
    IdDAO iddao = new IdDAO();
    iddao.guardarID(idncfas);
}


   





}
    
    
    /*public void mostrarItems() throws Exception {
    ItemDao dao;
    
        try {
          dao = new ItemDao();
          items = dao.mostrItems();
        } catch (Exception e) {
            throw e;
        }
    }
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
    
}*/
