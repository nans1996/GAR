/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Vasilisa
 */
@Entity
@Table(name = "personage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personage.findAll", query = "SELECT p FROM Personage p")
    , @NamedQuery(name = "Personage.findByIDPersonage", query = "SELECT p FROM Personage p WHERE p.iDPersonage = :iDPersonage")
    , @NamedQuery(name = "Personage.findByName", query = "SELECT p FROM Personage p WHERE p.name = :name")
    , @NamedQuery(name = "Personage.findByPrice", query = "SELECT p FROM Personage p WHERE p.price = :price")})
public class Personage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Personage")
    private Integer iDPersonage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private float price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDPersonage")
    private Collection<Goal> goalCollection;
    @OneToMany(mappedBy = "iDPersonage")
    private Collection<PersonageImage> personageImageCollection;
  
    
    public Personage() {
    }

    public Personage(Integer iDPersonage) {
        this.iDPersonage = iDPersonage;
    }

    public Personage(Integer iDPersonage, String name, float price) {
        this.iDPersonage = iDPersonage;
        this.name = name;
        this.price = price;
    }

    public Integer getIDPersonage() {
        return iDPersonage;
    }

    public void setIDPersonage(Integer iDPersonage) {
        this.iDPersonage = iDPersonage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Goal> getGoalCollection() {
        return goalCollection;
    }

    public void setGoalCollection(Collection<Goal> goalCollection) {
        this.goalCollection = goalCollection;
    }

    @XmlTransient
    public Collection<PersonageImage> getPersonageImageCollection() {
        return personageImageCollection;
    }

    public void setPersonageImageCollection(Collection<PersonageImage> personageImageCollection) {
        this.personageImageCollection = personageImageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPersonage != null ? iDPersonage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personage)) {
            return false;
        }
        Personage other = (Personage) object;
        if ((this.iDPersonage == null && other.iDPersonage != null) || (this.iDPersonage != null && !this.iDPersonage.equals(other.iDPersonage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
