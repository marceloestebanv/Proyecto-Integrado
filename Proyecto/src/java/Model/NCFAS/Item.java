/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.NCFAS;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcelo Verdugo
 */
@Entity
@Table(name = "item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByValor", query = "SELECT i FROM Item i WHERE i.valor = :valor"),
    @NamedQuery(name = "Item.findByDimension", query = "SELECT i FROM Item i WHERE i.dimension = :dimension"),
    @NamedQuery(name = "Item.findByIditem", query = "SELECT i FROM Item i WHERE i.itemPK.iditem = :iditem"),
    @NamedQuery(name = "Item.findByNcfasIdncfas", query = "SELECT i FROM Item i WHERE i.itemPK.ncfasIdncfas = :ncfasIdncfas")})
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    @Id
    private int valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dimension")
    private int dimension;
    @JoinColumn(name = "ncfas_idncfas", referencedColumnName = "idncfas", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ncfas ncfas;

    public Item() {
    }
    public Item(int valor, int dimension) {
        
        this.valor = valor;
        this.dimension = dimension;
    }
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Ncfas getNcfas() {
        return ncfas;
    }

    public void setNcfas(Ncfas ncfas) {
        this.ncfas = ncfas;
    }
    
}
