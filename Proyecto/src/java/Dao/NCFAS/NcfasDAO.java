package Dao.NCFAS;

import Model.NCFAS.Ncfas;
import Model.NCFAS.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import weka.core.Utils;



public class NcfasDAO extends DAO {
    
    
    
    public void ingresarNcafas(String nombrefamilia ,int parteproceso /*,Usuario usuario*/)  {
    String fechaactual;
    Date fecha= new Date(); 
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        fechaactual=formatoFecha.format(fecha);
        System.out.println(fechaactual);
        
    try{
           
        this.Conectar();
        PreparedStatement stmt = null;
        
            stmt = con.prepareStatement("INSERT INTO ncfas (nombrefamilia,parteproceso,fecha)"
                    + " values (?,?,?)");  
            stmt.setString(1, nombrefamilia);
            stmt.setInt(2, parteproceso);
            stmt.setString(3, fechaactual);
            /*stmt.setString(4, usuario.getRut())*/
            int retorno = stmt.executeUpdate();
            System.out.println("biiieennntoo");

        }catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();
        } finally { 
            this.Cerrar();
        }
    }
    
    
    public List<Ncfas> mostrarListNcfas() {
        
         Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<Ncfas> lista= new ArrayList();

        try {
            
            this.Conectar();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ncfas");

            while (rs.next()) {
               Ncfas tempNcfas = new Ncfas();
                tempNcfas.setIdncfas((int) rs.getObject(1));
                tempNcfas.setNombrefamilia(rs.getObject(2).toString());
                
                lista.add(tempNcfas);
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

        return lista;

    }
    
    public List<Ncfas> mostrarNcfases() throws Exception {
        ResultSet rs = null;
        List<Ncfas> lista;
        try {
            this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM ncfas");
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Ncfas tempNcfas = new Ncfas();
                tempNcfas.setIdncfas((Integer)rs.getObject(1));
                tempNcfas.setNombrefamilia(rs.getObject(2).toString());
                tempNcfas.setParteproceso((Integer) rs.getObject(3));
                lista.add(tempNcfas);
            } 
        }catch(Exception e){
        throw e;
        }finally{
        this.Cerrar();
        }
        return lista;
    }
    
    public List<Ncfas> mostrarNcfasesIniciales() throws Exception {
        ResultSet rs = null;
        List<Ncfas> lista;
        int i=1;
        try {
            this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM ncfas where parteproceso=?");
            stmt.setInt(1, i);
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Ncfas tempNcfas = new Ncfas();
                tempNcfas.setIdncfas((Integer)rs.getObject(1));
                tempNcfas.setNombrefamilia(rs.getObject(2).toString());
                tempNcfas.setParteproceso((Integer) rs.getObject(3));
                lista.add(tempNcfas);
            } 
        }catch(Exception e){
        throw e;
        }finally{
        this.Cerrar();
        }
        return lista;
    }
    
     public List<Ncfas> mostrarNcfasesFinales() throws Exception {
        ResultSet rs = null;
        List<Ncfas> lista;
        int i=3;
        try {
            this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM ncfas where parteproceso=?");
            stmt.setInt(1, i);
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Ncfas tempNcfas = new Ncfas();
                tempNcfas.setIdncfas((Integer)rs.getObject(1));
                tempNcfas.setNombrefamilia(rs.getObject(2).toString());
                tempNcfas.setParteproceso((Integer) rs.getObject(3));
                lista.add(tempNcfas);
            } 
        }catch(Exception e){
        throw e;
        }finally{
        this.Cerrar();
        }
        return lista;
    }
    
     public List<Ncfas> mostrarNcfasesporFechas(String date1, String date2) throws Exception {
        ResultSet rs = null;
        List<Ncfas> lista;
        try {
            this.Conectar();
            PreparedStatement stmt = null;
            
            System.out.println("Haremos la query con"+date1);
            stmt = con.prepareStatement("select * from ncfas where fecha between ? and ? ");
            stmt.setString(1,  date1);
            stmt.setString(2,  date2);
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Ncfas tempNcfas = new Ncfas();
                tempNcfas.setIdncfas((Integer)rs.getObject(1));
                tempNcfas.setNombrefamilia(rs.getObject(2).toString());
                tempNcfas.setParteproceso((Integer) rs.getObject(3));
                lista.add(tempNcfas);
            } 
        }catch(Exception e){
        throw e;
        }finally{
        this.Cerrar();
        }
        return lista;
    }
     
     
     public Ncfas obtenerNcfasporNombreFamilia(String nombrefamilia) throws Exception {
        ResultSet rs = null;
        Ncfas tempNcfas;
        tempNcfas = new Ncfas();
        int i=1;
        try {
            this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM ncfas where parteproceso=? and nombrefamilia=?");
            stmt.setInt(1, i);
            stmt.setString(2, nombrefamilia);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                tempNcfas.setIdncfas((Integer)rs.getObject(1));
                tempNcfas.setNombrefamilia(rs.getObject(2).toString());
                tempNcfas.setParteproceso((Integer) rs.getObject(3));
            }
        }catch(Exception e){
        throw e;
        }finally{
        this.Cerrar();
        }
        return tempNcfas;
    }
    
    
            
      public int obtenerIdPorotroID(int idncfas) {
      //System.out.println("usuario modif"+ncfas.getNombrefamilia()); 
      //System.out.println("usuario modif"+ncfas.getParteproceso()); 
      //System.out.println("usuario modif"+ncfas.getUsuariorut());
          System.out.println("el id es"+ idncfas);
       ResultSet rs = null;
       Ncfas tempNcfas;
       tempNcfas = new Ncfas();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        String nombrefamilia="";
        int idncfasretorno=0;
        try {
            this.Conectar();
            stmt = con.prepareStatement("select * from ncfas where idncfas= ?"); 
            stmt.setInt(1, idncfas);
            rs = stmt.executeQuery();
            while(rs.next()){
            tempNcfas.setNombrefamilia(rs.getObject(2).toString());    
            nombrefamilia =tempNcfas.getNombrefamilia();
            }
            System.out.println("el nombre a buscar es "+nombrefamilia);
            stmt2 = con.prepareStatement("select * from ncfas where nombrefamilia=? and parteproceso=?");
            stmt2.setString(1, nombrefamilia);
            stmt2.setInt(2, 1);
            rs = stmt2.executeQuery();
            while(rs.next()){
                
            tempNcfas.setIdncfas((Integer)rs.getObject(1));
            idncfasretorno=(tempNcfas.getIdncfas());          
            }
            
            System.out.println("EL ID QUE VAMOS A RETORNAR ES:"+idncfasretorno);
        } catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return idncfasretorno;
    }
    
    public void modificarNcfas(Ncfas ncfas) {
      System.out.println("usuario modif"+ncfas.getNombrefamilia()); 
      System.out.println("usuario modif"+ncfas.getParteproceso()); 
      //System.out.println("usuario modif"+ncfas.getUsuariorut()); 
       Connection con = null;
        PreparedStatement stmt = null;


        try {

            this.Conectar();

            
            stmt = con.prepareStatement("UPDATE ncfas SET nombrefamilia= ?,parteproceso= ? WHERE idncfas ='"+ncfas.getIdncfas()+"'"); 
            stmt.setString(1, ncfas.getNombrefamilia());
            stmt.setInt(2, ncfas.getParteproceso());
           // stmt.setString(3, ncfas.getUsuariorut());
           
            int retorno = stmt.executeUpdate();
            
        } catch (SQLException sqle) {
            System.out.println("SQLState: "
                    + sqle.getSQLState());
            System.out.println("SQLErrorCode: "
                    + sqle.getErrorCode());
            sqle.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void eliminarNcfas(Ncfas ncfas) {
      
        System.out.println("mandador a eliminar");
        System.out.println("usuario"+ncfas.getIdncfas());
         Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            this.Conectar();
            stmt = con.prepareStatement("DELETE FROM ncfas WHERE idncfas=?");
            stmt.setInt(1, ncfas.getIdncfas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            
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
public String obtenerNombreFamilia(int id) throws Exception {
        ResultSet rs = null;
        Ncfas tempNcfas;
        tempNcfas = new Ncfas();
        int i=1;
        try {
            this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT nombrefamilia FROM ncfas where idncfas=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while(rs.next()){
                
                tempNcfas.setIdncfas((Integer)rs.getObject(1));
                tempNcfas.setNombrefamilia(rs.getObject(2).toString());
            }
        }catch(Exception e){
        throw e;
        }finally{
        this.Cerrar();
        }
        return tempNcfas.getNombrefamilia();
    }
    

}