/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean.CAT;

import Dao.CAT.UsuarioDao;
import Model.CAT.MetricaRI;
import Model.CAT.Termino;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jean
 */
@ManagedBean(name = "analisis")
@SessionScoped

public class Analisis {



    private int idRelato;
    private int idTest;    
    

     
      private String[] relatos;
    private String rutExaminado;
    private String rutUsuario;
    
       private List<MetricaRI> metricasRI;
       private List<Termino> indiceTest;
       
       
      //aca se guardaran los terminos para serializar
       private List<Termino>[] terminosTest;
       
    
    private RI RI;
    private MineriaDatos MD;
    
   
    
    
    
     private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;
    
   
       
    /**
     * Creates a new instance of AnalisisBean
     * @throws java.io.IOException
     */
    public Analisis() throws IOException {
        
      
        
        
         faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
        {
            this.rutUsuario=httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
            
              relatos= new String[10];
              terminosTest= new ArrayList[10];
          
        }else{
            
            faceContext.getExternalContext().redirect("/Proyecto/faces/index.xhtml");
        }
         
      
    }
    
    
public void analizarTest() throws IOException, ClassNotFoundException{
    

        System.out.println("el rut del examinado es"+rutExaminado);
     //  metricasRI= new ArrayList<>();
         UsuarioDao dao= new UsuarioDao();
         
        
          //aca debemos insertar en la bd el test y los relatos lo insertamos acá ya que hay que obtener el id del test
          // que se insertará
        dao.insertarTest(relatos,rutExaminado,rutUsuario); 
        
        //obtenemos el id del ultimo test insertado y agregamos uno ya que este es nuevo
         idTest=dao.getUltimoTest();
         
         
 
        for (int i =1; i <= 10; i++) {
          RI = new RI(idTest, i-1, relatos[i-1]);
          RI.analizarRI();
     //     MetricaRI metricaRelato=RI.getMetrica();
       //   metricasRI.add(metricaRelato);
          crearCarpetaExaminado();
         

           //escribimos los terminos del relato en el archivo
          escribirTerminosExaminado(RI.getTerminos());
         
          //Acá seteamos la lista de terminos en su posicion correspondiente, la cual luego será indexada
          terminosTest[i-1]=RI.getTerminos();
        

        
     //fin del for final   
    }
          //serializamos el test
          serializarTest();
        
       // indexamos los relatos pertenecientes al test( todos los test) 
       // escribirMetricasTest();
      //  indexarTerminosTest();
      //     crearARFF();
           
        
       
         System.out.println(" ahora direccionaremos a rectificarTerminos");
        //Esto es importante ya que acá redireccionaremos una vez terminado el análisis 
        FacesContext fc=FacesContext.getCurrentInstance();
         fc.getExternalContext().redirect("/Proyecto/faces/rectificarTerminos.xhtml?rutExaminado="+rutExaminado+"&idTest="+idTest);//redirecciona la página
         
       
  
    }

public void serializarTest() throws IOException{
    
        //serialización
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
          
          //serialización
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(realPath+"/Tests/"+idTest+".obj"))) {
                salida.writeObject(terminosTest);
                System.out.println(" se serializó");
              
            }
    
}
    
public void escribirMetricasTest(){
    
    ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"

 
     String cadenaMetricas="";
     
     for (MetricaRI metrica: metricasRI){
         cadenaMetricas = cadenaMetricas+ metrica.getIdTest()+","+metrica.getIdRelato()+","+metrica.getCantidadTerminos()+
         ","+metrica.getCantidadPositivos()+","+metrica.getCantidadNegativos()+","+metrica.getCantidadNeutros()+
         ","+metrica.getCoincidencias()+"\n";
                 
     } 
    
    escribirArchivo(cadenaMetricas,realPath+"/Examinados/"+rutExaminado+"/metricasRI.txt",false); // se sobreescribira archivo, por eso es false  el parametro
    
    
}

public void escribirTerminosExaminado(List<Termino> terminos){
    
      ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"

 
     String listaArchivo="";
       for (Termino termino: terminos){
          listaArchivo=listaArchivo+termino.getPalabra()+","+termino.getIdTest()+","+termino.getIdLámina()+
                       ","+termino.getFrecuencia()+","+termino.getConnotacion()+","+termino.getTerminoAsociado()+"\n";
     
      } 
       
        escribirArchivo(listaArchivo,realPath+"/Examinados/"+rutExaminado+"/terminos.txt",false);
        
    
    
}
 
