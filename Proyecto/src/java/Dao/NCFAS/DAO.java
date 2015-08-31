package Dao.NCFAS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DAO {
    
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
    
    public void Conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf?user=pma&password=pmapass");
            
        } catch (SQLException e) {
            System.out.println("error"+e);
            
        } catch (ClassNotFoundException e) {
            System.out.println("error"+e);
        
        }
    }
    
    public void Cerrar(){
        try{
       if(con!=null){
           if(con.isClosed() == false){
            con.close();
            } 
        }
        } catch (SQLException e) {
            System.out.println("error"+e);
            
        } 
    }
     
}