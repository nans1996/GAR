/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vasilisa
 */
@Entity
@Table(name = "personage_image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonageImage.findAll", query = "SELECT p FROM PersonageImage p")
    , @NamedQuery(name = "PersonageImage.findByIDPersonageImage", query = "SELECT p FROM PersonageImage p WHERE p.iDPersonageImage = :iDPersonageImage")
    , @NamedQuery(name = "PersonageImage.findByLevel", query = "SELECT p FROM PersonageImage p WHERE p.level = :level")})
public class PersonageImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Personage_Image")
    private Integer iDPersonageImage;
    @Column(name = "Level")
    private Integer level;
    @JoinColumn(name = "ID_Personage", referencedColumnName = "ID_Personage")
    @ManyToOne
    private Personage iDPersonage;
    @JoinColumn(name = "ID_Image", referencedColumnName = "ID_Image")
    @OneToOne
    private Image iDImage;

    public PersonageImage() {
    }

    public PersonageImage(Integer iDPersonageImage) {
        this.iDPersonageImage = iDPersonageImage;
    }

    public Integer getIDPersonageImage() {
        return iDPersonageImage;
    }

    public void setIDPersonageImage(Integer iDPersonageImage) {
        this.iDPersonageImage = iDPersonageImage;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Personage getIDPersonage() {
        return iDPersonage;
    }

    public void setIDPersonage(Personage iDPersonage) {
        this.iDPersonage = iDPersonage;
    }

    public Image getIDImage() {
        return iDImage;
    }

    public void setIDImage(Image iDImage) {
        this.iDImage = iDImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPersonageImage != null ? iDPersonageImage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonageImage)) {
            return false;
        }
        PersonageImage other = (PersonageImage) object;
        if ((this.iDPersonageImage == null && other.iDPersonageImage != null) || (this.iDPersonageImage != null && !this.iDPersonageImage.equals(other.iDPersonageImage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PersonageImage[ iDPersonageImage=" + iDPersonageImage + " ]";
    }
    
}
