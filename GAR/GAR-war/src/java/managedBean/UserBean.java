/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entitys.User;
import interfaceDao.UserInterface;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.myfaces.trinidad.component.UIXTable;

/**
 *
 * @author Vasilisa
 */

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
    
    public UserBean(){
        user = new User();
    }
    
    private User user;
    protected UIXTable table;
    
}
