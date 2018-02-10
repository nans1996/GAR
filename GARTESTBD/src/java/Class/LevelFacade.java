/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class LevelFacade extends AbstractFacade<Level> {

    @PersistenceContext(unitName = "GARTESTBDPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LevelFacade() {
        super(Level.class);
    }
    
}
