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
import entity.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import entity.Client;
import entity.Message;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Vasilisa
 */
public class UserJpaController implements Serializable {

    public UserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws RollbackFailureException, Exception {
        if (user.getUserRoleCollection() == null) {
            user.setUserRoleCollection(new ArrayList<UserRole>());
        }
        if (user.getClientCollection() == null) {
            user.setClientCollection(new ArrayList<Client>());
        }
        if (user.getMessageCollection() == null) {
            user.setMessageCollection(new ArrayList<Message>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<UserRole> attachedUserRoleCollection = new ArrayList<UserRole>();
            for (UserRole userRoleCollectionUserRoleToAttach : user.getUserRoleCollection()) {
                userRoleCollectionUserRoleToAttach = em.getReference(userRoleCollectionUserRoleToAttach.getClass(), userRoleCollectionUserRoleToAttach.getUserRolePK());
                attachedUserRoleCollection.add(userRoleCollectionUserRoleToAttach);
            }
            user.setUserRoleCollection(attachedUserRoleCollection);
            Collection<Client> attachedClientCollection = new ArrayList<Client>();
            for (Client clientCollectionClientToAttach : user.getClientCollection()) {
                clientCollectionClientToAttach = em.getReference(clientCollectionClientToAttach.getClass(), clientCollectionClientToAttach.getIDClient());
                attachedClientCollection.add(clientCollectionClientToAttach);
            }
            user.setClientCollection(attachedClientCollection);
            Collection<Message> attachedMessageCollection = new ArrayList<Message>();
            for (Message messageCollectionMessageToAttach : user.getMessageCollection()) {
                messageCollectionMessageToAttach = em.getReference(messageCollectionMessageToAttach.getClass(), messageCollectionMessageToAttach.getIDMessage());
                attachedMessageCollection.add(messageCollectionMessageToAttach);
            }
            user.setMessageCollection(attachedMessageCollection);
            em.persist(user);
            for (UserRole userRoleCollectionUserRole : user.getUserRoleCollection()) {
                User oldUserOfUserRoleCollectionUserRole = userRoleCollectionUserRole.getUser();
                userRoleCollectionUserRole.setUser(user);
                userRoleCollectionUserRole = em.merge(userRoleCollectionUserRole);
                if (oldUserOfUserRoleCollectionUserRole != null) {
                    oldUserOfUserRoleCollectionUserRole.getUserRoleCollection().remove(userRoleCollectionUserRole);
                    oldUserOfUserRoleCollectionUserRole = em.merge(oldUserOfUserRoleCollectionUserRole);
                }
            }
            for (Client clientCollectionClient : user.getClientCollection()) {
                User oldIDUserOfClientCollectionClient = clientCollectionClient.getIDUser();
                clientCollectionClient.setIDUser(user);
                clientCollectionClient = em.merge(clientCollectionClient);
                if (oldIDUserOfClientCollectionClient != null) {
                    oldIDUserOfClientCollectionClient.getClientCollection().remove(clientCollectionClient);
                    oldIDUserOfClientCollectionClient = em.merge(oldIDUserOfClientCollectionClient);
                }
            }
            for (Message messageCollectionMessage : user.getMessageCollection()) {
                User oldIDUserOfMessageCollectionMessage = messageCollectionMessage.getIDUser();
                messageCollectionMessage.setIDUser(user);
                messageCollectionMessage = em.merge(messageCollectionMessage);
                if (oldIDUserOfMessageCollectionMessage != null) {
                    oldIDUserOfMessageCollectionMessage.getMessageCollection().remove(messageCollectionMessage);
                    oldIDUserOfMessageCollectionMessage = em.merge(oldIDUserOfMessageCollectionMessage);
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

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User persistentUser = em.find(User.class, user.getIDUser());
            Collection<UserRole> userRoleCollectionOld = persistentUser.getUserRoleCollection();
            Collection<UserRole> userRoleCollectionNew = user.getUserRoleCollection();
            Collection<Client> clientCollectionOld = persistentUser.getClientCollection();
            Collection<Client> clientCollectionNew = user.getClientCollection();
            Collection<Message> messageCollectionOld = persistentUser.getMessageCollection();
            Collection<Message> messageCollectionNew = user.getMessageCollection();
            List<String> illegalOrphanMessages = null;
            for (UserRole userRoleCollectionOldUserRole : userRoleCollectionOld) {
                if (!userRoleCollectionNew.contains(userRoleCollectionOldUserRole)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UserRole " + userRoleCollectionOldUserRole + " since its user field is not nullable.");
                }
            }
            for (Client clientCollectionOldClient : clientCollectionOld) {
                if (!clientCollectionNew.contains(clientCollectionOldClient)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Client " + clientCollectionOldClient + " since its IDUser field is not nullable.");
                }
            }
            for (Message messageCollectionOldMessage : messageCollectionOld) {
                if (!messageCollectionNew.contains(messageCollectionOldMessage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Message " + messageCollectionOldMessage + " since its IDUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<UserRole> attachedUserRoleCollectionNew = new ArrayList<UserRole>();
            for (UserRole userRoleCollectionNewUserRoleToAttach : userRoleCollectionNew) {
                userRoleCollectionNewUserRoleToAttach = em.getReference(userRoleCollectionNewUserRoleToAttach.getClass(), userRoleCollectionNewUserRoleToAttach.getUserRolePK());
                attachedUserRoleCollectionNew.add(userRoleCollectionNewUserRoleToAttach);
            }
            userRoleCollectionNew = attachedUserRoleCollectionNew;
            user.setUserRoleCollection(userRoleCollectionNew);
            Collection<Client> attachedClientCollectionNew = new ArrayList<Client>();
            for (Client clientCollectionNewClientToAttach : clientCollectionNew) {
                clientCollectionNewClientToAttach = em.getReference(clientCollectionNewClientToAttach.getClass(), clientCollectionNewClientToAttach.getIDClient());
                attachedClientCollectionNew.add(clientCollectionNewClientToAttach);
            }
            clientCollectionNew = attachedClientCollectionNew;
            user.setClientCollection(clientCollectionNew);
            Collection<Message> attachedMessageCollectionNew = new ArrayList<Message>();
            for (Message messageCollectionNewMessageToAttach : messageCollectionNew) {
                messageCollectionNewMessageToAttach = em.getReference(messageCollectionNewMessageToAttach.getClass(), messageCollectionNewMessageToAttach.getIDMessage());
                attachedMessageCollectionNew.add(messageCollectionNewMessageToAttach);
            }
            messageCollectionNew = attachedMessageCollectionNew;
            user.setMessageCollection(messageCollectionNew);
            user = em.merge(user);
            for (UserRole userRoleCollectionNewUserRole : userRoleCollectionNew) {
                if (!userRoleCollectionOld.contains(userRoleCollectionNewUserRole)) {
                    User oldUserOfUserRoleCollectionNewUserRole = userRoleCollectionNewUserRole.getUser();
                    userRoleCollectionNewUserRole.setUser(user);
                    userRoleCollectionNewUserRole = em.merge(userRoleCollectionNewUserRole);
                    if (oldUserOfUserRoleCollectionNewUserRole != null && !oldUserOfUserRoleCollectionNewUserRole.equals(user)) {
                        oldUserOfUserRoleCollectionNewUserRole.getUserRoleCollection().remove(userRoleCollectionNewUserRole);
                        oldUserOfUserRoleCollectionNewUserRole = em.merge(oldUserOfUserRoleCollectionNewUserRole);
                    }
                }
            }
            for (Client clientCollectionNewClient : clientCollectionNew) {
                if (!clientCollectionOld.contains(clientCollectionNewClient)) {
                    User oldIDUserOfClientCollectionNewClient = clientCollectionNewClient.getIDUser();
                    clientCollectionNewClient.setIDUser(user);
                    clientCollectionNewClient = em.merge(clientCollectionNewClient);
                    if (oldIDUserOfClientCollectionNewClient != null && !oldIDUserOfClientCollectionNewClient.equals(user)) {
                        oldIDUserOfClientCollectionNewClient.getClientCollection().remove(clientCollectionNewClient);
                        oldIDUserOfClientCollectionNewClient = em.merge(oldIDUserOfClientCollectionNewClient);
                    }
                }
            }
            for (Message messageCollectionNewMessage : messageCollectionNew) {
                if (!messageCollectionOld.contains(messageCollectionNewMessage)) {
                    User oldIDUserOfMessageCollectionNewMessage = messageCollectionNewMessage.getIDUser();
                    messageCollectionNewMessage.setIDUser(user);
                    messageCollectionNewMessage = em.merge(messageCollectionNewMessage);
                    if (oldIDUserOfMessageCollectionNewMessage != null && !oldIDUserOfMessageCollectionNewMessage.equals(user)) {
                        oldIDUserOfMessageCollectionNewMessage.getMessageCollection().remove(messageCollectionNewMessage);
                        oldIDUserOfMessageCollectionNewMessage = em.merge(oldIDUserOfMessageCollectionNewMessage);
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
                Integer id = user.getIDUser();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getIDUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UserRole> userRoleCollectionOrphanCheck = user.getUserRoleCollection();
            for (UserRole userRoleCollectionOrphanCheckUserRole : userRoleCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the UserRole " + userRoleCollectionOrphanCheckUserRole + " in its userRoleCollection field has a non-nullable user field.");
            }
            Collection<Client> clientCollectionOrphanCheck = user.getClientCollection();
            for (Client clientCollectionOrphanCheckClient : clientCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Client " + clientCollectionOrphanCheckClient + " in its clientCollection field has a non-nullable IDUser field.");
            }
            Collection<Message> messageCollectionOrphanCheck = user.getMessageCollection();
            for (Message messageCollectionOrphanCheckMessage : messageCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Message " + messageCollectionOrphanCheckMessage + " in its messageCollection field has a non-nullable IDUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
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

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
