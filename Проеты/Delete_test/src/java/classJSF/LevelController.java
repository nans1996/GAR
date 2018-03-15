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
import entity.Level;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import seans.LevelFacade;

/**
 *
 * @author Vasilisa
 */
public class LevelController {

    public LevelController() {
        pagingInfo = new PagingInfo();
        converter = new LevelConverter();
    }
    private Level level = null;
    private List<Level> levelItems = null;
    private LevelFacade jpaController = null;
    private LevelConverter converter = null;
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

    public LevelFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (LevelFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "levelJpa");
        }
        return jpaController;
    }

    public SelectItem[] getLevelItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getLevelItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Level getLevel() {
        if (level == null) {
            level = (Level) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLevel", converter, null);
        }
        if (level == null) {
            level = new Level();
        }
        return level;
    }

    public String listSetup() {
        reset(true);
        return "level_list";
    }

    public String createSetup() {
        reset(false);
        level = new Level();
        return "level_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(level);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Level was successfully created.");
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
        return scalarSetup("level_detail");
    }

    public String editSetup() {
        return scalarSetup("level_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        level = (Level) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentLevel", converter, null);
        if (level == null) {
            String requestLevelString = JsfUtil.getRequestParameter("jsfcrud.currentLevel");
            JsfUtil.addErrorMessage("The level with id " + requestLevelString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String levelString = converter.getAsString(FacesContext.getCurrentInstance(), null, level);
        String currentLevelString = JsfUtil.getRequestParameter("jsfcrud.currentLevel");
        if (levelString == null || levelString.length() == 0 || !levelString.equals(currentLevelString)) {
            String outcome = editSetup();
            if ("level_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit level. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(level);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Level was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentLevel");
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
                JsfUtil.addSuccessMessage("Level was successfully deleted.");
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
       // }
        return listSetup();
    }

    public List<Level> getLevelItems() {
        if (levelItems == null) {
            getPagingInfo();
            levelItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return levelItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "level_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "level_list";
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
        level = null;
        levelItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Level newLevel = new Level();
        String newLevelString = converter.getAsString(FacesContext.getCurrentInstance(), null, newLevel);
        String levelString = converter.getAsString(FacesContext.getCurrentInstance(), null, level);
        if (!newLevelString.equals(levelString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
