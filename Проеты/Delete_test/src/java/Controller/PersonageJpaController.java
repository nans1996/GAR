/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Goal;
import entity.Personage;
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
public class PersonageJpaController implements Serializable {

    public PersonageJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personage personage) throws RollbackFailureException, Exception {
        if (personage.getGoalCollection() == null) {
            personage.setGoalCollection(new ArrayList<Goal>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Goal> attachedGoalCollection = new ArrayList<Goal>();
            for (Goal goalCollectionGoalToAttach : personage.getGoalCollection()) {
                goalCollectionGoalToAttach = em.getReference(goalCollectionGoalToAttach.getClass(), goalCollectionGoalToAttach.getIDGoal());
                attachedGoalCollection.add(goalCollectionGoalToAttach);
            }
            personage.setGoalCollection(attachedGoalCollection);
            em.persist(personage);
            for (Goal goalCollectionGoal : personage.getGoalCollection()) {
                Personage oldIDPersonageOfGoalCollectionGoal = goalCollectionGoal.getIDPersonage();
                goalCollectionGoal.setIDPersonage(personage);
                goalCollectionGoal = em.merge(goalCollectionGoal);
                if (oldIDPersonageOfGoalCollectionGoal != null) {
                    oldIDPersonageOfGoalCollectionGoal.getGoalCollection().remove(goalCollectionGoal);
                    oldIDPersonageOfGoalCollectionGoal = em.merge(oldIDPersonageOfGoalCollectionGoal);
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

    public void edit(Personage personage) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Personage persistentPersonage = em.find(Personage.class, personage.getIDPersonage());
            Collection<Goal> goalCollectionOld = persistentPersonage.getGoalCollection();
            Collection<Goal> goalCollectionNew = personage.getGoalCollection();
            List<String> illegalOrphanMessages = null;
            for (Goal goalCollectionOldGoal : goalCollectionOld) {
                if (!goalCollectionNew.contains(goalCollectionOldGoal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Goal " + goalCollectionOldGoal + " since its IDPersonage field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Goal> attachedGoalCollectionNew = new ArrayList<Goal>();
            for (Goal goalCollectionNewGoalToAttach : goalCollectionNew) {
                goalCollectionNewGoalToAttach = em.getReference(goalCollectionNewGoalToAttach.getClass(), goalCollectionNewGoalToAttach.getIDGoal());
                attachedGoalCollectionNew.add(goalCollectionNewGoalToAttach);
            }
            goalCollectionNew = attachedGoalCollectionNew;
            personage.setGoalCollection(goalCollectionNew);
            personage = em.merge(personage);
            for (Goal goalCollectionNewGoal : goalCollectionNew) {
                if (!goalCollectionOld.contains(goalCollectionNewGoal)) {
                    Personage oldIDPersonageOfGoalCollectionNewGoal = goalCollectionNewGoal.getIDPersonage();
                    goalCollectionNewGoal.setIDPersonage(personage);
                    goalCollectionNewGoal = em.merge(goalCollectionNewGoal);
                    if (oldIDPersonageOfGoalCollectionNewGoal != null && !oldIDPersonageOfGoalCollectionNewGoal.equals(personage)) {
                        oldIDPersonageOfGoalCollectionNewGoal.getGoalCollection().remove(goalCollectionNewGoal);
                        oldIDPersonageOfGoalCollectionNewGoal = em.merge(oldIDPersonageOfGoalCollectionNewGoal);
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
                Integer id = personage.getIDPersonage();
                if (findPersonage(id) == null) {
                    throw new NonexistentEntityException("The personage with id " + id + " no longer exists.");
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
            Personage personage;
            try {
                personage = em.getReference(Personage.class, id);
                personage.getIDPersonage();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personage with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Goal> goalCollectionOrphanCheck = personage.getGoalCollection();
            for (Goal goalCollectionOrphanCheckGoal : goalCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personage (" + personage + ") cannot be destroyed since the Goal " + goalCollectionOrphanCheckGoal + " in its goalCollection field has a non-nullable IDPersonage field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(personage);
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

    public List<Personage> findPersonageEntities() {
        return findPersonageEntities(true, -1, -1);
    }

    public List<Personage> findPersonageEntities(int maxResults, int firstResult) {
        return findPersonageEntities(false, maxResults, firstResult);
    }

    private List<Personage> findPersonageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personage.class));
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

    public Personage findPersonage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personage.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personage> rt = cq.from(Personage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
