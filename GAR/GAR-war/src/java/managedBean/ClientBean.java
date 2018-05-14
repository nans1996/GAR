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
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import javax.faces.application.FacesMessage;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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
    @EJB
    private GoalUserFacadeLocal goalUserFacadeLocal;
    GoalUser goalUser = new GoalUser();
    @EJB
    private GoalFacadeLocal goalFacadeLocal;
    private Goal goal = new Goal();
    @EJB
    private LevelFacadeLocal levelFacadeLocal;
    Level level = new Level();
    @EJB
    private ClientFacadeLocal clientFacade;
    Client client = new Client();
    @EJB
    private UserFacadeLocal userFacadeLocal;
    private User user = new User();
    private UserBean userBean = new UserBean();
    @EJB
    private PersonageFacadeLocal personageFacadeLocal;
    private Personage personage = new Personage();

    /**
     * Creates a new instance of ClientBean
     */
    public ClientBean() {
    }

    public Personage getPersonage() {
        return personage;
    }

    public void setPersonage(Personage personage) {
        this.personage = personage;
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
    public String createClient() {
        this.clientFacade.create(this.client);
        return "index";
    }

    //удалить
    public void deleteClient(Client client) {
        this.clientFacade.remove(client);
    }

    //обновить клиента
//   public String editClient(User user){
//       this.user = user;// Насть что это?*
//       return "profile";
//   }
    public String editClient() {
        userFacadeLocal.edit(user);
        clientFacade.edit(client);
        // client = new Client();
        return "profile";
    }

    //вывести цели клиента
    public List<GoalUser> findAllGoalCurrentClient() {
        user = userFacadeLocal.findLogin(userBean.getCurrentUser());
        client = clientFacade.findIdUser(user.getIDUser());
        return goalUserFacadeLocal.findGoalCurrentClient(client.getIDClient());
    }

    //Добавление пользователю дефолтной цели 
    public String createGoalDefoltUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("id"));
        goal = goalFacadeLocal.find(id);
        goalUser.setIDGoal(goal);
        user = userFacadeLocal.findLogin(userBean.getCurrentUser());
        client = clientFacade.findIdUser(user.getIDUser());
        goalUser.setIDClient(client);
        goalUser.setLevelCollection(null);
        this.goalUserFacadeLocal.create(this.goalUser);
        //после добавления перебрасывает на index
        return "index";
    }

    public String editGoalUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(params.get("id"));
        goalUser = goalUserFacadeLocal.find(id);
        return "/goal_user?faces-redirect=true";
    }

    public String createGoalUser() {
        user = userFacadeLocal.findLogin(userBean.getCurrentUser());
        client = clientFacade.findIdUser(user.getIDUser());
        goal.setDirectory(true);
        goal.setGoalUserCollection(null);
        //пока с дефолтным персонажем
        personage = personageFacadeLocal.find(1);
        goal.setIDPersonage(personage);
        goalFacadeLocal.create(goal);
        goalUser.setIDGoal(goal);
        goalUser.setIDClient(client);
        goalUser.setLevelCollection(null);
        this.goalUserFacadeLocal.create(this.goalUser);
        //после добавления перебрасывает на index изменить на страницу подтверждения
        return "index";
    }

    //удалить
    public void deleteGoalUser(GoalUser goalUser) {
        this.goalUserFacadeLocal.remove(goalUser);
    }

    //обновить 
    public String editGoalUser(GoalUser goalUser) {
        this.goalUser = goalUser;
        return "edit";
    }

//   public String editGoalUser(){
//       this.goalUserFacadeLocal.edit(this.goalUser);
//       this.goalUser = new GoalUser();
//       return "index";
//   }
    //создать сообщение пока так же
    public String createMessage() {
        this.messageFacade.create(this.message);
        return "index";
    }

    //вывод дефолтных целей 
    public List<Goal> findAllGoalDefolt() {
        return goalFacadeLocal.findGoalDefolt();
    }

    //создать новую тему на форуме
    public String createTopic() {
        this.topicFacade.create(this.topic);
        return "index";
    }

    public String addLevel() {
        Date date = new Date();
        level.setDate(date);
        level.setLeveldate(true);
        level.setIDGoaluser(goalUser);
        levelFacadeLocal.create(level);
        return "index";
    }

    public int percentСomplianceGoalUser() {
        //goalUser = goalUserFacadeLocal.find(goalUser.getIDGoaluser());
        int percent = (100 * goalUser.getLevelCollection().size()) / 21;
        return percent;
    }
    //тут часть кода отвечвющая за календарик
    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        //установка события
        eventModel.addEvent(new DefaultScheduleEvent("Выполнено", previousDay8Pm(), previousDay8Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Выполнено", previousDay7Pm(), previousDay8Pm()));
        lazyEventModel = new LazyScheduleModel() {
        };
    }

    //пока дефолтные методы забора дат!
    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);
        return t.getTime();
    }

    private Date previousDay7Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 24);
        return t.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

//    public List<Client> rating() {
//        List<Client> list = clientFacade.findAll();
//        list.sort(new Comparator<Client>() {
//            @Override
//            public int compare(Client o1, Client o2) {
//                return o1.toString().compareTo(o2.toString());//какая-то логика*
//            }
//        });
//        return list;
//    }

    private LineChartModel areaModel;

    public LineChartModel getAreaModel() {
        createAreaModel();
        return areaModel;
    }

    private void createAreaModel() {
        areaModel = new LineChartModel();

        LineChartSeries target = new LineChartSeries();
        target.setFill(true);

        target.setLabel(goalUser.getIDGoal().getName());

        int namberLevel = 0;
        for (Level item : goalUser.getLevelCollection()) {
            if (item.getLeveldate()) {
                namberLevel++;
            }
            target.set(item.getDate().toString(), namberLevel);
        }

        areaModel.addSeries(target);

        areaModel.setTitle("Достижение цели");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Дни");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Шкала выполнения");
        yAxis.setMin(1);
        yAxis.setMax(goalUser.getLevelCollection().size());
    }

    public boolean checkDate() {
        boolean result;
        List<Level> levels = (List<Level>) goalUser.getLevelCollection();
        if (!levels.isEmpty()) {
            result = isDateEqual(levels.get(levels.size() - 1).getDate());
        } else {
            result = false;
        }
        return result;
    }

    public boolean isDateEqual(Date dateLevel) {
        boolean result = false;
        Date currentDate = new Date();
        if (dateLevel.getYear() == currentDate.getYear() && dateLevel.getMonth() == currentDate.getMonth() && dateLevel.getDay() == currentDate.getDay()) {
            result = true;
        }
        return result;
    }
    
    //рейтинг одного пользователя 
    public Client calculateClientReiting(Client client){
        int rating = 0;
        if (client.getGoalUserCollection() != null )
            for (GoalUser gu : client.getGoalUserCollection()) {
                if (gu.getLevelCollection().size()-1 == 21)
                    rating++;
            } 
        client.setRating(rating);
        return client;
    }
    //метод который считает рейтинг для всех
    public List<Client> calculateClientsRating() {
        List<Client> clients = clientFacade.findAll();
        List<Client> ratingList = new ArrayList<>();
        for(Client c : clients) {
            ratingList.add(calculateClientReiting(c));
        }
        Collections.sort(ratingList, (c1, c2) -> compaeInts(c1.getRating(), c2.getRating()));
        return ratingList;
    }
    
    private int compaeInts(int a, int b) {
        if(a < b)
            return -1;
        else if (a == b)
            return 0;
        else return 1;
            
    }
}
