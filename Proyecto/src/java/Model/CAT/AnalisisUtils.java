/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author jean
 */
public class AnalisisUtils {
    
    
      
    public List getTerminosLinea(String linea){
        //con este metodo dado una linea que se obtuvo de terminos lamina y el tesauro obtenemos una lista y asi
       //pode hacer contains
        List listapalabras = new ArrayList();
        String[] palabras= linea.split(",");
        
        for (int i = 0; i < palabras.length; i++) {
           listapalabras.add(palabras[i]);
              
        }   
      return listapalabras;  
    }
    
      public String quitarMayusculas(String texto){
      return texto.toLowerCase();  
    }
    
    public String saltoLineaToSpace(String relato){
        
         relato= relato.replace("\n", " ").replace("\t", " ").replace("\r", " ");
        return relato;
    }
    
     public String quitarNumeros(String relato){
        
         relato= relato.replace("0", "").replace("1", "").replace("2", "").replace("3", "").replace("4", "").replace("5", "");
        relato= relato.replace("6", "").replace("7", "").replace("0", "").replace("9", "");
         return relato;
    }
    
     public String quitarAcentos(String texto){
        
           // Cadena de caracteres original a sustituir.
    String original = "áàäéèëíìïóòöúùuÁÀÄÉÈËÍÌÏÓÒÖÚÙÜçÇ";
    // Cadena de caracteres ASCII que reemplazarán los originales.
    String ascii = "aaaeeeiiiooouuuAAAEEEIIIOOOUUUcC";
    String output = texto;
    for (int i=0; i<original.length(); i++) {
        // Reemplazamos los caracteres especiales.
        output = output.replace(original.charAt(i), ascii.charAt(i));
    }//for i
    return output;
    }
    
       public  List separarStopwords (List stopwords,String Relato){
           
          // le quitamos los saltos de linea al texto, las tabulaciones y los 
           Relato=saltoLineaToSpace(Relato);
            Relato=quitarMayusculas(Relato);
           Relato=quitarNumeros(Relato);
           Relato=quitarAcentos(Relato);
        
 List lista = new ArrayList();
String delimitadores= "[ .,;?!¡¿\'\"\\[\\]\\(\\)\\t]+";
String[] palabrasSeparadas = Relato.split(delimitadores);

                for (String palabrasSeparada : palabrasSeparadas) {
                    
                   // System.out.println(palabrasSeparada);
                    //evaluamos si la palabra no está en las stopwords
                    if(!stopwords.contains(palabrasSeparada))
                        lista.add(palabrasSeparada);
                        
                    
                    
                } 
                return lista;
    }
   
    
        public  int calcularFrec(List listaTerminos,String termino){
        int frec=0;
        
         for (Object term : listaTerminos) {
            if(termino.equals(term))
                frec++;
     
        }
   
     return frec;   
    }
       
    


  public  boolean existeTermino(List<Termino> lista, String terminoEvaluar){
          boolean existe=false;
          
          
           for (Termino term: lista){
               
               if(term.getPalabra().equals(terminoEvaluar)){
                   return true;   
               }     
               }
          
          return existe;
  
      }
    
  
  //comparador para orden descendente
   public static Comparator<TerminosGenerales> ordenarTerminosGenerales = new Comparator<TerminosGenerales>(){
           @Override
           public int compare(TerminosGenerales a, TerminosGenerales b){
               int resultado= Integer.compare(b.getFrecuencia(), a.getFrecuencia());
               if (resultado!=0) {return resultado;}
               
              
                return resultado;
               
               
        }};
             
  
    
    
    
    
    
    


    
    
    
    
    
}
