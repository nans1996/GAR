/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Message;
import entity.Topic;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Vasilisa
 */
public class TopicJpaController implements Serializable {

    public TopicJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Topic topic) throws RollbackFailureException, Exception {
        if (topic.getMessageCollection() == null) {
            topic.setMessageCollection(new ArrayList<Message>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Message> attachedMessageCollection = new ArrayList<Message>();
            for (Message messageCollectionMessageToAttach : topic.getMessageCollection()) {
                messageCollectionMessageToAttach = em.getReference(messageCollectionMessageToAttach.getClass(), messageCollectionMessageToAttach.getIDMessage());
                attachedMessageCollection.add(messageCollectionMessageToAttach);
            }
            topic.setMessageCollection(attachedMessageCollection);
            em.persist(topic);
            for (Message messageCollectionMessage : topic.getMessageCollection()) {
                Topic oldIDTopicOfMessageCollectionMessage = messageCollectionMessage.getIDTopic();
                messageCollectionMessage.setIDTopic(topic);
                messageCollectionMessage = em.merge(messageCollectionMessage);
                if (oldIDTopicOfMessageCollectionMessage != null) {
                    oldIDTopicOfMessageCollectionMessage.getMessageCollection().remove(messageCollectionMessage);
                    oldIDTopicOfMessageCollectionMessage = em.merge(oldIDTopicOfMessageCollectionMessage);
                }
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

    public void edit(Topic topic) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Topic persistentTopic = em.find(Topic.class, topic.getIDTopic());
            Collection<Message> messageCollectionOld = persistentTopic.getMessageCollection();
            Collection<Message> messageCollectionNew = topic.getMessageCollection();
            List<String> illegalOrphanMessages = null;
            for (Message messageCollectionOldMessage : messageCollectionOld) {
                if (!messageCollectionNew.contains(messageCollectionOldMessage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Message " + messageCollectionOldMessage + " since its IDTopic field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Message> attachedMessageCollectionNew = new ArrayList<Message>();
            for (Message messageCollectionNewMessageToAttach : messageCollectionNew) {
                messageCollectionNewMessageToAttach = em.getReference(messageCollectionNewMessageToAttach.getClass(), messageCollectionNewMessageToAttach.getIDMessage());
                attachedMessageCollectionNew.add(messageCollectionNewMessageToAttach);
            }
            messageCollectionNew = attachedMessageCollectionNew;
            topic.setMessageCollection(messageCollectionNew);
            topic = em.merge(topic);
            for (Message messageCollectionNewMessage : messageCollectionNew) {
                if (!messageCollectionOld.contains(messageCollectionNewMessage)) {
                    Topic oldIDTopicOfMessageCollectionNewMessage = messageCollectionNewMessage.getIDTopic();
                    messageCollectionNewMessage.setIDTopic(topic);
                    messageCollectionNewMessage = em.merge(messageCollectionNewMessage);
                    if (oldIDTopicOfMessageCollectionNewMessage != null && !oldIDTopicOfMessageCollectionNewMessage.equals(topic)) {
                        oldIDTopicOfMessageCollectionNewMessage.getMessageCollection().remove(messageCollectionNewMessage);
                        oldIDTopicOfMessageCollectionNewMessage = em.merge(oldIDTopicOfMessageCollectionNewMessage);
                    }
                }
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
                Integer id = topic.getIDTopic();
                if (findTopic(id) == null) {
                    throw new NonexistentEntityException("The topic with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Topic topic;
            try {
                topic = em.getReference(Topic.class, id);
                topic.getIDTopic();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The topic with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Message> messageCollectionOrphanCheck = topic.getMessageCollection();
            for (Message messageCollectionOrphanCheckMessage : messageCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Topic (" + topic + ") cannot be destroyed since the Message " + messageCollectionOrphanCheckMessage + " in its messageCollection field has a non-nullable IDTopic field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(topic);
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

    public List<Topic> findTopicEntities() {
        return findTopicEntities(true, -1, -1);
    }

    public List<Topic> findTopicEntities(int maxResults, int firstResult) {
        return findTopicEntities(false, maxResults, firstResult);
    }

    private List<Topic> findTopicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Topic.class));
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

    public Topic findTopic(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Topic.class, id);
        } finally {
            em.close();
        }
    }

    public int getTopicCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Topic> rt = cq.from(Topic.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
