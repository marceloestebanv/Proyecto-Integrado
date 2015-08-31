/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author jean
 */

@ManagedBean(name = "mannagerPDFBean")
@RequestScoped


public class MannagerPDFBean {

     @ManagedProperty("#{calcularMetricas}")
   private CalcularMetricas metricasTest;
   
   @ManagedProperty("#{testBean}")
    private TestBean testBean;
    
    /**
     * Creates a new instance of MannagerPDFBean
     */
    public MannagerPDFBean() {
    }

    
    
      public void exportarPDF() throws JRException, IOException{
        
        
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+"/resources/imagen/";

        
          
        //campo fijo (txtUsu.. valor del parmetro jasper, Mitocode... valor que le daremos desde java
        
    Map<String,Object> parametros= new HashMap<>();        
    parametros.put("nombreEx",testBean.getExaminado().getNombre());
    parametros.put("nombreUs", testBean.getUsuario().getNombre());
    parametros.put("realPath",realPath);
    parametros.put("fecha",testBean.getTest().getFecha().toString());
    parametros.put("globalCant",metricasTest.metricaGlobalTest.getCantidadTerminos()+"");
    parametros.put("globalPos",metricasTest.metricaGlobalTest.getCantidadPositivos()+"");
    parametros.put("globalNeg",metricasTest.metricaGlobalTest.getCantidadNegativos()+"");
    parametros.put("globalNeu",metricasTest.metricaGlobalTest.getCantidadNeutros()+"");
    parametros.put("globalCoinc",metricasTest.metricaGlobalTest.getCoincidencias()+"");
        
   
    System.out.println(testBean.getExaminado().getNombre());
    
   // parametros.put("nombreUs",testBean.getUsuario().getNombre());
        
        
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/report2.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(metricasTest.getMetricasTest()));
       
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=CAT-Resultados-"+testBean.getExaminado().getRut()+"-ID"+testBean.getTest().getIdTest() +".pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
    }
      
      
       public void exportarPDFRelatos() throws JRException, IOException{
        
           System.out.println(" hola");
        
             ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+"/resources/imagen/";

        
           
        //campo fijo (txtUsu.. valor del parmetro jasper, Mitocode... valor que le daremos desde java
        
    Map<String,Object> parametros= new HashMap<>();        
    parametros.put("nombreEx",testBean.getExaminado().getNombre());
    parametros.put("nombreUs", testBean.getUsuario().getNombre());
    parametros.put("realPath",realPath);
    parametros.put("fecha",testBean.getTest().getFecha().toString());
    
    

        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/relatos.jasper"));
      JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(testBean.getRelatosObject()));
      // JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JREmptyDataSource());
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=CAT-Relatos-"+testBean.getExaminado().getRut()+"-ID"+testBean.getTest().getIdTest() +".pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
    }
      
       
             public void exportarPDFEstadistExaminado() throws JRException, IOException{
        
        
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+"/resources/imagen/";

        
          
        //campo fijo (txtUsu.. valor del parmetro jasper, Mitocode... valor que le daremos desde java
        
    Map<String,Object> parametros= new HashMap<>();        
   
    parametros.put("nombreEx",testBean.getExaminado().getNombre());
    parametros.put("rutEx",testBean.getExaminado().getRut());
    parametros.put("realPath",realPath);
   // parametros.put("causaIn",testBean.getCausaById(testBean.getExaminado().getIdCausaIngreso()));
    parametros.put("cantTest",metricasTest.cantidadTestsExaminado+"");
    parametros.put("globalCant",metricasTest.metricaGlobalTestsExaminado.getCantidadTerminos()+"");
    parametros.put("globalPos",metricasTest.metricaGlobalTestsExaminado.getCantidadPositivos()+"");
    parametros.put("globalNeg",metricasTest.metricaGlobalTestsExaminado.getCantidadNegativos()+"");
    parametros.put("globalNeu",metricasTest.metricaGlobalTestsExaminado.getCantidadNeutros()+"");
    parametros.put("globalCoinc",metricasTest.metricaGlobalTestsExaminado.getCoincidencias()+"");
        
   
    System.out.println(testBean.getExaminado().getNombre());
    
   // parametros.put("nombreUs",testBean.getUsuario().getNombre());
        
        
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/estadistica.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(metricasTest.metricaTestsExaminado));
       
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=CAT-Estadisticas-"+testBean.getExaminado().getRut()+".pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
    }
       
      
             public void exportarPDFEstadistCausaIngreso() throws JRException, IOException{
        
        
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
 String realPath=(String) servletContext.getRealPath("/")+"/resources/imagen/";

        
          
        //campo fijo (txtUsu.. valor del parmetro jasper, Mitocode... valor que le daremos desde java
        
    Map<String,Object> parametros= new HashMap<>();        
    
    parametros.put("realPath",realPath);
    parametros.put("causaIn",testBean.getCausaById(metricasTest.causaIngreso));
    parametros.put("cantTest",metricasTest.cantidadTestsPorCausa+"");
    parametros.put("globalCant",metricasTest.metricaGlobalMuchosTests.getCantidadTerminos()+"");
    parametros.put("globalPos",metricasTest.metricaGlobalMuchosTests.getCantidadPositivos()+"");
    parametros.put("globalNeg",metricasTest.metricaGlobalMuchosTests.getCantidadNegativos()+"");
    parametros.put("globalNeu",metricasTest.metricaGlobalMuchosTests.getCantidadNeutros()+"");
    parametros.put("globalCoinc",metricasTest.metricaGlobalMuchosTests.getCoincidencias()+"");
        
   
    System.out.println(testBean.getExaminado().getNombre());
    
   // parametros.put("nombreUs",testBean.getUsuario().getNombre());
        
        
        
        File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/estCausaIng.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(metricasTest.metricaMuchosTests));
       
     HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=CAT-Estadisticas-CausaIngreso.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
    }
       
       
    public CalcularMetricas getMetricasTest() {
        
        
        return metricasTest;
    }

    public void setMetricasTest(CalcularMetricas metricasTest) {
         
        this.metricasTest = metricasTest;
    }

    public TestBean getTestBean() {
        
       
        return testBean;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }
    
    
    
    
}
