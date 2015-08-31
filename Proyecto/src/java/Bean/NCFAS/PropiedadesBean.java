package Bean.NCFAS;

import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;
 
@ManagedBean(name = "propiedadesBean")

public class PropiedadesBean {
    
     public void viewItem1() {
        RequestContext.getCurrentInstance().openDialog("propItem1");
    }
}
