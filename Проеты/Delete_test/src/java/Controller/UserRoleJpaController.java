/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.User;
import entity.UserRole;
import entity.UserRolePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Vasilisa
 */
public class UserRoleJpaController implements Serializable {

    public UserRoleJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserRole userRole) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (userRole.getUserRolePK() == null) {
            userRole.setUserRolePK(new UserRolePK());
        }
        userRole.getUserRolePK().setLogin(userRole.getUser().getLogin());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User user = userRole.getUser();
            if (user != null) {
                user = em.getReference(user.getClass(), user.getIDUser());
                userRole.setUser(user);
            }
            em.persist(userRole);
            if (user != null) {
                user.getUserRoleCollection().add(userRole);
                user = em.merge(user);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUserRole(userRole.getUserRolePK()) != null) {
                throw new PreexistingEntityException("UserRole " + userRole + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserRole userRole) throws NonexistentEntityException, RollbackFailureException, Exception {
        userRole.getUserRolePK().setLogin(userRole.getUser().getLogin());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserRole persistentUserRole = em.find(UserRole.class, userRole.getUserRolePK());
            User userOld = persistentUserRole.getUser();
            User userNew = userRole.getUser();
            if (userNew != null) {
                userNew = em.getReference(userNew.getClass(), userNew.getIDUser());
                userRole.setUser(userNew);
            }
            userRole = em.merge(userRole);
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getUserRoleCollection().remove(userRole);
                userOld = em.merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getUserRoleCollection().add(userRole);
                userNew = em.merge(userNew);
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
                UserRolePK id = userRole.getUserRolePK();
                if (findUserRole(id) == null) {
                    throw new NonexistentEntityException("The userRole with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UserRolePK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UserRole userRole;
            try {
                userRole = em.getReference(UserRole.class, id);
                userRole.getUserRolePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userRole with id " + id + " no longer exists.", enfe);
            }
            User user = userRole.getUser();
            if (user != null) {
                user.getUserRoleCollection().remove(userRole);
                user = em.merge(user);
            }
            em.remove(userRole);
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

    public List<UserRole> findUserRoleEntities() {
        return findUserRoleEntities(true, -1, -1);
    }

    public List<UserRole> findUserRoleEntities(int maxResults, int firstResult) {
        return findUserRoleEntities(false, maxResults, firstResult);
    }

    private List<UserRole> findUserRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserRole.class));
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

    public UserRole findUserRole(UserRolePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserRole.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserRoleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserRole> rt = cq.from(UserRole.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
