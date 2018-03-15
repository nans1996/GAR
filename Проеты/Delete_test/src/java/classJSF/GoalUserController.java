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
import entity.GoalUser;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.GoalUserFacade;

/**
 *
 * @author Vasilisa
 */
public class GoalUserController {

    public GoalUserController() {
        pagingInfo = new PagingInfo();
        converter = new GoalUserConverter();
    }
    private GoalUser goalUser = null;
    private List<GoalUser> goalUserItems = null;
    private GoalUserFacade jpaController = null;
    private GoalUserConverter converter = null;
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

    public GoalUserFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (GoalUserFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "goalUserJpa");
        }
        return jpaController;
    }

    public SelectItem[] getGoalUserItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getGoalUserItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public GoalUser getGoalUser() {
        if (goalUser == null) {
            goalUser = (GoalUser) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentGoalUser", converter, null);
        }
        if (goalUser == null) {
            goalUser = new GoalUser();
        }
        return goalUser;
    }

    public String listSetup() {
        reset(true);
        return "goalUser_list";
    }

    public String createSetup() {
        reset(false);
        goalUser = new GoalUser();
        return "goalUser_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(goalUser);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("GoalUser was successfully created.");
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
        return scalarSetup("goalUser_detail");
    }

    public String editSetup() {
        return scalarSetup("goalUser_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        goalUser = (GoalUser) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentGoalUser", converter, null);
        if (goalUser == null) {
            String requestGoalUserString = JsfUtil.getRequestParameter("jsfcrud.currentGoalUser");
            JsfUtil.addErrorMessage("The goalUser with id " + requestGoalUserString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String goalUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, goalUser);
        String currentGoalUserString = JsfUtil.getRequestParameter("jsfcrud.currentGoalUser");
        if (goalUserString == null || goalUserString.length() == 0 || !goalUserString.equals(currentGoalUserString)) {
            String outcome = editSetup();
            if ("goalUser_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit goalUser. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(goalUser);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("GoalUser was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentGoalUser");
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
                JsfUtil.addSuccessMessage("GoalUser was successfully deleted.");
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
     //       return relatedControllerOutcome;
     //   }
        return listSetup();
    }

    public List<GoalUser> getGoalUserItems() {
        if (goalUserItems == null) {
            getPagingInfo();
            goalUserItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return goalUserItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "goalUser_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "goalUser_list";
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
        goalUser = null;
        goalUserItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        GoalUser newGoalUser = new GoalUser();
        String newGoalUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, newGoalUser);
        String goalUserString = converter.getAsString(FacesContext.getCurrentInstance(), null, goalUser);
        if (!newGoalUserString.equals(goalUserString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
