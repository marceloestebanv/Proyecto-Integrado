/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jean
 */
public class Termino implements Serializable {
    
    private int idTest;
    private int idLámina;
    private int idTermino;
    private String palabra;
    private String terminoAsociado="-";
    private double frecuencia;
    private int connotacion;
    private static final long serialVersionUID = 1L;

    public String getTerminoAsociado() {
        return terminoAsociado;
    }

    public void setTerminoAsociado(String terminoAsociado) {
        this.terminoAsociado = terminoAsociado;
    }
    
    

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
 

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public int getIdLámina() {
        return idLámina;
    }

    public void setIdLámina(int idLámina) {
        this.idLámina = idLámina;
    }

    public int getIdTermino() {
        return idTermino;
    }

    public void setIdTermino(int idTermino) {
        this.idTermino = idTermino;
    }

    public double getFrecuencia() {
        return frecuencia;
    }
    
  
    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public int getConnotacion() {
        return connotacion;
    }

    public void setConnotacion(int connotacion) {
        this.connotacion = connotacion;
    }
  
  @Override  
public boolean equals(Object o){
    if(o instanceof Termino){
        Termino toCompare = (Termino) o;
        if(toCompare.connotacion==this.connotacion&&toCompare.palabra.equals(this.palabra)&&toCompare.terminoAsociado.equals(this.terminoAsociado))
        return true;
       
    }
    return false;
}
   

 @Override
    public int hashCode(){
        return Objects.hashCode(this.getPalabra());
    }
}
