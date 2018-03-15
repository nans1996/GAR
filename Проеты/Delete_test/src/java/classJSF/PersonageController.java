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
import entity.Personage;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.PersonageFacade;

/**
 *
 * @author Vasilisa
 */
public class PersonageController {

    public PersonageController() {
        pagingInfo = new PagingInfo();
        converter = new PersonageConverter();
    }
    private Personage personage = null;
    private List<Personage> personageItems = null;
    private PersonageFacade jpaController = null;
    private PersonageConverter converter = null;
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

    public PersonageFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (PersonageFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "personageJpa");
        }
        return jpaController;
    }

    public SelectItem[] getPersonageItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getPersonageItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Personage getPersonage() {
        if (personage == null) {
            personage = (Personage) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPersonage", converter, null);
        }
        if (personage == null) {
            personage = new Personage();
        }
        return personage;
    }

    public String listSetup() {
        reset(true);
        return "personage_list";
    }

    public String createSetup() {
        reset(false);
        personage = new Personage();
        return "personage_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(personage);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Personage was successfully created.");
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
        return scalarSetup("personage_detail");
    }

    public String editSetup() {
        return scalarSetup("personage_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        personage = (Personage) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPersonage", converter, null);
        if (personage == null) {
            String requestPersonageString = JsfUtil.getRequestParameter("jsfcrud.currentPersonage");
            JsfUtil.addErrorMessage("The personage with id " + requestPersonageString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String personageString = converter.getAsString(FacesContext.getCurrentInstance(), null, personage);
        String currentPersonageString = JsfUtil.getRequestParameter("jsfcrud.currentPersonage");
        if (personageString == null || personageString.length() == 0 || !personageString.equals(currentPersonageString)) {
            String outcome = editSetup();
            if ("personage_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit personage. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(personage);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Personage was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPersonage");
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
                JsfUtil.addSuccessMessage("Personage was successfully deleted.");
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
      //  if ((ERROR)) {
      //      return relatedControllerOutcome;
      //  }
        return listSetup();
    }

    public List<Personage> getPersonageItems() {
        if (personageItems == null) {
            getPagingInfo();
            personageItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return personageItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "personage_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "personage_list";
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
        personage = null;
        personageItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Personage newPersonage = new Personage();
        String newPersonageString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPersonage);
        String personageString = converter.getAsString(FacesContext.getCurrentInstance(), null, personage);
        if (!newPersonageString.equals(personageString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
