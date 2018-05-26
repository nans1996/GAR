/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

//import entitys.User;
import static com.sun.faces.facelets.util.Path.context;
import entity.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.ClientFacadeLocal;
import model.ImageFacadeLocal;
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
    private String USER_ROLE = "client";
    @EJB
    private UserRoleFacadeLocal userRoleFacadeLocal;
    @EJB
    private ClientFacadeLocal clientFacadeLocal;
    private Client client = new Client();
    @EJB
    private ImageFacadeLocal facadeLocal;
    public UserBean() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String createUser(){
        try{
        user.setMessageCollection(null);
        user.setTopicCollection(null);
        //клиента по id в стидиюы
        user.setClient(null);
        userFacade.create(user);
        
        rolePK.setLogin(user.getLogin());
        rolePK.setRole(USER_ROLE);
        userRole.setUserRolePK(rolePK);
        userRole.setUser(user);
        userRoleFacadeLocal.create(userRole);

        client.setBan(false);
        client.setIDUser(user);
        client.setGoalUserCollection(null);
        client.setiDImage(facadeLocal.find(1));
        clientFacadeLocal.create(client);
        
        }
        catch (javax.ejb.EJBException e){
           // String message="You must login to continue";
      // String message = e.getMessage();
            FacesContext.getCurrentInstance().addMessage("regForm:login", new FacesMessage("Пользователь с таким логином уже существует!!!"));
          //  e.printStackTrace();
          return "registration";
        }
        
            return "authorization";
        
    }
    
    //вырнуть текущего
    public User getCurrentUserObject(){
        return userFacade.findLogin(getCurrentUser());
    }
    
    //наименование такное-себе но менять не помню где
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
