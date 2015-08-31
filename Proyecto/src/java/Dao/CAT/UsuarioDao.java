/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.CAT;

import Model.CAT.CausaIngresoExaminado;
import Model.CAT.Examinado;
import Model.CAT.Test;
import Model.CAT.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author jean
 */
public class UsuarioDao {
    
    
    public boolean existePersona(Usuario UsuarioCons)   {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");

           stmt = con.prepareStatement("SELECT * FROM usuario WHERE rut= ? AND password= ? ");
           
            stmt.setString(1, UsuarioCons.getRut());
            stmt.setString(2, UsuarioCons.getPassword());
            rs = stmt.executeQuery();

            if (rs.next()) { //es un result set vacío 

                existe = true;


            }

        } catch (SQLException e) {
            System.out.println("error"+e);
            
        } catch (ClassNotFoundException e) {
            System.out.println("error"+e);
        
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

        return existe;
    }
    
       public boolean existeRut(String Rut)   {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");

           stmt = con.prepareStatement("SELECT * FROM usuario WHERE rut= ? ");
           
            stmt.setString(1, Rut);
        
            rs = stmt.executeQuery();

            if (rs.next()) { //es un result set vacío 

                existe = true;


            }

        } catch (SQLException e) {
            System.out.println("error"+e);
            
        } catch (ClassNotFoundException e) {
            System.out.println("error"+e);
        
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

        return existe;
    }
       
       public Usuario getUsuario(String Rut){
           
            Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       Usuario tempUsuario=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");

           stmt = con.prepareStatement("SELECT * FROM usuario WHERE rut= ? ");
           
            stmt.setString(1, Rut);
            rs = stmt.executeQuery();

            if (rs.next()) { //es un result set vacío 
             
               tempUsuario= new Usuario();
                tempUsuario.setRut(rs.getObject(1).toString());
                tempUsuario.setNombre(rs.getObject(2).toString());
                tempUsuario.setCorreo(rs.getObject(3).toString());
                tempUsuario.setPassword(rs.getObject(4).toString());
                tempUsuario.getTipoUsuario();
               
            }

        } catch (SQLException e) {
            System.out.println("error"+e);
            
        } catch (ClassNotFoundException e) {
            System.out.println("error"+e);
        
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

        return tempUsuario;
           
       }

     
    public int insertarUsuario(Usuario nuevoUsuario,String tipoUsuario) {
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
       int retorno=0;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");


            stmt = con.prepareStatement("INSERT INTO Usuario (rut,nombre,correo,password)"
                   
                    + " values (?,?,?,?)");
          
            stmt.setString(1, nuevoUsuario.getRut());
            stmt.setString(2, nuevoUsuario.getNombre());
            stmt.setString(3, nuevoUsuario.getCorreo());
            stmt.setString(4, nuevoUsuario.getPassword());

             retorno = stmt.executeUpdate();
            
            
            if (tipoUsuario.equals("psico")){
                stmt2 = con.prepareStatement("INSERT INTO psicologo (Usuario_rut) values (?)");
                stmt2.setString(1, nuevoUsuario.getRut());
                 int retorno2 = stmt2.executeUpdate();
            }
            if (tipoUsuario.equals("asist")){
                stmt2 = con.prepareStatement("INSERT INTO asistentesocial (Usuario_rut) values (?)");
                stmt2.setString(1, nuevoUsuario.getRut());
                 int retorno2 = stmt2.executeUpdate(); 
            }
            
         //   System.out.println("biiieennntoo");


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

    return retorno;
    }
    
    
      public int insertarExaminado(Examinado nuevoExaminado) {
        Connection con = null;
        PreparedStatement stmt = null;
  int retorno=0;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");


            stmt = con.prepareStatement("INSERT INTO examinado (rut,nombre,direccion,fechaNacimiento,escolaridad,nombreResponsable,parentescoResponsable,idRazonIngreso)"
                   
                    + " values (?,?,?,?,?,?,?,?)");
          
            stmt.setString(1, nuevoExaminado.getRut());
            stmt.setString(2, nuevoExaminado.getNombre());
            stmt.setString(3, nuevoExaminado.getDireccion());
            stmt.setString(4, nuevoExaminado.getFechaNac());
            stmt.setString(5, nuevoExaminado.getEscolaridad());
            stmt.setString(6, nuevoExaminado.getNombreRespons());
            stmt.setString(7, nuevoExaminado.getParentescoRespons());
            stmt.setInt(8, nuevoExaminado.getIdCausaIngreso());
            

             retorno = stmt.executeUpdate();
            
            //System.out.println("biiieennntoo");


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

   return retorno;
    }

