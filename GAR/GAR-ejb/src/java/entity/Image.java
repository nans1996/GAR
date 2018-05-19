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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Vasilisa
 */
@Entity
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")
    , @NamedQuery(name = "Image.findByIDImage", query = "SELECT i FROM Image i WHERE i.iDImage = :iDImage")
    , @NamedQuery(name = "Image.findByName", query = "SELECT i FROM Image i WHERE i.name = :name")
    , @NamedQuery(name = "Image.findByType", query = "SELECT i FROM Image i WHERE i.type = :type")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Image")
    private Integer iDImage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "data")
    private byte[] data;
    @OneToOne(mappedBy = "iDImage")
    private PersonageImage personageImage;

    @Transient
    private StreamedContent streamedContent;
    public Image() {
    }

    public Image(Integer iDImage) {
        this.iDImage = iDImage;
    }

    public Image(Integer iDImage, String name, String type, byte[] data) {
        this.iDImage = iDImage;
        this.name = name;
        this.type = type;
        this.data = data;
    }
    
    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }
    public Integer getIDImage() {
        return iDImage;
    }

    public void setIDImage(Integer iDImage) {
        this.iDImage = iDImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public PersonageImage getPersonageImage() {
        return personageImage;
    }

    public void setPersonageImage(PersonageImage personageImage) {
        this.personageImage = personageImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDImage != null ? iDImage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.iDImage == null && other.iDImage != null) || (this.iDImage != null && !this.iDImage.equals(other.iDImage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Image[ iDImage=" + iDImage + " ]";
    }
    
}
