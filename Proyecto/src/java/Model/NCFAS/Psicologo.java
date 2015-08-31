/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.NCFAS;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcelo Verdugo
 */
@Entity
@Table(name = "psicologo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Psicologo.findAll", query = "SELECT p FROM Psicologo p"),
    @NamedQuery(name = "Psicologo.findByUsuariorut", query = "SELECT p FROM Psicologo p WHERE p.usuariorut = :usuariorut")})
public class Psicologo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "Usuario_rut")
    private String usuariorut;
    @JoinColumn(name = "Usuario_rut", referencedColumnName = "rut", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;

    public Psicologo() {
    }

    public Psicologo(String usuariorut) {
        this.usuariorut = usuariorut;
    }

    public String getUsuariorut() {
        return usuariorut;
    }

    public void setUsuariorut(String usuariorut) {
        this.usuariorut = usuariorut;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariorut != null ? usuariorut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Psicologo)) {
            return false;
        }
        Psicologo other = (Psicologo) object;
        if ((this.usuariorut == null && other.usuariorut != null) || (this.usuariorut != null && !this.usuariorut.equals(other.usuariorut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Psicologo[ usuariorut=" + usuariorut + " ]";
    }
    
}
