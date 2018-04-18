/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Goal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class GoalFacade extends AbstractFacade<Goal> implements GoalFacadeLocal {

    @PersistenceContext(unitName = "GAR-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GoalFacade() {
        super(Goal.class);
    }
    
    @Override
    public List<Goal> findGoalDefolt(){
        Query q = em.createNamedQuery("Goal.findByDirectory");
        q.setParameter("directory", false);
        return (List<Goal>) q.getResultList();
    }
}
