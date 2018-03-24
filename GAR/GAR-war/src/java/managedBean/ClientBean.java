/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.*;
import entitys.*;

/**
 *
 * @author Анастасия
 */
@Named(value = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {
 //пробуем написать контроллер
    Client c = new Client();
    @EJB
    private ClientFacadeLocal clientFacade;

    /**
     * Creates a new instance of ClientBean
     */
    public ClientBean() {
    }
   //вывести всех клиентов
   public List<Client> findAllClient(){
      return this.clientFacade.findAll();
   }
   //создать клиента 
   public String createClient(){
       this.clientFacade.create(this.c);
       //после добавления перебрасывает на index
     return "index";
   }
    //удалить
   public void deleteClient(Client c){
       this.clientFacade.remove(c);
   }
   
   //обновить клиента
   public String editClient(Client c){
       this.c = c;
       return "edit";
   }
   public String editClient(){
       this.clientFacade.edit(this.c);
       this.c = new Client();
       return "index";
   }
}
