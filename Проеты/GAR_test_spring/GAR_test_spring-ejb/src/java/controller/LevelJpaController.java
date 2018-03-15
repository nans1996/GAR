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
import entity.GoalUser;
import entity.Level;
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
public class LevelJpaController implements Serializable {

    public LevelJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Level level) throws RollbackFailureException, Exception {
        if (level.getGoalUserCollection() == null) {
            level.setGoalUserCollection(new ArrayList<GoalUser>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<GoalUser> attachedGoalUserCollection = new ArrayList<GoalUser>();
            for (GoalUser goalUserCollectionGoalUserToAttach : level.getGoalUserCollection()) {
                goalUserCollectionGoalUserToAttach = em.getReference(goalUserCollectionGoalUserToAttach.getClass(), goalUserCollectionGoalUserToAttach.getIDGoaluser());
                attachedGoalUserCollection.add(goalUserCollectionGoalUserToAttach);
            }
            level.setGoalUserCollection(attachedGoalUserCollection);
            em.persist(level);
            for (GoalUser goalUserCollectionGoalUser : level.getGoalUserCollection()) {
                Level oldIDLevelOfGoalUserCollectionGoalUser = goalUserCollectionGoalUser.getIDLevel();
                goalUserCollectionGoalUser.setIDLevel(level);
                goalUserCollectionGoalUser = em.merge(goalUserCollectionGoalUser);
                if (oldIDLevelOfGoalUserCollectionGoalUser != null) {
                    oldIDLevelOfGoalUserCollectionGoalUser.getGoalUserCollection().remove(goalUserCollectionGoalUser);
                    oldIDLevelOfGoalUserCollectionGoalUser = em.merge(oldIDLevelOfGoalUserCollectionGoalUser);
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

    public void edit(Level level) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Level persistentLevel = em.find(Level.class, level.getIDLevel());
            Collection<GoalUser> goalUserCollectionOld = persistentLevel.getGoalUserCollection();
            Collection<GoalUser> goalUserCollectionNew = level.getGoalUserCollection();
            List<String> illegalOrphanMessages = null;
            for (GoalUser goalUserCollectionOldGoalUser : goalUserCollectionOld) {
                if (!goalUserCollectionNew.contains(goalUserCollectionOldGoalUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GoalUser " + goalUserCollectionOldGoalUser + " since its IDLevel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<GoalUser> attachedGoalUserCollectionNew = new ArrayList<GoalUser>();
            for (GoalUser goalUserCollectionNewGoalUserToAttach : goalUserCollectionNew) {
                goalUserCollectionNewGoalUserToAttach = em.getReference(goalUserCollectionNewGoalUserToAttach.getClass(), goalUserCollectionNewGoalUserToAttach.getIDGoaluser());
                attachedGoalUserCollectionNew.add(goalUserCollectionNewGoalUserToAttach);
            }
            goalUserCollectionNew = attachedGoalUserCollectionNew;
            level.setGoalUserCollection(goalUserCollectionNew);
            level = em.merge(level);
            for (GoalUser goalUserCollectionNewGoalUser : goalUserCollectionNew) {
                if (!goalUserCollectionOld.contains(goalUserCollectionNewGoalUser)) {
                    Level oldIDLevelOfGoalUserCollectionNewGoalUser = goalUserCollectionNewGoalUser.getIDLevel();
                    goalUserCollectionNewGoalUser.setIDLevel(level);
                    goalUserCollectionNewGoalUser = em.merge(goalUserCollectionNewGoalUser);
                    if (oldIDLevelOfGoalUserCollectionNewGoalUser != null && !oldIDLevelOfGoalUserCollectionNewGoalUser.equals(level)) {
                        oldIDLevelOfGoalUserCollectionNewGoalUser.getGoalUserCollection().remove(goalUserCollectionNewGoalUser);
                        oldIDLevelOfGoalUserCollectionNewGoalUser = em.merge(oldIDLevelOfGoalUserCollectionNewGoalUser);
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
                Integer id = level.getIDLevel();
                if (findLevel(id) == null) {
                    throw new NonexistentEntityException("The level with id " + id + " no longer exists.");
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
            Level level;
            try {
                level = em.getReference(Level.class, id);
                level.getIDLevel();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The level with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<GoalUser> goalUserCollectionOrphanCheck = level.getGoalUserCollection();
            for (GoalUser goalUserCollectionOrphanCheckGoalUser : goalUserCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Level (" + level + ") cannot be destroyed since the GoalUser " + goalUserCollectionOrphanCheckGoalUser + " in its goalUserCollection field has a non-nullable IDLevel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(level);
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

    public List<Level> findLevelEntities() {
        return findLevelEntities(true, -1, -1);
    }

    public List<Level> findLevelEntities(int maxResults, int firstResult) {
        return findLevelEntities(false, maxResults, firstResult);
    }

    private List<Level> findLevelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Level.class));
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

    public Level findLevel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Level.class, id);
        } finally {
            em.close();
        }
    }

    public int getLevelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Level> rt = cq.from(Level.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
