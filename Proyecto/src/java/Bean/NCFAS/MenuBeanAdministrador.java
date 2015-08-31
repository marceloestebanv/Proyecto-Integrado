/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.NCFAS;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
public class MenuBeanAdministrador {
    private  FacesContext contex = FacesContext.getCurrentInstance();
    
    public void nuevo() throws IOException { 
        contex.getExternalContext().redirect("/Proyecto/faces/nuevousuario.xhtml");
    }
     
    public void modificar() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/modificarusuario_1.xhtml");
    }
     
    public void eliminar() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/eliminarusuario.xhtml");
    }
    
    public void sistema() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/sistemaindex.xhtml");
    }
    
    public void ingresoNcfas() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/nuevoncfas.xhtml");
    }
    
    public void mineriadedatos() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/mineriadedatos.xhtml");
    }
    
    public void estadistica() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/estadisticadescriptiva_1.xhtml");
    }
   
    public void verncfas() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/gestionarncfases.xhtml");
    }
     
   public void verNcfases() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/itemsncfas_listar.xhtml");
    }
   
   public void verNcfasIniciales() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/verncfasporinicial.xhtml");
    }
   
   public void verNcfasFinales() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/verncfasporfinal.xhtml");
    }
   
   public void verNcfasPorFecha() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/verncfasporfecha.xhtml");
    }
   
   public void comparar() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/estadisticadescriptiva_comparacion.xhtml");
    }
   
   public void obtenercomparacion() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/estadisticadescriptivaDatos_comparacion.xhtml");
    }
   
    public void comparar2() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/estadisticadescriptiva_comparacion_2.xhtml");
    }
   
    public void obtenercomparacion2() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/comparacion_por_items_generales.xhtml");
    }
    
    public void comparar3() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/estadisticadescriptiva_comparacion_3.xhtml");
    }
   
    public void obtenercomparacion3() throws IOException {
        contex.getExternalContext().redirect("/Proyecto/faces/NCFASPages/comparacion_por_fecha.xhtml");
    }
    
    public void viewitem1() {
        RequestContext.getCurrentInstance().openDialog("propItem1");
    }
    
}
