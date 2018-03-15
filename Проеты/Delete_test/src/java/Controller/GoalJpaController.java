/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import entity.Goal;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Personage;
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
public class GoalJpaController implements Serializable {

    public GoalJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Goal goal) throws RollbackFailureException, Exception {
        if (goal.getGoalUserCollection() == null) {
            goal.setGoalUserCollection(new ArrayList<GoalUser>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personage IDPersonage = goal.getIDPersonage();
            if (IDPersonage != null) {
                IDPersonage = em.getReference(IDPersonage.getClass(), IDPersonage.getIDPersonage());
                goal.setIDPersonage(IDPersonage);
            }
            Collection<GoalUser> attachedGoalUserCollection = new ArrayList<GoalUser>();
            for (GoalUser goalUserCollectionGoalUserToAttach : goal.getGoalUserCollection()) {
                goalUserCollectionGoalUserToAttach = em.getReference(goalUserCollectionGoalUserToAttach.getClass(), goalUserCollectionGoalUserToAttach.getIDGoaluser());
                attachedGoalUserCollection.add(goalUserCollectionGoalUserToAttach);
            }
            goal.setGoalUserCollection(attachedGoalUserCollection);
            em.persist(goal);
            if (IDPersonage != null) {
                IDPersonage.getGoalCollection().add(goal);
                IDPersonage = em.merge(IDPersonage);
            }
            for (GoalUser goalUserCollectionGoalUser : goal.getGoalUserCollection()) {
                Goal oldIDGoalOfGoalUserCollectionGoalUser = goalUserCollectionGoalUser.getIDGoal();
                goalUserCollectionGoalUser.setIDGoal(goal);
                goalUserCollectionGoalUser = em.merge(goalUserCollectionGoalUser);
                if (oldIDGoalOfGoalUserCollectionGoalUser != null) {
                    oldIDGoalOfGoalUserCollectionGoalUser.getGoalUserCollection().remove(goalUserCollectionGoalUser);
                    oldIDGoalOfGoalUserCollectionGoalUser = em.merge(oldIDGoalOfGoalUserCollectionGoalUser);
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

    public void edit(Goal goal) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Goal persistentGoal = em.find(Goal.class, goal.getIDGoal());
            Personage IDPersonageOld = persistentGoal.getIDPersonage();
            Personage IDPersonageNew = goal.getIDPersonage();
            Collection<GoalUser> goalUserCollectionOld = persistentGoal.getGoalUserCollection();
            Collection<GoalUser> goalUserCollectionNew = goal.getGoalUserCollection();
            List<String> illegalOrphanMessages = null;
            for (GoalUser goalUserCollectionOldGoalUser : goalUserCollectionOld) {
                if (!goalUserCollectionNew.contains(goalUserCollectionOldGoalUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain GoalUser " + goalUserCollectionOldGoalUser + " since its IDGoal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (IDPersonageNew != null) {
                IDPersonageNew = em.getReference(IDPersonageNew.getClass(), IDPersonageNew.getIDPersonage());
                goal.setIDPersonage(IDPersonageNew);
            }
            Collection<GoalUser> attachedGoalUserCollectionNew = new ArrayList<GoalUser>();
            for (GoalUser goalUserCollectionNewGoalUserToAttach : goalUserCollectionNew) {
                goalUserCollectionNewGoalUserToAttach = em.getReference(goalUserCollectionNewGoalUserToAttach.getClass(), goalUserCollectionNewGoalUserToAttach.getIDGoaluser());
                attachedGoalUserCollectionNew.add(goalUserCollectionNewGoalUserToAttach);
            }
            goalUserCollectionNew = attachedGoalUserCollectionNew;
            goal.setGoalUserCollection(goalUserCollectionNew);
            goal = em.merge(goal);
            if (IDPersonageOld != null && !IDPersonageOld.equals(IDPersonageNew)) {
                IDPersonageOld.getGoalCollection().remove(goal);
                IDPersonageOld = em.merge(IDPersonageOld);
            }
            if (IDPersonageNew != null && !IDPersonageNew.equals(IDPersonageOld)) {
                IDPersonageNew.getGoalCollection().add(goal);
                IDPersonageNew = em.merge(IDPersonageNew);
            }
            for (GoalUser goalUserCollectionNewGoalUser : goalUserCollectionNew) {
                if (!goalUserCollectionOld.contains(goalUserCollectionNewGoalUser)) {
                    Goal oldIDGoalOfGoalUserCollectionNewGoalUser = goalUserCollectionNewGoalUser.getIDGoal();
                    goalUserCollectionNewGoalUser.setIDGoal(goal);
                    goalUserCollectionNewGoalUser = em.merge(goalUserCollectionNewGoalUser);
                    if (oldIDGoalOfGoalUserCollectionNewGoalUser != null && !oldIDGoalOfGoalUserCollectionNewGoalUser.equals(goal)) {
                        oldIDGoalOfGoalUserCollectionNewGoalUser.getGoalUserCollection().remove(goalUserCollectionNewGoalUser);
                        oldIDGoalOfGoalUserCollectionNewGoalUser = em.merge(oldIDGoalOfGoalUserCollectionNewGoalUser);
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
                Integer id = goal.getIDGoal();
                if (findGoal(id) == null) {
                    throw new NonexistentEntityException("The goal with id " + id + " no longer exists.");
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
            Goal goal;
            try {
                goal = em.getReference(Goal.class, id);
                goal.getIDGoal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The goal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<GoalUser> goalUserCollectionOrphanCheck = goal.getGoalUserCollection();
            for (GoalUser goalUserCollectionOrphanCheckGoalUser : goalUserCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Goal (" + goal + ") cannot be destroyed since the GoalUser " + goalUserCollectionOrphanCheckGoalUser + " in its goalUserCollection field has a non-nullable IDGoal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Personage IDPersonage = goal.getIDPersonage();
            if (IDPersonage != null) {
                IDPersonage.getGoalCollection().remove(goal);
                IDPersonage = em.merge(IDPersonage);
            }
            em.remove(goal);
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

    public List<Goal> findGoalEntities() {
        return findGoalEntities(true, -1, -1);
    }

    public List<Goal> findGoalEntities(int maxResults, int firstResult) {
        return findGoalEntities(false, maxResults, firstResult);
    }

    private List<Goal> findGoalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Goal.class));
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

    public Goal findGoal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Goal.class, id);
        } finally {
            em.close();
        }
    }

    public int getGoalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Goal> rt = cq.from(Goal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
