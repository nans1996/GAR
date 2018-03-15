/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classJSF;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import classJSF.util.JsfUtil;
import classJSF.util.PagingInfo;
import entity.Topic;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.TopicFacade;

/**
 *
 * @author Vasilisa
 */
public class TopicController {

    public TopicController() {
        pagingInfo = new PagingInfo();
        converter = new TopicConverter();
    }
    private Topic topic = null;
    private List<Topic> topicItems = null;
    private TopicFacade jpaController = null;
    private TopicConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "Delete_testPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public TopicFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (TopicFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "topicJpa");
        }
        return jpaController;
    }

    public SelectItem[] getTopicItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getTopicItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Topic getTopic() {
        if (topic == null) {
            topic = (Topic) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTopic", converter, null);
        }
        if (topic == null) {
            topic = new Topic();
        }
        return topic;
    }

    public String listSetup() {
        reset(true);
        return "topic_list";
    }

    public String createSetup() {
        reset(false);
        topic = new Topic();
        return "topic_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(topic);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Topic was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("topic_detail");
    }

    public String editSetup() {
        return scalarSetup("topic_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        topic = (Topic) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTopic", converter, null);
        if (topic == null) {
            String requestTopicString = JsfUtil.getRequestParameter("jsfcrud.currentTopic");
            JsfUtil.addErrorMessage("The topic with id " + requestTopicString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String topicString = converter.getAsString(FacesContext.getCurrentInstance(), null, topic);
        String currentTopicString = JsfUtil.getRequestParameter("jsfcrud.currentTopic");
        if (topicString == null || topicString.length() == 0 || !topicString.equals(currentTopicString)) {
            String outcome = editSetup();
            if ("topic_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit topic. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(topic);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Topic was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTopic");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Topic was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
       // if ((ERROR)) {
       //     return relatedControllerOutcome;
       // }
        return listSetup();
    }

    public List<Topic> getTopicItems() {
        if (topicItems == null) {
            getPagingInfo();
            topicItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return topicItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "topic_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "topic_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        topic = null;
        topicItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Topic newTopic = new Topic();
        String newTopicString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTopic);
        String topicString = converter.getAsString(FacesContext.getCurrentInstance(), null, topic);
        if (!newTopicString.equals(topicString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
