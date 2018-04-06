/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Personage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class PersonageFacade extends AbstractFacade<Personage> implements PersonageFacadeLocal {

    @PersistenceContext(unitName = "GAR-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonageFacade() {
        super(Personage.class);
    }
    
}
