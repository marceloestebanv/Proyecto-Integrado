package Bean.CAT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Model.CAT.AnalisisUtils;
import Model.CAT.MetricaRI;
import Model.CAT.Stemm_es;
import Model.CAT.Termino;
import Model.CAT.TerminoLamina;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


/**
 *
 * @author jean
 */
//@ManagedBean(name = "recuperacionInformacion")
//@ApplicationScoped

public class RI{


    private String relato;
    private int idRelato;
    private int idTest;    
     private int cantidadTerminos;
     private int cantidadPositivos;
     private int cantidadNegativos;
    private int cantidadNeutros;
     private  int coincidencias;
     
     private List<Termino> terminos;
     private MetricaRI metrica;
   
    private TerminoLamina terminoLamina;

 
    
    private  static final  List[] listaTerminosLaminas=llenarListaTerminosLamina("TerminosLaminas.txt");
    
     private static final   List[] listaTesauro=llenarListaTerminosLamina("Tesauro.txt");
    
      //levamos archivo de stop words a una lista
   //private static    List listaStopwors = llenarLista("stopWords.txt");
     private static    List listaStopwors;
    private static final   List listaPositivas= llenarLista("Positivas.txt");
   private static final   List listaNegativas=llenarLista("Negativas.txt");
   
   //queda al ultimo ya que necesita de las otras listas
   // private static    List<TerminoLamina>[] listaTerminosLaminas2=llenarListaTerminosLamina2("TerminosLaminas.txt");
    
   private static  List<TerminoLamina>[] listaTerminosLaminas2;
   
    /**
     * Creates a new instance of AnalisisBean
     */
    
   
   public RI(){
       
   }
 
   
   //constructor que toma como parametros los datos del test y el relato que se está evaluando
    public RI(int idTest,int idRelato,String relato) throws IOException, FileNotFoundException, ClassNotFoundException {
        this.relato = relato;
        this.idRelato = idRelato;
        this.idTest = idTest;
       
        
         this.cantidadPositivos=0;
      this.cantidadNegativos=0;
      this.cantidadNeutros=0;
       this.coincidencias=0;
      RI.listaTerminosLaminas2= deserializarTerminos();
      deserializarStopWords();
        
        
    }
   
   
  
    
    public void analizarRI() throws IOException{
       terminos=preprocesarRelato();
     //  metrica=generarMetrica(terminos);
    

    }
     
 
    public List<Termino> preprocesarRelato() throws IOException{
       
        AnalisisUtils analisis =new AnalisisUtils();
 
        System.out.println(" comienza el analisis");
        

             List<Termino> terminos=new ArrayList<>();
             int connotacion;
        int frecuencia;
     
        
             
        
         //quitamos los separadores y los stopwords
      List listaSeparada= analisis.separarStopwords(listaStopwors,relato); //tener cuidado aca ingresamos el relato que estamos viendo
       
      //calculamos la cantidad de palabras que tiene la lista separada
      
      cantidadTerminos=listaSeparada.size();
      
      
      
      
        for (Object listaSeparada1 : listaSeparada) {
            System.out.println("palabra :"+listaSeparada1);
            
        }
         
   // los ordenamos
     Collections.sort (listaSeparada);
     
     
     //Acá haremos una lista de objetos téminos con su respectivo id test, id relato , connotación, etc

      for (Object nuevotermino : listaSeparada) {
          
           connotacion = -1;
          
          if (listaPositivas.contains(nuevotermino)){
              connotacion=1;
             cantidadPositivos++; 
          }else if (listaNegativas.contains(nuevotermino)){
              connotacion=0;
              cantidadNegativos++;
          }else{
           cantidadNeutros++;   
          }
         //calculamos frecuencia;
         //   frecuencia= analisis.calcularFrec(listaSeparada, (String) nuevotermino);
         
       //        if((terminos.isEmpty())||!analisis.existeTermino(terminos, (String) nuevotermino)){
         
                   // si no existe  ya en la lista de terminos creamos el nuevo objeto
                   Termino termInsert= new Termino();
                   termInsert.setIdTest(idTest);
                   termInsert.setIdLámina(idRelato); //Aca seteamos el id del relato
                   termInsert.setPalabra((String) nuevotermino);
                   //si la coinciddencia no es vacia
                   TerminoLamina coincidencia=getTerminoCoincidencia((String)nuevotermino);
                   
                   //aca seteamos  la connotacion y a que termino esta asociado la coincidencia si es que no es vacia
                   if(!coincidencia.getTermino().equals("")){
                    termInsert.setTerminoAsociado(coincidencia.getTermino());
                    termInsert.setConnotacion(coincidencia.getConnotacion());
                    
                   }
                       
                       
                  termInsert.setFrecuencia(0);
                 termInsert.setIdTest(idTest);
                 termInsert.setIdLámina(idRelato);
                   terminos.add(termInsert);
                   
                   
               }        
                
 //          }     
          
       
  
        
      
// por cada termino, tenemos que buscarlo en la lista de terminos para la lámina a la que pertenece
       //terminos
        
       
      
      
      
      
      return terminos;
      
  
    }
    
    
    

    
/* 
  
    public TerminoLamina getTerminoCoincidencia(String pal){
        //este metodo segun una palabra entrega el termino lamina en el que hubo coincidencia, si no la hay 
        // devuelve en el termino un ""
        
     TerminoLamina terminodeLamina= new TerminoLamina();
    for(TerminoLamina termino:listaTerminosLaminas2[idRelato]){
      if (pal.equals(termino.getTermino()))  
        return termino;
      else if(termino.getTerminosAsociados().contains(pal))
              return termino;
    }
    
    //si llega aca no hay nada
    terminodeLamina.setTermino("");
    return terminodeLamina;
}
    
  */
    
    
     
