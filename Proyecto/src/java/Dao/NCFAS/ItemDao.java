/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.NCFAS;

import Model.NCFAS.Item;
import Model.NCFAS.Ncfas;
import Model.NCFAS.Usuario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo Verdugo
 */
public class ItemDao extends DAO{
    


public void obtenerIdNcfas() throws Exception{
           
         Connection con = null;
        Statement stmt2 = null;
        ResultSet rs = null;
        try {
            int retorno ;
            this.Conectar();
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               Ncfas tempNcfas = new Ncfas();
               retorno =  rs.getInt(1);
                /*tempNcfas.setIdncfas((int) rs.getObject(1));*/
                System.out.println(retorno);
            } 
        } catch (SQLException e) {
           
        } 
            
         finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
            }
        
            
        }
    
}

 public void ingresarItems1(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 1");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,1,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\1-ENTORNO.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 public void ingresarItems2(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 2");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,2,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\2-COMPETENCIAS.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 public void ingresarItems3(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 3");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,3,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\3-INTERACCIONES.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 public void ingresarItems4(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 4");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,4,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\4-SEGURIDAD.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 public void ingresarItems5(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 5");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,5,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\5-BIENESTAR.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 public void ingresarItems6(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 6");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,6,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\6-VIDASOCIAL.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 public void ingresarItems7(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 7");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,7,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\7-AUTONOMIA.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 public void ingresarItems8(int valor,int bandera) throws IOException  {
     
    
     FileWriter fichero = null; 
     PrintWriter pw = null; 
     System.err.println("llegasos a la dimension 8");
     Statement stmt2 = null;
        ResultSet rs = null;
        int retorno = 0;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
            if (rs.next()) {
               retorno =  rs.getInt(1);
                System.out.println(retorno);
            } 
           /* for(int i=0;i<=respuestas.length;i++){*/
            stmt = con.prepareStatement("INSERT INTO item (valor,dimension,ncfas_idncfas)"
                    + " values (?,8,?)");
        stmt.setInt(1,valor);
        stmt.setInt(2,retorno);
        stmt.executeUpdate();
        
        
       // acá escribimos en el archivo de texto lo mismo que escrcibimos en la BD
fichero = new FileWriter("C:\\Users\\Marcelo Verdugo\\Documents\\NetBeansProjects\\Proyecto\\8-SALUD.arff",true); 
pw = new PrintWriter(fichero);

if(bandera==1){
    pw.print(valor);
    pw.println("");
}else{
pw.print(valor+",");
        System.out.println("biiieennntoo");
}
}
       catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
            fichero.close();
            
        }
    }
 
 
 
  
    
    
    public List<Item> mostrarListItem() {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<Item> lista= new ArrayList();

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ncfas2?user=root&password=nbuser");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ncfas");

            while (rs.next()) {
               Item tempItem = new Item();
                tempItem.setDimension((int) rs.getObject(1));
                tempItem.setValor((int)rs.getObject(2));
                
                lista.add(tempItem);
            } 

        } catch (SQLException e) {
           
        } catch (ClassNotFoundException e) {
            
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException e) {
            }
        }

        return lista;

    }
    
    public Integer[] obtenerPuntajesDim1(int dim,int id) throws DAOException {
        int i=1;
        Integer[] listaValores;
        listaValores = new Integer[9];
        
        System.err.println("llegasos a la dimension a obtener los valores ");
        //Statement stmt2 = null;
        ResultSet rs = null;
        try{
            this.Conectar();
            
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt.setInt(1,dim);
            stmt.setInt(2,id);
            rs=stmt.executeQuery();
        System.out.println("query hecha");
                while(rs.next()){
                    listaValores[i]=rs.getInt("valor");
                    i++;
                }

        } catch (SQLException ex) {
            Logger.getLogger(DimensionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
        this.Cerrar();   
        return listaValores;
    }
    
    public Integer[] obtenerPuntajesItemsGenerales(int id) throws DAOException {
        int i=1;
        int j=1;
        int k=1;
        int l=1;
        int m=1;
        int n=1;
        int o=1;
        int p=1;
        
        Integer[] listaValores;
        listaValores = new Integer[8];
        Integer[] listaValores2;
        listaValores2 = new Integer[9];
        Integer[] listaValores3;
        listaValores3 = new Integer[9];
        Integer[] listaValores4;
        listaValores4 = new Integer[9];
        Integer[] listaValores5;
        listaValores5 = new Integer[9];
        Integer[] listaValores6;
        listaValores6 = new Integer[9];
        Integer[] listaValores7;
        listaValores7 = new Integer[9];
        Integer[] listaValores8;
        listaValores8 = new Integer[9];
        
        Integer[] listaValoresFinales;
        listaValoresFinales = new Integer[9];
        
        System.err.println("llegasos a obtener todos los items generales ");
        //Statement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        ResultSet rs6 = null;
        ResultSet rs7 = null;
        ResultSet rs8 = null;
        try{
            this.Conectar();
            
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt.setInt(1,1);
            stmt.setInt(2,id);
            rs=stmt.executeQuery();
        System.out.println("query hecha");
                while(rs.next()){
                    listaValores[i]=rs.getInt("valor");
                    if(i==7){
                        listaValoresFinales[1]=listaValores[i];
                    }
                    i++;
                }
                
             PreparedStatement stmt2 = null;
            stmt2 = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt2.setInt(1,2);
            stmt2.setInt(2,id);
            rs2=stmt2.executeQuery();
        System.out.println("query hecha");
                while(rs2.next()){
                    listaValores2[j]=rs2.getInt("valor");
                    if(j==8){
                        listaValoresFinales[2]=listaValores2[j];
                    }
                    j++;
                }
                
             PreparedStatement stmt3 = null;   
            stmt3 = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt3.setInt(1,3);
            stmt3.setInt(2,id);
            rs3=stmt3.executeQuery();
        System.out.println("query hecha");
                while(rs3.next()){
                    listaValores3[k]=rs3.getInt("valor");
                    if(i==k){
                        listaValoresFinales[3]=listaValores3[k];
                    }
                    k++;
                }
                
             PreparedStatement stmt4 = null;   
            stmt4 = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt4.setInt(1,4);
            stmt4.setInt(2,id);
            rs4=stmt4.executeQuery();
        System.out.println("query hecha");
                while(rs4.next()){
                    listaValores4[l]=rs4.getInt("valor");
                    if(l==8){
                        listaValoresFinales[4]=listaValores4[l];
                    }
                    l++;
                }
                
            PreparedStatement stmt5 = null;    
            stmt5 = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt5.setInt(1,5);
            stmt5.setInt(2,id);
            rs5=stmt5.executeQuery();
        System.out.println("query hecha");
                while(rs5.next()){
                    listaValores5[m]=rs5.getInt("valor");
                    if(m==7){
                        listaValoresFinales[5]=listaValores5[m];
                    }
                    m++;
                }
                
             PreparedStatement stmt6 = null;  
            stmt6 = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt6.setInt(1,6);
            stmt6.setInt(2,id);
            rs6=stmt6.executeQuery();
        System.out.println("query hecha");
                while(rs6.next()){
                    listaValores6[n]=rs6.getInt("valor");
                    if(n==6){
                        listaValoresFinales[6]=listaValores6[n];
                    }
                    n++;
                }
            PreparedStatement stmt7 = null;   
            stmt7 = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt7.setInt(1,7);
            stmt7.setInt(2,id);
            rs7=stmt7.executeQuery();
        System.out.println("query hecha");
                while(rs7.next()){
                    listaValores7[o]=rs7.getInt("valor");
                    if(o==7){
                        listaValoresFinales[7]=listaValores7[o];
                    }
                    o++;
                }
                
            PreparedStatement stmt8 = null;
            stmt8 = con.prepareStatement("SELECT valor FROM item where dimension= ? and ncfas_idncfas= ?");
            stmt8.setInt(1,8);
            stmt8.setInt(2,id);
            rs8=stmt8.executeQuery();
        System.out.println("query hecha");
                while(rs8.next()){
                    listaValores8[p]=rs8.getInt("valor");
                    if(p==8){
                        listaValoresFinales[8]=listaValores8[p];
                    }
                    p++;
                }

        } catch (SQLException ex) {
            Logger.getLogger(DimensionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
        this.Cerrar();   
        return listaValoresFinales;
    }
    
    
    /*public List<Ncfas> mostrarNcfas() throws Exception{
         
        List<Ncfas> lista;
        ResultSet rs=null;
        
         
        try {
                
                this.Conectar(); 
                
                
                PreparedStatement stmt = null;
                lista=new ArrayList();
                System.out.println("llegamos acá");
                rs=stmt.executeQuery("SELECT * FROM ncfas");
                System.out.println("query hecha");
                while(rs.next()){
                    Ncfas tempNcfas = new Ncfas();
                    tempNcfas.setIdncfas(rs.getInt("idncfas"));
                    tempNcfas.setNombrefamilia(rs.getString("nombrefamilia"));
                    lista.add(tempNcfas);
                    System.out.println("llegamos acá");
                } 
            } catch (SQLException e) {
           throw e;
        }finally{
                this.Cerrar();   
        }
            
        return lista;
        }*/

    public String obtenerFecha(int id) throws DAOException {
        int i=1;
        Integer[] listaValores;
        listaValores = new Integer[9];
        String fecha;
        fecha="";
        System.err.println("llegasos obtener la FECHA ");
        //Statement stmt2 = null;
        ResultSet rs = null;
        try{
            this.Conectar();
            
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT fecha from ncfas where idncfas= ?");
            stmt.setInt(1,id);
            rs=stmt.executeQuery();
        System.out.println("query hecha");
                while(rs.next()){
                    fecha=rs.getString("fecha");
                }

        } catch (SQLException ex) {
            Logger.getLogger(DimensionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
        this.Cerrar();   
        return fecha;
    }

}