    public void modificarUsuario(Usuario usuario) {
      System.out.println("usuario modif"+usuario.getRut()); 
      System.out.println("usuario modif"+usuario.getNombre()); 
      System.out.println("usuario modif"+usuario.getPassword()); 
       Connection con = null;
        PreparedStatement stmt = null;


        try {

            Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");

            
            stmt = con.prepareStatement("UPDATE usuario SET nombre= ?,correo= ?,password= ? WHERE rut ='"+usuario.getRut()+"'"); 
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getPassword());
           
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

    public void eliminarUsuario(Usuario usuario) {
        
        
        System.out.println("mandador a eliminar");
        System.out.println("usuario"+usuario.getRut());
         Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");


            stmt = con.prepareStatement("DELETE FROM usuario WHERE rut=?");
            stmt.setString(1, usuario.getRut());
            stmt.executeUpdate();


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
        };
    }

    
     public List<Usuario> mostrarUsuarios() {
        
         Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<Usuario> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Usuario");

            while (rs.next()) {
               Usuario tempUsuario = new Usuario();
                tempUsuario.setRut(rs.getObject(1).toString());
                tempUsuario.setNombre(rs.getObject(2).toString());
                tempUsuario.setCorreo(rs.getObject(3).toString());
                tempUsuario.setPassword(rs.getObject(4).toString());
                tempUsuario.setTipoUsuario(getTipoUsuario(rs.getObject(1).toString()));
                lista.add(tempUsuario);
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
    
    public List<Usuario> mostrarUsuarios2(String tipoUsuario) {
        
        if (tipoUsuario.equals("psico"))
                tipoUsuario="psicologo";
        if (tipoUsuario.equals("asist"))
            tipoUsuario="asistentesocial";
        
        
         Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<Usuario> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM usuario inner join "+ tipoUsuario +" where usuario.rut=Usuario_rut");

            while (rs.next()) {
               Usuario tempUsuario = new Usuario();
                tempUsuario.setRut(rs.getObject(1).toString());
                tempUsuario.setNombre(rs.getObject(2).toString());
                tempUsuario.setCorreo(rs.getObject(3).toString());
                tempUsuario.setPassword(rs.getObject(4).toString());
                lista.add(tempUsuario);
                System.out.println("hizo la pega");
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
    
    public String getTipoUsuario(String rutUsuario){
         
        String tipoUsuario=null;

           Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

     

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
           
            rs = stmt.executeQuery("SELECT * FROM administrador WHERE Usuario_rut='"+rutUsuario+"'");
            if (rs.next()) 
              return  tipoUsuario="Administrador";
          
            rs = stmt.executeQuery("SELECT * FROM psicologo WHERE Usuario_rut='"+rutUsuario+"'");
            if (rs.next()) 
              return  tipoUsuario="Psicologo";
            
            rs = stmt.executeQuery("SELECT * FROM asistentesocial WHERE Usuario_rut='"+rutUsuario+"'");
            if (rs.next()) 
              return  tipoUsuario="Asistente";

     

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

          
          return tipoUsuario;
        
        
    }
  
    
    public List<Examinado> mostrarExaminados() {
        
         Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<Examinado> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM examinado");

            while (rs.next()) {
               Examinado tempExaminado = new Examinado();
                tempExaminado.setRut(rs.getObject(1).toString());
                tempExaminado.setNombre(rs.getObject(2).toString());
                tempExaminado.setDireccion(rs.getObject(3).toString());
                tempExaminado.setFechaNac(rs.getObject(4).toString());
                tempExaminado.setEscolaridad(rs.getObject(5).toString());
                tempExaminado.setNombreRespons(rs.getObject(6).toString());
                tempExaminado.setParentescoRespons(rs.getObject(7).toString());
                tempExaminado.setIdCausaIngreso(Integer.parseInt(rs.getObject(8).toString()));
                lista.add(tempExaminado);
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
    
    public Examinado getExaminado(String rutExaminado){
        
             Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

     Examinado tempExaminado = new Examinado();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM examinado WHERE rut="+rutExaminado);

            while (rs.next()) {
               
                tempExaminado.setRut(rs.getObject(1).toString());
                tempExaminado.setNombre(rs.getObject(2).toString());
                tempExaminado.setDireccion(rs.getObject(3).toString());
                tempExaminado.setFechaNac(rs.getObject(4).toString());
                tempExaminado.setEscolaridad(rs.getObject(5).toString());
                tempExaminado.setNombreRespons(rs.getObject(6).toString());
                tempExaminado.setParentescoRespons(rs.getObject(7).toString());
                tempExaminado.setIdCausaIngreso(Integer.parseInt(rs.getObject(8).toString()));
               
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

        return tempExaminado;    
        
    }
    
       public Test getTest(int idTest){
          
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       
        Test tempTest = new Test();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM test WHERE idTest='"+idTest+"'");

            while (rs.next()) {
              
               tempTest.setIdTest(Integer.parseInt(rs.getObject(1).toString()));
                tempTest.setFecha((Timestamp) rs.getObject(2));
                tempTest.setRutExaminado(rs.getObject(4).toString());
                tempTest.setRutUsuario(rs.getObject(3).toString());
                
               
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

        return tempTest;
  
          
    }
    
    
      public void insertarTest(String[] relatos,String rutExaminado, String rutUsuario) {
        
          java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
      Timestamp timestamp = new Timestamp(date.getTime());
      

          Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;


        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");


            stmt = con.prepareStatement("INSERT INTO test (fecha,Usuario_rut,Examinado_rut)"
                   
                    + " values (?,?,?)");
          
            stmt.setTimestamp(1, timestamp);
            stmt.setString(2, rutUsuario);
            stmt.setString(3, rutExaminado);
            int retorno = stmt.executeUpdate();
            System.out.println(" se inserto el test");
            
  
            
           for (int i = 0; i < relatos.length; i++) {

                stmt2 = con.prepareStatement("INSERT INTO relato (idLamina,relato,test_idTest)"
                   
                    + " values (?,?,?)");
          
           
            stmt2.setInt(1, i);
            stmt2.setString(2, relatos[i]);
            stmt2.setInt(3, getUltimoTest());
             
            int retorno2 = stmt2.executeUpdate();
               
           }
           
            System.out.println(" se insertaron los relatos");


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
    
    
    
      public int getUltimoTest(){
          int id=0;

           Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

     

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT MAX(idTest) as id FROM Test");

            while (rs.next()) {
                if(rs.getObject(1)!=null)
                id= Integer.parseInt(rs.getObject(1).toString());
               
            } 
            
            System.out.println(" el id que se devolvio es"+id);

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

          
          return id;
      }
      
      public List<Test> getTests(){
          
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<Test> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM test");

            while (rs.next()) {
               Test tempTest = new Test();
               tempTest.setIdTest(Integer.parseInt(rs.getObject(1).toString()));
                tempTest.setFecha((Timestamp) rs.getObject(2));
                tempTest.setRutExaminado(rs.getObject(4).toString());
                tempTest.setRutUsuario(rs.getObject(3).toString());
                
                lista.add(tempTest);
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
      
      
      public void eliminarTest(Test test){
          
          System.out.println(" en eliminar test");
          System.out.println(" el id test es"+test.getIdTest());
         Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");


            stmt = con.prepareStatement("DELETE FROM test WHERE idTest=?");
            stmt.setInt(1, test.getIdTest());
            stmt.executeUpdate();


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
        };
          
      }
      
      
      public List<String> getRelatos(int idTest) {
        
          System.out.println(" entrando a los relatos el id test es "+idTest);
         Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<String> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM relato WHERE test_idTest ='"+idTest+"' ORDER BY idLamina ASC ");
            
            while (rs.next()) {
              
                lista.add(rs.getObject(3).toString());
            } 

            System.out.println(" la lista es ");
            for (String list: lista){
                System.out.println("relato"+lista);
            }
            
            
            System.out.println(" paos la lista");
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
      
      
      public List<String> getidTestsExaminado(String rutExaminado){
          
        
         Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<String> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT idTest FROM test WHERE Examinado_rut ='"+rutExaminado+"' ORDER BY idTest ASC");
            
            while (rs.next()) {
              
                lista.add(rs.getObject(1).toString());
            } 

            System.out.println(" la lista es ");
            for (String list: lista){
                System.out.println("relato"+lista);
            }
            
            
            System.out.println(" paos la lista");
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
      
 
      
      
      public List<String> getExaminadosRazonIngreso(int idRazonIngreso){
          
        

                   Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<String> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT rut FROM  examinado where idRazonIngreso='"+idRazonIngreso+"'");
            
            while (rs.next()) {
              System.out.println(" se encontro un examinado");
                lista.add(rs.getObject(1).toString());
                
            } 

            System.out.println(" la lista es ");
            for (String list: lista){
                System.out.println("relato"+lista);
            }
            
            
            System.out.println(" paos la lista");
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
      
      public List<String> getTestsRazonIngreso(int idRazonIngreso){
          
          
          

                   Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<String> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT distinct idTest FROM examinado inner join test "
                    + "where test.Examinado_rut=examinado.rut and examinado.idRazonIngreso='"+idRazonIngreso+"'");
            
            while (rs.next()) {
              System.out.println(" se encontro un test");
                lista.add(rs.getObject(1).toString());
                
            } 

            System.out.println(" la lista es ");
            for (String list: lista){
                System.out.println("relato"+lista);
            }
            
            
            System.out.println(" paos la lista");
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
      
     public List<CausaIngresoExaminado> getCausasIngreso(){


                   Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       List<CausaIngresoExaminado> lista= new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * from razonIngreso");
            
            while (rs.next()) {
              
                lista.add(new CausaIngresoExaminado(Integer.parseInt(rs.getObject(1).toString()), rs.getObject(2).toString()));
                
            } 

            
            System.out.println(" paos la lista");
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
     
     public CausaIngresoExaminado getCausaIngreso(int idCausa){


                   Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

       CausaIngresoExaminado causa = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ppf", "pma", "pmapass");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * from razonIngreso where idRazon='"+idCausa+"'");
            
            while (rs.next()) {
              
              causa= new CausaIngresoExaminado(Integer.parseInt(rs.getObject(1).toString()), rs.getObject(2).toString());
                
            } 

            
            System.out.println(" paos la lista");
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

        return causa;

         
         
         
         
     }
      
      
}



 