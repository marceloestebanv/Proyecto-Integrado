/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

import java.util.Date;

/**
 *
 * @author jean
 */
public class Examinado {
    
    private String rut;
    private String nombre;
    private String direccion;
    private String fechaNac;
    private String escolaridad;
    private String nombreRespons;
    private String parentescoRespons;
    private int idCausaIngreso;
    
   

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getNombreRespons() {
        return nombreRespons;
    }

    public void setNombreRespons(String nombreRespons) {
        this.nombreRespons = nombreRespons;
    }

    public String getParentescoRespons() {
        return parentescoRespons;
    }

    public void setParentescoRespons(String parentescoRespons) {
        this.parentescoRespons = parentescoRespons;
    }

    public int getIdCausaIngreso() {
        return idCausaIngreso;
    }

    public void setIdCausaIngreso(int idCausaIngreso) {
        this.idCausaIngreso = idCausaIngreso;
    }
    
    
    
    
    
}
