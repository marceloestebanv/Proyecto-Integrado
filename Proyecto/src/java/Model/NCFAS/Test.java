/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.NCFAS;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Marcelo Verdugo
 */
@Entity
@Table(name = "test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
    @NamedQuery(name = "Test.findByIdTest", query = "SELECT t FROM Test t WHERE t.idTest = :idTest"),
    @NamedQuery(name = "Test.findByFecha", query = "SELECT t FROM Test t WHERE t.fecha = :fecha")})
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTest")
    private Integer idTest;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "examinado_idExaminado", referencedColumnName = "idExaminado")
    @ManyToOne(optional = false)
    private Examinado examinadoidExaminado;
    @JoinColumn(name = "Usuario_rut", referencedColumnName = "rut")
    @ManyToOne(optional = false)
    private Usuario usuariorut;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testidTest")
    private Collection<Relato> relatoCollection;

    public Test() {
    }

    public Test(Integer idTest) {
        this.idTest = idTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Examinado getExaminadoidExaminado() {
        return examinadoidExaminado;
    }

    public void setExaminadoidExaminado(Examinado examinadoidExaminado) {
        this.examinadoidExaminado = examinadoidExaminado;
    }

    public Usuario getUsuariorut() {
        return usuariorut;
    }

    public void setUsuariorut(Usuario usuariorut) {
        this.usuariorut = usuariorut;
    }

    @XmlTransient
    public Collection<Relato> getRelatoCollection() {
        return relatoCollection;
    }

    public void setRelatoCollection(Collection<Relato> relatoCollection) {
        this.relatoCollection = relatoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTest != null ? idTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Test)) {
            return false;
        }
        Test other = (Test) object;
        if ((this.idTest == null && other.idTest != null) || (this.idTest != null && !this.idTest.equals(other.idTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Test[ idTest=" + idTest + " ]";
    }
    
}
