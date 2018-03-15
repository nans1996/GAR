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
import javax.persistence.Lob;
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
@Table(name = "client")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Client")
    private Integer iDClient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Birth")
    @Temporal(TemporalType.DATE)
    private Date dateBirth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Money")
    private float money;
    @Lob
    @Size(max = 65535)
    @Column(name = "Interests")
    private String interests;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDClient")
    private Collection<GoalUser> goalUserCollection;
    @JoinColumn(name = "ID_User", referencedColumnName = "ID_User")
    @ManyToOne(optional = false)
    private User iDUser;

    public Client() {
    }

    public Client(Integer iDClient) {
        this.iDClient = iDClient;
    }

    public Client(Integer iDClient, Date dateBirth, float money) {
        this.iDClient = iDClient;
        this.dateBirth = dateBirth;
        this.money = money;
    }

    public Integer getIDClient() {
        return iDClient;
    }

    public void setIDClient(Integer iDClient) {
        this.iDClient = iDClient;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    @XmlTransient
    public Collection<GoalUser> getGoalUserCollection() {
        return goalUserCollection;
    }

    public void setGoalUserCollection(Collection<GoalUser> goalUserCollection) {
        this.goalUserCollection = goalUserCollection;
    }

    public User getIDUser() {
        return iDUser;
    }

    public void setIDUser(User iDUser) {
        this.iDUser = iDUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDClient != null ? iDClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.iDClient == null && other.iDClient != null) || (this.iDClient != null && !this.iDClient.equals(other.iDClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Client[ iDClient=" + iDClient + " ]";
    }
    
}
