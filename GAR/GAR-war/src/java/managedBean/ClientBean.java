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

import java.util.ArrayList;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import java.util.Calendar;
import java.util.Collections;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;



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
    entity.Client client = new entity.Client();
    @EJB
    private UserFacadeLocal userFacadeLocal;
    private User user = new User();
    private UserBean userBean = new UserBean();
    @EJB
    private PersonageFacadeLocal personageFacadeLocal;
    private Personage personage = new Personage();
    
    private String search;
    private List<Goal> listGoals;

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

    public entity.Client getClient() {
        return client;
    }

    public void setClient(entity.Client client) {
        this.client = client;
    }

    //вывести всех клиентов
    public List<entity.Client> findAllClient() {
        return this.clientFacade.findAll();
    }

    //создать клиента 
    public String createClient() {
        this.clientFacade.create(this.client);
        return "index";
    }

    //удалить
    public void deleteClient(entity.Client client) {
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
        //заполняем 21 день как не выполненые что-бы было подряд
        Date date = new Date();
        for (int i = 0; i < 21; i++) {
            level.setIDGoaluser(goalUser);
            level.setDate(date);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date); //устанавливаем дату, с которой будет производить операции
            instance.add(Calendar.DAY_OF_MONTH, 1);// прибавляем день
            date = instance.getTime(); // получаем измененную дату
            level.setLeveldate(false);
            levelFacadeLocal.create(level);
        }

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
        //заполняем 21 день как не выполненые что-бы было подряд
        Date date = new Date();
        for (int i = 0; i < 21; i++) {
            level.setIDGoaluser(goalUser);
            level.setDate(date);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date); //устанавливаем дату, с которой будет производить операции
            instance.add(Calendar.DAY_OF_MONTH, 1);// прибавляем день
            date = instance.getTime(); // получаем измененную дату
            level.setLeveldate(false);
            levelFacadeLocal.create(level);
        }
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
    public void findAllGoalDefolt() {
        if (search.trim().length() == 0)//возможно просто длину достаточно проверить
        setListGoals(goalFacadeLocal.findGoalDefolt());
        else setListGoals(goalFacadeLocal.findGoalSearch(search.trim()));    
    }

    public List<Goal> AllGoalDefolt(){
        if (listGoals == null) setListGoals(goalFacadeLocal.findGoalDefolt());
        return listGoals;
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
    public entity.Client calculateClientReiting(entity.Client client){
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
    public List<entity.Client> calculateClientsRating() {
        List<entity.Client> clients = clientFacade.findAll();
        List<entity.Client> ratingList = new ArrayList<>();
        for(entity.Client c : clients) {
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
    
    //оплата персонажа
    public void payment() throws IOException {
        //получение jason вдруг пригодиться
//        HttpClient client = new DefaultHttpClient();
//        HttpGet request = new HttpGet("http://localhost:8081/account/all");
//        HttpResponse response = client.execute(request);
//        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            System.out.println(line);
//        }
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8081/account/purchase");
        List nameValuePairs = new ArrayList(1);
        nameValuePairs.add(new BasicNameValuePair("name", "value")); //you can as many name value pair as you want in the list.
        nameValuePairs.add(new BasicNameValuePair("expirationDate", "2018-05-31"));
        nameValuePairs.add(new BasicNameValuePair("holder", "Lapygina Vasilisa"));
        nameValuePairs.add(new BasicNameValuePair("codeSecurity", "321"));
        nameValuePairs.add(new BasicNameValuePair("codeCard", "1232353424"));
        nameValuePairs.add(new BasicNameValuePair("purchaseValue", "100"));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
          
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return the listGoals
     */
    public List<Goal> getListGoals() {
        return listGoals;
    }

    /**
     * @param listGoals the listGoals to set
     */
    public void setListGoals(List<Goal> listGoals) {
        this.listGoals = listGoals;
    }
}
