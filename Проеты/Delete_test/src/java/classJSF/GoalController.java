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
import entity.Goal;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.GoalFacade;

/**
 *
 * @author Vasilisa
 */
public class GoalController {

    public GoalController() {
        pagingInfo = new PagingInfo();
        converter = new GoalConverter();
    }
    private Goal goal = null;
    private List<Goal> goalItems = null;
    private GoalFacade jpaController = null;
    private GoalConverter converter = null;
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

    public GoalFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (GoalFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "goalJpa");
        }
        return jpaController;
    }

    public SelectItem[] getGoalItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getGoalItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Goal getGoal() {
        if (goal == null) {
            goal = (Goal) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentGoal", converter, null);
        }
        if (goal == null) {
            goal = new Goal();
        }
        return goal;
    }

    public String listSetup() {
        reset(true);
        return "goal_list";
    }

    public String createSetup() {
        reset(false);
        goal = new Goal();
        return "goal_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(goal);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Goal was successfully created.");
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
        return scalarSetup("goal_detail");
    }

    public String editSetup() {
        return scalarSetup("goal_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        goal = (Goal) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentGoal", converter, null);
        if (goal == null) {
            String requestGoalString = JsfUtil.getRequestParameter("jsfcrud.currentGoal");
            JsfUtil.addErrorMessage("The goal with id " + requestGoalString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String goalString = converter.getAsString(FacesContext.getCurrentInstance(), null, goal);
        String currentGoalString = JsfUtil.getRequestParameter("jsfcrud.currentGoal");
        if (goalString == null || goalString.length() == 0 || !goalString.equals(currentGoalString)) {
            String outcome = editSetup();
            if ("goal_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit goal. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(goal);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Goal was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentGoal");
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
                JsfUtil.addSuccessMessage("Goal was successfully deleted.");
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

    public List<Goal> getGoalItems() {
        if (goalItems == null) {
            getPagingInfo();
            goalItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return goalItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "goal_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "goal_list";
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
        goal = null;
        goalItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Goal newGoal = new Goal();
        String newGoalString = converter.getAsString(FacesContext.getCurrentInstance(), null, newGoal);
        String goalString = converter.getAsString(FacesContext.getCurrentInstance(), null, goal);
        if (!newGoalString.equals(goalString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
