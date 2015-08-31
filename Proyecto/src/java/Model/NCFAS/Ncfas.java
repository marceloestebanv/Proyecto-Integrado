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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Marcelo Verdugo
 */
@Entity
@Table(name = "ncfas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ncfas.findAll", query = "SELECT n FROM Ncfas n"),
    @NamedQuery(name = "Ncfas.findByIdncfas", query = "SELECT n FROM Ncfas n WHERE n.idncfas = :idncfas"),
    @NamedQuery(name = "Ncfas.findByNombrefamilia", query = "SELECT n FROM Ncfas n WHERE n.nombrefamilia = :nombrefamilia"),
    @NamedQuery(name = "Ncfas.findByFecha", query = "SELECT n FROM Ncfas n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "Ncfas.findByParteproceso", query = "SELECT n FROM Ncfas n WHERE n.parteproceso = :parteproceso")})
public class Ncfas implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ncfas")
    private Collection<Item> itemCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idncfas")
    private Integer idncfas;
    private Date fecha;
    @Size(max = 45)
    @Column(name = "nombrefamilia")
    private String nombrefamilia;
    @Column(name = "parteproceso")
    private int parteproceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ncfasIdncfas")
    @JoinColumn(name = "Usuario_rut", referencedColumnName = "rut")
    @ManyToOne(optional = false)
    private Usuario usuariorut;

    public Ncfas() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ncfas(Integer idncfas) {
        this.idncfas = idncfas;
    }

    public Ncfas(Integer idncfas, int parteproceso) {
        this.idncfas = idncfas;
        this.parteproceso = parteproceso;
    }

    public Integer getIdncfas() {
        return idncfas;
    }

    public void setIdncfas(Integer idncfas) {
        this.idncfas = idncfas;
    }

    public String getNombrefamilia() {
        return nombrefamilia;
    }

    public void setNombrefamilia(String nombrefamilia) {
        this.nombrefamilia = nombrefamilia;
    }


    public int getParteproceso() {
        return parteproceso;
    }

    public void setParteproceso(int parteproceso) {
        this.parteproceso = parteproceso;
    }

    

    public Usuario getUsuariorut() {
        return usuariorut;
    }

    public void setUsuariorut(Usuario usuariorut) {
        this.usuariorut = usuariorut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idncfas != null ? idncfas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ncfas)) {
            return false;
        }
        Ncfas other = (Ncfas) object;
        if ((this.idncfas == null && other.idncfas != null) || (this.idncfas != null && !this.idncfas.equals(other.idncfas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Ncfas[ idncfas=" + idncfas + " ]";
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }
    
}
