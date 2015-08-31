/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

import java.sql.Timestamp;

/**
 *
 * @author jean
 */
public class Test {
    
    
    public Test(){
        
    }
   private int idTest;
    private Timestamp fecha;
    private String rutUsuario;
     private String rutExaminado;

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public String getRutExaminado() {
        return rutExaminado;
    }

    public void setRutExaminado(String rutExaminado) {
        this.rutExaminado = rutExaminado;
    }
    
    
}
