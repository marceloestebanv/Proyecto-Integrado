/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.NCFAS;


import Dao.NCFAS.DAOException;
import Dao.NCFAS.DimensionesDao;
import Dao.NCFAS.IdDAO;
import Dao.NCFAS.ItemDao;
import Dao.NCFAS.NcfasDAO;
import Dao.NCFAS.PruebaWekaDao;
import Model.NCFAS.Ncfas;
import java.io.IOException;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
 

@ManagedBean
@Named(value = "chartbean")
@ViewScoped
@RequestScoped

public class ChartView implements Serializable{
    
 
    

    private LineChartModel lineModel1;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private LineChartModel lineModel2;
    private LineChartModel lineModel3;
    private LineChartModel lineModel4;
    private LineChartModel lineModel5;
    private LineChartModel lineModel6;
    private LineChartModel lineModel7;
    private LineChartModel lineModel8;
    private LineChartModel lineModel9;
    private LineChartModel lineModel10;
    private LineChartModel lineModel11;
    private LineChartModel lineModel12;
    private LineChartModel lineModel13;
    private LineChartModel lineModel14;
    private LineChartModel lineModel15;
    private LineChartModel lineModel16;
    private LineChartModel lineModel17;
    private LineChartModel lineModel18;
    private LineChartModel lineModel20;
    private PieChartModel mineriamodel;
    private Axis yAxis;
  
 
    
    @PostConstruct
    public void ChartView()  {
        
        createLineModels1();
        createLineModels2();
        createLineModels3();
        createLineModels4();
        createLineModels5();
        createLineModels6();
        createLineModels7();
        createLineModels8();
        createLineModelComparar();
        createLineModelComparar2();
        createLineModelComparar3();
        createLineModelComparar4();
        createLineModelComparar5();
        createLineModelComparar6();
        createLineModelComparar7();
        createLineModelComparar8();
        createLineModelComparar9();
        createLineModelCompararporFecha();
        
    }
 
   /* public void crearGraficos(){
    
    }*/

    public LineChartModel getLineModel20() {
        return lineModel20;
    }

    public void setLineModel20(LineChartModel lineModel20) {
        this.lineModel20 = lineModel20;
    }
    
    
    public LineChartModel getLineModel18() {
        return lineModel18;
    }

    public void setLineModel18(LineChartModel lineModel18) {
        this.lineModel18 = lineModel18;
    }
    
    
    public LineChartModel getLineModel17() {
        return lineModel17;
    }

    public void setLineModel17(LineChartModel lineModel17) {
        this.lineModel17 = lineModel17;
    }

    
    public LineChartModel getLineModel10() {
        return lineModel10;
    }

    public void setLineModel10(LineChartModel lineModel10) {
        this.lineModel10 = lineModel10;
    }

    public LineChartModel getLineModel11() {
        return lineModel11;
    }

    public void setLineModel11(LineChartModel lineModel11) {
        this.lineModel11 = lineModel11;
    }

    public LineChartModel getLineModel12() {
        return lineModel12;
    }

    public void setLineModel12(LineChartModel lineModel12) {
        this.lineModel12 = lineModel12;
    }

    public LineChartModel getLineModel13() {
        return lineModel13;
    }

    public void setLineModel13(LineChartModel lineModel13) {
        this.lineModel13 = lineModel13;
    }

    public LineChartModel getLineModel14() {
        return lineModel14;
    }

    public void setLineModel14(LineChartModel lineModel14) {
        this.lineModel14 = lineModel14;
    }

    public LineChartModel getLineModel15() {
        return lineModel15;
    }

    public void setLineModel15(LineChartModel lineModel15) {
        this.lineModel15 = lineModel15;
    }

    public LineChartModel getLineModel16() {
        return lineModel16;
    }

    public void setLineModel16(LineChartModel lineModel16) {
        this.lineModel16 = lineModel16;
    }

    
    public LineChartModel getLineModel9() {
        return lineModel9;
    }

    public void setLineModel9(LineChartModel lineModel9) {
        this.lineModel9 = lineModel9;
    }
    
    
     public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public LineChartModel getLineModel3() {
        return lineModel3;
    }

    public void setLineModel3(LineChartModel lineModel3) {
        this.lineModel3 = lineModel3;
    }

    public LineChartModel getLineModel4() {
        return lineModel4;
    }

    public void setLineModel4(LineChartModel lineModel4) {
        this.lineModel4 = lineModel4;
    }

    public LineChartModel getLineModel5() {
        return lineModel5;
    }

