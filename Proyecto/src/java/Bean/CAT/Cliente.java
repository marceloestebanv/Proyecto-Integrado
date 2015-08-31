/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.CAT;

/**
 *
 * @author jean
 */
public class Cliente {
    
    
    private String nombre; private Integer edad;
    public Cliente(String nombre, Integer edad) { this.nombre = nombre; this.edad = edad; } 
    
    public String getNombre() { return nombre; } 
    public void setNombre(String nombre) { this.nombre = nombre; } 
    public Integer getEdad() { return edad; } 
    public void setEdad(Integer edad) { this.edad = edad; } 
    
}
