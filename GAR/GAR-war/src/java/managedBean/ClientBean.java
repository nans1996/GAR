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

    @EJB
    private TopicFacadeLocal topicFacade;
Topic t = new Topic();
    @EJB
    private MessageFacadeLocal messageFacade;
    Message m = new Message();
 //пробуем написать контроллер
    @EJB
    private GoalUserFacadeLocal goalUserFacade;
GoalUser gu = new GoalUser();

    
    
    
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
   
    //вывести цели клиентов
   public List<GoalUser> findAllGoalClient(){
      return this.goalUserFacade.findAll();
   }
   //создать 
   public String createGoalUser(){
       this.goalUserFacade.create(this.gu);
       //после добавления перебрасывает на index
     return "index";
   }
    //удалить
   public void deleteGoalUser(GoalUser gu){
       this.goalUserFacade.remove(gu);
   }
   
   //обновить 
   public String editGoalUser(GoalUser gu){
       this.gu = gu;
       return "edit";
   }
   public String editGoalUser(){
       this.goalUserFacade.edit(this.gu);
       this.gu = new GoalUser();
       return "index";
   }
   //создать сообщение пока так же
    public String createMessage(){
       this.messageFacade.create(this.m);
       //после добавления перебрасывает на index
     return "index";
   }
    
    //создать новую тему на форуме
    public String createTopic(){
       this.topicFacade.create(this.t);
       //после добавления перебрасывает на index
     return "index";
   }
}
