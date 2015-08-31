/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.NCFAS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo Verdugo
 */
public class IdDAO {

    public IdDAO() {
    }
    
    
    
    
    
    
   public void guardarID(int id) throws Exception{
       
       FileWriter fichero = null; 
     PrintWriter pw = null; 
     try{
       fichero = new FileWriter("infoid.txt",true);
       pw = new PrintWriter(fichero);
       pw.print(id);
       pw.println("");
       System.out.println("biiieennntoo");
       
    }catch(Exception e){
        throw e;
    } finally{
        fichero.close();
        }
    
    
}
   
    public int retornarID() throws DAOException{

int retorno=0;
 try{  
BufferedReader bf = new BufferedReader(new FileReader("infoid.txt"));

String temp="";
String[] lineas;
lineas = new String[30];

int i=0;
while(temp!=null){
    
    temp = bf.readLine();
    if(temp==null)
        break;    
    retorno = Integer.parseInt(temp);
    i++;
}  
 
 
     System.out.println(retorno);
  
 }catch (IOException | NumberFormatException ex) {
            Logger.getLogger(DimensionesDao.class.getName()).log(Level.SEVERE, null, ex);
        }        

 
return retorno;

}

}