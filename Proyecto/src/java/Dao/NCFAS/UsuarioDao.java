/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.NCFAS;

import Model.NCFAS.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author jean
 */
public class UsuarioDao extends DAO{
    
      
    
    public boolean existePersona(Usuario UsuarioCons)   { 
        ResultSet rs = null;
        boolean existe = false;
         try{    
        this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE rut= ? AND password= ? ");
            stmt.setString(1, UsuarioCons.getRut());
            stmt.setString(2, UsuarioCons.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) { //es un result set vacío 
                existe = true;
            }
        } catch (SQLException e) {
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

    
    public Usuario retornaUsuario(Usuario UsuarioCons)   { 
        ResultSet rs = null;
        boolean existe = false;
        Usuario tempUsuario = new Usuario();
         try{    
        this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE rut= ? AND password= ? ");
            stmt.setString(1, UsuarioCons.getRut());
            stmt.setString(2, UsuarioCons.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) { //es un result set vacío 
                 
                tempUsuario.setRut(rs.getObject(1).toString());
                tempUsuario.setNombre(rs.getObject(2).toString());
                tempUsuario.setCorreo(rs.getObject(3).toString());
                tempUsuario.setPassword(rs.getObject(4).toString());
                existe = true;
            }
        } catch (SQLException e) {
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
    
    public int tipoUsuario(Usuario UsuarioCons)   { 
        ResultSet rs = null;
        int tipo = 0;
         try{    
        this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM administrador WHERE Usuario_rut= ?");
            stmt.setString(1, UsuarioCons.getRut());
            rs = stmt.executeQuery();
            if (rs.next()) { //es un result set vacío 
                tipo = 1;
            }
            
            PreparedStatement stmt2 = null;
            stmt2 = con.prepareStatement("SELECT * FROM asistentesocial WHERE Usuario_rut= ?");
            stmt2.setString(1, UsuarioCons.getRut());
            rs = stmt2.executeQuery();
            if (rs.next()) { //es un result set vacío 
                tipo = 2;
            }
            
            PreparedStatement stmt3 = null;
            stmt3 = con.prepareStatement("SELECT * FROM psicologo WHERE Usuario_rut= ?");
            stmt3.setString(1, UsuarioCons.getRut());
            rs = stmt3.executeQuery();
            if (rs.next()) { //es un result set vacío 
                tipo = 3;
            }
        } catch (SQLException e) {
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

        return tipo;
    }
     
    public void insertarUsuario(Usuario nuevoUsuario, int tipo) throws Exception {

        System.out.println("llegamos a insertar");

        try{
                      
            this.Conectar(); 
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("INSERT INTO usuario (rut,nombre,correo,password)"
                    + " values (?,?,?,?)");
          
            stmt.setString(1, nuevoUsuario.getRut());
            stmt.setString(2, nuevoUsuario.getNombre());
            stmt.setString(3, nuevoUsuario.getCorreo());
            stmt.setString(4, nuevoUsuario.getPassword());
            stmt.executeUpdate();
            
            System.out.println("biiieennntoo");
            if(tipo==2){
                try{
            insertarPsicologo(nuevoUsuario);
                }catch(Exception e){
                throw e;
                }
            }else {
                try{
            insertarAsistente(nuevoUsuario);
                }catch(Exception e){
                throw e;
                }
            }
        }catch(Exception e){
        throw e;
        }
        this.Cerrar();
        

        
        
       
    }
    
        public void insertarPsicologo(Usuario nuevoUsuario) throws Exception{
            
            try{
                      
            this.Conectar(); 
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("INSERT INTO psicologo (Usuario_rut)"
                    + " values (?)");
          
            stmt.setString(1, nuevoUsuario.getRut());
            stmt.executeUpdate();
            
            System.out.println("biiieennntoo insertamos psicologo");
            
            
        }catch(Exception e){
        throw e;
        }
        this.Cerrar();
             
                
        }
        
        public void insertarAsistente(Usuario nuevoUsuario) throws Exception{
            
            try{
                      
            this.Conectar(); 
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("INSERT INTO asistentesocial (Usuario_rut)"
                    + " values (?)");
          
            stmt.setString(1, nuevoUsuario.getRut());
            stmt.executeUpdate();
            
            System.out.println("biiieennntoo insertamos asistente");
            
            
        }catch(Exception e){
        throw e;
        }
        this.Cerrar();
            }
        
    public void modificarUsuario(Usuario usuario) {
      System.out.println("usuario modif"+usuario.getRut()); 
      System.out.println("usuario modif"+usuario.getNombre()); 
      System.out.println("usuario modif"+usuario.getPassword()); 
       
        PreparedStatement stmt = null;


        try {

            this.Conectar();

            
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
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.Conectar();
            stmt = con.prepareStatement("DELETE FROM usuario WHERE rut=?");
            stmt.setString(1, usuario.getRut());
            stmt.executeUpdate();
            System.out.println("USUARIO ELIMINADO");

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
    
    public void eliminarAsistente(Usuario usuario) {

        System.out.println("mandador a eliminar");
        System.out.println("usuario"+usuario.getRut());
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.Conectar();
            stmt = con.prepareStatement("DELETE FROM asistentesocial WHERE Usuario_rut=?");
            stmt.setString(1, usuario.getRut());
            stmt.executeUpdate();
            System.out.println("USUARIO ASISTENTE ELIMINADO");
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
    
    public void eliminarPsicologo(Usuario usuario) {
        
        
        System.out.println("mandador a eliminar");
        System.out.println("usuario"+usuario.getRut());
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.Conectar();
            stmt = con.prepareStatement("DELETE FROM psicologo WHERE Usuario_rut=?");
            stmt.setString(1, usuario.getRut());
            stmt.executeUpdate();
            System.out.println("USUARIO PSICOLOGO ELIMINADO");


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

  
 
public List<Usuario> mostrarUsuarios() throws Exception {
        
  
        ResultSet rs = null;
        List<Usuario> lista;
        try {
            this.Conectar();
            PreparedStatement stmt = null;
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Usuario tempUsuario = new Usuario();
                tempUsuario.setRut(rs.getObject(1).toString());
                tempUsuario.setNombre(rs.getObject(2).toString());
                tempUsuario.setCorreo(rs.getObject(3).toString());
                tempUsuario.setPassword(rs.getObject(4).toString());
                lista.add(tempUsuario);
            } 
        }catch(Exception e){
        throw e;
        }finally{
        this.Cerrar();
        }
        return lista;
    }
    
    
    
    
    
    
}
