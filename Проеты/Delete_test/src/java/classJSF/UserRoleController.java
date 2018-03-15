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
import entity.UserRole;
import entity.UserRolePK;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.UserRoleFacade;

/**
 *
 * @author Vasilisa
 */
public class UserRoleController {

    public UserRoleController() {
        pagingInfo = new PagingInfo();
        converter = new UserRoleConverter();
    }
    private UserRole userRole = null;
    private List<UserRole> userRoleItems = null;
    private UserRoleFacade jpaController = null;
    private UserRoleConverter converter = null;
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

    public UserRoleFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (UserRoleFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "userRoleJpa");
        }
        return jpaController;
    }

    public SelectItem[] getUserRoleItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getUserRoleItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public UserRole getUserRole() {
        if (userRole == null) {
            userRole = (UserRole) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUserRole", converter, null);
        }
        if (userRole == null) {
            userRole = new UserRole();
        }
        return userRole;
    }

    public String listSetup() {
        reset(true);
        return "userRole_list";
    }

    public String createSetup() {
        reset(false);
        userRole = new UserRole();
        userRole.setUserRolePK(new UserRolePK());
        return "userRole_create";
    }

    public String create() {
        userRole.getUserRolePK().setLogin(userRole.getUser().getLogin());
        // TODO: no setter methods were found in your primary key class
        //    entity.UserRolePK
        // and therefore initialization code need manual adjustments.
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(userRole);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("UserRole was successfully created.");
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
        return scalarSetup("userRole_detail");
    }

    public String editSetup() {
        return scalarSetup("userRole_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        userRole = (UserRole) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUserRole", converter, null);
        if (userRole == null) {
            String requestUserRoleString = JsfUtil.getRequestParameter("jsfcrud.currentUserRole");
            JsfUtil.addErrorMessage("The userRole with id " + requestUserRoleString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        userRole.getUserRolePK().setLogin(userRole.getUser().getLogin());
        // TODO: no setter methods were found in your primary key class
        //    entity.UserRolePK
        // and therefore initialization code need manual adjustments.
        String userRoleString = converter.getAsString(FacesContext.getCurrentInstance(), null, userRole);
        String currentUserRoleString = JsfUtil.getRequestParameter("jsfcrud.currentUserRole");
        if (userRoleString == null || userRoleString.length() == 0 || !userRoleString.equals(currentUserRoleString)) {
            String outcome = editSetup();
            if ("userRole_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit userRole. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(userRole);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("UserRole was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentUserRole");
        UserRolePK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("UserRole was successfully deleted.");
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

    public List<UserRole> getUserRoleItems() {
        if (userRoleItems == null) {
            getPagingInfo();
            userRoleItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return userRoleItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "userRole_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "userRole_list";
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
        userRole = null;
        userRoleItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        UserRole newUserRole = new UserRole();
        newUserRole.setUserRolePK(new UserRolePK());
        String newUserRoleString = converter.getAsString(FacesContext.getCurrentInstance(), null, newUserRole);
        String userRoleString = converter.getAsString(FacesContext.getCurrentInstance(), null, userRole);
        if (!newUserRoleString.equals(userRoleString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
