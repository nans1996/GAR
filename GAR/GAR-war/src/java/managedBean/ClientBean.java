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
import java.awt.image.BufferedImage;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    static final Logger LOGGER = Logger.getLogger(ClientBean.class);
    
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
    @EJB
    ImageFacadeLocal imageFacadeLocal;
    Image image = new Image();
    private String search;
    private List<Goal> listGoals;
    @EJB
    private PersonageImageFacadeLocal personageImageFacadeLocal;
    
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

    public String editClient() {
        userFacadeLocal.edit(user);
        clientFacade.edit(client);
        // client = new Client();
        return "profile";
    }

    //забанить/разбанить пользователя
    public String banClient(User u, boolean flag) {
        //            user = userFacadeLocal.find(m.getIDUser());
        client = clientFacade.findIdUser(u.getIDUser());
        if (flag) {
            client.setBan(true);
        } else {
            client.setBan(false);
        }
        clientFacade.edit(this.client);

        return "comment";
    }

    //проверить бан пользователя
    public boolean isBanClient(User u) {
        //user = userFacadeLocal.find(m.getIDUser());
        client = clientFacade.findIdUser(u.getIDUser());
        return client.getBan();
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
        personage = personageFacadeLocal.find(personage.getIDPersonage());
        boolean paymentFlag = false;
        if (personage.getPrice() > 0){
            String purchaseValue = String.valueOf(personage.getPrice());
            try {
                if (!holder.isEmpty()&&!codeCard.isEmpty()&&!codeSecurity.isEmpty()&&!expirationDate.isEmpty()){//ну хоть что-то проверим
                    paymentFlag  = payment(holder, codeCard, codeSecurity, expirationDate, purchaseValue);
                    if (paymentFlag) {  
                        FacesMessage message = new FacesMessage("Прошла оплата персонажа.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }else {
                        FacesMessage message = new FacesMessage("Оплата не прошла. Проверьте корректность и аклуальность введенных данных.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                }else {
                    FacesMessage message = new FacesMessage("Данные оплаты не заполнены.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } catch (IOException ex) {
                LOGGER.error("Оплата персонажа не прошла. Ошибка подключения к сервису.",ex);
                FacesMessage message = new FacesMessage("Оплата персонажа не прошла. Ошибка с серсвисом.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            paymentFlag  = true;
        }
        try {
            if (paymentFlag) {
                user = userFacadeLocal.findLogin(userBean.getCurrentUser());
                client = clientFacade.findIdUser(user.getIDUser());
                goal.setDirectory(true);
                goal.setGoalUserCollection(null);
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
                FacesMessage message = new FacesMessage("Цель успешно добавлена.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (EJBException ex) {
            LOGGER.error("Персонаж не добавлен. Ошибка: ",ex);
            FacesMessage message = new FacesMessage("Персонаж не добавлен. Обратитесь к администратору.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        //после добавления перебрасывает на index изменить на страницу подтверждения
        return "goal";
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
        {
            setListGoals(goalFacadeLocal.findGoalDefolt());
        } else {
            setListGoals(goalFacadeLocal.findGoalSearch(search.trim()));
        }
    }

    public List<Goal> AllGoalDefolt() {
        if (listGoals == null) {
            setListGoals(goalFacadeLocal.findGoalDefolt());
        }
        return listGoals;
    }

    //создать новую тему на форуме
    public String createTopic() {
        this.topicFacade.create(this.topic);
        return "index";
    }

    public String addLevel() {
        Collection<Level> levels = goalUserFacadeLocal.find(goalUser.getIDGoaluser()).getLevelCollection();
        for (Level item : levels) {
            if (isDateEqual(item.getDate()) && !item.getLeveldate()) {
                item.setLeveldate(true);
                levelFacadeLocal.edit(item);
            }
        }
        return "/goal_user?faces-redirect=true";
    }

    //выполненость
    public int percentСomplianceGoalUser() {
        //goalUser = goalUserFacadeLocal.find(goalUser.getIDGoaluser());
        int caunt = 0;
        Collection<Level> levels = goalUserFacadeLocal.find(goalUser.getIDGoaluser()).getLevelCollection();
        for (Level item : levels) {
            if (item.getLeveldate()) {
                caunt++;
            }
        }

        int percent = (100 * caunt) / 21;
        return percent;
        //return 100;
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

   
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Goal> getListGoals() {
        return listGoals;
    }

    public void setListGoals(List<Goal> listGoals) {
        this.listGoals = listGoals;
    }

    private LineChartModel areaModel;

    public LineChartModel getAreaModel() {
        createAreaModel();
        return areaModel;
    }

    //график
    private void createAreaModel() {
        areaModel = new LineChartModel();

        LineChartSeries target = new LineChartSeries();
        target.setFill(true);
        goalUser = goalUserFacadeLocal.find(goalUser.getIDGoaluser());
        target.setLabel(goalUser.getIDGoal().getName());

        int namberLevel = 0;
        for (Level item : goalUser.getLevelCollection()) {
            if (item.getLeveldate()) {
                namberLevel++;
            }
            target.set(item.getDate().toString(), namberLevel);
            if (isDateEqual(item.getDate())) {
                break;
            }
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
        Collection<Level> levels = goalUserFacadeLocal.find(goalUser.getIDGoaluser()).getLevelCollection();
        if (!levels.isEmpty()) {
            for (Level level : levels) {
                if (isDateEqual(level.getDate()) && level.getLeveldate()) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public boolean isDateEqual(Date dateLevel) {
        LocalDate current = LocalDate.now();
        LocalDate levelLocalDate = Instant.ofEpochMilli(dateLevel.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return levelLocalDate.isEqual(current);
    }

    //рейтинг одного пользователя 
    public entity.Client calculateClientReiting(entity.Client client) {
        int rating = 0;
        if (client.getGoalUserCollection() != null) {
            for (GoalUser gu : client.getGoalUserCollection()) {
                if (gu.getLevelCollection().size() - 1 == 21) {
                    rating++;
                }
            }
        }
        client.setRating(rating);
        return client;
    }

    //метод который считает рейтинг для всех
    public List<entity.Client> calculateClientsRating() {
        List<entity.Client> clients = clientFacade.findAll();
        List<entity.Client> ratingList = new ArrayList<>();
        for (entity.Client c : clients) {
            ratingList.add(calculateClientReiting(c));
        }
        Collections.sort(ratingList, (c1, c2) -> compaeInts(c1.getRating(), c2.getRating()));
        return ratingList;
    }

    private int compaeInts(int a, int b) {
        if (a < b) {
            return -1;
        } else if (a == b) {
            return 0;
        } else {
            return 1;
        }

    }

    //не уверена что это правильно 
    private String expirationDate;
    private String holder;
    private String codeSecurity;
    private String codeCard;

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getCodeSecurity() {
        return codeSecurity;
    }

    public void setCodeSecurity(String codeSecurity) {
        this.codeSecurity = codeSecurity;
    }

    public String getCodeCard() {
        return codeCard;
    }

    public void setCodeCard(String codeCard) {
        this.codeCard = codeCard;
    }
    
    //оплата персонажа
    public boolean payment(String holder,String codeCard,String codeSecurity,String expirationDate,String purchaseValue) throws IOException {
        boolean result = false;
        HttpClient clientHttp = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:8081/account/purchase");
        List nameValuePairs = new ArrayList(1);
        nameValuePairs.add(new BasicNameValuePair("name", "value")); //you can as many name value pair as you want in the list.
        nameValuePairs.add(new BasicNameValuePair("expirationDate", expirationDate));//2018-05-31
        nameValuePairs.add(new BasicNameValuePair("holder", holder));//Lapygina Vasilisa
        nameValuePairs.add(new BasicNameValuePair("codeSecurity", codeSecurity));//321
        nameValuePairs.add(new BasicNameValuePair("codeCard", codeCard));//1232353424
        nameValuePairs.add(new BasicNameValuePair("purchaseValue",purchaseValue));//
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = clientHttp.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            result = line.equals("true");
        }
        return result;
    }
    //вывод картинки персонажу
//    public StreamedContent getImageGoal(int id) throws IOException {
//        Personage personage = personageFacadeLocal.find(id);
//        List<PersonageImage> personageImages = (List<PersonageImage>) personage.getPersonageImageCollection();
//        Image img = imageFacadeLocal.find(personageImages.get(1).getIDImage());//допустим будем выводить картинку на 1 уровне 
//        return new DefaultStreamedContent(new ByteArrayInputStream(img.getData()), img.getType());
//    }
    public StreamedContent getStreamedImageById() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            context = FacesContext.getCurrentInstance();
            Map<String, String> params = context.getExternalContext().getRequestParameterMap();
            int id = Integer.parseInt(params.get("id"));
            Personage pers = personageFacadeLocal.find(id);
            List<PersonageImage> personageImages = personageImageFacadeLocal.findAll();
            Image img = null;
            for (PersonageImage personageImage : personageImages) {
                if (personageImage.getIDPersonage().getIDPersonage() == pers.getIDPersonage()&& personageImage.getLevel() == 1) {
                    img = imageFacadeLocal.find(personageImage.getIDImage().getIDImage());
                    break;
                }
            }
            //Image img = imageFacadeLocal.find(personageImages.get(0).getIDImage().getIDImage());//допустим будем выводить картинку на 1 уровне 
            if (img != null) {
                return new DefaultStreamedContent(new ByteArrayInputStream(img.getData()), img.getType());
            }
            return null;
        }
    }

    public List<Personage> AllPersonages() {
        List<Personage> personages = personageFacadeLocal.findAll();
        return personages;
    }
    
    //добавление авотарки пользователю
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) throws IOException {
        this.file = file;
    }

    //читаем картиночку из потока в байтовый массив
    public byte[] InputStreamToArryByte(InputStream in) throws IOException {
        byte[] imageInByte;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", baos);
            imageInByte = baos.toByteArray();
        }
        return imageInByte;
    }
    
    public void uploadProfile() throws IOException {
        if (file != null) {
            image.setName(file.getFileName());
            image.setType(file.getContentType());
            image.setPersonageImage(null);
            image.setData(InputStreamToArryByte(file.getInputstream()));
            imageFacadeLocal.create(image);
            
            user = userFacadeLocal.findLogin(userBean.getCurrentUser());
            client = clientFacade.findIdUser(user.getIDUser());
            client.setiDImage(image);
            clientFacade.edit(client);

            FacesMessage message = new FacesMessage("Добавлен файл:", file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);   
        }
        
    }
    
    public StreamedContent getAvatar(){
        user = userFacadeLocal.findLogin(userBean.getCurrentUser());
        client = clientFacade.findIdUser(user.getIDUser());
        Image img = client.getiDImage();
        return new DefaultStreamedContent(new ByteArrayInputStream(img.getData()), img.getType());
    }
    
    public List<Personage> personagesFild() {
        return personageFacadeLocal.findAll();
    }
}