    public void setLineModel5(LineChartModel lineModel5) {
        this.lineModel5 = lineModel5;
    }

    public LineChartModel getLineModel6() {
        return lineModel6;
    }

    public void setLineModel6(LineChartModel lineModel6) {
        this.lineModel6 = lineModel6;
    }

    public LineChartModel getLineModel7() {
        return lineModel7;
    }

    public void setLineModel7(LineChartModel lineModel7) {
        this.lineModel7 = lineModel7;
    }

    public LineChartModel getLineModel8() {
        return lineModel8;
    }

    public void setLineModel8(LineChartModel lineModel8) {
        this.lineModel8 = lineModel8;
    }
    
    
    public PieChartModel getMineriamodel() {
        return mineriamodel;
    }

    public void setMineriamodel(PieChartModel mineriamodel) {
        this.mineriamodel = mineriamodel;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
    
    public LineChartModel getAreaModel() {
        return lineModel2;
    }
     
    public PieChartModel getPieModel2() {
        return pieModel2;
    } 
     
   /* private void createPieModels() {
        //createPieModel1();
        //createPieModel2();
        createMineriaModel();
    }
  */ 
    public LineChartModel getLineModel2() {
        return lineModel2;
    }
    
 /*private void createMineriaModel(){   
 int valorcumple=0;
 int nocumple=0;    
     try{
         PruebaWekaDao dao;  
        dao = new PruebaWekaDao();
     valorcumple=dao.retornarNumero();
        System.out.println(valorcumple);
        nocumple=52-valorcumple;
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        mineriamodel = new PieChartModel();
        mineriamodel.set("Cumplimiento de la hipotesis", valorcumple);  
        mineriamodel.set("No cumple hipotesis", nocumple);
        mineriamodel.setShowDataLabels(true);
        mineriamodel.setTitle("Porcentage de la Hipotesis");
        mineriamodel.setDataFormat("value");
        mineriamodel.setLegendPosition("w"); 
    }*/
    
    private void createPieModel1()  {
        
       
      Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
     DimensionesDao dao;  
     dao = new DimensionesDao();
     listaValores=dao.obtenerValoresDim1();
        System.out.println(listaValores[2]);
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
     pieModel1 = new PieChartModel();
        pieModel1.set("Valoración +2", listaValores[1]);
        pieModel1.set("Valoración +1", listaValores[2]);
        pieModel1.set("Valoración +0", listaValores[3]);
        pieModel1.set("Valoración -1", listaValores[4]);
        pieModel1.set("Valoración -2",listaValores[5]);
        pieModel1.set("Valoración -3", listaValores[6]);
        pieModel1.setShowDataLabels(true);
        pieModel1.setTitle("Valores Dimensión Entorno");
        pieModel1.setDataFormat("value");
        pieModel1.setLegendPosition("w");
         
        }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();
         
        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);
        pieModel2.set("Brand 4", 421);
         
        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
    
    public void createLineModels1() {  

        lineModel1 = initCategoryModel1();
        lineModel1.setTitle("DIMENSION ENTORNO");
        lineModel1.setLegendPosition("e");
        lineModel1.setShowPointLabels(true);
        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel1() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(1,dao2.retornarID());
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      /*  ChartSeries confort = new ChartSeries();
      
        confort.setLabel("CONFORT");
        confort.set("Item 1", 1);
        confort.set("Item 2", 1);
        confort.set("Item 3", 1);
        confort.set("Item 4", 1);
        confort.set("Item 5", 1);
        confort.set("Item 6", 1);
        confort.set("Item 7", 1);*/
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item 6", -1);
        riesgo.set("Item General", -1);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item General", listaValores[7]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
    
     private void createLineModels2() {
        lineModel2 = initCategoryModel2();
        lineModel2.setTitle("COMPETENCIAS PARENTALES");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);
    }
     private LineChartModel initCategoryModel2() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(2,dao2.retornarID());
        System.out.println(listaValores[2]);
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
        LineChartModel model = new LineChartModel();
 
      
   
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item 6", -1);
        riesgo.set("Item 7", -1);
        riesgo.set("Item General", -1);
 
