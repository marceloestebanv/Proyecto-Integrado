/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jean
 */
public class TerminoLamina implements Serializable{
    
    private String termino;
    private int connotacion;
    private List<String> terminosAsociados;
    private static final long serialVersionUID = 1L;
    
    public TerminoLamina(){
        
    }
         
    

    public TerminoLamina(String termino, int connotacion, List terminosAsociados) {
        this.termino = termino;
        this.connotacion = connotacion;
        this.terminosAsociados = terminosAsociados;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public int getConnotacion() {
        return connotacion;
    }

    public void setConnotacion(int connotacion) {
        this.connotacion = connotacion;
    }

    public List<String> getTerminosAsociados() {
        return terminosAsociados;
    }

    public void setTerminosAsociados(List<String> terminosAsociados) {
        this.terminosAsociados = terminosAsociados;
    }

   public String getTerminosAsociadosString(){
       String term="";
       if(terminosAsociados!=null){
       for(Object termin:terminosAsociados){
          term=term+", "+termin;           
       }
       }
    term=term.replaceFirst(",","");
      
       return term;
   }
    
    
    
    
    
}