public void crearCarpetaExaminado(){
    
     ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"

    
    
    File folder = new File(realPath+"Examinados/"+rutExaminado);
    if (!folder.exists()){
        folder.mkdirs();
    }

    
}
    
   
    public void escribirArchivo(String texto, String ruta,Boolean archivoNuevo){
        //este metodo todos los terminos de la lista de termninos en un archivo
       
        

        FileWriter fichero = null;
        PrintWriter pw = null;
        
      
        
        try
        {     //aca vemos si el archivo que crearemos es nuevo o se escribira sobre uno existente
            if (archivoNuevo==true)
            fichero = new FileWriter(ruta);
            else
            fichero = new FileWriter(ruta,true); 
            
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
    
    
    public void indexarTerminosTest(){
        
        
        Comparator<Termino> ordenAlfabetico = new Comparator<Termino>(){
           @Override
           public int compare(Termino a, Termino b){
               return a.getPalabra().compareTo(b.getPalabra());
               
              
        }};
        
        
        List<Termino> terminos = new ArrayList();
        
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
System.out.println(" el ral path es"+ realPath);
        
   
        
    
       File archivo = null;
       FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         
          //leer el archivo de terminos del examinados
          archivo = new File (realPath+"/Examinados/"+rutExaminado+"/terminos.txt");
        
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;  
       
         
         while(((linea=br.readLine())!=null)){
             
           
     
             System.out.println("  linea"+linea); 
             String[] lineaSplit=linea.split(",");
             Termino nuevoTerm = new Termino();
             nuevoTerm.setPalabra(lineaSplit[0]);
             nuevoTerm.setIdTest(Integer.parseInt(lineaSplit[1]));
             nuevoTerm.setIdLámina(Integer.parseInt(lineaSplit[2]));
             nuevoTerm.setFrecuencia(Double.parseDouble(lineaSplit[3]));
             nuevoTerm.setConnotacion(Integer.parseInt(lineaSplit[4]));
             
             terminos.add(nuevoTerm);
             
             
         }     
         
         //procedemos a ordenar
          System.out.println("cantidad terminos antes "+terminos.size());
         Collections.sort(terminos,ordenAlfabetico);
          System.out.println("cantidad terminos despues "+terminos.size());
         
         //llevamos una lista con todas las palabras que van apareciendo para llevar 
         //conteo de los idTermino que asignemos
         List<String> palabrasIndice = new ArrayList<>();
         int countIdTerm=0;
         
         for (Termino termino: terminos){
             // si no existe en la lista, le asignamos el id correspondiente, comenzando de 0 y aumentamos el contador
             if (!palabrasIndice.contains(termino.getPalabra())){
                countIdTerm++;
                 termino.setIdTermino(countIdTerm); 
                  palabrasIndice.add(termino.getPalabra());
                 
             }else{
                 // si ya existe en la lista le asignamos el id termino del ultimo que añadimos o el actual
                 termino.setIdTermino(countIdTerm);
                 
             }
             
         }
         //Aca seteamos el los terminos indexados en la variable de indice que utilizaremos
        
         
         // luego de añadirle el id termino (o podemos ir creando el string a escribir el archivo de una vez, para
         // no repetir el recorrer la lista ) creamos el string a imprimir en el archivo
         
         
         String indice="";
         for ( Termino termino2: terminos){
             indice= indice+termino2.getIdTermino()+","+termino2.getPalabra()+","+termino2.getIdTest()+","+
                     termino2.getIdLámina()+","+termino2.getFrecuencia()+","+termino2.getConnotacion()+"\n";

         }
         
        escribirArchivo(indice,realPath+"/Examinados/"+rutExaminado+"/indice.txt",true);
          indiceTest=terminos;
         
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
       
        
        
        
        
        
    }
    
    
    
    public void crearARFF(){
        
           ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
System.out.println(" el ral path es"+ realPath);
        
        
        
        //para crear un arrff primero debemos ordenar por otro criterio la lista de terminos ( ordenarlo por idTest e idRelato
        
     // creamos una variable local del indice invertido
        
         Comparator<Termino> ordenarTerminos = new Comparator<Termino>(){
           @Override
           public int compare(Termino a, Termino b){
               int resultado= Integer.compare(a.getIdTest(), b.getIdTest());
               if (resultado!=0) {return resultado;}
               
               resultado= Integer.compare(a.getIdLámina(),b.getIdLámina());
                return resultado;
               
               
        }};
        
        
        List<Termino> terminosArff = indiceTest;
        
        Collections.sort(terminosArff, ordenarTerminos);
        
        
        
          String indice="";
         for ( Termino termino2: terminosArff){
             indice= indice+termino2.getIdTest()+","+termino2.getIdLámina()+","+termino2.getIdTermino()+","+termino2.getPalabra()+","+
                     +termino2.getFrecuencia()+","+termino2.getConnotacion()+"\n";

         }
         
         escribirArchivo(indice,realPath+"/Examinados/"+rutExaminado+"/preArff.txt",true);
         
         
         //debemos llevar un contador de la cantidad de palabras maxima de un relato
         
         // por cada relato de cada test hay que crear una linea con todos los terminos del relato
         
         int cantMaxTerm=0;
         int countTerminos=0;
         int index=1;
         
         String datosArff="";
         String idTerminos="";
         
         int idTestAnterior= terminosArff.get(0).getIdTest();
         int idLaminaAnterior = terminosArff.get(0).getIdLámina();
         
         for (Termino termino: terminosArff){
             if(idTestAnterior==termino.getIdTest()&&idLaminaAnterior==termino.getIdLámina()&&terminosArff.size()!=index){
                 
                 idTerminos=idTerminos+","+termino.getIdTermino();
                 countTerminos++;
                 
    
             }else{
               
                 
                 if(terminosArff.size()==index){
                     idTerminos=idTerminos+","+termino.getIdTermino();
                     countTerminos++;
   
                 }
                   //ya cambió la cosa hay que guardar la linea
                 datosArff=datosArff+idTestAnterior+","+idLaminaAnterior+idTerminos+"\n";
                 
                 
                 
                 
                 //actualizamos la cantidad maxima de terminos
                 if(countTerminos>cantMaxTerm)
                     cantMaxTerm=countTerminos;
                  
            idTestAnterior=termino.getIdTest();
             idLaminaAnterior=termino.getIdLámina();
             countTerminos=1;
             idTerminos=","+termino.getIdTermino();
             
           
                 }
                 
             index++;
         }
         
         
         System.out.println(" la cantidad maxima de terminos es"+cantMaxTerm);
           this.escribirArchivo(datosArff,realPath+"/Examinados/"+rutExaminado+"/arffTest.arff",true);
         
        
        //Aca ya tenemos los datos necesarios para el.arff hay que llenar con 0s las posiciones
           
           List<String> lineasArff=llenarLista("/Examinados/"+rutExaminado+"/arffTest.arff");
           
           
           
          //aca por cada linea calcularemos la cantidad de terminos que hay que añadir( los -1s), ya tenemos la cantidad de terminos
           //maximo , añadiremos los -1s y luego escribiremos nuevamente el archivo.
           
           String datosFinalesArff="";
           String agregado=",-1";
           for (String linea: lineasArff){
               String[] lineaSplit= linea.split(",");
               
               System.out.println("la cantidad de terminos de la linea" +lineaSplit.length);
              // int cantidadTermLinea = ((linea.length()+1)/2)-2;
                int cantidadTermLinea = lineaSplit.length-2;
               int diferencia= cantMaxTerm-cantidadTermLinea;
               
               String agregadoLinea="";
               if(diferencia!=0)
                   agregadoLinea=String.format("%0" + diferencia + "d", 0).replace("0",agregado);
                
               datosFinalesArff=datosFinalesArff+linea+agregadoLinea+"\n";
               
               //a la nueva linea le agregamos los nuevos terminos
           //nuevaLinea=nuevaLinea+linea+String.format("%0" + diferencia + "d", 0).replace("0",agregado)+"\n";
               
               
           }
          
           String nombreArff="@RELATION CAT"+rutExaminado+"\n\n\n";
           String header="";
           
           header="@ATTRIBUTE IdTest NUMERIC\n @ATTRIBUTE IdRelato NUMERIC\n ";
          for (int i = 1; i <= cantMaxTerm; i++) {
            header=header+"@ATTRIBUTE Palabra"+i+ " NUMERIC\n";
        }
           
          String etiqueta="@DATA\n\n";
           
           
           String cadenaArff=nombreArff+header+etiqueta+datosFinalesArff;
           
           
          escribirArchivo(cadenaArff,realPath+"/Examinados/"+rutExaminado+"/arffTest.arff",true);
           
           
        

          
    
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

    public String[] getRelatos() {
        return relatos;
    }

    public void setRelatos(String[] relatos) {
        this.relatos = relatos;
    }

    public String getRutExaminado() {
        return rutExaminado;
    }

    public void setRutExaminado(String rutExaminado) {
        this.rutExaminado = rutExaminado;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public List<MetricaRI> getMetricasRI() {
        return metricasRI;
    }

    public void setMetricasRI(List<MetricaRI> metricasRI) {
        this.metricasRI = metricasRI;
    }

 
    
    

    
}
