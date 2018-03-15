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
import entity.Message;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.MessageFacade;

/**
 *
 * @author Vasilisa
 */
public class MessageController {

    public MessageController() {
        pagingInfo = new PagingInfo();
        converter = new MessageConverter();
    }
    private Message message = null;
    private List<Message> messageItems = null;
    private MessageFacade jpaController = null;
    private MessageConverter converter = null;
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

    public MessageFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (MessageFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "messageJpa");
        }
        return jpaController;
    }

    public SelectItem[] getMessageItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getMessageItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Message getMessage() {
        if (message == null) {
            message = (Message) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMessage", converter, null);
        }
        if (message == null) {
            message = new Message();
        }
        return message;
    }

    public String listSetup() {
        reset(true);
        return "message_list";
    }

    public String createSetup() {
        reset(false);
        message = new Message();
        return "message_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(message);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Message was successfully created.");
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
        return scalarSetup("message_detail");
    }

    public String editSetup() {
        return scalarSetup("message_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        message = (Message) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentMessage", converter, null);
        if (message == null) {
            String requestMessageString = JsfUtil.getRequestParameter("jsfcrud.currentMessage");
            JsfUtil.addErrorMessage("The message with id " + requestMessageString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String messageString = converter.getAsString(FacesContext.getCurrentInstance(), null, message);
        String currentMessageString = JsfUtil.getRequestParameter("jsfcrud.currentMessage");
        if (messageString == null || messageString.length() == 0 || !messageString.equals(currentMessageString)) {
            String outcome = editSetup();
            if ("message_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit message. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(message);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Message was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentMessage");
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
                JsfUtil.addSuccessMessage("Message was successfully deleted.");
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
     //   if ((ERROR)) {
     //       return relatedControllerOutcome;
     //   }
        return listSetup();
    }

    public List<Message> getMessageItems() {
        if (messageItems == null) {
            getPagingInfo();
            messageItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return messageItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "message_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "message_list";
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
        message = null;
        messageItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Message newMessage = new Message();
        String newMessageString = converter.getAsString(FacesContext.getCurrentInstance(), null, newMessage);
        String messageString = converter.getAsString(FacesContext.getCurrentInstance(), null, message);
        if (!newMessageString.equals(messageString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
