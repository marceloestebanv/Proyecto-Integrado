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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcelo Verdugo
 */
@Entity
@Table(name = "relato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relato.findAll", query = "SELECT r FROM Relato r"),
    @NamedQuery(name = "Relato.findByIdRelato", query = "SELECT r FROM Relato r WHERE r.idRelato = :idRelato"),
    @NamedQuery(name = "Relato.findByIdLamina", query = "SELECT r FROM Relato r WHERE r.idLamina = :idLamina")})
public class Relato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRelato")
    private Integer idRelato;
    @Column(name = "idLamina")
    private Integer idLamina;
    @Lob
    @Size(max = 65535)
    @Column(name = "relato")
    private String relato;
    @JoinColumn(name = "test_idTest", referencedColumnName = "idTest")
    @ManyToOne(optional = false)
    private Test testidTest;

    public Relato() {
    }

    public Relato(Integer idRelato) {
        this.idRelato = idRelato;
    }

    public Integer getIdRelato() {
        return idRelato;
    }

    public void setIdRelato(Integer idRelato) {
        this.idRelato = idRelato;
    }

    public Integer getIdLamina() {
        return idLamina;
    }

    public void setIdLamina(Integer idLamina) {
        this.idLamina = idLamina;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public Test getTestidTest() {
        return testidTest;
    }

    public void setTestidTest(Test testidTest) {
        this.testidTest = testidTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelato != null ? idRelato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relato)) {
            return false;
        }
        Relato other = (Relato) object;
        if ((this.idRelato == null && other.idRelato != null) || (this.idRelato != null && !this.idRelato.equals(other.idRelato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Relato[ idRelato=" + idRelato + " ]";
    }
    
}
