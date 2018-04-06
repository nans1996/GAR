/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Vasilisa
 */
@Entity
@Table(name = "topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t")
    , @NamedQuery(name = "Topic.findByIDTopic", query = "SELECT t FROM Topic t WHERE t.iDTopic = :iDTopic")
    , @NamedQuery(name = "Topic.findByName", query = "SELECT t FROM Topic t WHERE t.name = :name")
    , @NamedQuery(name = "Topic.findByDate", query = "SELECT t FROM Topic t WHERE t.date = :date")})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Topic")
    private Integer iDTopic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "ID_User", referencedColumnName = "ID_User")
    @ManyToOne(optional = false)
    private User iDUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDTopic")
    private Collection<Message> messageCollection;

    public Topic() {
    }

    public Topic(Integer iDTopic) {
        this.iDTopic = iDTopic;
    }

    public Topic(Integer iDTopic, String name, Date date) {
        this.iDTopic = iDTopic;
        this.name = name;
        this.date = date;
    }

    public Integer getIDTopic() {
        return iDTopic;
    }

    public void setIDTopic(Integer iDTopic) {
        this.iDTopic = iDTopic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getIDUser() {
        return iDUser;
    }

    public void setIDUser(User iDUser) {
        this.iDUser = iDUser;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDTopic != null ? iDTopic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.iDTopic == null && other.iDTopic != null) || (this.iDTopic != null && !this.iDTopic.equals(other.iDTopic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Topic[ iDTopic=" + iDTopic + " ]";
    }
    
}
