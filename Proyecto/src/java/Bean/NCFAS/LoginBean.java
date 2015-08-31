package Bean.NCFAS;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

    private String nombre;
    private String clave;
    private String rut;
    private boolean logeado = false;
    private int tipo;

    private String pass1;
    private String pass2;
    private String passActual;
    private String mensaje;
    private boolean mostrarMensaje = false;
    private boolean mostrarBoton = false;

    private static final int ESTUDIANTE = 1;
    private static final int PROFESOR = 2;
    private static final int ADMIN = 3;

    public LoginBean() {
       // ManejaConfig c = new ManejaConfig();
        //c.getPropValues();
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isMostrarBoton() {
        return mostrarBoton;
    }

    public void setMostrarBoton(boolean mostrarBoton) {
        this.mostrarBoton = mostrarBoton;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isMostrarMensaje() {
        return mostrarMensaje;
    }

    public void setMostrarMensaje(boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }

    public String getPassActual() {
        return passActual;
    }

    public void setPassActual(String passActual) {
        this.passActual = passActual;
    }

    public boolean estaLogeado() {
        return logeado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

 /*   public void identificar(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        logeado = this.validarCombinacion(nombre, clave);
        if (logeado) {
            logeado = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", nombre);
        } else {
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", logeado);
        if (logeado) {
            if (tipo == 1) {
                context.addCallbackParam("view", "home1.xhtml");
            } else {
                context.addCallbackParam("view", "home2.xhtml");
            }

        }
    }*/

    /*public String identificar2() {
        logeado = this.validarCombinacion(nombre, clave);
        RequestContext context = RequestContext.getCurrentInstance();

        FacesMessage msg = null;

        if (logeado) {
            if (this.tipo == 1) {
                //debemos revisar si cumplio los pasos previos

                Conector c = new Conector();
                ResultSet consulta = null;
                ResultSet rs = null;

                //==================================================
                String select = "select declarado, fs1 from Estudiante where correo='" + this.nombre + " ';";
                try {
                    int topic = 0;
                    int estilo = 0;
                    Statement st = c.conexion.createStatement();
                    rs = st.executeQuery(select);
                    while (rs.next()) {
                        topic = rs.getInt(1);
                        estilo = rs.getInt(2);
                    }
                    System.out.println("ANTES DE REVISAR DEBEMOS RETORNAR EL CUESTIONARIO");
                    System.out.println("topic: " + topic);
                    if (estilo == -1) {
                        System.out.println("DEBEMOS RETORNAR EL CUESTIONARIO");
                        return "homeNewEstudiante.xhtml?username=" + nombre;
                    }

                    if (topic == -1) {
                        try {
                            FacesContext contex = FacesContext.getCurrentInstance();
                            System.out.println("DEBEMOS PREGUNTAR POR LOS TOPICOS");
                            contex.getExternalContext().redirect("/AppTitulo/faces/conocimientoPrevio.xhtml?username=" + nombre);
                        } catch (Exception e) {
                            System.out.println("Me voy al carajo, no funciona esta redireccion " + e);
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                return "screenEstudiante";
            } else {
                return "screenProfesor";
            }
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Las Credenciales no son válidas"));

            System.out.println("crendenciales no validas");
            return "index";
        }
    }

    public String identificar3() {
        logeado = this.validarCombinacion(nombre, clave);
        RequestContext context = RequestContext.getCurrentInstance();

        FacesMessage msg = null;

       
            if (logeado  && this.tipo == 3) {

                return "crearProfesor"; 
               
                

            

        } else {
            
            logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "Las Credenciales no son válidas"));

            System.out.println("crendenciales no validas");
            return "administrador";

        }
    }*/

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    /*public void autenticar() throws ServletException, IOException{

        FacesContext contex = FacesContext.getCurrentInstance();        
        System.out.println("usuario: "+usuario.getRut());
        System.out.println("pass: "+usuario.getPassword());
        UsuarioDao valida= new UsuarioDao();        
        if (valida.existePersona(usuario)){
            System.out.println("existe en la bd");
                if(valida.tipoUsuario(usuario)==1){
                   contex.getExternalContext().redirect("/Proyecto/faces/administradorV2.xhtml");
                } if(valida.tipoUsuario(usuario)==2 || valida.tipoUsuario(usuario)==3 ){
                   contex.getExternalContext().redirect("/Proyecto/faces/sistema.xhtml");
                } 
        }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario no encontrado."));
        }   
    }*/
   /* private boolean validarCombinacion(String correo, String pass) {
        System.out.println("ESTAMOS EN EL METODO VALIDAR");
        

        ResultSet consulta = null;
        int cantidad = 0;
        ResultSet rs = null;

        //==================================================
        String select = "select count(*) from Usuario where correo='" + correo + "' and pass='" + pass + "';";
        try {

            Statement st = c.conexion.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                cantidad = rs.getInt(1);
                System.out.println("correo: " + correo);
                System.out.println("pass: " + pass);
                System.out.println(select);
                System.out.println("la cantidad de coincidencias es :" + cantidad);
            }
            if (cantidad == 0) {
                return false;
            } else {

                select = "select correo, tipo from Usuario where correo='" + correo + "' and pass='" + pass + "';";
              
                rs = st.executeQuery(select);

                while (rs.next()) {
                    System.out.println("el tipo es " + rs.getInt(2));
                    tipo = rs.getInt(2);
                }

                return true;

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    public void cambiarPass() {

        if (clave.equals(passActual)) {

            if (pass1.equals(pass2)) {
                System.out.println("contraseñas identicas, se actualiza ");

                String update = "update Usuarios set pass='" + pass1 + "' where id=" + this.nombre + " ";
                mensaje = "<div class=\"bs-callout bs-callout-info\"><h4>Operación Exitosa</h4>La contraseña ha sido actualizada</div>";

                try {
                    Retorno.actualizarPass(nombre, pass1);
                } catch (DAOException ex) {
                    System.out.println("error llamando al metodo actualiza pass");
                }
                mostrarMensaje = true;
                mostrarBoton = true;

            } else {
                mensaje = "<div class=\"bs-callout bs-callout-danger\"><h4>Advertencia</h4>Las nuevas contraseñas no coinciden</div>";
                mostrarMensaje = true;
                mostrarBoton = false;

            }

        } else {
            System.out.println("la pass actual no es valida");
            mensaje = "<div class=\"bs-callout bs-callout-danger\"><h4>Advertencia</h4>La contraseña actual no es la correcta</div>";
            mostrarMensaje = true;
            mostrarBoton = false;

        }
    }*/
}
