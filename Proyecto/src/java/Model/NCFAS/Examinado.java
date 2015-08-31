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
@Table(name = "examinado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examinado.findAll", query = "SELECT e FROM Examinado e"),
    @NamedQuery(name = "Examinado.findByIdExaminado", query = "SELECT e FROM Examinado e WHERE e.idExaminado = :idExaminado"),
    @NamedQuery(name = "Examinado.findByRut", query = "SELECT e FROM Examinado e WHERE e.rut = :rut"),
    @NamedQuery(name = "Examinado.findByNombre", query = "SELECT e FROM Examinado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Examinado.findByDireccion", query = "SELECT e FROM Examinado e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Examinado.findByFechaNacimiento", query = "SELECT e FROM Examinado e WHERE e.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Examinado.findByEscolaridad", query = "SELECT e FROM Examinado e WHERE e.escolaridad = :escolaridad"),
    @NamedQuery(name = "Examinado.findByNombreResponsable", query = "SELECT e FROM Examinado e WHERE e.nombreResponsable = :nombreResponsable"),
    @NamedQuery(name = "Examinado.findByParentestoResponsable", query = "SELECT e FROM Examinado e WHERE e.parentestoResponsable = :parentestoResponsable")})
public class Examinado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idExaminado")
    private Integer idExaminado;
    @Size(max = 12)
    @Column(name = "rut")
    private String rut;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 60)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 50)
    @Column(name = "escolaridad")
    private String escolaridad;
    @Size(max = 50)
    @Column(name = "nombreResponsable")
    private String nombreResponsable;
    @Size(max = 50)
    @Column(name = "parentestoResponsable")
    private String parentestoResponsable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinadoidExaminado")
    private Collection<Test> testCollection;

    public Examinado() {
    }

    public Examinado(Integer idExaminado) {
        this.idExaminado = idExaminado;
    }

    public Integer getIdExaminado() {
        return idExaminado;
    }

    public void setIdExaminado(Integer idExaminado) {
        this.idExaminado = idExaminado;
    }

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getParentestoResponsable() {
        return parentestoResponsable;
    }

    public void setParentestoResponsable(String parentestoResponsable) {
        this.parentestoResponsable = parentestoResponsable;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExaminado != null ? idExaminado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examinado)) {
            return false;
        }
        Examinado other = (Examinado) object;
        if ((this.idExaminado == null && other.idExaminado != null) || (this.idExaminado != null && !this.idExaminado.equals(other.idExaminado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Examinado[ idExaminado=" + idExaminado + " ]";
    }
    
}
