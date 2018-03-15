/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.RollbackFailureException;
import entity.Client;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.User;
import entity.GoalUser;
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
public class ClientJpaController implements Serializable {

    public ClientJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) throws RollbackFailureException, Exception {
        if (client.getGoalUserCollection() == null) {
            client.setGoalUserCollection(new ArrayList<GoalUser>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User IDUser = client.getIDUser();
            if (IDUser != null) {
                IDUser = em.getReference(IDUser.getClass(), IDUser.getIDUser());
                client.setIDUser(IDUser);
            }
            Collection<GoalUser> attachedGoalUserCollection = new ArrayList<GoalUser>();
            for (GoalUser goalUserCollectionGoalUserToAttach : client.getGoalUserCollection()) {
                goalUserCollectionGoalUserToAttach = em.getReference(goalUserCollectionGoalUserToAttach.getClass(), goalUserCollectionGoalUserToAttach.getIDGoaluser());
                attachedGoalUserCollection.add(goalUserCollectionGoalUserToAttach);
            }
            client.setGoalUserCollection(attachedGoalUserCollection);
            em.persist(client);
            if (IDUser != null) {
                IDUser.getClientCollection().add(client);
                IDUser = em.merge(IDUser);
            }
            for (GoalUser goalUserCollectionGoalUser : client.getGoalUserCollection()) {
                Client oldIDClientOfGoalUserCollectionGoalUser = goalUserCollectionGoalUser.getIDClient();
                goalUserCollectionGoalUser.setIDClient(client);
                goalUserCollectionGoalUser = em.merge(goalUserCollectionGoalUser);
                if (oldIDClientOfGoalUserCollectionGoalUser != null) {
                    oldIDClientOfGoalUserCollectionGoalUser.getGoalUserCollection().remove(goalUserCollectionGoalUser);
                    oldIDClientOfGoalUserCollectionGoalUser = em.merge(oldIDClientOfGoalUserCollectionGoalUser);
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

    public void edit(Client client) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Client persistentClient = em.find(Client.class, client.getIDClient());
            User IDUserOld = persistentClient.getIDUser();
            User IDUserNew = client.getIDUser();
            Collection<GoalUser> goalUserCollectionOld = persistentClient.getGoalUserCollection();
            Collection<GoalUser> goalUserCollectionNew = client.getGoalUserCollection();
            List<String> illegalOrphanMessages = null;
            for (GoalUser goalUserCollectionOldGoalUser : goalUserCollectionOld) {
                if (!goalUserCollectionNew.contains(goalUserCollectionOldGoalUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GoalUser " + goalUserCollectionOldGoalUser + " since its IDClient field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (IDUserNew != null) {
                IDUserNew = em.getReference(IDUserNew.getClass(), IDUserNew.getIDUser());
                client.setIDUser(IDUserNew);
            }
            Collection<GoalUser> attachedGoalUserCollectionNew = new ArrayList<GoalUser>();
            for (GoalUser goalUserCollectionNewGoalUserToAttach : goalUserCollectionNew) {
                goalUserCollectionNewGoalUserToAttach = em.getReference(goalUserCollectionNewGoalUserToAttach.getClass(), goalUserCollectionNewGoalUserToAttach.getIDGoaluser());
                attachedGoalUserCollectionNew.add(goalUserCollectionNewGoalUserToAttach);
            }
            goalUserCollectionNew = attachedGoalUserCollectionNew;
            client.setGoalUserCollection(goalUserCollectionNew);
            client = em.merge(client);
            if (IDUserOld != null && !IDUserOld.equals(IDUserNew)) {
                IDUserOld.getClientCollection().remove(client);
                IDUserOld = em.merge(IDUserOld);
            }
            if (IDUserNew != null && !IDUserNew.equals(IDUserOld)) {
                IDUserNew.getClientCollection().add(client);
                IDUserNew = em.merge(IDUserNew);
            }
            for (GoalUser goalUserCollectionNewGoalUser : goalUserCollectionNew) {
                if (!goalUserCollectionOld.contains(goalUserCollectionNewGoalUser)) {
                    Client oldIDClientOfGoalUserCollectionNewGoalUser = goalUserCollectionNewGoalUser.getIDClient();
                    goalUserCollectionNewGoalUser.setIDClient(client);
                    goalUserCollectionNewGoalUser = em.merge(goalUserCollectionNewGoalUser);
                    if (oldIDClientOfGoalUserCollectionNewGoalUser != null && !oldIDClientOfGoalUserCollectionNewGoalUser.equals(client)) {
                        oldIDClientOfGoalUserCollectionNewGoalUser.getGoalUserCollection().remove(goalUserCollectionNewGoalUser);
                        oldIDClientOfGoalUserCollectionNewGoalUser = em.merge(oldIDClientOfGoalUserCollectionNewGoalUser);
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
                Integer id = client.getIDClient();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
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
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getIDClient();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<GoalUser> goalUserCollectionOrphanCheck = client.getGoalUserCollection();
            for (GoalUser goalUserCollectionOrphanCheckGoalUser : goalUserCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Client (" + client + ") cannot be destroyed since the GoalUser " + goalUserCollectionOrphanCheckGoalUser + " in its goalUserCollection field has a non-nullable IDClient field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User IDUser = client.getIDUser();
            if (IDUser != null) {
                IDUser.getClientCollection().remove(client);
                IDUser = em.merge(IDUser);
            }
            em.remove(client);
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

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
