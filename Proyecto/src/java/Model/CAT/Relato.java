/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

/**
 *
 * @author jean
 */
public class Relato {
    
   private int idRelato;
   private int idTest;
   private String relato;

    public Relato(int idRelato, int idTest, String relato) {
        this.idRelato = idRelato;
        this.idTest = idTest;
        this.relato = relato;
    }
   
   
      public Relato() {
       
    }
   
   

    public int getIdRelato() {
        return idRelato;
    }

    public void setIdRelato(int idRelato) {
        this.idRelato = idRelato;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }
   
   
   
    
}
