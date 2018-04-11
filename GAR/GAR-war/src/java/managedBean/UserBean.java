/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

//import entitys.User;
import entity.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.UserFacadeLocal;
import org.apache.myfaces.trinidad.component.UIXTable;

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

    public UserBean() {
    }

    //вывести всех пользователей
    public List<User> findAll() {
        return this.userFacade.findAll();
    }
    //создать  

    public String createUser() {
        this.userFacade.create(this.getU());
        //после добавления перебрасывает на index
        return "index";
    }

    //удалить
    public void deleteUser(User user) {
        this.userFacade.remove(user);
    }

    //обновить 
    public String editUser(User user) {
        this.setU(user);
        return "edit";
    }

    public String editUser() {
        this.userFacade.edit(this.getU());
        this.setU(new User());
        return "index";
    }

    public User getU() {
        return user;
    }

    public void setU(User user) {
        this.user = user;
    }
    
    public User getCurrentUses(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, Object> params = fc.getExternalContext().getSessionMap();
        String login = (String) params.get(UserBean.USER_KEY);
        return userFacade.findLogin(login);
    }

}
