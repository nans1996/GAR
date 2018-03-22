/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entitys.User;
import dao.UserFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.myfaces.trinidad.component.UIXTable;

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
    
}
