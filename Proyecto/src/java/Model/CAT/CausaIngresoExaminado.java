/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.CAT;

import Dao.CAT.UsuarioDao;

/**
 *
 * @author jean
 */
public class CausaIngresoExaminado {
    
    int idCausa;
    String causa;

    public CausaIngresoExaminado(int idCausa, String causa) {
        this.idCausa = idCausa;
        this.causa = causa;
    }

    public CausaIngresoExaminado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdCausa() {
        return idCausa;
    }

    public void setIdCausa(int idCausa) {
        this.idCausa = idCausa;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }
    
    
  
    
}
