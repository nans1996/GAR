/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import dao.UserDAORemote;
import entities.User;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Vasilisa
 */

//@Named("userBean")
//@ConversationScoped
@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
    
    //public UserBean(){
    //    user = new User();
    //}
    
   // private User user;
    
    //@EJB
    //UserDAORemote remoteInterfacesUser;
    
   // public void setUser(User user) {
   //     this.user = user;
   // }

   // public User getUser() {
   //     return user;
   // }
    
    //public String create() {
        //remoteInterfacesUser.create(user); 
    //    return "index";
    //}
  //  public String action(){
   //     FacesContext fc = FacesContext.getCurrentInstance();
   //     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
   //     int id = Integer.parseInt(params.get("ID_user")); 
   //    // User item = remoteInterfacesUser.getId(id);
   //     //user = User.copy(item);
   //     return "edit";
   // }
    
    //public String save() {
      //  //remoteInterfacesUser.update(user);
      //  return "index";
        
    //}
}
