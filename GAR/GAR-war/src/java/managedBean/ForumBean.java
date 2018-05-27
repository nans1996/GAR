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
import javax.faces.context.FacesContext;
import model.ClientFacadeLocal;
import model.UserFacadeLocal;
import org.apache.log4j.Logger;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Анастасия
 */
@Named(value = "forumBean")
@SessionScoped//может стоит на запрос?
public class ForumBean implements Serializable {

    static final Logger LOGGER = Logger.getLogger(ForumBean.class);
    
    public ForumBean() {
    }
    
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
    
    public void setMessageTopic(List<Message> messageTopic) {
        this.messageTopic = messageTopic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Message> getMessageTopic() {
        List<Message> messages = null;
        try {
             messages = messageTopic = messageFacade.findByIdTopic(getId());
        } catch (EJBException ejbe) {
            LOGGER.error("Ошибка при получении списка сообщений по теме:", ejbe);
        }
        return messages;
    }

    public List<Topic> getAllTopics() {
        List<Topic> topics = null;
        try {
            topics = topicFacade.findAll();
        } catch (EJBException ejbe) {
            LOGGER.error("Ошибка при получении списка тем:", ejbe);
        }
        return topics;
    }

    public List<Message> getAllMessage() {
        List<Message> messages = null;
        try {
            messages = messageFacade.findAll();
        } catch (EJBException ejbe) {
            LOGGER.error("Ошибка при получении списка сообщений:", ejbe);
        }
        return messages;
    }

    // Вывести сообщения по id темы
    public String messageIdTopic() {
        FacesMessage message = null;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            id = Integer.parseInt(params.get("id"));
            messageTopic = messageFacade.findByIdTopic(getId());
        } catch (EJBException ejbe) {
            LOGGER.error("Ошибка при получении списка сообщений:", ejbe);
            message = new FacesMessage("Ошибка", "Ошибка при выводе сообщений.");
        } catch (NumberFormatException nfe){
            LOGGER.error("Ошибка при выводе сообщений. Пришли не верные параметры:", nfe);
            message = new FacesMessage("Ошибка", "Ошибка при выводе сообщений.");
        }
        if (message != null) FacesContext.getCurrentInstance().addMessage(null, message);
        return "comment";

    }

    public String deleteMessage(Message message) {
        FacesMessage facesMessage;
        try {
            messageFacade.remove(message);
            facesMessage = new FacesMessage("Успех", "Сообщение успешно удалено.");
        } catch (EJBException ejbe) {
            LOGGER.error("Ошибка при удалении сообщения:", ejbe);
            facesMessage = new FacesMessage("Ошибка", "Ошибка при удалении сообщения.");
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        return "comment";
    }

    public String createTopic() {
        FacesMessage facesMessage;
        try {
            topic.setDate(new Date());
            user = userFacade.findLogin(userBean.getCurrentUser());
            topic.setIDUser(user);
            this.topicFacade.create(this.getTopic());
            facesMessage = new FacesMessage("Ошибка", "Тема успешно создана.");
        }catch (EJBException ejbe){
            LOGGER.error("Ошибка создании темы:", ejbe);
            facesMessage = new FacesMessage("Ошибка", "Ошибка создании темы.");
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        return "forum";     
    }

    public String createMessage() {
        try {
            user = userFacade.findLogin(userBean.getCurrentUser());
            client = clientFacade.findIdUser(user.getIDUser());
            if (!client.getBan()) {
                message.setDate(new Date());
                message.setIDTopic(topicFacade.find(getId()));
                message.setIDUser(user);
                this.messageFacade.create(this.message);
                message.setContent("");
            }
        } catch (EJBException ejbe) {
            LOGGER.error("Ошибка при отправке сообщения:", ejbe);
            FacesMessage facesMessage = new FacesMessage("Ошибка", "Ошибка при отправке сообщения.");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return "comment";
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
