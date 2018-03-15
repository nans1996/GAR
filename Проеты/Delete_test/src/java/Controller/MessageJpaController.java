/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import entity.Message;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.User;
import entity.Topic;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Vasilisa
 */
public class MessageJpaController implements Serializable {

    public MessageJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Message message) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User IDUser = message.getIDUser();
            if (IDUser != null) {
                IDUser = em.getReference(IDUser.getClass(), IDUser.getIDUser());
                message.setIDUser(IDUser);
            }
            Topic IDTopic = message.getIDTopic();
            if (IDTopic != null) {
                IDTopic = em.getReference(IDTopic.getClass(), IDTopic.getIDTopic());
                message.setIDTopic(IDTopic);
            }
            em.persist(message);
            if (IDUser != null) {
                IDUser.getMessageCollection().add(message);
                IDUser = em.merge(IDUser);
            }
            if (IDTopic != null) {
                IDTopic.getMessageCollection().add(message);
                IDTopic = em.merge(IDTopic);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Message message) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Message persistentMessage = em.find(Message.class, message.getIDMessage());
            User IDUserOld = persistentMessage.getIDUser();
            User IDUserNew = message.getIDUser();
            Topic IDTopicOld = persistentMessage.getIDTopic();
            Topic IDTopicNew = message.getIDTopic();
            if (IDUserNew != null) {
                IDUserNew = em.getReference(IDUserNew.getClass(), IDUserNew.getIDUser());
                message.setIDUser(IDUserNew);
            }
            if (IDTopicNew != null) {
                IDTopicNew = em.getReference(IDTopicNew.getClass(), IDTopicNew.getIDTopic());
                message.setIDTopic(IDTopicNew);
            }
            message = em.merge(message);
            if (IDUserOld != null && !IDUserOld.equals(IDUserNew)) {
                IDUserOld.getMessageCollection().remove(message);
                IDUserOld = em.merge(IDUserOld);
            }
            if (IDUserNew != null && !IDUserNew.equals(IDUserOld)) {
                IDUserNew.getMessageCollection().add(message);
                IDUserNew = em.merge(IDUserNew);
            }
            if (IDTopicOld != null && !IDTopicOld.equals(IDTopicNew)) {
                IDTopicOld.getMessageCollection().remove(message);
                IDTopicOld = em.merge(IDTopicOld);
            }
            if (IDTopicNew != null && !IDTopicNew.equals(IDTopicOld)) {
                IDTopicNew.getMessageCollection().add(message);
                IDTopicNew = em.merge(IDTopicNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = message.getIDMessage();
                if (findMessage(id) == null) {
                    throw new NonexistentEntityException("The message with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Message message;
            try {
                message = em.getReference(Message.class, id);
                message.getIDMessage();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The message with id " + id + " no longer exists.", enfe);
            }
            User IDUser = message.getIDUser();
            if (IDUser != null) {
                IDUser.getMessageCollection().remove(message);
                IDUser = em.merge(IDUser);
            }
            Topic IDTopic = message.getIDTopic();
            if (IDTopic != null) {
                IDTopic.getMessageCollection().remove(message);
                IDTopic = em.merge(IDTopic);
            }
            em.remove(message);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Message> findMessageEntities() {
        return findMessageEntities(true, -1, -1);
    }

    public List<Message> findMessageEntities(int maxResults, int firstResult) {
        return findMessageEntities(false, maxResults, firstResult);
    }

    private List<Message> findMessageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Message.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Message findMessage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Message.class, id);
        } finally {
            em.close();
        }
    }

    public int getMessageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Message> rt = cq.from(Message.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
