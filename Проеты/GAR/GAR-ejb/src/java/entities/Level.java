/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
 * @author Vasilisa
 */
@Entity
@Table(name = "level")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Level.findAll", query = "SELECT l FROM Level l")
    , @NamedQuery(name = "Level.findByIDLevel", query = "SELECT l FROM Level l WHERE l.iDLevel = :iDLevel")
    , @NamedQuery(name = "Level.findByDate", query = "SELECT l FROM Level l WHERE l.date = :date")
    , @NamedQuery(name = "Level.findByLeveldate", query = "SELECT l FROM Level l WHERE l.leveldate = :leveldate")})
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Level")
    private Integer iDLevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "Level_date")
    private Integer leveldate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDLevel")
    private Collection<GoalUser> goalUserCollection;

    public Level() {
    }

    public Level(Integer iDLevel) {
        this.iDLevel = iDLevel;
    }

    public Level(Integer iDLevel, Date date) {
        this.iDLevel = iDLevel;
        this.date = date;
    }

    public Integer getIDLevel() {
        return iDLevel;
    }

    public void setIDLevel(Integer iDLevel) {
        this.iDLevel = iDLevel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLeveldate() {
        return leveldate;
    }

    public void setLeveldate(Integer leveldate) {
        this.leveldate = leveldate;
    }

    @XmlTransient
    public Collection<GoalUser> getGoalUserCollection() {
        return goalUserCollection;
    }

    public void setGoalUserCollection(Collection<GoalUser> goalUserCollection) {
        this.goalUserCollection = goalUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDLevel != null ? iDLevel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Level)) {
            return false;
        }
        Level other = (Level) object;
        if ((this.iDLevel == null && other.iDLevel != null) || (this.iDLevel != null && !this.iDLevel.equals(other.iDLevel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Level[ iDLevel=" + iDLevel + " ]";
    }
    
}
