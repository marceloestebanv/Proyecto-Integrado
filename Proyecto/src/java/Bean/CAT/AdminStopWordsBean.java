/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author jean
 */
@ManagedBean(name = "adminStopWordsBean")
@ViewScoped

public class AdminStopWordsBean {

    /**
     * Creates a new instance of AdminStopWordsBean
     */
    private  List stopWords;
    
    private String stopWord;

  
    
    public AdminStopWordsBean() throws IOException, FileNotFoundException, ClassNotFoundException {
    
        deserializarStopWords();
    
    }
   
       public void serializarStopWords() throws IOException{
    
        //serialización
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
          
          //serialización
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(realPath+"/Terminos/StopWords.obj"))) {
               
                salida.writeObject(stopWords);
                System.out.println(" se serializó");
              
            }
    
}
     
       public void deserializarStopWords() throws FileNotFoundException, IOException, ClassNotFoundException{
           
           ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
         
           //recuperar los terminos // setearle el rut del examinado para obtenerlo
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(realPath+"/Terminos/StopWords.obj"));
                stopWords=(List<String>)entrada.readObject();
            
       }
       
       public void añadirStopWord(){
           stopWords.add(stopWord);
           System.out.println("se añadió"+stopWord);
           
       }
       public void quitarStopWord(String stopword){
           stopWords.remove(stopword);
       }

    public List getStopWords() {
        return stopWords;
    }

    public void setStopWords(List stopWords) {
        this.stopWords = stopWords;
    }

    public String getStopWord() {
        return stopWord;
    }

    public void setStopWord(String stopWord) {
        this.stopWord = stopWord;
    }
       

   

       
       
       
}
