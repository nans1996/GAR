/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Goal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class GoalFacade extends AbstractFacade<Goal> {

    @PersistenceContext(unitName = "GARTESTBD_version2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GoalFacade() {
        super(Goal.class);
    }
    
}
