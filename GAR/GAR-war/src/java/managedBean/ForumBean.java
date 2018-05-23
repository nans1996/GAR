/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import model.MessageFacadeLocal;
import model.TopicFacadeLocal;
import entity.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import static managedBean.AdministratorBean.LOGGER;
import model.ClientFacadeLocal;
import model.UserFacadeLocal;

/**
 *
 * @author Анастасия
 */
@Named(value = "forumBean")
@SessionScoped//может стоит на запрос?
public class ForumBean implements Serializable {

    @EJB
    private ClientFacadeLocal clientFacade;
    private Client client = new Client();
    private List<Message> messageTopic;
    @EJB
    private UserFacadeLocal userFacade;

    @EJB
    private MessageFacadeLocal messageFacade;
    private Message message = new Message();
    @EJB
    private TopicFacadeLocal topicFacade;
    private Topic topic = new Topic();
    private User user = new User();
    private UserBean userBean = new UserBean();
    private int id;
    @EJB
    private ClientFacadeLocal clientFacadeLocal;

    /**
     * Creates a new instance of ForumBean
     */
    public ForumBean() {
    }

    public List<Topic> getAllTopics() {
        List<Topic> listTopic = topicFacade.findAll();
        return listTopic;
    }

    public List<Message> getAllMessage() {
        return this.messageFacade.findAll();
    }

    // тут типа пытаюсь вывести сообщения по id темы
    public String messageIdTopic() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        id = Integer.parseInt(params.get("id"));
        messageTopic = messageFacade.findByIdTopic(getId());
        return "comment";

    }

    public String deleteMessage(Message message) {
        this.messageFacade.remove(message);
        return "comment";

    }

//    public int countMessage(int id) {
//        return topicFacade.find(id).getMessageCollection().size();
//    }
    public String createTopic() {
        topic.setDate(new Date());
        user = userFacade.findLogin(userBean.getCurrentUser());
        topic.setIDUser(user);
        this.topicFacade.create(this.getTopic());
        return "forum";
    }

    public String createMessage() {
        user = userFacade.findLogin(userBean.getCurrentUser());
        client = clientFacade.findIdUser(user.getIDUser());
        if (!client.getBan()) {
            message.setDate(new Date());
            //пока сделаем дефолд
            message.setIDTopic(topicFacade.find(getId()));
            message.setIDUser(user);
            this.messageFacade.create(this.message);
            message.setContent("");
        }
        return "comment";
    }

    /**
     * @return the topic
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * @return the message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * @return the messageTopic
     */
    public List<Message> getMessageTopic() {
        return messageTopic = messageFacade.findByIdTopic(getId());
    }

    /**
     * @param messageTopic the messageTopic to set
     */
    public void setMessageTopic(List<Message> messageTopic) {
        this.messageTopic = messageTopic;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    //Назначить бан/снять бан
    public String ban() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            int id = Integer.parseInt(params.get("id"));
            Client client = clientFacadeLocal.find(id);
            client.setBan(!client.getBan());
            clientFacadeLocal.edit(client);
        } catch (EJBException ex) {
            LOGGER.error("Ошибка при установки/снятии бана на пользователя.", ex);
            FacesMessage message = new FacesMessage("Ошибка при установки/снятии бана на пользователя.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "/comment?faces-redirect=true";
    }
}
