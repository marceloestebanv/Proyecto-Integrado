package Converter.NCFAS;

import com.google.common.primitives.UnsignedBytes;
import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcelo Verdugo
 */
@FacesConverter("procesoConverter")
public class ProcesoConverter implements Converter{
  
    
@Override
public String getAsString(FacesContext context, UIComponent component, Object value){
int parteproceso;
String parteprocesostring="";
    
    if(value!=null){
    parteproceso = (int)value;
        switch(parteproceso){
            case 1:
                parteprocesostring="Inicial";
                break;
            case 2:
                parteprocesostring="Porceso";
                break;
            case 3:
                parteprocesostring="Final";
                break;
        }
    }
    return parteprocesostring;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
