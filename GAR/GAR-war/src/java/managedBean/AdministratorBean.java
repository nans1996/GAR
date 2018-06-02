/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.User;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import model.*;
import entity.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.apache.log4j.Logger;

/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "administratorBean")
@SessionScoped
public class AdministratorBean {

    public AdministratorBean(){

    }
    static final Logger LOGGER = Logger.getLogger(AdministratorBean.class);
    private User user = new User();

    @EJB
    private UserFacadeLocal userFacade;

    @EJB
    ImageFacadeLocal imageFacadeLocal;
    Image image = new Image();
    @EJB
    PersonageImageFacadeLocal personageImageFacadeLocal;
    @EJB
    PersonageFacadeLocal personageFacadeLocal;
    Personage personage = new Personage();
    @EJB
    GoalFacadeLocal goalFacadeLocal;
    private Goal goal = new Goal();
    private PersonageImage personageImage = new PersonageImage();
    private String quantityLevel;
    @EJB
    private ClientFacadeLocal clientFacadeLocal;
    //для назначения админов 
    private UserRole userRole = new UserRole();
    private UserRolePK rolePK = new UserRolePK();
    private String USER_ROLE = "admin";
    @EJB
    private UserRoleFacadeLocal userRoleFacadeLocal;
    @EJB
    private ClientFacadeLocal clientFacade;

    //Вывод списка пользователей для администратора
    public List<User> getAllUser() {
        List<User> users = userFacade.findAll();
        if (users == null) {
            LOGGER.error("Пустой список пользователей.");
        }
        return users;
    }

    public String getQuantityLevel() {
        return quantityLevel;
    }

    public void setQuantityLevel(String quantityLevel) {
        this.quantityLevel = quantityLevel;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public PersonageImage getPersonageImage() {
        return personageImage;
    }

    public void setPersonageImage(PersonageImage personageImage) {
        this.personageImage = personageImage;
    }

    public Personage getPersonage() {
        return personage;
    }

    public void setPersonage(Personage personage) {
        this.personage = personage;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //Добавление и вывод картинок персонажей
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getImageCreate() throws IOException {
        if (file != null) {
            try {
                return new DefaultStreamedContent(file.getInputstream(), file.getContentType());
            } catch (IOException ioe) {
                LOGGER.error("Ошибка при добавлении картинок",ioe);
            }
        } else {
            LOGGER.error("Картинка при добавлении пустая и не может быть выведена.");
        }
        return null;
    }

    //читаем картиночку из потока в байтовый массив
    public byte[] InputStreamToArryByte(InputStream in) {
        byte[] imageInByte = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", baos);
            imageInByte = baos.toByteArray();
        } catch (IOException ex) {
            LOGGER.error("Ошибка при чтении картинки из потока в байтовый массив.", ex);
        }
        return imageInByte;
    }

    //добавление картинки персонажу на уровень
    public void uploadImagePersonage(){
        FacesMessage message;
        if (file != null) {
            try {
                image.setName(file.getFileName());
                image.setType(file.getContentType());
                image.setPersonageImage(null);
                image.setData(InputStreamToArryByte(file.getInputstream()));
                imageFacadeLocal.create(image);
                personage = personageFacadeLocal.findPersonageByName(personage.getName());
                personageImage.setIDImage(image);
                personageImage.setIDPersonage(personage);
                personageImage.setLevel(Integer.parseInt(quantityLevel));
                personageImageFacadeLocal.create(personageImage);
                message = new FacesMessage("Добавлен файл:", file.getFileName());
                
            } catch (EJBException | IOException ex) {
                LOGGER.error("Ошибка при добавлении картинки.", ex);
                message = new FacesMessage("Не добавлен файл:", file.getFileName());
            } 
        } else {
            LOGGER.error("Файл пуст и не может быть добавлен.");
            message = new FacesMessage("Файл пуст и не может быть добавлен.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Personage> personagesFild() {
        return personageFacadeLocal.findAll();
    }

    //список дефолтных персонажей
    public List<Goal> goalDef() {
        return goalFacadeLocal.findGoalDefolt();
    }

    public List<String> levelFild() {
        List<String> results = new ArrayList<String>();
        for (int i = 1; i < 22; i++) {
            results.add("" + i);
        }
        return results;
    }

    //Назначить бан/снять бан
    public String ban() {
        FacesMessage message;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            int id = Integer.parseInt(params.get("id"));
            Client client = clientFacadeLocal.find(id);
            client.setBan(!client.getBan());
            clientFacadeLocal.edit(client);
            message = new FacesMessage("Успешно", "Успешно услаовлен/снят бан");
        } catch (EJBException ex) {
            LOGGER.error("Ошибка при установки/снятии бана на пользователя.", ex);
            message = new FacesMessage("Ошибка при установки/снятии бана на пользователя.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "/user?faces-redirect=true";
    }

    public String addAdmin() {
        FacesMessage message;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            int id = Integer.parseInt(params.get("id"));
            Client client = clientFacadeLocal.find(id);
            User user = userFacade.find(client.getIDUser().getIDUser());

            rolePK.setLogin(user.getLogin());
            rolePK.setRole(USER_ROLE);
            userRole.setUserRolePK(rolePK);
            userRole.setUser(user);
            userRoleFacadeLocal.create(userRole);

            clientFacadeLocal.edit(client);
            message = new FacesMessage("Успешно", "Успешно добавлен администратор");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (EJBException ex) {
            LOGGER.error("Ошибка при добавлении администратора.", ex);
            message = new FacesMessage("Ошибка добавление администратора");  
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "/user?faces-redirect=true";
    }

    public List<Goal> getAllGoal() {
        return goalFacadeLocal.findAll();
    }

    //Установка статуса цели 
    public String directory() {
        FacesMessage message;
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            int id = Integer.parseInt(params.get("id"));
            Goal goal = goalFacadeLocal.find(id);
            goal.setDirectory(!goal.getDirectory());
            goalFacadeLocal.edit(goal);
            message = new FacesMessage("Успех ", "Статус цели изменен.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (EJBException ex) {
            LOGGER.error("Ошибка при изменении статуса цели.", ex);
            message = new FacesMessage("Ошибка ", "Статус цели не изменен.");  
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "goalAdministration";
    }

    //добавление картинки дефолтной цели
    public void uploadImageGoalDef() {
        FacesMessage message;
        if (file != null) {
            try {
                //добавляем картинку в БД
                image.setName(file.getFileName());
                image.setType(file.getContentType());
                image.setPersonageImage(null);
                image.setData(InputStreamToArryByte(file.getInputstream()));
                imageFacadeLocal.create(image);
                //изменяем цель
                goal = goalFacadeLocal.find(goal.getIDGoal());
                goal.setiDImage(image);
                goalFacadeLocal.edit(goal);
                message = new FacesMessage("Добавлен файл:", file.getFileName());
            } catch (EJBException | IOException ex) {
                LOGGER.error("Ошибка при добавлении картинки.", ex);
                message = new FacesMessage("Не добавлен файл:", file.getFileName());
                
            }    
        } else {
            LOGGER.error("Файл пуст и не может быть добавлен.");
            message = new FacesMessage("Файл пуст и не может быть добавлен.");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
