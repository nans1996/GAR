/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

//import entitys.User;
import entitys.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.UserFacadeLocal;
import org.apache.myfaces.trinidad.component.UIXTable;

/**
 *
 * @author Vasilisa
 */

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {

    @EJB
    private UserFacadeLocal userFacade;
    User u = new User();
    
    public UserBean(){
        
    }
    
     //вывести всех пользователей
   public List<User> findAllUser(){
      return this.userFacade.findAll();
   }
   //создать  
   public String createUser(){
       this.userFacade.create(this.u);
       //после добавления перебрасывает на index
     return "index";
   }
    //удалить
   public void deleteUser(User u){
       this.userFacade.remove(u);
   }
   
   //обновить 
   public String editUser(User u){
       this.u = u;
       return "edit";
   }
   public String editUser(){
       this.userFacade.edit(this.u);
       this.u = new User();
       return "index";
   }

}
