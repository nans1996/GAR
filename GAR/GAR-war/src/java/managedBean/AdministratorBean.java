/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.myfaces.trinidad.component.UIXTable;
import model.*;
import org.primefaces.event.FileUploadEvent;
/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "administratorBean")
@RequestScoped
public class AdministratorBean {
    public AdministratorBean() {
        //user = new User();
    }

    @EJB
    private UserFacadeLocal userFacade;
    private User user = new User();
    
    //private User user;
    protected UIXTable table;
    @EJB
    UserFacadeLocal userFacadeLocal;

    public void setTable(UIXTable table) {
        this.table = table;
    }

    public UIXTable getTable() {
        return table;
    }
    
//    Администрирование пользователей
    public List<User> getAll() {
        return  userFacadeLocal.findAll();
    }
        //вывести всех пользователей
    public List<User> findAll() {
        return this.userFacade.findAll();
    }
    //создать  

    public String createUser() {
        this.userFacade.create(this.getUser());
        //после добавления перебрасывает на index
        return "index";
    }
    
    //удалить
    public void deleteUser(User user) {
        this.userFacade.remove(user);
    }

    //обновить 
    public String editUser(User user) {
        this.setUser(user);
        return "edit";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
