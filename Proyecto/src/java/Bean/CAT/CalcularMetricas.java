/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;

import Dao.CAT.UsuarioDao;
import Model.CAT.AnalisisUtils;
import Model.CAT.MetricaRI;
import Model.CAT.Termino;
import Model.CAT.TerminosGenerales;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author jean
 */
@ManagedBean(name = "calcularMetricas")
@SessionScoped



public class CalcularMetricas {


    
    
    int idTest;
    List<Termino>[] listaTerminosTest;
    List<MetricaRI> metricasTest;
    MetricaRI metricaGlobalTest;
    
    
    
     //Esta es una lista de tests
   //List <List<Termino>[]> terminosTests;
  List<MetricaRI> metricaMuchosTests;
  MetricaRI metricaGlobalMuchosTests;
  int causaIngreso;
  
  int cantidadTests;
  int cantidadTestsPorCausa;
  String ListaExaminadosString;
  
   
   //Esta es la lista  de test de un examinado
   List<MetricaRI> metricaTestsExaminado;
  MetricaRI metricaGlobalTestsExaminado;
  int cantidadTestsExaminado;
  String ListaTestsExaminadoString;
   String rutExaminado;
    
    
    /**
     * Creates a new instance of CalcularMetricas
     */
    public CalcularMetricas() {

        // aca luego setear que sea hasta 10
         listaTerminosTest= new ArrayList[10];
    }



    
       public List<Termino>[] getTerminosdelTest() throws FileNotFoundException, IOException, ClassNotFoundException{
        
        
        
  ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
         
           //recuperar los terminos // setearle el rut del examinado para obtenerlo
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(realPath+"/Tests/"+idTest+".obj"));
                listaTerminosTest=(List<Termino>[])entrada.readObject();
                entrada.close();
                
              
           
