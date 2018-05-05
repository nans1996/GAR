/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

//import entitys.User;
import entity.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.UserFacadeLocal;
import model.UserRoleFacadeLocal;

/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {



    public static final String USER_KEY = "CurrentUser";
        
    @EJB
    private UserFacadeLocal userFacade;
    private User user = new User();
    private UserRole userRole = new  UserRole();
    private UserRolePK rolePK = new UserRolePK();
    private String USER_ROLE = "gambler";
    @EJB
    private UserRoleFacadeLocal userRoleFacadeLocal;
    public UserBean() {
    }

//    public String editUser() {
//        this.userFacade.edit(this.getUser());
//        this.setUser(new User());
//        return "index";
//    }
//
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String createUser(){
        user.setMessageCollection(null);
        user.setTopicCollection(null);
        user.setUserRoleCollection(null);
        user.setClientCollection(null);
        userFacade.create(user);
        
        rolePK.setLogin(user.getLogin());
        rolePK.setRole(USER_ROLE);
        userRole.setUserRolePK(rolePK);
        userRole.setUser(user);
        userRoleFacadeLocal.create(userRole);
        return "authorization";
    }
    
    public User getCurrentUses(){
        return userFacade.findLogin(getCurrentUser());
    }
    
    public String getCurrentUser(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, Object> params = fc.getExternalContext().getSessionMap();
        String login = (String) params.get(UserBean.USER_KEY);
        return login;
    }
    
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index?faces-redirect=true";
    }
    public boolean isUserLoggedIn() {
        String login = getCurrentUser();
        boolean result = !((login == null) || login.isEmpty());
        return result;
    }
}
