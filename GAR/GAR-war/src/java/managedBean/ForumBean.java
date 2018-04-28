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
import model.UserFacadeLocal;

/**
 *
 * @author Анастасия
 */
@Named(value = "forumBean")
@SessionScoped//может стоит на запрос?
public class ForumBean implements Serializable {

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

    public int countMessage(int id) {
        return topicFacade.find(id).getMessageCollection().size();
    }

    public String createTopic() {
        this.topicFacade.create(this.getTopic());
        return "forum";
    }

    public String createMessage() {
        message.setDate(new Date());
        //пока сделаем дефолд
        message.setSubject("subject");
        message.setIDTopic(topicFacade.find(1));
        user = userFacade.findLogin(userBean.getCurrentUser());
        message.setIDUser(user);
        this.messageFacade.create(this.message);
        message.setContent("");
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

}
