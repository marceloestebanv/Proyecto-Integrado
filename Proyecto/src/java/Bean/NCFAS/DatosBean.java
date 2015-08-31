
package Bean.NCFAS;



import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ManagedBean
@Named(value = "datosbean")
@RequestScoped
@ViewScoped



public class DatosBean implements Serializable   {
    
    private int id;
    
    private Integer[] valores;

    public Integer[] getValores() {
        return valores;
    }

    public void setValores(Integer[] valores) {
        this.valores = valores;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public DatosBean() {
    
    System.out.println("entramos al contructor");
        valores = new Integer[4];

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        //Integer categorySelected = new Integer((String) params.get("username" ));
        

        for (int i = 1; i < valores.length; i++) {
            valores[i] = 0;
        }
        
    
    }
    
     public void guardarIDSeleccionados(int id) throws Exception{
         
         try{
     
         System.out.println(id);
         }catch(Exception e){
         throw e;
         }
         
         
     
     }

    
    
}
