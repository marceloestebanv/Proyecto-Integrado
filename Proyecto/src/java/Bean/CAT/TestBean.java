/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;

import Dao.CAT.UsuarioDao;
import Model.CAT.CausaIngresoExaminado;
import Model.CAT.Examinado;
import Model.CAT.MetricaRI;
import Model.CAT.Relato;
import Model.CAT.Test;
import Model.CAT.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author jean
 */

@ManagedBean(name = "testBean")
@ViewScoped

public class TestBean {
    
  
    
   // esto es para obtener la info del examinado y del usuario
   private Usuario usuario;
   private Examinado examinado;
   
   
   
   
   private List<Test> tests;
   private Test test;
   private List<String> relatos;
   private List <MetricaRI> metricas;
   private MetricaRI metricaGlobalTest;
   
   

   
   

 private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage facesMessage;
    
    public TestBean() throws IOException {
        
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
        {
            usuario= new Usuario();
            
            usuario.setRut(httpServletRequest.getSession().getAttribute("sessionUsuario").toString());
            test= new Test();
        tests= new ArrayList<>();
        metricas = new ArrayList<>();
        metricaGlobalTest= new  MetricaRI();
        
        }else{
            
            faceContext.getExternalContext().redirect("/Proyecto/faces/index.xhtml");
        }
        
       
    
    }
    

    
    
    public List<Test> getTests() {
        
        UsuarioDao dao = new UsuarioDao();
        tests=dao.getTests();

        return tests;
    }
    
    
    public void redireccionar(int idTest) throws IOException {
        
        System.out.println(" ahora si");
         FacesContext fc=FacesContext.getCurrentInstance();
         fc.getExternalContext().redirect("/Proyecto/faces/mostrarRelatos.xhtml?idTest="+idTest);//redirecciona la página
        
    
}
    
    
        public void redireccionarResultado(int idTest, String rutExaminado) throws IOException {
        
        System.out.println(" ahora si");
         FacesContext fc=FacesContext.getCurrentInstance();
         fc.getExternalContext().redirect("/Proyecto/faces/resultadoAnalisis.xhtml?rutExaminado="+rutExaminado+"&idTest="+idTest);
        
    
}
    
      public List<String> getRelatos() {
        
        UsuarioDao dao = new UsuarioDao();
        relatos=dao.getRelatos(test.getIdTest());
       // test=new Test();
                
        
        return relatos;
    }
      