    public TerminoLamina getTerminoCoincidencia(String pal){
        //este metodo segun una palabra entrega el termino lamina en el que hubo coincidencia, si no la hay 
        // devuelve en el termino un ""
     
        
        //definimos Objeto para realizar comparaciones segun la raiz
        Stemm_es raiz = new Stemm_es();
        
     TerminoLamina terminodeLamina= new TerminoLamina();
    for(TerminoLamina termino:listaTerminosLaminas2[idRelato]){
        
        //si coincide el termino o la raiz es igual se encuentra coincidencia
      if (pal.equals(termino.getTermino()) ||(raiz.stemm(pal).equals(raiz.stemm(termino.getTermino()))))  {
        return termino;
      }else{
      
          // si no coincide el termino buscamos en sus terminos asociados y si coincide este o su raiz, retornamos
        for (String terminoAsociado:termino.getTerminosAsociados()){
          
            if (pal.equals(terminoAsociado) ||(raiz.stemm(pal).equals(raiz.stemm(terminoAsociado))))  
                return termino;
        } 
          
      }
      
      
    }
    
    //si llega aca no hay nada
    terminodeLamina.setTermino("");
    return terminodeLamina;
}
    
    
   
    public  MetricaRI generarMetrica(List<Termino> terminos){
        
        AnalisisUtils analisis= new AnalisisUtils();
         
        for (Termino termino : terminos) {
            //boolean existe=false;
           
           //buscamos en lista de palabraas para ese
            
            // sabemos que el id de la lamina del temino, tiene que ser igual al id de la lista de las lineas con terminos
            //que queremos obtener
          
            
            for (Object lineaConTerminos: listaTerminosLaminas[termino.getIdLámina()] ){
                
               
              
                //separamos la linea de la lista correspondiente en termino
                List terminosSeparados=analisis.getTerminosLinea((String) lineaConTerminos);
                            
              
                
                //por cada termino comparamos si existe en la linea de terminos, si existe, añadimos una coincidencia
                if(terminosSeparados.contains(termino.getPalabra())){
                    
                    System.out.println("coincidio en la lista de terminos"+termino.getPalabra());
                   
                    termino.setTerminoAsociado(termino.getPalabra());
                    
                    coincidencias++;
                    //existe=true;
                }else{
                    
                   //la lista con terminos separados en su segundo item contiene el id de la linea que tenemos que revisar del tesauro
                   // System.out.println(" el indice que esperamos es:"+terminosSeparados.get(1));
                    String indiceString= (String)terminosSeparados.get(1);
                    
                    int idLineaTesauro= Integer.parseInt(indiceString);
                    //Aca tenemos que obtener la linea exacta que coincide desde el id del termino al id del tesauro
                    String lineaTesauro=(String) listaTesauro[termino.getIdLámina()].get(idLineaTesauro-1);
                   // System.out.println(" mostramos la linea del tesauro a evaluar"+lineaTesauro);
                    
                    //separamos la linea de la lista correspondiente en termino
                  List terminosSeparadosTesauro=analisis.getTerminosLinea((String) lineaTesauro);
                  
                   if (terminosSeparadosTesauro.contains(termino.getPalabra())){
                      coincidencias++;
                       System.out.println("coincidio en el tesauro"+termino.getPalabra());
                       
                       //Esta es la que vale
                       System.out.println("La correspondiente palabra en la lista de terminos es: "+terminosSeparados.get(2));
                       termino.setTerminoAsociado((String)terminosSeparados.get(2));
                  }
                  
                    
                    
                    
                }  
                
            }
              
            
            
         //final del analisis de la lista de terminos   
        }
        
      
        
        
    MetricaRI metricaRelato= new MetricaRI(idTest, idRelato, cantidadTerminos, cantidadPositivos, cantidadNegativos, cantidadNeutros, coincidencias);
      
    return metricaRelato; 
      
        
        
        
    }
    
    
    
   
    public void escribirArchivo(String texto, String ruta){
         
    
        
         ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"

 
       

        FileWriter fichero = null;
        PrintWriter pw = null;
        
      
        
        try
        {
            fichero = new FileWriter(realPath+ruta,true);
            
            pw = new PrintWriter(fichero);

                pw.print(texto);
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
        
        
    }
 
    
    public static  List[] llenarListaTerminosLamina (String nombreArchivo){
        
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+nombreArchivo; // Sustituye "/" por el directorio ej: "/upload"
System.out.println(" el ral path es"+ realPath);
        
        System.out.println("llenando los termnios");
        List[]  listaTerminosLaminas= new ArrayList[10];
        char charcount;
       List lista= new ArrayList();
       File archivo = null;
       FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         
          archivo = new File (realPath);
          System.out.println(" se creo el archivo");
          
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;  
         int count=1;
         // en esta lista se guardaran las lineas de cada relato
         List lineas = new ArrayList();
         //debemos recorrer todo el fichero
         
         while(((linea=br.readLine())!=null)){
             System.out.println("  linea"+linea);    
           

             //si el primer termino de la linea es igual al id relato que estamos evaluando, lo agregamos, si no
             //hay que pasar al otro relato (count++)
              
              charcount= (char)(count+'0');
            if((linea.charAt(0)!=charcount)&&(count<=listaTerminosLaminas.length)){ 
              
                
               listaTerminosLaminas[count-1]=lineas; 
             
                count++;
            
             lineas= new ArrayList();
             lineas.add(linea);
      }else{ 
            
        lineas.add(linea);
         }    
             
         
         }     
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
       
       
       
        
     return listaTerminosLaminas;   
    }


        public static  List<TerminoLamina>[] llenarListaTerminosLamina2 (String nombreArchivo){
        
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+nombreArchivo; // Sustituye "/" por el directorio ej: "/upload"
System.out.println(" el ral path es"+ realPath);
        
          
        System.out.println("llenando los termnios");
       
      //  List[]  listaTerminosLamina= new ArrayList[10];
        char charcount;
       //List lista= new ArrayList();
       List <TerminoLamina>[] listaLamina = new ArrayList[10];
       
       File archivo = null;
       FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         
          archivo = new File (realPath);
          System.out.println(" se creo el archivo");
          
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;  
         int count=1;
         
         //aca contaremos el id de la posicion de la linea que se esta leyendo
         
         int countPosicion=0;
         // en esta lista se guardaran las lineas de cada relato
        // List lineas = new ArrayList();
         //debemos recorrer todo el fichero
         
         
         //aca guardaremos los terminos correspondientes
           List<TerminoLamina> terminos = new ArrayList();
         
         while(((linea=br.readLine())!=null)){
             
          //   System.out.println("  linea"+linea);    
           

             //si el primer termino de la linea es igual al id relato que estamos evaluando, lo agregamos, si no
             //hay que pasar al otro relato (count++)
              
              charcount= (char)(count+'0');
            if((linea.charAt(0)!=charcount)&&(count<=listaLamina.length)){ 
              
                
              // listaTerminosLamina[count-1]=lineas; 
               
               listaLamina[count-1]=terminos;
                count++;
                
                //el contador de la posicion de la linea debe volver a 0
                countPosicion=0;
            
            // lineas= new ArrayList();
           //  lineas.add(linea);
             
             terminos=new ArrayList();
             
             
                
         String[] lineaSeparada;
          lineaSeparada = linea.split(",");
          String palabra= lineaSeparada[2];
          
          //si la palabra no esta ni en la lista de positivas ni negativas, se setea neutra
          //si es necesario habra que crear un archivo con palabras neutras y que por defecto sean 
          //seteadas negativas las palabras que no coinciden
         int connotacion;
          if(listaPositivas.contains(palabra))
            connotacion=1;
          else if(listaNegativas.contains(palabra))
              connotacion=0;
          else
              connotacion=-1;
          
          String  tesauro = (String)listaTesauro[count-1].get(countPosicion);
       
          String[] palabras=tesauro.split(",");
           List<String> items=new ArrayList();
          
           for (String s:palabras){
              items.add(s);  
          }
          
         //hay que eliminar las posiciones 0 y 1 porque conteinen ids
          
          
           TerminoLamina termino= new TerminoLamina(palabra,connotacion,items);
          
           
           terminos.add(termino);
             
             
             
             
             
             
      }else{ 
          
       
         
         String[] lineaSeparada;
          lineaSeparada = linea.split(",");
          String palabra= lineaSeparada[2];
          
          //si la palabra no esta ni en la lista de positivas ni negativas, se setea neutra
          //si es necesario habra que crear un archivo con palabras neutras y que por defecto sean 
          //seteadas negativas las palabras que no coinciden
         int connotacion;
          if(listaPositivas.contains(palabra))
            connotacion=1;
          else if(listaNegativas.contains(palabra))
              connotacion=0;
          else
              connotacion=-1;
          
          String  tesauro = (String)listaTesauro[count-1].get(countPosicion);
       
           String[] palabras=tesauro.split(",");
           List<String> items=new ArrayList();
          
           for (String s:palabras){
              items.add(s);  
          }
         
           TerminoLamina termino= new TerminoLamina(palabra,connotacion,items);
          
           
           terminos.add(termino);
          
                
       // lineas.add(linea);
        
        //el contador del la posicion aumenta pues aun no se ha terminado los terminos laminas del relato
        countPosicion++;
        
         }    
             
         
         }     
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
       
       
       
        
     return listaLamina;   
    }
    
      
    public static  List llenarLista (String nombreArchivo){
      
         ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+nombreArchivo; // Sustituye "/" por el directorio ej: "/upload"

        
        List lista= new ArrayList();
       File archivo = null;
       FileReader fr = null;
      BufferedReader br = null;
 
      
      
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (realPath);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
           // System.out.println(linea);
         lista.add(linea);
         
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
       
       
       
        
     return lista;   
    }

    
    public void remove(TerminoLamina termino, int idLamina) {
    try {
        
        System.out.println(" se quiere eliminar de la lista de terminos ");
        System.out.println("termino "+termino.getTermino());
        System.out.println("id de la lamina" + idLamina);
        
        //hay que borrar tambien los terminos del tesauro
        listaTerminosLaminas2[idLamina].remove(termino);
        System.out.println(" se ha eliminado");
        
        for (TerminoLamina term: listaTerminosLaminas2[idLamina]){
            System.out.println("termino lamina: "+term.getTermino());
        }
        
       
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void eliminarTermAsoc(String termAsoc){

       List<String> term= terminoLamina.getTerminosAsociados();
       term.remove(termAsoc);
       terminoLamina.setTerminosAsociados(term);
        
    }
    

       
       public static List<TerminoLamina>[] cargarTerminosLaminas(){
         List<TerminoLamina>[] lista= new ArrayList[10];
         
      List<String> list = new ArrayList<String>(){{add("one"); add("two"); add("three");}};
      TerminoLamina termino= new TerminoLamina("casa",-1,list);
      lista[0]=new ArrayList<>();
      lista[0].add(termino);
      
      
      List<String> list2 = new ArrayList<String>(){{add("cuatro"); add("cinco"); add("siete");}};
      TerminoLamina termino2= new TerminoLamina("queque",-1,list2);
      lista[1]=new ArrayList<>();
      lista[1].add(termino2);
      
           
           return lista;
       }
       
 
            public static List<TerminoLamina>[] deserializarTerminos() throws FileNotFoundException, IOException, ClassNotFoundException{
          
                List<TerminoLamina>[] term;
                
           ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
         
           //recuperar los terminos // setearle el rut del examinado para obtenerlo
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(realPath+"/Terminos/Terminos.obj"));
                term=(List<TerminoLamina>[])entrada.readObject();
                
                return term;
       }
       
           public void deserializarStopWords() throws FileNotFoundException, IOException, ClassNotFoundException{
           
           ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
         
           //recuperar los terminos // setearle el rut del examinado para obtenerlo
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(realPath+"/Terminos/StopWords.obj"));
                listaStopwors=(List<String>)entrada.readObject();
            
       } 
    
    
    public List<Termino> getTerminos() {
        return terminos;
    }

    public void setTerminos(List<Termino> terminos) {
        this.terminos = terminos;
    }

    public MetricaRI getMetrica() {
        return metrica;
    }

    public void setMetrica(MetricaRI metrica) {
        this.metrica = metrica;
    }
    

    
     public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
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

    public TerminoLamina getTerminoLamina() {
        return terminoLamina;
    }

    public void setTerminoLamina(TerminoLamina terminoLamina) throws CloneNotSupportedException {
        
        this.terminoLamina=terminoLamina;
      
       
    }

    public static List<TerminoLamina>[] getListaTerminosLaminas2() {
        return listaTerminosLaminas2;
    }

    public static void setListaTerminosLaminas2(List<TerminoLamina>[] listaTerminosLaminas2) {
        RI.listaTerminosLaminas2 = listaTerminosLaminas2;
    }
    
     
    
    public  List<TerminoLamina> getTerminosLaminaId(int idLamina){
        

       return listaTerminosLaminas2[idLamina];
    }
    
    
     public List[] getTerminosLaminas(){
        
        
       return listaTerminosLaminas;
    }
    
    
    public String getTerminosTesauroId(int idLamina, int idPos){
        
        return (String)listaTesauro[idLamina].get(idPos);
    }

    public static List getListaStopwors() {
        return listaStopwors;
    }

    public static void setListaStopwors(List listaStopwors) {
        RI.listaStopwors = listaStopwors;
    }

 
}
