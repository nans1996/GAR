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
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * @author Vasilisa
 */
@ManagedBean(name = "administratorBean")
@SessionScoped
public class AdministratorBean {

    private final Logger logger = Logger.getLogger(AdministratorBean.class.getName());
    private FileHandler fh = null;
    
    public AdministratorBean() throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("D:/GAR.log"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        //user = new User();
    }


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
    
//    Администрирование пользователей
    public List<User> getAllUser() {
        List<User> users = userFacade.findAll();
        logger.info("Выведен список пользователей");
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
    
    //создать  
    public String createUser() { //?
        this.userFacade.create(this.getUser());
        //после добавления перебрасывает на index
        return "index";
    }
    
    //удалить
    public void deleteUser(User user) { //?
        this.userFacade.remove(user);
    }

    //обновить 
    public String editUser(User user) { //?
        this.setUser(user);
        return "edit";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getSavedImage(){
        Image img = imageFacadeLocal.find(1);
        return new DefaultStreamedContent(new ByteArrayInputStream(img.getData()), img.getType());
    }

    public StreamedContent getImageCreate() throws IOException {
        if (file != null) {
            return new DefaultStreamedContent(file.getInputstream(), file.getContentType());
        }
        return null;
    }
        
    //читаем картиночку из потока в байтовый массив
    public byte[] InputStreamToArryByte(InputStream in) throws IOException {
        byte[] imageInByte;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage bImageFromConvert = ImageIO.read(in);
            ImageIO.write(bImageFromConvert, "jpg", baos);
            imageInByte = baos.toByteArray();
        }
        logger.info("Произведено чтение картинки за потока в байтовый формат");
        return imageInByte;
    }
    public void upload() throws IOException {
        if (file != null) {
            image.setName(file.getFileName());
            image.setType(file.getContentType());
            image.setPersonageImage(null);
            image.setData(InputStreamToArryByte(file.getInputstream()));
            imageFacadeLocal.create(image);
            personage = personageFacadeLocal.findPersonageByName(personage.getName());
            personageImage.setIDImage(image);
            personageImage.setIDPersonage(personage);
            personageImage.setLevel(1);
            personageImageFacadeLocal.create(personageImage);
            FacesMessage message = new FacesMessage("Добавлен файл:", file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);   
            logger.info("Довавнение картинки на уровень персонажу");
        } else {
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
}
