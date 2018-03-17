/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vasilisa
 */
@Entity
@Table(name = "goal_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoalUser.findAll", query = "SELECT g FROM GoalUser g")
    , @NamedQuery(name = "GoalUser.findByIDGoaluser", query = "SELECT g FROM GoalUser g WHERE g.iDGoaluser = :iDGoaluser")})
public class GoalUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Goal_user")
    private Integer iDGoaluser;
    @JoinColumn(name = "ID_Level", referencedColumnName = "ID_Level")
    @ManyToOne(optional = false)
    private Level iDLevel;
    @JoinColumn(name = "ID_Goal", referencedColumnName = "ID_Goal")
    @ManyToOne(optional = false)
    private Goal iDGoal;
    @JoinColumn(name = "ID_Client", referencedColumnName = "ID_Client")
    @ManyToOne(optional = false)
    private Client iDClient;

    public GoalUser() {
    }

    public GoalUser(Integer iDGoaluser) {
        this.iDGoaluser = iDGoaluser;
    }

    public Integer getIDGoaluser() {
        return iDGoaluser;
    }

    public void setIDGoaluser(Integer iDGoaluser) {
        this.iDGoaluser = iDGoaluser;
    }

    public Level getIDLevel() {
        return iDLevel;
    }

    public void setIDLevel(Level iDLevel) {
        this.iDLevel = iDLevel;
    }

    public Goal getIDGoal() {
        return iDGoal;
    }

    public void setIDGoal(Goal iDGoal) {
        this.iDGoal = iDGoal;
    }

    public Client getIDClient() {
        return iDClient;
    }

    public void setIDClient(Client iDClient) {
        this.iDClient = iDClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDGoaluser != null ? iDGoaluser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GoalUser)) {
            return false;
        }
        GoalUser other = (GoalUser) object;
        if ((this.iDGoaluser == null && other.iDGoaluser != null) || (this.iDGoaluser != null && !this.iDGoaluser.equals(other.iDGoaluser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.GoalUser[ iDGoaluser=" + iDGoaluser + " ]";
    }
    
}
