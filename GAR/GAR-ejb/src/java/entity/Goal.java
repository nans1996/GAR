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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vasilisa
 */
@Entity
@Table(name = "goal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g")
    , @NamedQuery(name = "Goal.findByIDGoal", query = "SELECT g FROM Goal g WHERE g.iDGoal = :iDGoal")
    , @NamedQuery(name = "Goal.findByName", query = "SELECT g FROM Goal g WHERE g.name = :name")
    , @NamedQuery(name = "Goal.findByDirectory", query = "SELECT g FROM Goal g WHERE g.directory = :directory")
    , @NamedQuery(name = "Goal.findByIDImage", query = "SELECT g FROM Goal g WHERE g.iDImage = :iDImage")})
public class Goal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Goal")
    private Integer iDGoal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Directory")
    private boolean directory;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "ID_Image", referencedColumnName = "ID_Image")
    @OneToOne
    private Image iDImage;
    @JoinColumn(name = "ID_Personage", referencedColumnName = "ID_Personage")
    @ManyToOne(optional = false)
    private Personage iDPersonage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDGoal")
    private Collection<GoalUser> goalUserCollection;

    public Goal() {
    }

    public Goal(Integer iDGoal) {
        this.iDGoal = iDGoal;
    }

    public Goal(Integer iDGoal, String name, boolean directory, String description) {
        this.iDGoal = iDGoal;
        this.name = name;
        this.directory = directory;
        this.description = description;
    }

    public Integer getIDGoal() {
        return iDGoal;
    }

    public void setIDGoal(Integer iDGoal) {
        this.iDGoal = iDGoal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getiDImage() {
        return iDImage;
    }

    public void setiDImage(Image iDImage) {
        this.iDImage = iDImage;
    }

    public Personage getIDPersonage() {
        return iDPersonage;
    }

    public void setIDPersonage(Personage iDPersonage) {
        this.iDPersonage = iDPersonage;
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
        hash += (iDGoal != null ? iDGoal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Goal)) {
            return false;
        }
        Goal other = (Goal) object;
        if ((this.iDGoal == null && other.iDGoal != null) || (this.iDGoal != null && !this.iDGoal.equals(other.iDGoal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Goal[ iDGoal=" + iDGoal + " ]";
    }
    
}
