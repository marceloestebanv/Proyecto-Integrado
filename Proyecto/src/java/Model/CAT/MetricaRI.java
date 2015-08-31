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
public class MetricaRI  implements Serializable{
    
    private int idRelato;
    private int idTest;    
    private int cantidadTerminos;
    private int cantidadPositivos;
    private int cantidadNegativos;
    private int cantidadNeutros;
    private  int coincidencias;
    private List<TerminosGenerales> terminosGenerales;
    private static final long serialVersionUID = 1L;

     //Podria añadirse tambien las palabras que coincidieron

    
          @Override
        public MetricaRI clone() throws CloneNotSupportedException {
   MetricaRI side = null;
        try {
            side = (MetricaRI) super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e);
        }
        return side;
}

    public MetricaRI(int idRelato, int idTest, int cantidadTerminos, int cantidadPositivos, int cantidadNegativos, int cantidadNeutros, int coincidencias, List<TerminosGenerales> terminosGenerales) {
        this.idRelato = idRelato;
        this.idTest = idTest;
        this.cantidadTerminos = cantidadTerminos;
        this.cantidadPositivos = cantidadPositivos;
        this.cantidadNegativos = cantidadNegativos;
        this.cantidadNeutros = cantidadNeutros;
        this.coincidencias = coincidencias;
        this.terminosGenerales = terminosGenerales;
    }
    
    
       public MetricaRI(int idRelato, int idTest, int cantidadTerminos, int cantidadPositivos, int cantidadNegativos, int cantidadNeutros, int coincidencias) {
        this.idRelato = idRelato;
        this.idTest = idTest;
        this.cantidadTerminos = cantidadTerminos;
        this.cantidadPositivos = cantidadPositivos;
        this.cantidadNegativos = cantidadNegativos;
        this.cantidadNeutros = cantidadNeutros;
        this.coincidencias = coincidencias;
        
    }
    


    public MetricaRI() {
        
    }
     
     

    public int getIdRelato() {
        return idRelato;
    }

    public void setIdRelato(int idRelato) {
        this.idRelato = idRelato;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public int getCantidadTerminos() {
        return cantidadTerminos;
    }

    public void setCantidadTerminos(int cantidadTerminos) {
        this.cantidadTerminos = cantidadTerminos;
    }

    public int getCantidadPositivos() {
        return cantidadPositivos;
    }

    public void setCantidadPositivos(int cantidadPositivos) {
        this.cantidadPositivos = cantidadPositivos;
    }

    public int getCantidadNegativos() {
        return cantidadNegativos;
    }

    public void setCantidadNegativos(int cantidadNegativos) {
        this.cantidadNegativos = cantidadNegativos;
    }

    public int getCantidadNeutros() {
        return cantidadNeutros;
    }

    public void setCantidadNeutros(int cantidadNeutros) {
        this.cantidadNeutros = cantidadNeutros;
    }

    public int getCoincidencias() {
        return coincidencias;
    }

    public void setCoincidencias(int coincidencias) {
        this.coincidencias = coincidencias;
    }

    public List<TerminosGenerales> getTerminosGenerales() {
        return terminosGenerales;
    }

    public void setTerminosGenerales(List<TerminosGenerales> terminosGenerales) {
        this.terminosGenerales = terminosGenerales;
    }
     
     
     
     
     
     
     
     
     
    
    
}
