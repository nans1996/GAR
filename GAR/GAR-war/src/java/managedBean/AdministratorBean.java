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
import java.io.ByteArrayInputStream;
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
    
    public AdministratorBean() throws IOException {
        
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
    private GoalFacadeLocal goalFacadeLocal;
    private Goal goal = new Goal();
    private PersonageImage personageImage = new PersonageImage();
    private String quantityLevel;
    @EJB
    private ClientFacadeLocal clientFacadeLocal;
    
    //Вывод списка пользователей для администратора
    public List<User> getAllUser() {
        List<User> users = userFacade.findAll();
        if (users == null) LOGGER.error("Пустой список пользователей.");
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
            return new DefaultStreamedContent(file.getInputstream(), file.getContentType());
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
    public void upload() throws IOException {
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
                FacesMessage message = new FacesMessage("Добавлен файл:", file.getFileName());
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (EJBException ex) {
                LOGGER.error("Ошибка при добавлении картинки.", ex);
                FacesMessage message = new FacesMessage("Не добавлен файл:", file.getFileName());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            LOGGER.error("Файл пуст и не может быть добавлен.");
            FacesMessage message = new FacesMessage("Файл пуст и не может быть добавлен.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
    }
    public List<Personage> personagesFild() {
        return personageFacadeLocal.findAll();
    }
    public List<String> levelFild() {
        List<String> results = new ArrayList<String>();
        for (int i = 1; i < 22; i++) {
            results.add(""+i);
        }
        return results;
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
        return "/user?faces-redirect=true";
    }
}