     public List<Relato> getRelatosObject(){
         
         List<Relato> listaRelatos= new ArrayList<>();
         
         for (int i = 0; i < relatos.size(); i++) {
             Relato nuevoRelato= new Relato(i+1,test.getIdTest(),relatos.get(i));
             listaRelatos.add(nuevoRelato);
             
         }
      
         
         return listaRelatos;
     } 
      
          
    public List<MetricaRI> getMetricas(){
          
        //Me entrega una lista de metricas del analisis para sólo un test en particular que esté seteado en el bean
        
        
        System.out.println(" el id del test es"+ test.getIdTest());
            System.out.println(" el id ruts"+ test.getRutExaminado());
        
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+"/Examinados/"+this.test.getRutExaminado()+"/metricasRI.txt"; // Sustituye "/" por el directorio ej: "/upload"

        
      
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
         
         //si acaba la linea o la lista tiene las 10 metricas de cada relato
         while((linea=br.readLine())!=null||this.metricas.size()==10){
           // System.out.println(linea);
             
           String[] lineaSeparada=linea.split(",");  
           MetricaRI metrica = null ;
         if(lineaSeparada[0].equals(this.test.getIdTest()+"")){
           metrica= new MetricaRI(this.test.getIdTest(),Integer.parseInt(lineaSeparada[1]), Integer.parseInt(lineaSeparada[2]), 
                                    Integer.parseInt(lineaSeparada[3]), Integer.parseInt(lineaSeparada[4]), 
                                    Integer.parseInt(lineaSeparada[5]),Integer.parseInt(lineaSeparada[6]));
         
            metricas.add(metrica);
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
       
        return metricas;
        
    }
    
      
    
    
    public List<MetricaRI> getMetricasGlobalesExaminado() throws CloneNotSupportedException {
        // Para este metodo necesitamos el rut del examinado y se utilizaran los metodos
        // ya implementados para obtener los resultados globales de la implementacion
        // SI es muy lento será necesario calcular los resultados del analisis y crear un archivo
        // de valores globales y luego leerlos y leerlo por cada uno tomar los resultados y mostrarlos
        
        
        List<MetricaRI> metricasTestsExaminado= new ArrayList<>();
        // debemos obtener todos los id test de un examinado
        UsuarioDao dao = new UsuarioDao();
        List<String> todosTestExaminado=dao.getidTestsExaminado(test.getRutExaminado());
        
        for (String idTest: todosTestExaminado){
       
         metricas= new ArrayList<>();
         metricaGlobalTest= new MetricaRI();
          test.setIdTest(Integer.parseInt(idTest));
           getMetricas();
           
           getMetricaGlobalTest();
           MetricaRI temp;
            temp = (MetricaRI) metricaGlobalTest.clone();
            
            metricasTestsExaminado.add(temp);
                
        }
        

        return metricasTestsExaminado;
    }
    
    
    
    
    public void setMetricas(List<MetricaRI> metricas) {
        this.metricas = metricas;
    }

    public MetricaRI getMetricaGlobalTest() {
       
       int cantNegativos=0;
       int cantPositivos=0;
       int cantNeutros=0;
       int cantTerminos=0;
       int coincidencias=0;
        
        for (MetricaRI metrica: metricas){
            cantNegativos=cantNegativos+metrica.getCantidadNegativos();
            cantPositivos= cantNegativos+metrica.getCantidadPositivos();
            cantNeutros=cantNeutros+metrica.getCantidadNeutros();
            cantTerminos=cantTerminos+metrica.getCantidadTerminos();
            coincidencias=coincidencias+metrica.getCoincidencias();
        }
        metricaGlobalTest.setCantidadNegativos(cantNegativos);
        metricaGlobalTest.setCantidadNeutros(cantNeutros);
        metricaGlobalTest.setCantidadPositivos(cantPositivos);
        metricaGlobalTest.setCantidadTerminos(cantTerminos);
        metricaGlobalTest.setCoincidencias(coincidencias);
        metricaGlobalTest.setIdTest(this.test.getIdTest());
        metricaGlobalTest.setIdRelato(0);
      
        return metricaGlobalTest;
    }

    public void setMetricaGlobalTest(MetricaRI metricaGlobalTest) {
        this.metricaGlobalTest = metricaGlobalTest;
    }

    
 
     public BarChartModel initBarModel(MetricaRI metrica) {
         
         
           BarChartModel model = new BarChartModel();
         
        model.setTitle("Connotacion");
        model.setLegendPosition("ne");
         
        Axis xAxis = model.getAxis(AxisType.X);
      //  xAxis.setLabel("Connotacion");
         
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
       // yAxis.setMax(50);
         

 
        ChartSeries neutros = new ChartSeries();
       neutros.setLabel("neutros");
        neutros.set("Relato "+metrica.getIdRelato(),metrica.getCantidadNeutros());
       
 
        ChartSeries positivos = new ChartSeries();
       positivos.setLabel("positivos");
        positivos.set("Relato "+(metrica.getIdRelato()+1),metrica.getCantidadPositivos());
   
          ChartSeries negativos = new ChartSeries();
        negativos.setLabel("negativos");
        negativos.set("Relato "+(metrica.getIdRelato()+1),metrica.getCantidadNegativos());
        
        
 
        model.addSeries(negativos);
        model.addSeries(positivos);
        model.addSeries(neutros);
         
        return model;
    }
     
    
   
  public PieChartModel createPieModel1(MetricaRI metrica) {
       PieChartModel pieModel1 = new PieChartModel();
         
        pieModel1.set("Coincidencias", metrica.getCoincidencias());
        pieModel1.set("No Coincidencias", metrica.getCantidadTerminos()-metrica.getCoincidencias());
       pieModel1.setSeriesColors("4BB2C5,CC6666");
         
        pieModel1.setTitle("Coincidencias");
        
        //wester ubicacion de la leyenda
        pieModel1.setLegendPosition("w");
       
        return pieModel1;
    }
    
      public PieChartModel createPieModelConnotacion(MetricaRI metrica) {
       PieChartModel pieModel1 = new PieChartModel();
         
        pieModel1.set("Neutros", metrica.getCantidadNeutros());
        pieModel1.set("Positivos",metrica.getCantidadPositivos());
        pieModel1.set("Negativos",metrica.getCantidadNegativos());
        
         
        pieModel1.setTitle("Connotación");
        pieModel1.setSeriesColors("4BB2C5, 579575 ,CC6666");
        
               
        //wester ubicacion de la leyenda
        pieModel1.setLegendPosition("w");
       
        return pieModel1;
    }
    
    

    
    
     public void eliminarTest(Test test){
         System.out.println("el test id es "+test.getIdTest());  
    UsuarioDao linkDAO= new UsuarioDao();
        linkDAO.eliminarTest(test);
        test= new Test();
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    
       public Test getTest() {
        UsuarioDao dao= new UsuarioDao();
        test=dao.getTest(test.getIdTest());
           
        return test;
    }
   

    public void setRelatos(List<String> relatos) {
        this.relatos = relatos;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    
    
    
    public Usuario getUsuario() {
        UsuarioDao dao= new UsuarioDao();
        usuario=dao.getUsuario(test.getRutUsuario());
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Examinado getExaminado() {
        
        UsuarioDao dao= new UsuarioDao();
        examinado=dao.getExaminado(test.getRutExaminado());
        return examinado;
    }

    public void setExaminado(Examinado examinado) {
        this.examinado = examinado;
    }

    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }

   
       
      public String getCausaById(int idCausa){
        UsuarioDao dao= new UsuarioDao();
       CausaIngresoExaminado causa= dao.getCausaIngreso(idCausa);
       return causa.getCausa();
       
        
    }
        
   

    
    
    
    
    
    
    
}
