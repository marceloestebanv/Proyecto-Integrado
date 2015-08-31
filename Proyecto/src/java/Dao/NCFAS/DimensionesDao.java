/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.NCFAS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo Verdugo
 */
public class DimensionesDao extends DAO {

    public DimensionesDao() {
        
        
    }
    
    
    // contador de la cantidad de items de la dimension 1
    
    
    public Integer[] obtenerValoresDim1() throws DAOException {
        
        int contadorDN=0;
        int contador2=0;
        int contador1=0;
        int contador0=0;
        int contador_1=0;
        int contador_2=0;
        int contador_3=0;
        int contadorNA=0;
        Integer[] listaValores;
        listaValores = new Integer[8];
        
        System.err.println("llegasos a la dimension a obtener los valores ");
        Statement stmt2 = null;
        ResultSet rs = null;
        try{
            this.Conectar();
            PreparedStatement stmt = null;
            stmt2 = con.createStatement();
            rs = stmt2.executeQuery("SELECT MAX(idncfas) as idncfas from ncfas");
        
        stmt2 = con.createStatement();
        
            rs = stmt2.executeQuery("SELECT valor FROM item where dimension='1'");
        
        System.out.println("query hecha");
                while(rs.next()){
                    
                    if((rs.getInt("valor")==2)){
                        contador2++;
                    } if(rs.getInt("valor")==1){
                        contador1++;
                    } if(rs.getInt("valor")==0){
                        contador0++;
                    } if(rs.getInt("valor")==-1){
                        contador_1++;
                    } if(rs.getInt("valor")==-2){
                        contador_2++;
                    }  if(rs.getInt("valor")==-3){
                        contador_3++;
                    } if(rs.getInt("valor")==4) {
                        contadorNA++;
                    } if(rs.getInt("valor")==3){
                        contadorDN++;
                    }
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(DimensionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
                
                listaValores[1]=contadorDN;
                listaValores[2]=contador2;
                listaValores[3]=contador1;
                listaValores[4]=contador0;
                listaValores[5]=contador_1;
                listaValores[6]=contador_2;
                listaValores[7]=contador_3;
                
             /*   System.out.println(listaValores[1]);
               System.out.println(listaValores[2]);
               System.out.println(listaValores[3]);
               System.out.println(listaValores[4]);
               System.out.println(listaValores[5]);
               System.out.println(listaValores[6]);
               System.out.println(listaValores[7]);*/
               
            
        
                this.Cerrar();   
        return listaValores;
         
        
    }
    
}
