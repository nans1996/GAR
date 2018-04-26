/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Message;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Vasilisa
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> implements MessageFacadeLocal {

    @PersistenceContext(unitName = "GAR-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int countMessageId(int id){
        Query count = em.createNamedQuery("SELECT Count(m) FROM Message m WHERE m.iDTopic = :iDTopic")
                .setParameter("iDTopic", id);
   return (int)count.getSingleResult();

           }
    
    public MessageFacade() {
        super(Message.class);
    }
    
}
