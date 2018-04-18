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
import entity.*;
import java.util.Date;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author Анастасия
 */
@Named(value = "clientBean")
@SessionScoped
public class ClientBean implements Serializable {

    @EJB
    private TopicFacadeLocal topicFacade;
    Topic topic = new Topic();
    @EJB
    private MessageFacadeLocal messageFacade;
    Message message = new Message();
    //пробуем написать контроллер
    @EJB
    private GoalUserFacadeLocal goalUserFacade;
    GoalUser goalUser = new GoalUser();
    @EJB
    private GoalFacadeLocal goalFacadeLocal;
    private Goal goal = new Goal();
    Level level = new Level();
    @EJB
    private LevelFacadeLocal levelFacadeLocal;
    Client client = new Client();
    @EJB
    private ClientFacadeLocal clientFacade;
    private User user = new User();
    private UserBean userBean = new UserBean();
    @EJB
    private UserFacadeLocal userFacadeLocal;

    /**
     * Creates a new instance of ClientBean
     */
    public ClientBean() {
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public Goal getGoal() {
        return goal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public GoalUser getGoalUser() {
        return goalUser;
    }

    public void setGoalUser(GoalUser goalUser) {
        this.goalUser = goalUser;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    //вывести всех клиентов
    public List<Client> findAllClient() {
        return this.clientFacade.findAll();
    }
   //создать клиента 
   public String createClient(){
       this.clientFacade.create(this.client);
       //после добавления перебрасывает на index
     return "index";
   }
    //удалить
   public void deleteClient(Client client){
       this.clientFacade.remove(client);
   }
   
   //обновить клиента
   public String editClient(Client client){
       this.client = client;
       return "edit";
   }
   public String editClient(){
       this.clientFacade.edit(this.client);
       this.client = new Client();
       return "index";
   }
   
    //вывести цели клиентов
   public List<GoalUser> findAllGoalClient(){
      return this.goalUserFacade.findAll();
   }
   //создать 
   public String createGoalUser() {
       FacesContext fc = FacesContext.getCurrentInstance();
       Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
       int id = Integer.parseInt(params.get("id"));
       goal = goalFacadeLocal.find(id);
       goalUser.setIDGoal(goal);
       user = userFacadeLocal.findLogin(userBean.getCurrentUser());
       client = clientFacade.findIdUser(user.getIDUser());
       goalUser.setIDClient(client);
       goalUser.setLevelCollection(null);   
        this.goalUserFacade.create(this.goalUser);
        //после добавления перебрасывает на index
        return "index";
    }
    //удалить
   public void deleteGoalUser(GoalUser goalUser){
       this.goalUserFacade.remove(goalUser);
   }
   
   //обновить 
   public String editGoalUser(GoalUser goalUser){
       this.goalUser = goalUser;
       return "edit";
   }
   public String editGoalUser(){
       this.goalUserFacade.edit(this.goalUser);
       this.goalUser = new GoalUser();
       return "index";
   }
   //создать сообщение пока так же
    public String createMessage(){
       this.messageFacade.create(this.message);
       //после добавления перебрасывает на index
     return "index";
   }
    
   //вывод дефолтных целей 
    public List<Goal> findAllGoalDefolt(){
    return goalFacadeLocal.findGoalDefolt();
    }
    
    //создать новую тему на форуме
    public String createTopic(){
       this.topicFacade.create(this.topic);
       //после добавления перебрасывает на index
        return "index";
   }
    
}