        ChartSeries valores = new ChartSeries();
       
        
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     private void createLineModels3() {
        lineModel3 = initCategoryModel3();
        lineModel3.setTitle("INTERACCIONES FAMILIARES");
        lineModel3.setLegendPosition("e");
        lineModel3.setShowPointLabels(true);
        lineModel3.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel3.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);
    }
     private LineChartModel initCategoryModel3() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
         IdDAO dao2;  
     dao2 = new IdDAO();
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(3,dao2.retornarID());
        System.out.println(listaValores[2]);
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
        LineChartModel model = new LineChartModel();
 
      
   
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item 6", -1);
        riesgo.set("Item 7", -1);
        riesgo.set("Item General", -1);
 
        ChartSeries valores = new ChartSeries();
       
        
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     private void createLineModels4() {
        lineModel4 = initCategoryModel4();
        lineModel4.setTitle("SEGURIDAD SOCIAL");
        lineModel4.setLegendPosition("e");
        lineModel4.setShowPointLabels(true);
        lineModel4.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel4.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);
    }
     private LineChartModel initCategoryModel4() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
         IdDAO dao2;  
     dao2 = new IdDAO();
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(4,dao2.retornarID());
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
        LineChartModel model = new LineChartModel();
 
    
   
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item 6", -1);
        riesgo.set("Item 7", -1);
        riesgo.set("Item General", -1);
 
        ChartSeries valores = new ChartSeries();
       
        
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     private void createLineModels5() {
        lineModel5 = initCategoryModel5();
        lineModel5.setTitle("BIENESTAR DEL NIÑO");
        lineModel5.setLegendPosition("e");
        lineModel5.setShowPointLabels(true);
        lineModel5.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel5.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);
    }
     private LineChartModel initCategoryModel5() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
         IdDAO dao2;  
     dao2 = new IdDAO();
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(5,dao2.retornarID());
        System.out.println(listaValores[2]);
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
        LineChartModel model = new LineChartModel();
 
      
   
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item 6", -1);
        riesgo.set("Item General", -1);
 
        ChartSeries valores = new ChartSeries();
       
        
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item General", listaValores[7]);
           
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     private void createLineModels6() {
        lineModel6 = initCategoryModel6();
        lineModel6.setTitle("VIDA SOCIAL COMUNITARIA");
        lineModel6.setLegendPosition("e");
        lineModel6.setShowPointLabels(true);
        lineModel6.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel6.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);
    }
     private LineChartModel initCategoryModel6() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
         IdDAO dao2;  
     dao2 = new IdDAO();
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(6,dao2.retornarID());
        System.out.println(listaValores[2]);
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
        LineChartModel model = new LineChartModel();
 
      
   
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item General", -1);
 
        ChartSeries valores = new ChartSeries();
       
        
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item General", listaValores[6]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     private void createLineModels7() {
        lineModel7 = initCategoryModel7();
        lineModel7.setTitle("AUTONOMÍA");
        lineModel7.setLegendPosition("e");
        lineModel7.setShowPointLabels(true);
        lineModel7.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel7.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);
    }
     private LineChartModel initCategoryModel7() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
         IdDAO dao2;  
     dao2 = new IdDAO();
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(7,dao2.retornarID());
        System.out.println(listaValores[2]);
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
        LineChartModel model = new LineChartModel();
 
      
   
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item General", -1);
        
 
        ChartSeries valores = new ChartSeries();
       
        
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item General", listaValores[6]);
        
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     private void createLineModels8() {
        lineModel8 = initCategoryModel8();
        lineModel8.setTitle("SALUD FAMILIAR");
        lineModel8.setLegendPosition("e");
        lineModel8.setShowPointLabels(true);
        lineModel8.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel8.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);
    }
     private LineChartModel initCategoryModel8() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     try{
         IdDAO dao2;  
     dao2 = new IdDAO();
     ItemDao dao;  
     dao = new ItemDao();
     listaValores=dao.obtenerPuntajesDim1(8,dao2.retornarID());
        System.out.println(listaValores[2]);
     
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
     
        LineChartModel model = new LineChartModel();
 
      
   
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.set("Item 1", -1);
        riesgo.set("Item 2", -1);
        riesgo.set("Item 3", -1);
        riesgo.set("Item 4", -1);
        riesgo.set("Item 5", -1);
        riesgo.set("Item 6", -1);
        riesgo.set("Item 7", -1);
        riesgo.set("Item General", -1);
 
        ChartSeries valores = new ChartSeries();
       
        
        valores.setLabel("VALORES DE LA DIMENSION");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar() {  

        lineModel9 = initCategoryModel9();
        lineModel9.setTitle("DIMENSION ENTORNO");
        lineModel9.setLegendPosition("e");
        lineModel9.setShowPointLabels(true);
        lineModel9.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel9.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel9() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(1,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(1,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      /*  ChartSeries confort = new ChartSeries();
      
        confort.setLabel("CONFORT");
        confort.set("Item 1", 1);
        confort.set("Item 2", 1);
        confort.set("Item 3", 1);
        confort.set("Item 4", 1);
        confort.set("Item 5", 1);
        confort.set("Item 6", 1);
        confort.set("Item 7", 1);*/
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item 6", listaValores2[6]);
        riesgo.set("Item General", listaValores2[7]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item General", listaValores[7]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar2() {  

        lineModel10 = initCategoryModel10();
        lineModel10.setTitle("DIMENSION COMPETENCIAS PARENTALES");
        lineModel10.setLegendPosition("e");
        lineModel10.setShowPointLabels(true);
        lineModel10.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel10.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel10() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(2,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(2,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item 6", listaValores2[6]);
        riesgo.set("Item 7", listaValores2[7]);
        riesgo.set("Item General", listaValores2[8]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar3() {  

        lineModel11 = initCategoryModel11();
        lineModel11.setTitle("DIMENSION ENTORNO");
        lineModel11.setLegendPosition("e");
        lineModel11.setShowPointLabels(true);
        lineModel11.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel11.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel11() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(3,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(3,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      /*  ChartSeries confort = new ChartSeries();
      
        confort.setLabel("CONFORT");
        confort.set("Item 1", 1);
        confort.set("Item 2", 1);
        confort.set("Item 3", 1);
        confort.set("Item 4", 1);
        confort.set("Item 5", 1);
        confort.set("Item 6", 1);
        confort.set("Item 7", 1);*/
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item 6", listaValores2[6]);
        riesgo.set("Item 7", listaValores2[7]);
        riesgo.set("Item General", listaValores2[8]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar4() {  

        lineModel12 = initCategoryModel12();
        lineModel12.setTitle("DIMENSION SEGURIDAD SOCIAL");
        lineModel12.setLegendPosition("e");
        lineModel12.setShowPointLabels(true);
        lineModel12.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel12.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel12() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(4,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(4,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item 6", listaValores2[6]);
        riesgo.set("Item 7", listaValores2[7]);
        riesgo.set("Item General", listaValores2[8]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar5() {  

        lineModel13 = initCategoryModel13();
        lineModel13.setTitle("DIMENSION BIENESTAR DEL NIÑO");
        lineModel13.setLegendPosition("e");
        lineModel13.setShowPointLabels(true);
        lineModel13.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel13.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel13() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(5,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(5,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      /*  ChartSeries confort = new ChartSeries();
      
        confort.setLabel("CONFORT");
        confort.set("Item 1", 1);
        confort.set("Item 2", 1);
        confort.set("Item 3", 1);
        confort.set("Item 4", 1);
        confort.set("Item 5", 1);
        confort.set("Item 6", 1);
        confort.set("Item 7", 1);*/
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item 6", listaValores2[6]);
        riesgo.set("Item General", listaValores2[7]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item General", listaValores[7]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar6() {  

        lineModel14 = initCategoryModel14();
        lineModel14.setTitle("DIMENSION VIDA SOCIAL COMUNITARIA");
        lineModel14.setLegendPosition("e");
        lineModel14.setShowPointLabels(true);
        lineModel14.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel14.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel14() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(6,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(6,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
     
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item General", listaValores2[7]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item General", listaValores[7]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar7() {  

        lineModel15 = initCategoryModel15();
        lineModel15.setTitle("DIMENSION AUTONOMIA");
        lineModel15.setLegendPosition("e");
        lineModel15.setShowPointLabels(true);
        lineModel15.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel15.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel15() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(7,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(7,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item General", listaValores2[6]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item General", listaValores[6]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar8() {  

        lineModel16 = initCategoryModel16();
        lineModel16.setTitle("DIMENSION SALUD FAMILIAR");
        lineModel16.setLegendPosition("e");
        lineModel16.setShowPointLabels(true);
        lineModel16.getAxes().put(AxisType.X, new CategoryAxis("ITEMS"));
        yAxis = lineModel16.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel16() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
     IdDAO dao2;  
     dao2 = new IdDAO();
     
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     listaValores=dao.obtenerPuntajesDim1(8,dao2.retornarID());
     
     listaValores2=dao1.obtenerPuntajesDim1(8,elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("RIESGO");
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("Item 1", listaValores2[1]);
        riesgo.set("Item 2", listaValores2[2]);
        riesgo.set("Item 3", listaValores2[3]);
        riesgo.set("Item 4", listaValores2[4]);
        riesgo.set("Item 5", listaValores2[5]);
        riesgo.set("Item 6", listaValores2[6]);
        riesgo.set("Item 7", listaValores2[7]);
        riesgo.set("Item General", listaValores2[8]);
 
        ChartSeries valores = new ChartSeries();
       
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("Item 1", listaValores[1]);
        valores.set("Item 2", listaValores[2]);
        valores.set("Item 3", listaValores[3]);
        valores.set("Item 4", listaValores[4]);
        valores.set("Item 5", listaValores[5]);
        valores.set("Item 6", listaValores[6]);
        valores.set("Item 7", listaValores[7]);
        valores.set("Item General", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelComparar9() {  

        lineModel17 = initCategoryModel17();
        lineModel17.setTitle("ITEMS GENERALES");
        lineModel17.setLegendPosition("e");
        lineModel17.setShowPointLabels(true);
        lineModel17.getAxes().put(AxisType.X, new CategoryAxis("DIMENSIONES"));
        yAxis = lineModel17.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel17() {
        
     Integer listaValores[];
     listaValores = new Integer[8];
     
     Integer listaValores2[];
     listaValores2 = new Integer[8];
     
     int elotroid;
     
     try{
         
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     IdDAO dao2;  
     dao2 = new IdDAO();
   
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     
     //OBTENGO EL ID DE LA OTRA APRECIACION A PARTIR DEL ID DEL NCFAS FINAL
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     //OBTENGO LOS VALORES DE LA DIMENSION 8 DEL NCFAS FINAL
     listaValores=dao.obtenerPuntajesItemsGenerales(dao2.retornarID());
     
     //OBTENGO LOS VALORES DE LA DIMENSION 8 DEL NCFAS INICIAL
     listaValores2=dao1.obtenerPuntajesItemsGenerales(elotroid);
        System.out.println(listaValores[2]);
     }catch(DAOException ex){
     Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
     }
        LineChartModel model = new LineChartModel();
 
      
        ChartSeries riesgo = new ChartSeries();    
        riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("DIM 1", listaValores2[1]);
        riesgo.set("DIM 2", listaValores2[2]);
        riesgo.set("DIM 3", listaValores2[3]);
        riesgo.set("DIM 4", listaValores2[4]);
        riesgo.set("DIM 5", listaValores2[5]);
        riesgo.set("DIM 6", listaValores2[6]);
        riesgo.set("DIM 7", listaValores2[7]);
        riesgo.set("DIM 8", listaValores2[8]);
 
        ChartSeries valores = new ChartSeries();
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
        valores.set("DIM 1", listaValores[1]);
        valores.set("DIM 2", listaValores[2]);
        valores.set("DIM 3", listaValores[3]);
        valores.set("DIM 4", listaValores[4]);
        valores.set("DIM 5", listaValores[5]);
        valores.set("DIM 6", listaValores[6]);
        valores.set("DIM 7", listaValores[7]);
        valores.set("DIM 8", listaValores[8]);
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
         System.out.println("rellenamos la dim 1 :S");
       //areaModel.addSeries(confort);
  return model;
        
    }
     
     public void createLineModelCompararporFecha() {  

        lineModel20 = initCategoryModel20();
        lineModel20.setTitle("COMPARACIÓN POR FECHAS");
        lineModel20.setLegendPosition("e");
        lineModel20.setShowPointLabels(true);
        lineModel20.getAxes().put(AxisType.X, new CategoryAxis("FECHAS"));
        yAxis = lineModel20.getAxis(AxisType.Y);
        yAxis.setLabel("VALORES");
        yAxis.setMin(-3);
        yAxis.setMax(2);               
    }
    
     private LineChartModel initCategoryModel20() {
        
     Integer listaValores[];
     listaValores = new Integer[9];
     
     String listaMeses[];
     listaMeses = new String[13];
     listaMeses[1]="ENERO";
     listaMeses[2]="FEBR.";
     listaMeses[3]="MARZO";
     listaMeses[4]="ABRIL";
     listaMeses[5]="MAYO";
     listaMeses[6]="JUNIO";
     listaMeses[7]="JULIO";
     listaMeses[8]="AGOSTO";
     listaMeses[9]="SEPT.";
     listaMeses[10]="OCTUB.";
     listaMeses[11]="NOVIEM.";
     listaMeses[12]="DICIEM.";
     
     Integer listaValores2[];
     listaValores2 = new Integer[9];
     
     String fecha1;
     String fecha2;
     String fechainicial="";
     String fechafinal="";
     int fecha1int=0;
     int fecha2int=0;
     
     int elotroid;
     
     try{
         
     ItemDao dao;  
     dao = new ItemDao();
     
     ItemDao dao1;  
     dao1 = new ItemDao();
     
     IdDAO dao2;  
     dao2 = new IdDAO();
   
     NcfasDAO dao3;
     dao3=new NcfasDAO();
     
     //OBTENGO EL ID DE LA OTRA APRECIACION A PARTIR DEL ID DEL NCFAS FINAL
     elotroid=dao3.obtenerIdPorotroID(dao2.retornarID());
     //OBTENGO LOS VALORES DE LA DIMENSION 8 DEL NCFAS FINAL
     listaValores=dao.obtenerPuntajesItemsGenerales(dao2.retornarID());
     
     //OBTENGO LOS VALORES DE LA DIMENSION 8 DEL NCFAS INICIAL
     listaValores2=dao1.obtenerPuntajesItemsGenerales(elotroid);
     
     //OBETNER FECHA APRECIACION INICIAL
     fecha2=dao.obtenerFecha(dao2.retornarID());
     //OBTENER FECHA APRECIACION FINAL
     fecha1=dao1.obtenerFecha(elotroid);
     

    String delimiter = "-";
    String[] temp;
    temp = new String[3];
    String[] temp2;
    temp2 = new String[3];
    temp2 = fecha1.split(delimiter);
    temp = fecha2.split(delimiter);
   
    
    fecha1int=Integer.parseInt(temp[1]);
    fecha2int=Integer.parseInt(temp2[1]);
    
    fechainicial=temp[0]+"-"+listaMeses[fecha2int];
    fechafinal=temp2[0]+"-"+listaMeses[fecha1int];
    
        
     System.out.println(listaValores[2]);
    }catch(DAOException ex){
    Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
        LineChartModel model = new LineChartModel();
 
      
        ChartSeries riesgo = new ChartSeries(); 
         
         riesgo.setLabel("DIMENSIÓN COMPETENCIAS PARENTALES");       
         riesgo.set(fechafinal, listaValores2[2]);
         riesgo.set(fechainicial, listaValores[2]);
         for(int i=fecha1int;i<fecha2int;i++){
         //seteo los meses intermedios
             riesgo.set(listaMeses[i], null);
         }
         
         
        
       /*  ChartSeries valores = new ChartSeries();
        valores.setLabel("VALORES DE LA APRECIACIÓN INICIAL");
            for(int i=1;i<=listaValores.length;i++){
         valores.set(listaMeses[i], listaValores2[i]);
        }*/
        /*riesgo.setLabel("VALORES DE LA APRECIACIÓN FINAL");
        riesgo.set("ENTORNO", listaValores2[1]);
        riesgo.set("COMPETENCIAS PARENTALES", listaValores2[2]);
        riesgo.set("INTERACCIONES FAMILIARES", listaValores2[3]);
        riesgo.set("SEGURIDAD SOCIAL", listaValores2[4]);
        riesgo.set("BIENESTAR DEL NIÑO", listaValores2[5]);
        riesgo.set("VIDA SOCIAL COMUNITARIA", listaValores2[6]);
        riesgo.set("AUTONOMÍA", listaValores2[7]);
        riesgo.set("SALUD FAMILIAR", listaValores2[8]);*/
 
        ChartSeries valores = new ChartSeries();
        valores.setLabel("DIMENSIÓN INTERACCIONES FAMILIARES");
        valores.set(fechafinal, listaValores2[3]); 
        valores.set(fechainicial, listaValores[3]); 
          for(int i=fecha1int;i<fecha2int;i++){
         //seteo los meses intermedios
             valores.set(listaMeses[i], null);
         }
        /*valores.set("ENTORNO", listaValores[1]);
        valores.set("COMPETENCIAS PARENTALES", listaValores[2]);
        valores.set("INTERACCIONES FAMILIARES", listaValores[3]);
        valores.set("SEGURIDAD SOCIAL", listaValores[4]);
        valores.set("BIENESTAR DEL NIÑO", listaValores[5]);
        valores.set("VIDA SOCIAL COMUNITARIA", listaValores[6]);
        valores.set("AUTONOMÍA", listaValores[7]);
        valores.set("SALUD FAMILIAR", listaValores[8]);*/
        
       
       // model.addSeries(confort);
        model.addSeries(riesgo);
        model.addSeries(valores);
       //areaModel.addSeries(confort);
  return model;
        
    }
}
