/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.NCFAS;

import Dao.NCFAS.DAOException;
import Dao.NCFAS.IdDAO;
import Dao.NCFAS.ItemDao;
import Dao.NCFAS.NcfasDAO;
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
@Named(value = "listaritembean")
@RequestScoped
@ViewScoped

public class ListarItemsBean implements Serializable{
    
    private Item item;
    private Ncfas ncfas;
    private Integer[] valores1;
    private Integer[] valores2;
    private Integer[] valores3;
    private Integer[] valores4;
    private Integer[] valores5;
    private Integer[] valores6;
    private Integer[] valores7;
    private Integer[] valores8;
    private Integer[] valoresID;
    private List<Item> items; 
    private int idamandar;
    private String nombrefamilia;

    public String getNombrefamilia() {
        return nombrefamilia;
    }

    public void setNombrefamilia(String nombrefamilia) {
        this.nombrefamilia = nombrefamilia;
    }
    
    

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Ncfas getNcfas() {
        return ncfas;
    }

    public void setNcfas(Ncfas ncfas) {
        this.ncfas = ncfas;
    }

    public Integer[] getValores1() {
        return valores1;
    }

    public void setValores1(Integer[] valores1) {
        this.valores1 = valores1;
    }

    public Integer[] getValores2() {
        return valores2;
    }

    public void setValores2(Integer[] valores2) {
        this.valores2 = valores2;
    }

    public Integer[] getValores3() {
        return valores3;
    }

    public void setValores3(Integer[] valores3) {
        this.valores3 = valores3;
    }

    public Integer[] getValores4() {
        return valores4;
    }

    public void setValores4(Integer[] valores4) {
        this.valores4 = valores4;
    }

    public Integer[] getValores5() {
        return valores5;
    }

    public void setValores5(Integer[] valores5) {
        this.valores5 = valores5;
    }

    public Integer[] getValores6() {
        return valores6;
    }

    public void setValores6(Integer[] valores6) {
        this.valores6 = valores6;
    }

    public Integer[] getValores7() {
        return valores7;
    }

    public void setValores7(Integer[] valores7) {
        this.valores7 = valores7;
    }

    public Integer[] getValores8() {
        return valores8;
    }

    public void setValores8(Integer[] valores8) {
        this.valores8 = valores8;
    }

    public Integer[] getValoresID() {
        return valoresID;
    }

    public void setValoresID(Integer[] valoresID) {
        this.valoresID = valoresID;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getIdamandar() {
        return idamandar;
    }

    public void setIdamandar(int idamandar) {
        this.idamandar = idamandar;
    }
    
    
    
    public ListarItemsBean() throws Exception {
    System.out.println("entramos al contructor");
        valores1 = new Integer[9];
        valores2 = new Integer[9];
        valores3 = new Integer[9];
        valores4 = new Integer[9];
        valores5 = new Integer[9];
        valores6 = new Integer[9];
        valores7 = new Integer[9];
        valores8 = new Integer[9];
        valoresID = new Integer[4];

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        //Integer categorySelected = new Integer((String) params.get("username" ));
        
        for (int i = 1; i < valoresID.length; i++) {
            valoresID[i] = 0;
        }
        
        for (int i = 1; i < valores1.length; i++) {
            valores1[i] = 0;
        }
        for (int i = 1; i < valores2.length; i++) {
            valores2[i] = 0;
        }
        for (int i = 1; i < valores3.length; i++) {
            valores3[i] = 0;
        }
        for (int i = 1; i < valores4.length; i++) {
            valores4[i] = 0;
        }
        for (int i = 1; i < valores5.length; i++) {
            valores5[i] = 0;
        }
        for (int i = 1; i < valores6.length; i++) {
            valores6[i] = 0;
        }
        for (int i = 1; i < valores7.length; i++) {
            valores7[i] = 0;
        }
        for (int i = 1; i < valores8.length; i++) {
            valores8[i] = 0;
        }
        listarEntorno();
        listarDim2();
        listarDim3();
        listarDim4();
        listarDim5();
        listarDim6();
        listarDim7();
        listarDim8();
    }
    
    public void listarEntorno() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores1=dao.obtenerPuntajesDim1(1,dao2.retornarID());
        System.out.println(valores1[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
    public void listarDim2() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores2=dao.obtenerPuntajesDim1(2,dao2.retornarID());
        System.out.println(valores1[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
    public void listarDim3() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores3=dao.obtenerPuntajesDim1(3,dao2.retornarID());
        System.out.println(valores1[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
    public void listarDim4() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores4=dao.obtenerPuntajesDim1(4,dao2.retornarID());
        System.out.println(valores1[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
    public void listarDim5() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores5=dao.obtenerPuntajesDim1(5,dao2.retornarID());
  
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
    public void listarDim6() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores6=dao.obtenerPuntajesDim1(6,dao2.retornarID());
        System.out.println(valores6[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
    public void listarDim7() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores7=dao.obtenerPuntajesDim1(7,dao2.retornarID());
        System.out.println(valores7[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
    public void listarDim8() throws Exception{
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     valores8=dao.obtenerPuntajesDim1(8,dao2.retornarID());
        System.out.println(valores8[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    
     public void retornaNombreFamilia() throws Exception{
     
     try{
     NcfasDAO dao2;  
     dao2 = new NcfasDAO();
     
     IdDAO dao;  
     dao = new IdDAO();
     nombrefamilia=dao2.obtenerNombreFamilia(dao.retornarID());
        System.out.println(valores1[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
}
}
