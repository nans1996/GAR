/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Level;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entity.*;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class LevelFacade extends AbstractFacade<Level> implements LevelFacadeLocal {

    @PersistenceContext(unitName = "GAR-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LevelFacade() {
        super(Level.class);
    }
    
    @Override
    public List<Level> findGoalLevel(GoalUser goaluser){
        Query q = em.createNamedQuery("Level.findByIdGoal");
        q.setParameter("iDGoaluser", goaluser);
        return (List<Level>) q.getResultList();
    }
    
}