        return listaTerminosTest;
    }


       
    public List<MetricaRI> calculaMetrica(){
     
 
          List<MetricaRI> metricaTest= new ArrayList<>();
        
        //setearemos el id del relato , este lo incrementaremos abajo
        int idRelato=1;
        for (List<Termino> listaTermino: listaTerminosTest){
            
            
        int cantPositivos=0;
        int cantNegativos=0;
        int cantNeutros=0;
        int cantTotalTerminos=0;
        int coincidencias=0;
        
        List<TerminosGenerales> termGenerales=new ArrayList<>();
        List<String> termCoinci= new ArrayList<>();
      
            
            cantTotalTerminos=listaTermino.size();
            
            for (Termino terminoTest: listaTermino){
                
                //calculamos connotacion
                if(terminoTest.getConnotacion()==1)
                    cantPositivos++;
                else if(terminoTest.getConnotacion()==0)
                        cantNegativos++;
                else 
                  cantNeutros++;  
                        
                //vemos si hay coincidencia y agregamos el termino si la hay para luego calcular la frecuencia de uso de la coincidencia
                if(!terminoTest.getTerminoAsociado().equals("-")){
                    coincidencias++;
                    termCoinci.add((String)terminoTest.getTerminoAsociado());
                }    
                
                
            }
            
            //calculamos la frecuencia de los terminos asociados
            Set<String> unique = new HashSet<String>(termCoinci);
            
        
            
            //contamos la coincidencia de cada termino unico y lo agregamos a la lista
            for (String unico:unique){
               
                int count=0;
                for(String term:termCoinci){
                    if(unico.equals(term))
                        count++;
                }
                TerminosGenerales termGeneral=new TerminosGenerales(unico, count);
                termGenerales.add(termGeneral);
            }
            
            
            
            MetricaRI metrica= new MetricaRI();
            metrica.setCantidadNegativos(cantNegativos);
            metrica.setCantidadNeutros(cantNeutros);
            metrica.setCantidadPositivos(cantPositivos);
            metrica.setCantidadTerminos(cantTotalTerminos);
            metrica.setCoincidencias(coincidencias);
            
           //ordenamos por orden Alfabetico
            Collections.sort(termGenerales,AnalisisUtils.ordenarTerminosGenerales);
            metrica.setTerminosGenerales(termGenerales);
            metrica.setIdRelato(idRelato);
            
            metricaTest.add(metrica);
            idRelato++;
            
            
            
        }
     
        
        
       return metricaTest; 
    }
    
    
   
    
  public MetricaRI calculaMetricaGlobal( List<MetricaRI> metricasTest){
      
      MetricaRI metricaGlobal= new MetricaRI();
      
        int cantPositivos=0;
        int cantNegativos=0;
        int cantNeutros=0;
        int cantTotalTerminos=0;
        int coincidencias=0;
      for(MetricaRI metrica: metricasTest){
          cantPositivos=cantPositivos+metrica.getCantidadPositivos();
          cantNeutros=cantNeutros+metrica.getCantidadNeutros();
          cantNegativos=cantNegativos+metrica.getCantidadNegativos();
          cantTotalTerminos=cantTotalTerminos+metrica.getCantidadTerminos();
          coincidencias=coincidencias+metrica.getCoincidencias();
          
      }
     
      metricaGlobal.setCantidadNegativos(cantNegativos);
      metricaGlobal.setCantidadNeutros(cantNeutros);
      metricaGlobal.setCantidadPositivos(cantPositivos);
      metricaGlobal.setCoincidencias(coincidencias);
      metricaGlobal.setCantidadTerminos(cantTotalTerminos);
      return metricaGlobal;
      
  }  
 
  
  public void consultarMetricasExaminadoPagina(String rutExaminado) throws IOException, FileNotFoundException, ClassNotFoundException{
      
         calcularMetricasTestsByExaminado(rutExaminado);
         
         if (cantidadTestsExaminado==0){
           
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Examinado sin Tests",  "") );
             
         }else{
      
        
         //Esto es importante ya que acá redireccionaremos una vez terminado el análisis 
        FacesContext fc=FacesContext.getCurrentInstance();
         fc.getExternalContext().redirect("/Proyecto/faces/estadisticasExaminado.xhtml?rutExaminado="+rutExaminado);//redirecciona la página

  }
  }
 

  
   public void calcularMetricaTest(int idTest) throws IOException, FileNotFoundException, ClassNotFoundException{
      
        System.out.println(" calculando metricas");        

//primero debemos obtener el test y setearlo en la lista de terminos
      this.idTest=idTest;
       getTerminosdelTest();
       calcularFrecuenciasTest();
      serializarTest();
       metricasTest=calculaMetrica();
      metricaGlobalTest=calculaMetricaGlobal(metricasTest);
      serializarMetricasTest();
      
      
      
      
      
      
      
      
       //Esto es importante ya que acá redireccionaremos una vez terminado el análisis 
      //  FacesContext fc=FacesContext.getCurrentInstance();
     //    fc.getExternalContext().redirect("/Proyecto/faces/resultadoAnalisis_1.xhtml?idTest="+idTest);//redirecciona la página

     //  System.out.println(" redireccionando a resultado analisis");
        
    }
   
   
       public void calcularFrecuenciasTest(){
           //recibe los terminos del test y elimina las repeticiones, calculando la frecuencia
           
           System.out.println(" calculando frecuencia test");
        for (int i = 0; i < listaTerminosTest.length; i++) {
                   
             
            
            List<Termino> listaTerminosTemp= new ArrayList<>();
            
           
            for (Termino termino: listaTerminosTest[i]){
             
                
                
                if(listaTerminosTemp.contains(termino)){
                   
                     System.out.println(" se encontro repe"); 
                 Termino terminotemp= listaTerminosTemp.get( listaTerminosTemp.indexOf(termino));
                 
                 terminotemp.setFrecuencia(terminotemp.getFrecuencia()+1);
                 
                    System.out.println(" se agrego repe");  
   
                }else{
                    termino.setFrecuencia(1);
                   listaTerminosTemp.add(termino);
                    System.out.println(" se agrego el termino"+termino.getPalabra());
                    
                }
                  
                
            }
            
            listaTerminosTest[i]=listaTerminosTemp;
            
        }
        
        
    }
   
   

        public void consultarMetricasTestPagina(int idTest) throws IOException, FileNotFoundException, ClassNotFoundException{
      this.idTest=idTest;
       this.metricasTest=deserializarMetricasTest(idTest);
   
         metricaGlobalTest=calculaMetricaGlobal(metricasTest);
      
        
         //Esto es importante ya que acá redireccionaremos una vez terminado el análisis 
        FacesContext fc=FacesContext.getCurrentInstance();
         fc.getExternalContext().redirect("/Proyecto/faces/consultarResultadoAnalisis.xhtml?idTest="+idTest);//redirecciona la página

        
  }
  
  public List<MetricaRI> calcularMetricasTestsByExaminado(String rutExaminado) throws IOException, FileNotFoundException, ClassNotFoundException{
      this.rutExaminado=rutExaminado;
      List<MetricaRI>[] metricasTestExaminado=deserializarMetricasMuchosTestsByExaminado(rutExaminado);
      
      List<MetricaRI> metricasTodosTestsExaminado= new ArrayList<>();
      
    metricasTodosTestsExaminado=calculaMetricaMuchosTests(metricasTestExaminado);
      
      metricaTestsExaminado= metricasTodosTestsExaminado;
      metricaGlobalTestsExaminado=calculaMetricaGlobal(metricaTestsExaminado);
      
      return metricasTodosTestsExaminado; 
  }

   
    public void calcularMetricasByCausaIngreso(int causaIngreso) throws IOException, FileNotFoundException, ClassNotFoundException{
      // este metodo me devuelve las metricas de todos los test que se tomaron en cuenta // cada item es un relato
    
 
        //aca inicializamos la cantidad de tests globales que se estan tomando en cuenta 
        // cada vez que se calcula la metrica global por examinado se suma su cantidad de tests
        cantidadTestsPorCausa=0;
        
       System.out.println(" calcular metrica por causa ingreso");
       this.causaIngreso=causaIngreso;
       
       UsuarioDao dao = new UsuarioDao();
       List<String> examinadosByCausaIngreso = dao.getExaminadosRazonIngreso(causaIngreso);
       ListaExaminadosString=examinadosByCausaIngreso+"";
       
       
       
       
       //Aca hay que guardar todos los resultados parciales de las metricas obtenidas por cada examinado
        List<MetricaRI>[] resultadosParcialesbyExaminado= new ArrayList[examinadosByCausaIngreso.size()];
       
        
        for (int i = 0; i < examinadosByCausaIngreso.size(); i++) {
            
            List<MetricaRI> metricasTestExaminado= calcularMetricasTestsByExaminado(examinadosByCausaIngreso.get(i)); 
            
            resultadosParcialesbyExaminado[i]=metricasTestExaminado;
            
               
           }
        
        //luego de haber hecho por cada examinado el calculo de las metricas , hay que hacer un calculo relato por relato
        
        metricaMuchosTests=calculaMetricaMuchosTests(resultadosParcialesbyExaminado);
        metricaGlobalMuchosTests=calculaMetricaGlobal(metricaMuchosTests);
        
        /*
       for (String examinado: examinadosByCausaIngreso){
           List<MetricaRI> metricasTestExaminado= calcularMetricasTestsByExaminado(examinado);   
       }
       */
       
    

    // calucular la metrica global asi tal cual
 
    if(cantidadTestsPorCausa>0){
    
    //Esto es importante ya que acá redireccionaremos una vez terminado el análisis 
        FacesContext fc=FacesContext.getCurrentInstance();
         fc.getExternalContext().redirect("/Proyecto/faces/estadisticasRazonIngreso.xhtml");//redirecciona la página

        System.out.println(" redireccionando a estadisticas razon ingreso "+causaIngreso);
    }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se han añadido tests para esta causa de Ingreso",  "") ); 
        
    }
    
  } 
    
 
  public List<MetricaRI> calculaMetricaMuchosTests(List<MetricaRI>[] metricaMuchosTestsDeserializadas) throws IOException, FileNotFoundException, ClassNotFoundException{
      
  
      
 
    

      List<MetricaRI> metricasTests= new ArrayList<>();
   
      
     // for (int i = 0; i <9; i++) {
         //for (int i = 0; i <= metricaMuchosTestsDeserializadas[1].size(); i++) { 
        
     //cantidad de tests encontrados
      
      //tener cuidado con la cantidad de posiciones que tiene el arreglo
      for (int i = 0; i < 10; i++) {   
             
           
        int cantPositivos=0;
        int cantNegativos=0;
        int cantNeutros=0;
        int cantTotalTerminos=0;
        int coincidencias=0;
        List<TerminosGenerales> termGenerales=new ArrayList<>();
        
     
         
        MetricaRI metrica= new MetricaRI();
        
       //aca tener cuidado 
          for (int j = 0; j < metricaMuchosTestsDeserializadas.length; j++) {
              
             metrica= metricaMuchosTestsDeserializadas[j].get(i); 
            
              cantPositivos=cantPositivos+metrica.getCantidadPositivos();
          cantNeutros=cantNeutros+metrica.getCantidadNeutros();
          cantNegativos=cantNegativos+metrica.getCantidadNegativos();
          cantTotalTerminos=cantTotalTerminos+metrica.getCantidadTerminos();
          coincidencias=coincidencias+metrica.getCoincidencias();
          
          for(TerminosGenerales termGeneral:metrica.getTerminosGenerales()){
              
              if(termGenerales.contains(termGeneral)){
               //si lo contiene hay que sumarle la frecuencia
                  System.out.println(" lo contiene  y por lo tanto se sumara su frecuencia");
                 int index= termGenerales.indexOf(termGeneral);
                 int frecuencia=termGenerales.get(index).getFrecuencia();
                 termGenerales.get(index).setFrecuencia(termGeneral.getFrecuencia()+frecuencia);
                 
                  
              }else{
                  termGenerales.add(termGeneral);
                  
                  
              }
              
          }
          
          
          
              
              //sumar el valor i de cada uno de los terminos y luego agregarlo a una lista
             
              
              
          }
          
          MetricaRI metricaTest=new MetricaRI();
          
           metricaTest.setCantidadNegativos(cantNegativos);
      metricaTest.setCantidadNeutros(cantNeutros);
      metricaTest.setCantidadPositivos(cantPositivos);
      metricaTest.setCoincidencias(coincidencias);
      metricaTest.setCantidadTerminos(cantTotalTerminos);
     
      //ordenamos los terminosgenerales
       Collections.sort(termGenerales,AnalisisUtils.ordenarTerminosGenerales);
      metricaTest.setTerminosGenerales(termGenerales);
     
      metricasTests.add(metricaTest);
          
          
      }
   
   
      
    return metricasTests;
      
}
 

  
  
 public List<MetricaRI>[] deserializarMetricasTestsByRazonIngreso(int causaIngreso) throws IOException, FileNotFoundException, ClassNotFoundException{
    //la causa de ingreso ya esta seteada
     
     
     
    
     // aca obtenemos la causa de ingreso y hay que obtener las metricas de los tests que cumplan con esto deserializandolas
     UsuarioDao dao = new UsuarioDao();
     List<String> listaTests=dao.getTestsRazonIngreso(causaIngreso);
     
     for (String hola: listaTests ){
         
         System.out.println(" el id del test recogido es"+hola);
          
      }
     
     
     
     //teniendo todos los tests ahora hay que llenar el arreglo con las metricas
      List<MetricaRI>[] metricasMuchosTest=new ArrayList[listaTests.size()];
      
      for (int i = 0; i < metricasMuchosTest.length; i++) {
          //aca se setea la lista de metricas que se utilizaran
          
          metricasMuchosTest[i]=deserializarMetricasTest(Integer.parseInt(listaTests.get(i)));
          
     }
 
      
 
      
     
     
     
  return metricasMuchosTest;
 } 
  
  public List<MetricaRI>[] deserializarMetricasMuchosTestsByExaminado(String rutExaminado) throws IOException, FileNotFoundException, ClassNotFoundException{
    //la causa de ingreso ya esta seteada
     
     
     
    
     // aca obtenemos la causa de ingreso y hay que obtener las metricas de los tests que cumplan con esto deserializandolas
     UsuarioDao dao = new UsuarioDao();
     List<String> listaTests=dao.getidTestsExaminado(rutExaminado);
     
     ListaTestsExaminadoString=listaTests+"";
     cantidadTestsExaminado= listaTests.size();
     
     //esto lo inicializaremos cada vez que se consulte las estadisticas volverá a ser 0; pero las sumas parciales de todos
     //los tests me daran la cantidad global de tests
     cantidadTestsPorCausa=cantidadTestsPorCausa+cantidadTestsExaminado;
     
          //esta es la cantidad de test que se obtuvieron
      System.out.println(" Cantidad Test Examinado "+rutExaminado+": "+listaTests.size());
      System.out.println("Lista de test del Examinado "+rutExaminado+": "+listaTests);
      
    
     

     
     //teniendo todos los tests ahora hay que llenar el arreglo con las metricas
      List<MetricaRI>[] metricasMuchosTest=new ArrayList[listaTests.size()];
      
      for (int i = 0; i < metricasMuchosTest.length; i++) {
          //aca se setea la lista de metricas que se utilizaran
          
          metricasMuchosTest[i]=deserializarMetricasTest(Integer.parseInt(listaTests.get(i)));
          
     }
 
      
 
      
     
     
     
  return metricasMuchosTest;
 } 
  
  
  public void serializarMetricasTest() throws IOException{
    
        //serialización
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
          
          //serialización
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(realPath+"/Tests/Metricas/"+idTest+".obj"))) {
                salida.writeObject(metricasTest);
                System.out.println(" se serializó");
              
            }
    
}
  
      public void serializarTest() throws IOException{
    
        //serialización
          ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
          
          //serialización
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(realPath+"/Tests/"+listaTerminosTest[0].get(0).getIdTest()+".obj"))) {
                salida.writeObject(listaTerminosTest);
                System.out.println(" se serializó");
              
            }
    
}
  
    public List<MetricaRI> deserializarMetricasTest(int idTest) throws FileNotFoundException, IOException, ClassNotFoundException{
        
             List<MetricaRI> metricasTests;     
  ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/"); // Sustituye "/" por el directorio ej: "/upload"
          
         
           //recuperar los terminos // setearle el rut del examinado para obtenerlo
                ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(realPath+"/Tests/Metricas/"+idTest+".obj"));
                metricasTests=(List<MetricaRI>)entrada.readObject();
                
              
           
        return metricasTests;
    }


   public List<TerminosGenerales> getTerminosGenerales(MetricaRI metrica){
       
       int index= metricasTest.indexOf(metrica);
       
       return metricasTest.get(index).getTerminosGenerales();
   }
   
     public List<TerminosGenerales> getTerminosGeneralesMetricaTestsExaminado(MetricaRI metrica){
       
       int index= metricaTestsExaminado.indexOf(metrica);
       
       return metricaTestsExaminado.get(index).getTerminosGenerales();
   }
     
       public List<TerminosGenerales> getTerminosGeneralesMetricaMuchosTest(MetricaRI metrica){
       
       int index= metricaMuchosTests.indexOf(metrica);
       
       return metricaMuchosTests.get(index).getTerminosGenerales();
   }
  
    
    public List<Termino>[] getListaTerminosTest() {
        
        return listaTerminosTest;
    }

    public void setListaTerminosTest(List<Termino>[] listaTerminosTest) {
        this.listaTerminosTest = listaTerminosTest;
    }



    public List<MetricaRI> getMetricasTest() {
        return metricasTest;
    }

    public void setMetricasTest(List<MetricaRI> metricasTest) {
        this.metricasTest = metricasTest;
    }

    public MetricaRI getMetricaGlobalTest() {
        return metricaGlobalTest;
    }

    public void setMetricaGlobalTest(MetricaRI metricaGlobalTest) {
        this.metricaGlobalTest = metricaGlobalTest;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

   

    public void setMetricaMuchosTests(List<MetricaRI> metricasMuchosTests) {
        this.metricaMuchosTests = metricasMuchosTests;
    }

    public List<MetricaRI> getMetricaMuchosTests() {
        return metricaMuchosTests;
    }
    
    

    public MetricaRI getMetricaGlobalMuchosTests() {
        return metricaGlobalMuchosTests;
    }

    public void setMetricaGlobalMuchosTests(MetricaRI metricaGlobalMuchosTests) {
        this.metricaGlobalMuchosTests = metricaGlobalMuchosTests;
    }

    public int getCausaIngreso() {
        return causaIngreso;
    }

    public void setCausaIngreso(int causaIngreso) {
        this.causaIngreso = causaIngreso;
    }

    public int getCantidadTests() {
        return cantidadTests;
    }

    public void setCantidadTests(int cantidadTests) {
        this.cantidadTests = cantidadTests;
    }

    public String getListaExaminadosString() {
        return ListaExaminadosString;
    }

    public void setListaExaminadosString(String ListaExaminadosString) {
        this.ListaExaminadosString = ListaExaminadosString;
    }

    public int getCantidadTestsExaminado() {
        return cantidadTestsExaminado;
    }

    public void setCantidadTestsExaminado(int cantidadTestsExaminado) {
        this.cantidadTestsExaminado = cantidadTestsExaminado;
    }

    public String getListaTestsExaminadoString() {
        return ListaTestsExaminadoString;
    }

    public void setListaTestsExaminadoString(String ListaTestsExaminadoString) {
        this.ListaTestsExaminadoString = ListaTestsExaminadoString;
    }

    public String getRutExaminado() {
        return rutExaminado;
    }

    public void setRutExaminado(String rutExaminado) {
        this.rutExaminado = rutExaminado;
    }

    public List<MetricaRI> getMetricaTestsExaminado() {
        return metricaTestsExaminado;
    }

    public void setMetricaTestsExaminado(List<MetricaRI> metricaTestsExaminado) {
        this.metricaTestsExaminado = metricaTestsExaminado;
    }

    public MetricaRI getMetricaGlobalTestsExaminado() {
        return metricaGlobalTestsExaminado;
    }

    public void setMetricaGlobalTestsExaminado(MetricaRI metricaGlobalTestsExaminado) {
        this.metricaGlobalTestsExaminado = metricaGlobalTestsExaminado;
    }

  public List<Termino> getTerminosRelato(int idRelato){
      return this.listaTerminosTest[idRelato];
      
      
  }

    public int getCantidadTestsPorCausa() {
        return cantidadTestsPorCausa;
    }

    public void setCantidadTestsPorCausa(int cantidadTestsPorCausa) {
        this.cantidadTestsPorCausa = cantidadTestsPorCausa;
    }
    
 
    
}
