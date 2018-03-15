/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Level;
import entity.Goal;
import entity.Client;
import entity.GoalUser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Vasilisa
 */
public class GoalUserJpaController implements Serializable {

    public GoalUserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GoalUser goalUser) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Level IDLevel = goalUser.getIDLevel();
            if (IDLevel != null) {
                IDLevel = em.getReference(IDLevel.getClass(), IDLevel.getIDLevel());
                goalUser.setIDLevel(IDLevel);
            }
            Goal IDGoal = goalUser.getIDGoal();
            if (IDGoal != null) {
                IDGoal = em.getReference(IDGoal.getClass(), IDGoal.getIDGoal());
                goalUser.setIDGoal(IDGoal);
            }
            Client IDClient = goalUser.getIDClient();
            if (IDClient != null) {
                IDClient = em.getReference(IDClient.getClass(), IDClient.getIDClient());
                goalUser.setIDClient(IDClient);
            }
            em.persist(goalUser);
            if (IDLevel != null) {
                IDLevel.getGoalUserCollection().add(goalUser);
                IDLevel = em.merge(IDLevel);
            }
            if (IDGoal != null) {
                IDGoal.getGoalUserCollection().add(goalUser);
                IDGoal = em.merge(IDGoal);
            }
            if (IDClient != null) {
                IDClient.getGoalUserCollection().add(goalUser);
                IDClient = em.merge(IDClient);
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

    public void edit(GoalUser goalUser) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            GoalUser persistentGoalUser = em.find(GoalUser.class, goalUser.getIDGoaluser());
            Level IDLevelOld = persistentGoalUser.getIDLevel();
            Level IDLevelNew = goalUser.getIDLevel();
            Goal IDGoalOld = persistentGoalUser.getIDGoal();
            Goal IDGoalNew = goalUser.getIDGoal();
            Client IDClientOld = persistentGoalUser.getIDClient();
            Client IDClientNew = goalUser.getIDClient();
            if (IDLevelNew != null) {
                IDLevelNew = em.getReference(IDLevelNew.getClass(), IDLevelNew.getIDLevel());
                goalUser.setIDLevel(IDLevelNew);
            }
            if (IDGoalNew != null) {
                IDGoalNew = em.getReference(IDGoalNew.getClass(), IDGoalNew.getIDGoal());
                goalUser.setIDGoal(IDGoalNew);
            }
            if (IDClientNew != null) {
                IDClientNew = em.getReference(IDClientNew.getClass(), IDClientNew.getIDClient());
                goalUser.setIDClient(IDClientNew);
            }
            goalUser = em.merge(goalUser);
            if (IDLevelOld != null && !IDLevelOld.equals(IDLevelNew)) {
                IDLevelOld.getGoalUserCollection().remove(goalUser);
                IDLevelOld = em.merge(IDLevelOld);
            }
            if (IDLevelNew != null && !IDLevelNew.equals(IDLevelOld)) {
                IDLevelNew.getGoalUserCollection().add(goalUser);
                IDLevelNew = em.merge(IDLevelNew);
            }
            if (IDGoalOld != null && !IDGoalOld.equals(IDGoalNew)) {
                IDGoalOld.getGoalUserCollection().remove(goalUser);
                IDGoalOld = em.merge(IDGoalOld);
            }
            if (IDGoalNew != null && !IDGoalNew.equals(IDGoalOld)) {
                IDGoalNew.getGoalUserCollection().add(goalUser);
                IDGoalNew = em.merge(IDGoalNew);
            }
            if (IDClientOld != null && !IDClientOld.equals(IDClientNew)) {
                IDClientOld.getGoalUserCollection().remove(goalUser);
                IDClientOld = em.merge(IDClientOld);
            }
            if (IDClientNew != null && !IDClientNew.equals(IDClientOld)) {
                IDClientNew.getGoalUserCollection().add(goalUser);
                IDClientNew = em.merge(IDClientNew);
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
                Integer id = goalUser.getIDGoaluser();
                if (findGoalUser(id) == null) {
                    throw new NonexistentEntityException("The goalUser with id " + id + " no longer exists.");
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
            GoalUser goalUser;
            try {
                goalUser = em.getReference(GoalUser.class, id);
                goalUser.getIDGoaluser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The goalUser with id " + id + " no longer exists.", enfe);
            }
            Level IDLevel = goalUser.getIDLevel();
            if (IDLevel != null) {
                IDLevel.getGoalUserCollection().remove(goalUser);
                IDLevel = em.merge(IDLevel);
            }
            Goal IDGoal = goalUser.getIDGoal();
            if (IDGoal != null) {
                IDGoal.getGoalUserCollection().remove(goalUser);
                IDGoal = em.merge(IDGoal);
            }
            Client IDClient = goalUser.getIDClient();
            if (IDClient != null) {
                IDClient.getGoalUserCollection().remove(goalUser);
                IDClient = em.merge(IDClient);
            }
            em.remove(goalUser);
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

    public List<GoalUser> findGoalUserEntities() {
        return findGoalUserEntities(true, -1, -1);
    }

    public List<GoalUser> findGoalUserEntities(int maxResults, int firstResult) {
        return findGoalUserEntities(false, maxResults, firstResult);
    }

    private List<GoalUser> findGoalUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GoalUser.class));
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

    public GoalUser findGoalUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GoalUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getGoalUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GoalUser> rt = cq.from(GoalUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
