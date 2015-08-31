/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;



import Model.CAT.AnalisisUtils;
import Model.CAT.Termino;
import Model.CAT.TerminoLamina;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jean
 */
@ManagedBean(name = "adminTerminosBean")
@ViewScoped

public class AdminTerminosBean {

    //Esta clase almacenara la lista de terminos que irán directamente
    
    /**
     * Creates a new instance of EstadisticaBean
     */
  
    
 //este atributo setea el id de la lamina que se quiere modificar
private int idLaminaPagina=0;
 

 private  List <TerminoLamina>[] term ;

 
  private TerminoLamina terminoLamina;
  
  private TerminoLamina termino;
  
  private String palabra;
  
    public AdminTerminosBean() throws IOException, FileNotFoundException, ClassNotFoundException {
  
    term= RecuperacionInformacion.deserializarTerminos();
    termino=new TerminoLamina();

        
    }
  
    
    public void agregarTermino(int idLamina){
        
        
                
       //Vemos que no sea null el id del relato en el arreglo de terminos asociados,le creamos una lista
              if (term[idLamina]==null){
              term[idLamina]=new ArrayList<>();
              
              }
              
        
    List<String> listae=new ArrayList<>();
     //listae.add("hola");
    
  
 String delimitadores= "[ .,;?!¡¿\'\"\\[\\]\\(\\)\\t]+";
String[] palabrasSeparadas = termino.getTermino().split(delimitadores);


    if(palabrasSeparadas.length==1){
        termino.setTermino(formatearTermino(termino.getTermino()));


         if(!existsTermino(termino.getTermino(),idLamina)){
            termino.setTerminosAsociados(listae);
            term[idLamina].add(termino);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "El término fue añadido "));
       
         }else{
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El término ya existe, o forma parte de terminos asociados "));
            }
        
    }else{
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los términos deben ser de una palabra "));
        
    }
    
        termino= new TerminoLamina();
    }
  
    public String formatearTermino(String termino){
      
AnalisisUtils analisis = new AnalisisUtils(); 

   termino=analisis.quitarAcentos(termino);
    termino=analisis.quitarMayusculas(termino);
    termino=analisis.quitarNumeros(termino);
    
    return termino;
    }
    
    public boolean existsTermino(String palabra,int idLamina){
       boolean existe=false;
        for(TerminoLamina  terminoLam:term[idLamina]){
           if (terminoLam.getTermino().equals(palabra))
            return true;
           
           for(String termAsoc:terminoLam.getTerminosAsociados()){
               if (termAsoc.equals(palabra))
                   return true;
               
           }

        }
       return existe; 
    }
    
   public List<TerminoLamina> getTerminosLaminaId(int idLamina){
        

       return term[idLamina];
    }
   
   
   
   
    
     public void eliminarTermAsoc(String termAsoc){

       List<String> term= terminoLamina.getTerminosAsociados();
       term.remove(termAsoc);
       terminoLamina.setTerminosAsociados(term);
        
    }
     
          public void añadirTermAsoc(String termAsoc){
              
        
      
              
              
              
 String delimitadores= "[ .,;?!¡¿\'\"\\[\\]\\(\\)\\t]+";
String[] palabrasSeparadas =termAsoc.split(delimitadores);


    if(palabrasSeparadas.length==1){
            termAsoc=formatearTermino(termAsoc);              
              

            if(!existsTermino(termAsoc, idLaminaPagina)){
            terminoLamina.getTerminosAsociados().add(termAsoc);
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", "El término asociado fue añadido "));      
    
            } else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El término asociado ya existe, o es parte de los Términos Generales de la Lámina "));  
      
         }
    
    
    }else{
        
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Los términos deben ser de una palabra "));   
    }
    
   
          }
          
          public void añadirTerminoAsocDesdeRectificar(Termino termino){
              // desde rectificar terminos estamos agregando una nueva palabra al diccionario con id relato y termino asociado al cual hay que añadirla
             String palabraAñadir=termino.getPalabra();
            int  idRelato=termino.getIdLámina();
           String   termAsoc= termino.getTerminoAsociado();
              
              System.out.println("ela palabra a añadir es "+palabraAñadir);
              System.out.println(" el id relato es"+idRelato);
              System.out.println(" el termino al cual debemos asociarlo es "+termAsoc);
              
              
              //aca se agrega
              this.term[idRelato].get(getIndexTerminoLamina(idRelato, termAsoc)).getTerminosAsociados().add(palabraAñadir);
              
           
              
              
          }
          
            public void añadirNuevoTerminoDesdeRectificar(Termino termino){
              // desde rectificar terminos estamos agregando una nueva palabra al diccionario con id relato y termino asociado al cual hay que añadirla
              
                
              System.out.println("ela palabra a añadir es "+termino.getPalabra());
              System.out.println(" el id relato es"+termino.getIdLámina());
              System.out.println(" el nuevo Termino es  "+termino.getTerminoAsociado());
              
              String palabraAñadir=termino.getPalabra();
              int idRelato=termino.getIdLámina();
              String nuevoTermino=termino.getTerminoAsociado();
              int connotacion=termino.getConnotacion();
              
              
              
                      
             //verificar que no exista en la lista para añadirla si existe lanzar mensaje
              if(palabraAñadir.equals(nuevoTermino)){
                  
              //tener cuidado con el null en la lista
              TerminoLamina nuevo =new TerminoLamina(palabraAñadir, connotacion, new ArrayList());
                  this.term[idRelato].add(nuevo);
               }else{
                  List<String> lista= new ArrayList<>();
                  lista.add(palabraAñadir);
                   TerminoLamina nuevo =new TerminoLamina(nuevoTermino, connotacion, lista);
                   this.term[idRelato].add(nuevo);
                  
              }
              
            
              
              
          }
          
          public int getIndexTerminoLamina (int idRelato,String Palabratermino){
              //este metodo me retorna el indice donde se encuentra el termino lamina asociado a una palabra, si no esta es -1
              int numero=-1;
             for (int i = 0; i < term[idRelato].size(); i++) {
                      
                 if(term[idRelato].get(i).getTermino().equals(Palabratermino)){
                     System.out.println(" el id del termino es "+i);
                 return i;
                 }
                 
                  }
              return numero;
          }
          
          
          
          
        // public TerminoLamina getTerminoRectificar()
          
     
      public void remove(TerminoLamina termino, int idLamina) {
    try {
        
        System.out.println(" se quiere eliminar de la lista de terminos ");
        System.out.println("termino "+termino.getTermino());
        System.out.println("id de la lamina" + idLamina);
        
        //hay que borrar tambien los terminos del tesauro
        term[idLamina].remove(termino);
        System.out.println(" se ha eliminado");
        
        for (TerminoLamina termi: term[idLamina]){
            System.out.println("termino lamina: "+termi.getTermino());
        }
        
       
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
      
      
    
          
    public void editar( int idLamina ){
        
        System.out.println(" vamos a editar");
    }

        
       public void serializarTerminos() throws FileNotFoundException, IOException{
  //serialización
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
          
          //serialización
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(realPath+"/Terminos/Terminos.obj"))) {
                salida.writeObject(term);
                RecuperacionInformacion.setListaTerminosLaminas2(term);
                System.out.println(" se serializó");
                FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Se han guardado los cambios",  "") );
               
            } 
           
       }
       
       
       
       
       public void deserializarTerminos() throws FileNotFoundException, IOException, ClassNotFoundException{
           
           ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
         
           //recuperar los terminos // setearle el rut del examinado para obtenerlo
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(realPath+"/Terminos/Terminos.obj"));
                term=(List<TerminoLamina>[])entrada.readObject();
                
                System.out.println("los terminos tienen "+ term.length);
       }
       
       public String clasificacion(int connotacion){
        //Metodo que dada una clasificacion me retorna el string correspondiente
        String clasif="Neutro";
        if (connotacion==0) return "Negativo";
        if (connotacion==1) return "Positivo";
        
            return clasif;
    }
 
      

    public List<TerminoLamina>[] getTerm() {
        return term;
    }

    public void setTerm(List<TerminoLamina>[] term) {
        this.term = term;
    }

    public TerminoLamina getTerminoLamina() {
        return terminoLamina;
    }

    public void setTerminoLamina(TerminoLamina terminoLamina) {
        this.terminoLamina = terminoLamina;
    }

    public TerminoLamina getTermino() {
        return termino;
    }

    public void setTermino(TerminoLamina termino) {
        this.termino = termino;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
       
        this.palabra = palabra;
    }

    public int getIdLaminaPagina() {
        return idLaminaPagina;
    }

    public void setIdLaminaPagina(int idLaminaPagina) {
        this.idLaminaPagina = idLaminaPagina;
    }
     
    
    
    
    
    
    
    public void gestionRectificarTerminos(Termino termino){
           
       //Termino  itemSelecionado = (Termino) event.getComponent().getAttributes().get("cursoItem");   
           
        RequestContext context = RequestContext.getCurrentInstance(); 
  
    
    context.addCallbackParam(":dlgmodificar2",termino);
    
       } 
    
    
    
    
    
}
