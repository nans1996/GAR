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
import entity.Client;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.ClientFacade;

/**
 *
 * @author Vasilisa
 */
public class ClientController {

    public ClientController() {
        pagingInfo = new PagingInfo();
        converter = new ClientConverter();
    }
    private Client client = null;
    private List<Client> clientItems = null;
    private ClientFacade jpaController = null;
    private ClientConverter converter = null;
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

    public ClientFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (ClientFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "clientJpa");
        }
        return jpaController;
    }

    public SelectItem[] getClientItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getClientItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Client getClient() {
        if (client == null) {
            client = (Client) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentClient", converter, null);
        }
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public String listSetup() {
        reset(true);
        return "client_list";
    }

    public String createSetup() {
        reset(false);
        client = new Client();
        return "client_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(client);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Client was successfully created.");
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
        return scalarSetup("client_detail");
    }

    public String editSetup() {
        return scalarSetup("client_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        client = (Client) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentClient", converter, null);
        if (client == null) {
            String requestClientString = JsfUtil.getRequestParameter("jsfcrud.currentClient");
            JsfUtil.addErrorMessage("The client with id " + requestClientString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String clientString = converter.getAsString(FacesContext.getCurrentInstance(), null, client);
        String currentClientString = JsfUtil.getRequestParameter("jsfcrud.currentClient");
        if (clientString == null || clientString.length() == 0 || !clientString.equals(currentClientString)) {
            String outcome = editSetup();
            if ("client_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit client. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(client);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Client was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentClient");
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
                JsfUtil.addSuccessMessage("Client was successfully deleted.");
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
    //    if ((ERROR)) {
     //       return relatedControllerOutcome;
      //  }
        return listSetup();
    }

    public List<Client> getClientItems() {
        if (clientItems == null) {
            getPagingInfo();
            clientItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return clientItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "client_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "client_list";
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
        client = null;
        clientItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Client newClient = new Client();
        String newClientString = converter.getAsString(FacesContext.getCurrentInstance(), null, newClient);
        String clientString = converter.getAsString(FacesContext.getCurrentInstance(), null, client);
        if (!newClientString.equals(clientString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
