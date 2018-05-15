/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.GoalUser;
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
public class GoalUserFacade extends AbstractFacade<GoalUser> implements GoalUserFacadeLocal {

    @PersistenceContext(unitName = "GAR-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GoalUserFacade() {
        super(GoalUser.class);
    }
    
    @Override
    public List<GoalUser> findGoalCurrentClient(Integer iDClient){
        Query q = em.createNamedQuery("GoalUser.findAllCurrentClient");
        q.setParameter("iDClient", iDClient);
        return (List<GoalUser>) q.getResultList();
    }
    
  
}
